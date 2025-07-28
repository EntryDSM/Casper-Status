package hs.kr.entrydsm.status.infrastructure.grpc.server

import com.google.protobuf.Empty
import hs.kr.entrydsm.casper.status.proto.StatusServiceGrpcKt
import hs.kr.entrydsm.casper.status.proto.StatusServiceProto
import hs.kr.entrydsm.status.infrastructure.grpc.server.dto.response.InternalStatusResponse
import hs.kr.entrydsm.status.domain.status.application.port.`in`.GetAllStatusUseCase
import hs.kr.entrydsm.status.domain.status.application.port.`in`.GetStatusByReceiptCodeUseCase
import hs.kr.entrydsm.status.domain.status.application.port.`in`.UpdateExamCodeUseCase
import hs.kr.entrydsm.status.infrastructure.grpc.server.mapper.StatusGrpcMapper
import net.devh.boot.grpc.server.service.GrpcService

/**
 * 상태 관련 gRPC 서비스 구현 클래스입니다.
 * 다른 마이크로서비스와의 gRPC 통신을 통해 상태 정보를 제공합니다.
 *
 * @property getStatusByReceiptCodeUseCase 접수번호로 상태 조회 유스케이스
 * @property getAllStatusUseCase 모든 상태 조회 유스케이스
 * @property updateExamCodeUseCase 수험번호 업데이트 유스케이스
 * @property statusGrpcMapper gRPC 메시지 변환 매퍼
 */
@GrpcService
class StatusGrpcService(
    private val getStatusByReceiptCodeUseCase: GetStatusByReceiptCodeUseCase,
    private val getAllStatusUseCase: GetAllStatusUseCase,
    private val updateExamCodeUseCase: UpdateExamCodeUseCase,
    private val statusGrpcMapper: StatusGrpcMapper
): StatusServiceGrpcKt.StatusServiceCoroutineImplBase() {

    /**
     * 모든 상태 리스트를 조회합니다.
     *
     * @param request Empty 요청
     * @return 모든 상태 정보를 포함한 gRPC 응답
     */
    override suspend fun getStatusList(request: Empty): StatusServiceProto.GetStatusListResponse {
        val statusList = getAllStatusUseCase.execute()

        val internalStatusResponseList = statusList.map {
            InternalStatusResponse(
                id = it.id,
                applicationStatus = it.applicationStatus,
                examCode = it.examCode,
                isFirstRoundPass = it.isFirstRoundPass,
                isSecondRoundPass = it.isSecondRoundPass,
                receiptCode = it.receiptCode
            )
        }
        return statusGrpcMapper.toGetStatusListResponse(internalStatusResponseList)
    }

    /**
     * 접수번호로 특정 상태를 조회합니다.
     *
     * @param request 접수번호가 포함된 gRPC 요청
     * @return 해당 접수번호의 상태 정보 gRPC 응답
     */
    override suspend fun getStatusByReceiptCode(request: StatusServiceProto.GetStatusByReceiptCodeRequest): StatusServiceProto.GetStatusByReceiptCodeResponse {

        val status = getStatusByReceiptCodeUseCase.execute(request.receiptCode)

        val internalStatusResponse = InternalStatusResponse(
            id = status.id,
            applicationStatus = status.applicationStatus,
            examCode = status.examCode,
            isFirstRoundPass = status.isFirstRoundPass,
            isSecondRoundPass = status.isSecondRoundPass,
            receiptCode = status.receiptCode
        )
        return statusGrpcMapper.toGetStatusByReceiptCodeResponse(internalStatusResponse)
    }

    /**
     * 수험번호를 업데이트합니다.
     *
     * @param request 접수번호와 새로운 수험번호가 포함된 gRPC 요청
     * @return Empty 응답
     */
    override suspend fun updateExamCode(request: StatusServiceProto.GetExamCodeRequest): Empty {

        updateExamCodeUseCase.execute(request.receiptCode, request.examCode)
        return Empty.getDefaultInstance()
    }

}
