package com.ggg.studyApp.util

import com.ggg.studyApp.dto.MemberDto
import com.ggg.studyApp.service.MemberService
import io.jsonwebtoken.Claims
import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.time.LocalDateTime
import java.util.*
import javax.crypto.SecretKey

@Component
class JWTUtil(@Value("\${jwt.secret.key}") secretKey: String){

    private val logger = LoggerFactory.getLogger(JWTUtil::class.java)

    private val key: SecretKey = Keys.hmacShaKeyFor(Base64.getDecoder().decode(secretKey))

    @Autowired
    private lateinit var memberService: MemberService

    /**
     * 토큰 생성
     */
    fun createToken(memberDto: MemberDto,hours : Long):String{

        val now = LocalDateTime.now()
        return Jwts.builder()
            .setSubject(memberDto.code!!)
            .setIssuedAt(java.sql.Timestamp.valueOf(now))
            .setExpiration(java.sql.Timestamp.valueOf(now.plusHours(hours)))
//            .setExpiration(java.sql.Timestamp.valueOf(now.plusSeconds(hours)))
            .signWith(key) // 서명 알고리즘 및 키
            .compact()
    }

    /**
     * 토큰 검증
     */
    fun validateToken(token:String): Claims {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .body
//        return try {
//            logger.info("@@@@@@@@@@@@@@@@@")
//            logger.info("토큰 검증")
//            logger.info("@@@@@@@@@@@@@@@@@")
//            Jwts.parserBuilder()
//                .setSigningKey(key)
//                .build()
//                .parseClaimsJws(token)
//                .body
//
//            true
//        }catch(ex:ExpiredJwtException){
//            logger.info("@@@@@@@@@@@@@@@@@")
//            logger.info("토큰 만료")
//            logger.info("@@@@@@@@@@@@@@@@@")
//            val claims = ex.claims
//
//            val memberDto = MemberDto()
//            memberDto.code = claims.subject
//
//            val findMember = memberService.findByCode(claims.subject)
//
//
//
//            true
//        }catch (ex:Exception){
//            logger.error(ex.message)
//            false
//        }
    }
}