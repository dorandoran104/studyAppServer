package com.ggg.studyApp.service

import com.ggg.studyApp.dto.MemberDto
import com.ggg.studyApp.entity.Member
import com.ggg.studyApp.repository.HomeRepository
import com.ggg.studyApp.util.RandomUtil
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class HomeService {

    private val logger = LoggerFactory.getLogger(HomeService::class.java)

    @Autowired
    private lateinit var randomUtil:RandomUtil

    @Autowired
    private lateinit var homeRepository: HomeRepository

    /**
     * 회원가입 > 인증문자 발송하기
     * @param mobileNumber
     */
    fun sendCertification(memberDto: MemberDto):HashMap<String,Any>{
        val resultObj = HashMap<String,Any>()

        val certificationNumber = randomUtil.createRandomNumber(6)
        resultObj["certificationNumber"] = certificationNumber


        /**
         * 나중에 문자메세지 발송 개발 예정
         */

        resultObj["result"] = true
        return resultObj
    }

    /**
     * 닉네임 중복조회
     * @param memberDto nickname
     * @return count
     */
    fun existsNickname(memberDto: MemberDto): Int {
        return homeRepository.countByNickname(memberDto.nickname!!)
    }

    /**
     * 아이디 중복 조회
     * @param memberDto
     * @return count
     */
    fun existsId(memberDto:MemberDto):Int {
        return homeRepository.countById(memberDto.id!!)
    }

    fun signUp(memberDto: MemberDto):HashMap<String,Any>{
        val resultObj = HashMap<String,Any>()

        //한번더 아이디 중복조회
        if(homeRepository.countById(memberDto.id!!) > 0){
            resultObj["result"] = false
            resultObj["errMessage"] = "이미 사용중인 아이디 입니다."
            return resultObj
        }

        //한번더 닉네임 중복조회
        if(homeRepository.countByNickname(memberDto.nickname!!) > 0){
            resultObj["result"] = false
            resultObj["errMessage"] = "이미 사용중인 닉네임 입니다."
            return resultObj
        }

        //랜덤코드 생성
        var existsFlag = false
        while(!existsFlag){
            val randomCode = randomUtil.createRandomString(13)
            if(homeRepository.countByCode(randomCode) == 0){
                existsFlag = true
                memberDto.code = randomCode
            }
        }
        logger.info(memberDto.code)
        val bcrypt = BCryptPasswordEncoder()
        val encodePassword = bcrypt.encode(memberDto.password)
        memberDto.password = encodePassword
        val member = Member.fromDto(memberDto)
        val saveMember = homeRepository.save(member)
        resultObj["result"] = saveMember.idx != null
        return resultObj
    }
}