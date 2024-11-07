package com.ggg.studyApp.service

import com.ggg.studyApp.dto.ClassDto
import com.ggg.studyApp.dto.ClassMemberDto
import com.ggg.studyApp.entity.ClassEntity
import com.ggg.studyApp.entity.ClassMemberEntity
import com.ggg.studyApp.repository.ClassMemberRepository
import com.ggg.studyApp.repository.ClassRepository
import com.ggg.studyApp.repository.MemberRepository
import com.ggg.studyApp.util.JWTUtil
import com.ggg.studyApp.util.RandomUtil
import jakarta.servlet.http.HttpServletRequest
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate
import java.time.LocalDateTime

@Service
class ClassService {

    @Autowired
    private lateinit var jwtUtil: JWTUtil

    @Autowired
    private lateinit var randomUtil : RandomUtil

    @Autowired
    private lateinit var memberRepository: MemberRepository

    @Autowired
    private lateinit var classRepository: ClassRepository

    @Autowired
    private lateinit var classMemberRepository: ClassMemberRepository

    private val logger = LoggerFactory.getLogger(ClassService::class.java)

    /**
     * 모임 리스트 출력
     */
    fun list(request: HttpServletRequest): HashMap<String, Any> {
        val resultObj = HashMap<String,Any>()
        val token = request.getHeader("Authorization").replace("Bearer", "").trim()
        val claims = jwtUtil.validateToken(token)

        val member = memberRepository.findByCode(claims.subject)

        val classMemberList = classMemberRepository.findByMemberIdx(member.idx!!)
        val classIdxList = ArrayList<Int>()
        for(classMember:ClassMemberDto in classMemberList){
            classIdxList.add(classMember.classIdx!!)
        }

        val classList = classRepository.findAllByIdxIn(classIdxList)

        resultObj["data"] = classList
        resultObj["result"] = true

        return resultObj
    }

    @Transactional
    fun save(classDto: ClassDto, request: HttpServletRequest):HashMap<String,Any>{
        val resultObj = HashMap<String,Any>()

        val token = request.getHeader("Authorization").replace("Bearer", "").trim()
        val claims = jwtUtil.validateToken(token)

        val member = memberRepository.findByCode(claims.subject)

        if(classDto.name == null || classDto.description == null || classDto.maxMembers == null){
            resultObj["result"] = false
            resultObj["errMessage"] = "빈값이 존재합니다."
            return resultObj
        }

        var existsFlag= false
        while(!existsFlag){
            val randomCode = randomUtil.createRandomString(13)
            if(classRepository.countByCode(randomCode) == 0){
                classDto.code = randomCode
                existsFlag = true
            }
        }
        classDto.leaderMemberIdx = member.idx


        val saveClass = classRepository.save(ClassEntity.fromDto(classDto))

        val classMemberDto = ClassMemberDto()
        classMemberDto.classIdx = saveClass.idx
        classMemberDto.memberIdx = member.idx

        classMemberRepository.save(ClassMemberEntity.fromDto(classMemberDto))
        resultObj["result"] = true
        return resultObj

    }


}