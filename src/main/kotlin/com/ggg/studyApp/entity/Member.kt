package com.ggg.studyApp.entity

import com.ggg.studyApp.dto.MemberDto
import jakarta.persistence.*

@Entity
@Table(name = "member")
data class Member (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var idx:Int? = null
    ,var nickname:String? = null
    ,var id:String? = null
    ,var code:String? = null
    ,var password:String? = null
    ,var accessToken:String? = null
    ,var refreshToken:String? = null
){
    companion object{
        fun fromDto(memberDto: MemberDto) = Member(
            idx = memberDto.idx
            , code = memberDto.code
            , nickname = memberDto.nickname
            , id = memberDto.id
            , password = memberDto.password
            , accessToken = memberDto.accessToken
            , refreshToken = memberDto.refreshToken

        )
    }
}