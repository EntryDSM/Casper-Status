package hs.kr.entrydsm.status.domain.status.application.service

import hs.kr.entrydsm.status.domain.status.application.port.`in`.AnnounceResultUseCase
import hs.kr.entrydsm.status.domain.status.application.port.out.QueryStatusPort
import hs.kr.entrydsm.status.domain.status.application.port.out.SaveStatusPort
import hs.kr.entrydsm.status.domain.status.exception.StatusNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * 합격 결과 발표 서비스 클래스입니다.
 * 전형 진행 중인 지원자의 상태를 합격 여부 확인 상태로 변경합니다.
 *
 * @property queryStatusPort 상태 조회 포트
 * @property saveStatusPort 상태 저장 포트
 */
@Transactional
@Service
class AnnounceResultService(
    private val queryStatusPort: QueryStatusPort,
    private val saveStatusPort: SaveStatusPort,
) : AnnounceResultUseCase {
    /**
     * 합격 결과를 발표합니다.
     * 전형 진행 중 상태에서 합격 여부 확인 상태로 변경합니다.
     *
     * @param receiptCode 접수번호
     * @throws StatusNotFoundException 해당 접수번호의 상태가 존재하지 않는 경우
     */
    override fun execute(receiptCode: Long) {
        val status =
            queryStatusPort.findByReceiptCode(receiptCode)
                ?: throw StatusNotFoundException

        val resultStatus = status.announceResult()
        saveStatusPort.save(resultStatus)
    }
}
