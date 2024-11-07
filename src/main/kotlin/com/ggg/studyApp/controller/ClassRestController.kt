package com.ggg.studyApp.controller

import com.ggg.studyApp.dto.ClassDto
import com.ggg.studyApp.service.ClassService
import jakarta.servlet.http.HttpServletRequest
import org.apache.coyote.Response
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/class/api")
class ClassRestController {

    private val logger = LoggerFactory.getLogger(ClassRestController::class.java)

    @Autowired
    private lateinit var classService: ClassService

    /**
     * 모임 리스트
     */
    @GetMapping("/list")
    fun classList(request:HttpServletRequest):ResponseEntity<HashMap<String,Any>>{
        var resultObj = HashMap<String,Any>()
        try {
            resultObj = classService.list(request)
            return ResponseEntity.ok(resultObj)
        }catch(ex:Exception){
            logger.error(ex.message)
            resultObj["result"] = false
            return ResponseEntity.internalServerError().body(resultObj)
        }
    }

    /**
     * 모임 저장
     */
    @PostMapping("/save")
    fun classSave(@RequestBody classDto: ClassDto, request: HttpServletRequest):ResponseEntity<HashMap<String,Any>>{
        var resultObj = HashMap<String,Any>()
        try {
            resultObj = classService.save(classDto, request)
            return ResponseEntity.ok(resultObj)
        }catch (ex:Exception){
            logger.error(ex.message)
            resultObj["result"] = false
            return ResponseEntity.internalServerError().body(resultObj)
        }
    }
}