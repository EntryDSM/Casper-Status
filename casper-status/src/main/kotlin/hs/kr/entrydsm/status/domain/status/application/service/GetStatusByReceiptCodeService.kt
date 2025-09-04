package hs.kr.entrydsm.status.domain.status.application.service

import hs.kr.entrydsm.status.domain.status.adapter.out.domain.StatusCache
import hs.kr.entrydsm.status.domain.status.adapter.out.persistence.repository.StatusCacheRepository
import hs.kr.entrydsm.status.domain.status.application.port.`in`.GetStatusByReceiptCodeUseCase
import hs.kr.entrydsm.status.domain.status.application.port.out.QueryStatusPort
import hs.kr.entrydsm.status.domain.status.exception.StatusNotFoundException
import hs.kr.entrydsm.status.infrastructure.grpc.server.dto.response.InternalStatusResponse
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * 접수번호로 상태 조회 서비스 클래스입니다.
 * 특정 접수번호의 상태 정보를 조회하고 캐시를 활용하여 성능을 최적화합니다.
 *
 * @property queryStatusPort 상태 조회 포트
 * @property statusCacheRepository 상태 캐시 저장소
 */
@Transactional(readOnly = true)
@Service
class GetStatusByReceiptCodeService(
    private val queryStatusPort: QueryStatusPort,
    private val statusCacheRepository: StatusCacheRepository,
) : GetStatusByReceiptCodeUseCase {
    /**
     * 접수번호로 상태 정보를 조회합니다.
     * 캐시가 없는 경우 새로 생성하여 저장합니다.
     *
     * @param receiptCode 조회할 접수번호
     * @return 해당 접수번호의 상태 정보 응답
     * @throws StatusNotFoundException 해당 접수번호의 상태가 존재하지 않는 경우
     */
    override fun execute(receiptCode: Long): InternalStatusResponse {
        val status = queryStatusPort.findByReceiptCode(receiptCode) ?: throw StatusNotFoundException

        statusCacheRepository.findById(receiptCode).orElseGet {
            statusCacheRepository.save(StatusCache.from(status))
        }

        return status.run {
            InternalStatusResponse(
                id = id!!,
                receiptCode = receiptCode,
                applicationStatus = applicationStatus,
                examCode = examCode,
                isFirstRoundPass = isFirstRoundPass,
                isSecondRoundPass = isSecondRoundPass,
            )
        }
    }
}
