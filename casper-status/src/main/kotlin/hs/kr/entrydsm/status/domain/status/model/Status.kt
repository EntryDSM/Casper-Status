package hs.kr.entrydsm.status.domain.status.model

/**
 * 지원 상태 도메인 모델 클래스입니다.
 * 지원자의 전형 진행 상태와 관련 정보를 관리합니다.
 * 불변성을 보장하는 데이터 클래스로 설계되었습니다.
 *
 * @property id 상태 식별자
 * @property applicationStatus 현재 지원 상태
 * @property examCode 수험번호
 * @property isFirstRoundPass 1차 전형 합격 여부
 * @property isSecondRoundPass 2차 전형 합격 여부
 * @property receiptCode 접수번호
 */
data class Status(
    val id: Long?,
    val applicationStatus: ApplicationStatus,
    val examCode: String?,
    val isFirstRoundPass: Boolean,
    val isSecondRoundPass: Boolean,
    val receiptCode: Long,
) {
    /**
     * 지원서를 제출 상태로 변경합니다.
     *
     * @return 제출 상태로 변경된 Status 인스턴스
     */
    fun submitApplication(): Status {
        return copy(applicationStatus = ApplicationStatus.SUBMITTED)
    }

    /**
     * 지원서 제출을 취소하고 작성 중 상태로 변경합니다.
     *
     * @return 작성 중 상태로 변경된 Status 인스턴스
     */
    fun cancelSubmit(): Status {
        return copy(applicationStatus = ApplicationStatus.WRITING)
    }

    /**
     * 서류가 도착했음을 표시하고 서류 접수 완료 상태로 변경합니다.
     *
     * @return 서류 접수 완료 상태로 변경된 Status 인스턴스
     */
    fun markDocumentsArrived(): Status {
        return copy(applicationStatus = ApplicationStatus.DOCUMENTS_RECEIVED)
    }

    /**
     * 서류가 도착했음을 미표시하고 서류 도착 대기 상태로 변경합니다.
     *
     * @return 서류 도착 대기 상태로 변경된 Status 인스턴스
     */
    fun markDocumentsNotArrived(): Status {
        return copy(applicationStatus = ApplicationStatus.WAITING_DOCUMENTS)
    }

    /**
     * 전형을 시작하고 전형 진행 중 상태로 변경합니다.
     *
     * @return 전형 진행 중 상태로 변경된 Status 인스턴스
     */
    fun startScreening(): Status {
        return copy(applicationStatus = ApplicationStatus.SCREENING_IN_PROGRESS)
    }

    /**
     * 합격 결과를 발표하고 합격 여부 확인 상태로 변경합니다.
     *
     * @return 합격 여부 확인 상태로 변경된 Status 인스턴스
     */
    fun announceResult(): Status {
        return copy(applicationStatus = ApplicationStatus.RESULT_ANNOUNCED)
    }

    /**
     * 수험번호를 변경합니다.
     *
     * @param examCode 새로운 수험번호
     * @return 수험번호가 변경된 Status 인스턴스
     */
    fun changeExamCode(examCode: String): Status = copy(examCode = examCode)
}
