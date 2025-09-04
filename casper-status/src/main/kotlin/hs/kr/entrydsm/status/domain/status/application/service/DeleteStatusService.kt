package hs.kr.entrydsm.status.domain.status.application.service

import hs.kr.entrydsm.status.domain.status.application.port.`in`.DeleteStatusUseCase
import hs.kr.entrydsm.status.domain.status.application.port.out.DeleteStatusPort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * 상태 삭제 서비스 클래스입니다.
 * 특정 접수번호의 상태 정보를 삭제합니다.
 *
 * @property deleteStatusPort 상태 삭제 포트
 */
@Service
class DeleteStatusService(
    private val deleteStatusPort: DeleteStatusPort,
) : DeleteStatusUseCase {
    /**
     * 접수번호로 상태 정보를 삭제합니다.
     *
     * @param receiptCode 삭제할 접수번호
     */
    @Transactional
    override fun execute(receiptCode: Long) {
        deleteStatusPort.deleteByReceiptCode(receiptCode)
    }
}
