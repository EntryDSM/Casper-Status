package hs.kr.entrydsm.status.domain.status.application.service

import hs.kr.entrydsm.status.infrastructure.grpc.server.dto.response.InternalStatusResponse
import hs.kr.entrydsm.status.domain.status.application.port.`in`.GetAllStatusUseCase
import hs.kr.entrydsm.status.domain.status.application.port.out.QueryStatusPort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * 모든 상태 조회 서비스 클래스입니다.
 * 전체 지원자의 상태 정보를 조회하고 응답 형태로 변환합니다.
 *
 * @property queryStatusPort 상태 조회 포트
 */
@Transactional(readOnly = true)
@Service
class GetAllStatusService(
    private val queryStatusPort: QueryStatusPort,
): GetAllStatusUseCase {
    
    /**
     * 모든 상태 정보를 조회합니다.
     *
     * @return 모든 상태 정보를 포함한 응답 리스트
     */
    override fun execute(): List<InternalStatusResponse> {
        val status = queryStatusPort.findAll()

        return status.map {
            InternalStatusResponse(
                id = it.id!!,
                applicationStatus =  it.applicationStatus,
                receiptCode = it.receiptCode,
                examCode = it.examCode,
                isFirstRoundPass = it.isFirstRoundPass,
                isSecondRoundPass = it.isSecondRoundPass
            )
        }
    }
}
