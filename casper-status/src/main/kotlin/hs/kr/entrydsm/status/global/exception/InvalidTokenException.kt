package hs.kr.entrydsm.status.global.exception

import hs.kr.entrydsm.status.global.error.exception.CasperException
import hs.kr.entrydsm.status.global.error.exception.ErrorCode

/**
 * 토큰이 유효하지 않을 때 발생하는 예외입니다.
 * JWT 토큰의 형식이 잘못되었거나 서명이 일치하지 않는 경우 사용됩니다.
 */
object InvalidTokenException : CasperException(
    ErrorCode.INVALID_TOKEN
)
