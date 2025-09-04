package hs.kr.entrydsm.status.domain.status.application.service

import hs.kr.entrydsm.status.domain.status.application.port.`in`.StartScreeningUseCase
import hs.kr.entrydsm.status.domain.status.application.port.out.QueryStatusPort
import hs.kr.entrydsm.status.domain.status.application.port.out.SaveStatusPort
import hs.kr.entrydsm.status.domain.status.exception.StatusNotFoundException
import org.springframework.stereotype.Service

/**
 * 전형 시작 서비스 클래스입니다.
 * 서류 접수가 완료된 지원자의 상태를 전형 진행 중으로 변경합니다.
 *
 * @property queryStatusPort 상태 조회 포트
 * @property saveStatusPort 상태 저장 포트
 */
@Service
class StartScreeningService(
    private val queryStatusPort: QueryStatusPort,
    private val saveStatusPort: SaveStatusPort,
) : StartScreeningUseCase {
    /**
     * 전형을 시작합니다.
     * 서류 접수 완료 상태에서 전형 진행 중 상태로 변경합니다.
     *
     * @param receiptCode 접수번호
     * @throws StatusNotFoundException 해당 접수번호의 상태가 존재하지 않는 경우
     */
    override fun execute(receiptCode: Long) {
        val status = queryStatusPort.findByReceiptCode(receiptCode) ?: throw StatusNotFoundException
        val screeningStatus = status.startScreening()
        saveStatusPort.save(screeningStatus)
    }
}
