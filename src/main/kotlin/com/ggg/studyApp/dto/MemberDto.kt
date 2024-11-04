package com.ggg.studyApp.dto

data class MemberDto(
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
)