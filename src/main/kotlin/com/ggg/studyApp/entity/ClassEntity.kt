package com.ggg.studyApp.entity

import com.ggg.studyApp.dto.ClassDto
import com.ggg.studyApp.dto.MemberDto
import jakarta.persistence.*
import java.time.LocalDate
import java.time.LocalDateTime

@Entity
@Table(name = "class")
data class ClassEntity (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var idx:Int? = null,
    var code:String? = null,
    var name:String? = null,
    var description:String? = null,
    var leaderMemberIdx:Int? = null,
    var currentMembers:Int? = null,
    var maxMembers:Int? = null,
    var category:String? = null,
    var startDate: LocalDate? = null,
    var endDate:LocalDate? = null,
    var status:String? = null,
    var createAt:LocalDateTime? = null,
    var modifyAt:LocalDateTime? = null
){
    companion object{
        fun fromDto(classDto: ClassDto) = ClassEntity(
            idx = classDto.idx
            , code = classDto.code
            , name = classDto.name
            , description = classDto.description
            , leaderMemberIdx = classDto.leaderMemberIdx
            , currentMembers = classDto.currentMembers
            , maxMembers = classDto.maxMembers
            , category = classDto.category
            , startDate =  classDto.startDate
            , endDate =  classDto.endDate
            , status = classDto.status
            , createAt = classDto.createAt
            , modifyAt = classDto.modifyAt
        )
    }
}