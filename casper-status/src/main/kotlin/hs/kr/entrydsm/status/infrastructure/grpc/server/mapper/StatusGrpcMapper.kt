package hs.kr.entrydsm.status.infrastructure.grpc.server.mapper

import hs.kr.entrydsm.casper.status.proto.StatusServiceProto
import hs.kr.entrydsm.status.domain.status.model.ApplicationStatus
import hs.kr.entrydsm.status.infrastructure.grpc.server.dto.response.InternalStatusResponse
import org.springframework.stereotype.Component

/**
 * 상태 정보를 gRPC 프로토콜 버퍼와 매핑하는 매퍼 클래스입니다.
 * 내부 DTO와 gRPC 메시지 간의 변환을 담당합니다.
 */
@Component
class StatusGrpcMapper {
    /**
     * 상태 리스트를 gRPC 응답으로 변환합니다.
     *
     * @param statusList 변환할 상태 응답 리스트
     * @return gRPC 상태 리스트 응답
     */
    fun toGetStatusListResponse(statusList: List<InternalStatusResponse>): StatusServiceProto.GetStatusListResponse {
        return StatusServiceProto.GetStatusListResponse.newBuilder()
            .addAllStatusList(statusList.map { toStatusInfoElement(it) })
            .build()
    }

    /**
     * 단일 상태를 gRPC 응답으로 변환합니다.
     *
     * @param response 변환할 상태 응답
     * @return gRPC 상태 조회 응답
     */
    fun toGetStatusByReceiptCodeResponse(response: InternalStatusResponse): StatusServiceProto.GetStatusByReceiptCodeResponse {
        return StatusServiceProto.GetStatusByReceiptCodeResponse.newBuilder()
            .setStatus(toStatusInfoElement(response))
            .build()
    }

    /**
     * 상태 응답을 gRPC 상태 정보 요소로 변환합니다.
     *
     * @param response 변환할 상태 응답
     * @return gRPC 상태 정보 요소
     */
    private fun toStatusInfoElement(response: InternalStatusResponse): StatusServiceProto.StatusInfoElement {
        return StatusServiceProto.StatusInfoElement.newBuilder()
            .setId(response.id)
            .setApplicationStatus(toProtoApplicationStatus(response.applicationStatus))
            .setExamCode(response.examCode ?: "")
            .setIsFirstRoundPass(response.isFirstRoundPass)
            .setIsSecondRoundPass(response.isSecondRoundPass)
            .setReceiptCode(response.receiptCode)
            .build()
    }

    /**
     * 도메인 지원 상태를 gRPC 프로토콜 지원 상태로 변환합니다.
     *
     * @param applicationStatus 변환할 도메인 지원 상태
     * @return gRPC 프로토콜 지원 상태
     */
    private fun toProtoApplicationStatus(applicationStatus: ApplicationStatus): StatusServiceProto.ApplicationStatus {
        return when (applicationStatus) {
            ApplicationStatus.NOT_APPLIED -> StatusServiceProto.ApplicationStatus.NOT_APPLIED
            ApplicationStatus.SUBMITTED -> StatusServiceProto.ApplicationStatus.SUBMITTED
            ApplicationStatus.WRITING -> StatusServiceProto.ApplicationStatus.WRITING
            ApplicationStatus.WAITING_DOCUMENTS -> StatusServiceProto.ApplicationStatus.WAITING_DOCUMENTS
            ApplicationStatus.DOCUMENTS_RECEIVED -> StatusServiceProto.ApplicationStatus.DOCUMENTS_RECEIVED
            ApplicationStatus.SCREENING_IN_PROGRESS -> StatusServiceProto.ApplicationStatus.SCREENING_IN_PROGRESS
            ApplicationStatus.RESULT_ANNOUNCED -> StatusServiceProto.ApplicationStatus.RESULT_ANNOUNCED
        }
    }
}
