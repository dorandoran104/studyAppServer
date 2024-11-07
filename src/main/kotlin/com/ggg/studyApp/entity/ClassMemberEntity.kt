package com.ggg.studyApp.entity

import com.ggg.studyApp.dto.ClassDto
import com.ggg.studyApp.dto.ClassMemberDto
import com.ggg.studyApp.dto.MemberDto
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "class_member")
data class ClassMemberEntity (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var idx:Int? = null,
    var memberIdx:Int? = null,
    var classIdx:Int? = null,
    var createAt:LocalDateTime? = null,
    var role:String? = null,
){
    companion object{
        fun fromDto(classMemberDto: ClassMemberDto) = ClassMemberEntity(
            idx = classMemberDto.idx
            , memberIdx = classMemberDto.memberIdx
            , classIdx = classMemberDto.classIdx
            , createAt = classMemberDto.createAt
            , role = classMemberDto.role
        )
    }
}