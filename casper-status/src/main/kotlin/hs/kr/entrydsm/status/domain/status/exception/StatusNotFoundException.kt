package hs.kr.entrydsm.status.domain.status.exception

import hs.kr.entrydsm.status.global.error.exception.CasperException
import hs.kr.entrydsm.status.global.error.exception.ErrorCode

/**
 * 상태를 찾을 수 없을 때 발생하는 예외입니다.
 * 요청한 접수번호에 해당하는 상태 정보가 존재하지 않는 경우 사용됩니다.
 */
object StatusNotFoundException : CasperException(ErrorCode.STATUS_NOT_FOUND) {
    val EXCEPTION = StatusNotFoundException
}
