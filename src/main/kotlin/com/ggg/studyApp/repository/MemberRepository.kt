package com.ggg.studyApp.repository

import com.ggg.studyApp.dto.MemberDto
import com.ggg.studyApp.entity.Member
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface MemberRepository: JpaRepository<Member, Int> {

    fun findByCode(code:String): MemberDto
}