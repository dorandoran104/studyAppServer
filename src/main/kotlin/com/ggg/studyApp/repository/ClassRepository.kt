package com.ggg.studyApp.repository

import com.ggg.studyApp.dto.ClassDto
import com.ggg.studyApp.entity.ClassEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface ClassRepository: JpaRepository<ClassEntity, Int> {

    fun countByCode(code:String):Int

    fun findAllByIdxIn(classIdxList:List<Int>):List<ClassDto>

}