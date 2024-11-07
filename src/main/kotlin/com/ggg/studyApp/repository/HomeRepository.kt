package com.ggg.studyApp.repository

import com.ggg.studyApp.dto.MemberDto
import com.ggg.studyApp.entity.MemberEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface HomeRepository:JpaRepository<MemberEntity,Int> {

    fun countByNickname(nickname: String):Int

    fun countById(id:String):Int

    fun countByCode(code:String):Int

    fun findById(id:String):MemberDto
}