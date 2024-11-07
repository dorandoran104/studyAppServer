package com.ggg.studyApp.dto

import java.time.LocalDate
import java.time.LocalDateTime

data class ClassDto(
    var idx:Int? = null,
    var code:String? = null,
    var name:String? = null,
    var description:String? = null,
    var leaderMemberIdx:Int? = null,
    var currentMembers:Int? = 1,
    var maxMembers:Int? = null,
    var category:String? = null,
    var startDate:LocalDate? = null,
    var endDate:LocalDate? = null,
    var status:String? = "recruiting",
    var createAt:LocalDateTime? = LocalDateTime.now(),
    var modifyAt:LocalDateTime? = null
)