package hs.kr.entrydsm.status.domain.status.application.port.`in`

/**
 * 전형 시작 유스케이스 인터페이스입니다.
 * 서류 접수 완료 후 1차 또는 2차 전형을 시작하는 기능을 정의합니다.
 */
interface StartScreeningUseCase {

    /**
     * 전형을 시작합니다.
     * 서류 접수 완료 상태에서 전형 진행 중 상태로 변경합니다.
     *
     * @param receiptCode 접수번호
     */
    fun execute(receiptCode: Long)
}
