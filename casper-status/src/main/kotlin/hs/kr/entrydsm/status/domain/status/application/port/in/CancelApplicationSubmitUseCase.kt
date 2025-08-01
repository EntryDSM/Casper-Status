package hs.kr.entrydsm.status.domain.status.application.port.`in`

/**
 * 지원서 제출 취소 유스케이스 인터페이스입니다.
 * 제출된 지원서를 작성 중 상태로 되돌리는 기능을 정의합니다.
 */
interface CancelApplicationSubmitUseCase {

    /**
     * 지원서 제출을 취소합니다.
     * 제출 완료 상태에서 작성 중 상태로 변경합니다.
     *
     * @param receiptCode 접수번호
     */
    fun execute(receiptCode: Long)
}
