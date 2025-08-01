package hs.kr.entrydsm.status.domain.status.application.port.`in`

/**
 * 상태 업데이트 유스케이스 인터페이스입니다.
 * 지원자의 상태 정보를 업데이트하는 기능을 정의합니다.
 */
interface UpdateStatusUseCase {

    /**
     * 상태를 업데이트합니다.
     *
     * @param receiptCode 접수번호
     */
    fun execute(receiptCode: Long)
}
