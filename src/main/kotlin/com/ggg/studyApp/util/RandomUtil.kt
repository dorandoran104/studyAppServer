package com.ggg.studyApp.util

import org.springframework.stereotype.Component
import kotlin.math.pow

@Component
class RandomUtil {

    /**
     * @param length 길이
     * @return 길이의 따른 숫자 자릿수 랜덤 생성
     */
    fun createRandomNumber(length:Int):String{
        val maxNumber = (10.0.pow(length) -1).toInt()
        return (0..maxNumber).random().toString().padStart(length,'0')
    }

    fun createRandomString(length:Int):String{
        val characters = ('0'..'9') + ('A'..'Z') + ('a'..'z')

        return (1..length)
            .map { characters.random() }  // 랜덤한 문자 선택
            .joinToString("")  // 리스트를 문자열로 변환
    }
}