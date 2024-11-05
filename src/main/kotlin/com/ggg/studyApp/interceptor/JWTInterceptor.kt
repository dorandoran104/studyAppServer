package com.ggg.studyApp.interceptor

import com.ggg.studyApp.dto.MemberDto
import com.ggg.studyApp.entity.Member
import com.ggg.studyApp.repository.HomeRepository
import com.ggg.studyApp.service.HomeService
import com.ggg.studyApp.service.MemberService
import com.ggg.studyApp.util.JWTUtil
import io.jsonwebtoken.ExpiredJwtException
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.servlet.HandlerInterceptor

@Component
class JWTInterceptor : HandlerInterceptor {

    @Autowired
    private lateinit var jwtUtil: JWTUtil

    @Autowired
    private lateinit var memberService: MemberService

    @Autowired
    private lateinit var homeRepository: HomeRepository

    private val logger = LoggerFactory.getLogger(JWTInterceptor::class.java)

    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        val token = request.getHeader("Authorization").replace("Bearer","").trim()
        // 토큰이 없으면 로그인 화면으로 가야함
        if(token == "") {
            response.status = HttpStatus.UNAUTHORIZED.value()
            return false
        }
        //디코딩
        try {
            jwtUtil.validateToken(token)
            return  true
        }catch(ex:ExpiredJwtException){
            logger.run {
                info("@@@@@@@@@@@@@@@@@")
                info(" 토큰 만료")
                info("@@@@@@@@@@@@@@@@@")
            }
            val claims = ex.claims
            val member = memberService.findByCode(claims.subject)
            try {
                jwtUtil.validateToken(member.refreshToken!!)

                //새로운 토큰 발급
                val newToken = jwtUtil.createToken(member,1)
                response.setHeader("access_token",newToken)

                //db 변경
                member.accessToken = newToken
                homeRepository.save(Member.fromDto(member))
                logger.run {
                    info("@@@@@@@@@@@@@@@@@")
                    info(" 토큰 재발행")
                    info("@@@@@@@@@@@@@@@@@")
                }
                return  true
            }catch (ex:Exception){
                logger.error(ex.message)
                logger.run {
                    info("@@@@@@@@@@@@@@@@@")
                    info(" 리프레쉬 토큰 ")
                    info("@@@@@@@@@@@@@@@@@")
                }
                response.status = HttpStatus.UNAUTHORIZED.value()
                return false
            }
        }catch (ex:Exception){
            logger.run {
                info("@@@@@@@@@@@@@@@@@")
                info(" 토큰 만료")
                info("@@@@@@@@@@@@@@@@@")
            }
            return false
        }
    }
}