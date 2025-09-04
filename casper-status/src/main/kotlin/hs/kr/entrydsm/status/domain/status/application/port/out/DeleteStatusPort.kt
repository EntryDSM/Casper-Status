package hs.kr.entrydsm.status.domain.status.application.port.out

/**
 * 상태 삭제 작업을 위한 포트 인터페이스입니다.
 * 헥사고날 아키텍처에서 도메인 계층이 인프라스트럭처 계층과 통신하기 위한 인터페이스입니다.
 */
interface DeleteStatusPort {
    /**
     * 접수번호로 상태 정보를 삭제합니다.
     *
     * @param receiptCode 삭제할 접수번호
     */
    fun deleteByReceiptCode(receiptCode: Long)
}
