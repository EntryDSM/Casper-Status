package hs.kr.entrydsm.status.domain.status.adapter.`in`.web

import hs.kr.entrydsm.status.domain.status.application.port.`in`.AnnounceResultUseCase
import hs.kr.entrydsm.status.domain.status.application.port.`in`.CancelApplicationSubmitUseCase
import hs.kr.entrydsm.status.domain.status.application.port.`in`.StartScreeningUseCase
import hs.kr.entrydsm.status.domain.status.application.port.`in`.UpdateIsPrintsArrivedUseCase
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * 관리자용 상태 관리 REST API 컨트롤러입니다.
 * 관리자가 지원자의 상태를 직접 변경할 수 있는 기능을 제공합니다.
 *
 * @property updateIsPrintsArrivedUseCase 서류 도착 상태 변경 유스케이스
 * @property cancelApplicationSubmitUseCase 지원서 제출 취소 유스케이스
 * @property startScreeningUseCase 전형 시작 유스케이스
 * @property announceResultUseCase 합격 발표 유스케이스
 */
@RestController
@RequestMapping("/admin/status")
class AdminWebController(
    private val updateIsPrintsArrivedUseCase: UpdateIsPrintsArrivedUseCase,
    private val cancelApplicationSubmitUseCase: CancelApplicationSubmitUseCase,
    private val startScreeningUseCase: StartScreeningUseCase,
    private val announceResultUseCase: AnnounceResultUseCase
) {
    
    /**
     * 지원서 제출을 취소합니다.
     * 제출된 지원서를 작성 중 상태로 되돌립니다.
     *
     * @param receiptCode 접수번호
     */
    @PatchMapping("/submitted/{receipt-code}")
    fun cancelApplicationSubmit(@PathVariable("receipt-code") receiptCode: Long) {
        cancelApplicationSubmitUseCase.execute(receiptCode)
    }

    /**
     * 서류 도착을 확인합니다.
     * 등기우편으로 제출된 서류의 도착을 관리자가 확인하여 상태를 변경합니다.
     *
     * @param receiptCode 접수번호
     */
    @PatchMapping("/prints-arrived/{receipt-code}")
    fun updateIsPrintsArrivedService(@PathVariable("receipt-code") receiptCode: Long) {
        updateIsPrintsArrivedUseCase.execute(receiptCode)
    }

    /**
     * 전형을 시작합니다.
     * 서류 검토가 완료된 후 1차 또는 2차 전형을 시작합니다.
     *
     * @param receiptCode 접수번호
     */
    @PatchMapping("/screening/{receipt-code}")
    fun startScreening(@PathVariable("receipt-code") receiptCode: Long) {
        startScreeningUseCase.execute(receiptCode)
    }

    /**
     * 합격 결과를 발표합니다.
     * 최종 전형 결과를 발표하고 상태를 변경합니다.
     *
     * @param receiptCode 접수번호
     */
    @PatchMapping("/announce/{receipt-code}")
    fun announceResult(@PathVariable("receipt-code") receiptCode: Long) {
        announceResultUseCase.execute(receiptCode)
    }
}
