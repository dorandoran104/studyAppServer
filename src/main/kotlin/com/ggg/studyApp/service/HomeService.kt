package com.ggg.studyApp.service

import com.ggg.studyApp.dto.MemberDto
import com.ggg.studyApp.util.RandomUtil
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class HomeService {

    @Autowired
    private lateinit var randomUtil:RandomUtil

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
}