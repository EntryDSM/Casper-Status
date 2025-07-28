package hs.kr.entrydsm.status.domain.status.application.port.out

import hs.kr.entrydsm.status.domain.status.model.Status

/**
 * 상태 저장 작업을 위한 포트 인터페이스입니다.
 * 헥사고날 아키텍처에서 도메인 계층이 인프라스트럭처 계층과 통신하기 위한 인터페이스입니다.
 */
interface SaveStatusPort {
    /**
     * 상태 정보를 저장합니다.
     *
     * @param status 저장할 상태 도메인 모델
     */
    fun save(status: Status)
}
