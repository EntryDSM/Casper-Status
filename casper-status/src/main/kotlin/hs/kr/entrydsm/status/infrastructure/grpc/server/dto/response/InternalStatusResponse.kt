package hs.kr.entrydsm.status.infrastructure.grpc.server.dto.response

import hs.kr.entrydsm.status.domain.status.model.ApplicationStatus

/**
 * 내부 gRPC 통신용 상태 응답 DTO 클래스입니다.
 * 다른 마이크로서비스와의 통신에서 상태 정보를 전달하는 데 사용됩니다.
 *
 * @property id 상태 식별자
 * @property applicationStatus 현재 지원 상태
 * @property examCode 수험번호
 * @property isFirstRoundPass 1차 전형 합격 여부
 * @property isSecondRoundPass 2차 전형 합격 여부
 * @property receiptCode 접수번호
 */
data class InternalStatusResponse(
    val id: Long,
    val applicationStatus: ApplicationStatus,
    var examCode: String? = null,
    var isFirstRoundPass: Boolean = false,
    var isSecondRoundPass: Boolean = false,
    val receiptCode: Long
)
