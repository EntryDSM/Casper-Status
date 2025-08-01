package hs.kr.entrydsm.status.domain.status.application.port.`in`

/**
 * 새로운 상태를 생성하는 유스케이스 인터페이스입니다.
 * 지원자가 최초로 원서를 접수할 때 상태 정보를 생성합니다.
 */
interface CreateStatusUseCase {

    /**
     * 접수번호를 기반으로 새로운 상태를 생성합니다.
     *
     * @param receiptCode 생성할 상태의 접수번호
     */
    fun execute(receiptCode: Long)
}
