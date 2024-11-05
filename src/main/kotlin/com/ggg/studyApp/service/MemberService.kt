package com.ggg.studyApp.service

import com.ggg.studyApp.dto.MemberDto
import com.ggg.studyApp.entity.Member
import com.ggg.studyApp.repository.MemberRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class MemberService {

    @Autowired
    private lateinit var memberRepository: MemberRepository

    fun findByCode(code:String):MemberDto{
        return memberRepository.findByCode(code)

    }
}