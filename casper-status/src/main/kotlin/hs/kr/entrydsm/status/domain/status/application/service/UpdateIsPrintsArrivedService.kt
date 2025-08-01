package hs.kr.entrydsm.status.domain.status.application.service

import hs.kr.entrydsm.status.domain.status.application.port.`in`.UpdateIsPrintsArrivedUseCase
import hs.kr.entrydsm.status.domain.status.application.port.out.QueryStatusPort
import hs.kr.entrydsm.status.domain.status.application.port.out.SaveStatusPort
import hs.kr.entrydsm.status.domain.status.exception.StatusNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * 서류 도착 상태 업데이트 서비스 클래스입니다.
 * 등기우편으로 제출된 서류의 도착을 확인하고 상태를 변경합니다.
 *
 * @property queryStatusPort 상태 조회 포트
 * @property saveStatusPort 상태 저장 포트
 */
@Transactional
@Service
class UpdateIsPrintsArrivedService(
    private val queryStatusPort: QueryStatusPort,
    private val saveStatusPort: SaveStatusPort,
): UpdateIsPrintsArrivedUseCase {
    
    /**
     * 서류 도착 상태를 업데이트합니다.
     * 서류 도착 대기 상태에서 서류 접수 완료 상태로 변경합니다.
     *
     * @param receiptCode 접수번호
     * @throws StatusNotFoundException 해당 접수번호의 상태가 존재하지 않는 경우
     */
    override fun execute(receiptCode: Long) {
        val status = queryStatusPort.findByReceiptCode(receiptCode)
            ?: throw StatusNotFoundException

        val updatedStatus = status.markDocumentsArrived()
        saveStatusPort.save(updatedStatus)
    }
}
