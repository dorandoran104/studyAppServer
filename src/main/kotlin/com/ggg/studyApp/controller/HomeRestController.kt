package com.ggg.studyApp.controller

import com.ggg.studyApp.dto.MemberDto
import com.ggg.studyApp.service.HomeService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/home/api")
class HomeController {

    private val logger = LoggerFactory.getLogger(HomeController::class.java)

    @Autowired
    private lateinit var homeService: HomeService

    @PostMapping("/login")
    fun login(@RequestBody memberDto: MemberDto){
        println(memberDto.toString())
    }

    @PostMapping("/sendCertification")
    fun sendCertification(@RequestBody memberDto:MemberDto):HashMap<String,Any>{
        var resultObject = HashMap<String,Any>()
        try {
            resultObject = homeService.sendCertification(memberDto)
        }catch (ex:Exception){
            resultObject["result"] = false
            resultObject["errMessage"] = "문자전송에 실패하였습니다."
        }
        return resultObject
    }


    @PostMapping("/existsNickname")
    fun existsNickname(@RequestBody memberDto: MemberDto):HashMap<String,Any>{
        val resultObject = HashMap<String,Any>()
        try {
            val count = homeService.existsNickname(memberDto)
            resultObject["result"] = count == 0
            resultObject["errMessage"] = if (count == 0) "" else "이미 사용중인 닉네임입니다."
        }catch (ex:Exception){
            logger.error(ex.message)
            resultObject["result"] = false
            resultObject["errMessage"] = "오류가 발생했습니다."
        }
        return resultObject
    }

}