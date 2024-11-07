package com.ggg.studyApp.dto

import java.time.LocalDateTime

data class ClassMemberDto(
    var idx:Int? = null,
    var memberIdx:Int? = null,
    var classIdx:Int? = null,
    var createAt:LocalDateTime? = LocalDateTime.now(),
    var role:String? = null,
)