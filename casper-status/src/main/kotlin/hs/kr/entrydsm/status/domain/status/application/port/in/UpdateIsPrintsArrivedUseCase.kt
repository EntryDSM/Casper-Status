package hs.kr.entrydsm.status.domain.status.application.port.`in`

/**
 * 서류 도착 확인 유스케이스 인터페이스입니다.
 * 등기우편으로 제출된 서류의 도착을 확인하고 상태를 변경하는 기능을 정의합니다.
 */
interface UpdateIsPrintsArrivedUseCase {
    /**
     * 서류 도착을 확인합니다.
     * 서류 도착 대기 상태에서 서류 접수 완료 상태로 변경합니다.
     *
     * @param receiptCode 접수번호
     */
    fun execute(receiptCode: Long)
}
