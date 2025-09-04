package hs.kr.entrydsm.status.domain.status.application.service

import hs.kr.entrydsm.status.domain.status.application.port.`in`.UpdateStatusUseCase
import hs.kr.entrydsm.status.domain.status.application.port.out.QueryStatusPort
import hs.kr.entrydsm.status.domain.status.application.port.out.SaveStatusPort
import hs.kr.entrydsm.status.domain.status.exception.StatusNotFoundException
import hs.kr.entrydsm.status.domain.status.model.ApplicationStatus
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * 상태 업데이트 서비스 클래스입니다.
 * 지원서 제출 시 상태를 제출 완료에서 서류 도착 대기 상태로 자동 변경합니다.
 *
 * @property queryStatusPort 상태 조회 포트
 * @property saveStatusPort 상태 저장 포트
 */
@Transactional
@Service
class UpdateStatusService(
    private val queryStatusPort: QueryStatusPort,
    private val saveStatusPort: SaveStatusPort,
) : UpdateStatusUseCase {
    /**
     * 지원서 제출 상태를 업데이트합니다.
     * 제출 상태로 변경한 후 자동으로 서류 도착 대기 상태로 변경합니다.
     *
     * @param receiptCode 접수번호
     * @throws StatusNotFoundException 해당 접수번호의 상태가 존재하지 않는 경우
     */
    override fun execute(receiptCode: Long) {
        val status = queryStatusPort.findByReceiptCode(receiptCode) ?: throw StatusNotFoundException

        // SUBMITTED 상태로 변경 후 바로 WAITING_DOCUMENTS로 변경
        val submittedStatus = status.submitApplication()
        val waitingStatus = submittedStatus.copy(applicationStatus = ApplicationStatus.WAITING_DOCUMENTS)
        saveStatusPort.save(waitingStatus)
    }
}
