package com.ggg.studyApp.repository

import com.ggg.studyApp.dto.ClassMemberDto
import com.ggg.studyApp.entity.ClassEntity
import com.ggg.studyApp.entity.ClassMemberEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ClassMemberRepository: JpaRepository<ClassMemberEntity, Int> {

    fun findByMemberIdx(memberIdx:Int):ArrayList<ClassMemberDto>

}