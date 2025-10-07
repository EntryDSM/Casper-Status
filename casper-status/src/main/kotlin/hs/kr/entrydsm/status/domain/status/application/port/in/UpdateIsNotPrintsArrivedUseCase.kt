package hs.kr.entrydsm.status.domain.status.application.port.`in`

/**
 * 서류 미도착 수정 유스케이스 인터페이스입니다.
 */
interface UpdateIsNotPrintsArrivedUseCase {
    /**
     * 서류 접수 완료 상태에서 서류 도착 대기 상태로 변경합니다.
     *
     * @param receiptCode 접수번호
     */
    fun execute(receiptCode: Long)
}