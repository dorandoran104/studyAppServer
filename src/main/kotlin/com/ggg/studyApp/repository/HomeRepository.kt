package com.ggg.studyApp.repository

import com.ggg.studyApp.entity.Member
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface HomeRepository:JpaRepository<Member,Int> {

    fun countByNickname(nickname: String):Int

    fun countById(id:String):Int

    fun countByCode(code:String):Int

}