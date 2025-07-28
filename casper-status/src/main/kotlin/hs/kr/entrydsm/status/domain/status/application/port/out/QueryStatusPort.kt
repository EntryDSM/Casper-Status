package hs.kr.entrydsm.status.domain.status.application.port.out

import hs.kr.entrydsm.status.domain.status.model.Status

/**
 * 상태 조회 작업을 위한 포트 인터페이스입니다.
 * 헥사고날 아키텍처에서 도메인 계층이 인프라스트럭처 계층과 통신하기 위한 인터페이스입니다.
 */
interface QueryStatusPort {

    /**
     * 접수번호로 상태 정보를 조회합니다.
     *
     * @param receiptCode 조회할 접수번호
     * @return 조회된 상태 도메인 모델 (없으면 null)
     */
    fun findByReceiptCode(receiptCode: Long): Status?
    
    /**
     * 모든 상태 정보를 조회합니다.
     *
     * @return 모든 상태 도메인 모델 리스트
     */
    fun findAll(): List<Status>

}
