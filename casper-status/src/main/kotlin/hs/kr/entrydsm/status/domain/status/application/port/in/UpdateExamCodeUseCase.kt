package hs.kr.entrydsm.status.domain.status.application.port.`in`

/**
 * 수험번호 업데이트 유스케이스 인터페이스입니다.
 * 지원자의 수험번호를 변경하는 기능을 정의합니다.
 */
interface UpdateExamCodeUseCase {

    /**
     * 수험번호를 업데이트합니다.
     *
     * @param receiptCode 접수번호
     * @param examCode 새로운 수험번호
     */
    fun execute(receiptCode: Long, examCode: String)
}
