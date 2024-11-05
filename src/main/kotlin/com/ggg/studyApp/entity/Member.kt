package com.ggg.studyApp.entity

import com.ggg.studyApp.dto.MemberDto
import jakarta.persistence.*

@Entity
@Table(name = "member")
data class Member (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var idx:Int? = null,
    var accessToken:String ? = null,
    var refreshToken:String ? = null,
    var code:String? = null,
    var id:String? = null,
    var password:String? = null,
    var mobileNumber:String? = null,
    var nickname:String? = null,
    var fileIdx:Int? = null,
    var createAt:String? = null,
    var modifyAt:String? = null,
    var deleteAt:String? = null,
    var deleteYn:String? = null,
    var withdrawYn:String? = null,
    var withdrawAt:String? = null
){
    companion object{
        fun fromDto(memberDto: MemberDto) = Member(
            idx = memberDto.idx
            , accessToken = memberDto.accessToken
            , refreshToken = memberDto.refreshToken
            , code = memberDto.code
            , id = memberDto.id
            , password = memberDto.password
            , mobileNumber = memberDto.mobileNumber
            , nickname = memberDto.nickname
            , fileIdx = memberDto.fileIdx
            , createAt = memberDto.createAt
            , modifyAt = memberDto.modifyAt
            , deleteAt = memberDto.deleteAt
            , deleteYn = memberDto.deleteYn
            , withdrawYn = memberDto.withdrawYn
            , withdrawAt = memberDto.withdrawAt
        )
    }
}