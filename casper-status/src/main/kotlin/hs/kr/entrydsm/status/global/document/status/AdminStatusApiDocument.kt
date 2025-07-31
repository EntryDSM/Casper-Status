package hs.kr.entrydsm.status.global.document.status

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.PathVariable

/**
 * Admin Status API 문서화를 위한 인터페이스입니다.
 */
@Tag(name = "Admin Status", description = "관리자용 지원 상태 관리 API")
interface AdminStatusApiDocument {

    @Operation(
        summary = "지원서 제출 취소",
        description = "제출된 지원서를 작성 중 상태로 되돌립니다. 관리자 전용 기능입니다."
    )
    @ApiResponses(
        ApiResponse(
            responseCode = "204",
            description = "지원서 제출 취소 성공",
            content = arrayOf(Content())
        ),
        ApiResponse(
            responseCode = "404",
            description = "상태를 찾을 수 없음 - Status Not Found",
            content = arrayOf(Content())
        )
    )
    fun cancelApplicationSubmit(
        @Parameter(description = "접수번호", required = true)
        @PathVariable("receipt-code") receiptCode: Long
    )

    @Operation(
        summary = "서류 도착 확인",
        description = "등기우편으로 제출된 서류의 도착을 확인하여 서류 접수 완료 상태로 변경합니다."
    )
    @ApiResponses(
        ApiResponse(
            responseCode = "204",
            description = "서류 도착 확인 성공",
            content = arrayOf(Content())
        ),
        ApiResponse(
            responseCode = "404",
            description = "상태를 찾을 수 없음 - Status Not Found",
            content = arrayOf(Content())
        )
    )
    fun updateIsPrintsArrived(
        @Parameter(description = "접수번호", required = true)
        @PathVariable("receipt-code") receiptCode: Long
    )

    @Operation(
        summary = "전형 시작",
        description = "서류 검토가 완료된 후 1차 또는 2차 전형을 시작합니다."
    )
    @ApiResponses(
        ApiResponse(
            responseCode = "204",
            description = "전형 시작 성공",
            content = arrayOf(Content())
        ),
        ApiResponse(
            responseCode = "404",
            description = "상태를 찾을 수 없음 - Status Not Found",
            content = arrayOf(Content())
        )
    )
    fun startScreening(
        @Parameter(description = "접수번호", required = true)
        @PathVariable("receipt-code") receiptCode: Long
    )

    @Operation(
        summary = "합격 결과 발표",
        description = "최종 전형 결과를 발표하고 합격 여부 확인 상태로 변경합니다."
    )
    @ApiResponses(
        ApiResponse(
            responseCode = "204",
            description = "합격 결과 발표 성공",
            content = arrayOf(Content())
        ),
        ApiResponse(
            responseCode = "404",
            description = "상태를 찾을 수 없음 - Status Not Found",
            content = arrayOf(Content())
        )
    )
    fun announceResult(
        @Parameter(description = "접수번호", required = true)
        @PathVariable("receipt-code") receiptCode: Long
    )
}