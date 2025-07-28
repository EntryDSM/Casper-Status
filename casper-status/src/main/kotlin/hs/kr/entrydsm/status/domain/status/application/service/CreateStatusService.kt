package hs.kr.entrydsm.status.domain.status.application.service

import hs.kr.entrydsm.status.domain.status.application.port.`in`.CreateStatusUseCase
import hs.kr.entrydsm.status.domain.status.application.port.out.QueryStatusPort
import hs.kr.entrydsm.status.domain.status.application.port.out.SaveStatusPort
import hs.kr.entrydsm.status.domain.status.model.ApplicationStatus
import hs.kr.entrydsm.status.domain.status.model.Status
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * 상태 생성 서비스 클래스입니다.
 * 새로운 지원자의 초기 상태를 생성합니다.
 *
 * @property queryStatusPort 상태 조회 포트
 * @property saveStatusPort 상태 저장 포트
 */
@Service
class CreateStatusService(
    private val queryStatusPort: QueryStatusPort,
    private val saveStatusPort: SaveStatusPort
): CreateStatusUseCase {
    
    /**
     * 새로운 지원자의 초기 상태를 생성합니다.
     * 이미 상태가 존재하는 경우 생성하지 않습니다.
     *
     * @param receiptCode 접수번호
     */
    @Transactional
    override fun execute(receiptCode: Long) {
        queryStatusPort.findByReceiptCode(receiptCode)?: saveStatusPort.save(
            Status(
                id = null,
                applicationStatus = ApplicationStatus.NOT_APPLIED,
                examCode = null,
                isFirstRoundPass = false,
                isSecondRoundPass = false,
                receiptCode = receiptCode,
            )
        )
    }
}
