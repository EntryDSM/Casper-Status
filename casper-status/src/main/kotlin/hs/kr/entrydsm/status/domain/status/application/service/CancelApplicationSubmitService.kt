package hs.kr.entrydsm.status.domain.status.application.service

import hs.kr.entrydsm.status.domain.status.application.port.`in`.CancelApplicationSubmitUseCase
import hs.kr.entrydsm.status.domain.status.application.port.out.QueryStatusPort
import hs.kr.entrydsm.status.domain.status.application.port.out.SaveStatusPort
import hs.kr.entrydsm.status.domain.status.exception.StatusNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * 지원서 제출 취소 서비스 클래스입니다.
 * 제출된 지원서를 작성 중 상태로 되돌립니다.
 *
 * @property queryStatusPort 상태 조회 포트
 * @property saveStatusPort 상태 저장 포트
 */
@Service
class CancelApplicationSubmitService(
    private val queryStatusPort: QueryStatusPort,
    private val saveStatusPort: SaveStatusPort,
) : CancelApplicationSubmitUseCase {
    /**
     * 지원서 제출을 취소합니다.
     * 제출된 지원서를 작성 중 상태로 되돌립니다.
     *
     * @param receiptCode 접수번호
     * @throws StatusNotFoundException 해당 접수번호의 상태가 존재하지 않는 경우
     */
    @Transactional
    override fun execute(receiptCode: Long) {
        val status =
            queryStatusPort.findByReceiptCode(receiptCode)
                ?: throw StatusNotFoundException

        val canceledStatus = status.cancelSubmit()
        saveStatusPort.save(canceledStatus)
    }
}
