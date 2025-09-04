package hs.kr.entrydsm.status.domain.status.application.service

import hs.kr.entrydsm.status.domain.status.application.port.`in`.UpdateExamCodeUseCase
import hs.kr.entrydsm.status.domain.status.application.port.out.QueryStatusPort
import hs.kr.entrydsm.status.domain.status.application.port.out.SaveStatusPort
import hs.kr.entrydsm.status.domain.status.exception.StatusNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * 수험번호 업데이트 서비스 클래스입니다.
 * 지원자의 수험번호를 변경하는 기능을 제공합니다.
 *
 * @property queryStatusPort 상태 조회 포트
 * @property saveStatusPort 상태 저장 포트
 */
@Service
class UpdateExamCodeService(
    private val queryStatusPort: QueryStatusPort,
    private val saveStatusPort: SaveStatusPort,
) : UpdateExamCodeUseCase {
    /**
     * 수험번호를 업데이트합니다.
     *
     * @param receiptCode 접수번호
     * @param examCode 새로운 수험번호
     * @throws StatusNotFoundException 해당 접수번호의 상태가 존재하지 않는 경우
     */
    @Transactional
    override fun execute(
        receiptCode: Long,
        examCode: String,
    ) {
        val status = queryStatusPort.findByReceiptCode(receiptCode) ?: throw StatusNotFoundException

        val updatedExamCode = status.changeExamCode(examCode)
        saveStatusPort.save(updatedExamCode)
    }
}
