package hs.kr.entrydsm.status.domain.status.application.port.`in`

/**
 * 상태 삭제 유스케이스 인터페이스입니다.
 * 특정 접수번호의 상태 정보를 삭제하는 기능을 정의합니다.
 */
interface DeleteStatusUseCase {
    /**
     * 접수번호로 상태 정보를 삭제합니다.
     *
     * @param receiptCode 삭제할 접수번호
     */
    fun execute(receiptCode: Long)
}
