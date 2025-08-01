package hs.kr.entrydsm.status.domain.status.application.port.`in`

/**
 * 합격 결과 발표 유스케이스 인터페이스입니다.
 * 최종 전형 결과를 발표하고 상태를 변경하는 기능을 정의합니다.
 */
interface AnnounceResultUseCase {
    
    /**
     * 합격 결과를 발표합니다.
     * 전형 진행 중 상태에서 합격 여부 확인 상태로 변경합니다.
     *
     * @param receiptCode 접수번호
     */
    fun execute(receiptCode: Long)
}
