package hs.kr.entrydsm.status.domain.status.adapter.`in`.web

import hs.kr.entrydsm.status.domain.status.application.port.`in`.GetAllStatusUseCase
import hs.kr.entrydsm.status.domain.status.application.port.`in`.GetStatusByReceiptCodeUseCase
import hs.kr.entrydsm.status.infrastructure.grpc.server.dto.response.InternalStatusResponse
import hs.kr.entrydsm.status.domain.status.application.port.`in`.UpdateExamCodeUseCase
import hs.kr.entrydsm.status.global.document.status.InternalStatusApiDocument
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

/**
 * 내부 시스템용 상태 조회 REST API 컨트롤러입니다.
 * 다른 마이크로서비스에서 지원자의 상태 정보를 조회할 수 있는 기능을 제공합니다.
 *
 * @property getStatusByReceiptCodeUseCase 접수번호별 상태 조회 유스케이스
 * @property getAllStatusUseCase 전체 상태 조회 유스케이스
 * @property updateExamCodeUseCase 수험번호 업데이트 유스케이스
 */
@RestController
@RequestMapping("/internal/status")
class InternalStatusWebController(
    private val getStatusByReceiptCodeUseCase: GetStatusByReceiptCodeUseCase,
    private val getAllStatusUseCase: GetAllStatusUseCase,
    private val updateExamCodeUseCase: UpdateExamCodeUseCase
) : InternalStatusApiDocument {
    
    /**
     * 접수번호로 상태를 조회합니다.
     *
     * @param receiptCode 접수번호
     * @return 지원자의 상태 정보
     */
    @GetMapping("/{receipt-code}")
    override fun getStatusByReceiptCode(@PathVariable("receipt-code") receiptCode: Long): InternalStatusResponse {
        return getStatusByReceiptCodeUseCase.execute(receiptCode)
    }

    /**
     * 모든 지원자의 상태 목록을 조회합니다.
     *
     * @return 전체 지원자 상태 정보 목록
     */
    @GetMapping("/list")
    override fun getAllStatus(): List<InternalStatusResponse> {
        return getAllStatusUseCase.execute()
    }

    /**
     * 수험번호를 업데이트합니다.
     *
     * @param receiptCode 접수번호
     * @param examCode 새로운 수험번호
     */
    @PutMapping("/{receipt-code}")
    override fun updateExamCode(@PathVariable("receipt-code") receiptCode: Long, @RequestParam examCode: String) {
        updateExamCodeUseCase.execute(receiptCode, examCode)
    }
}
