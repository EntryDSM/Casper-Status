package hs.kr.entrydsm.status.global.document.status

import hs.kr.entrydsm.status.infrastructure.grpc.server.dto.response.InternalStatusResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam

/**
 * Internal Status API 문서화를 위한 인터페이스입니다.
 */
@Tag(name = "Internal Status", description = "내부 시스템용 지원 상태 API")
interface InternalStatusApiDocument {

    @Operation(
        summary = "접수번호로 상태 조회",
        description = "특정 접수번호의 지원 상태 정보를 조회합니다. 캐시를 활용하여 성능을 최적화합니다."
    )
    @ApiResponses(
        ApiResponse(
            responseCode = "200",
            description = "상태 조회 성공",
            content = arrayOf(Content(schema = Schema(implementation = InternalStatusResponse::class)))
        ), // todo: 나중에 머지 했을 때도 오류 났을 경우 수정하기
        ApiResponse(
            responseCode = "404",
            description = "상태를 찾을 수 없음 - Status Not Found",
            content = arrayOf(Content())
        )
    )
    /**
     * 접수번호로 지원자의 상태 정보를 조회합니다.
     */
    fun getStatusByReceiptCode(
        @Parameter(description = "조회할 접수번호", required = true)
        @PathVariable("receipt-code") receiptCode: Long
    ): InternalStatusResponse

    @Operation(
        summary = "모든 상태 조회",
        description = "전체 지원자의 상태 정보 목록을 조회합니다."
    )
    @ApiResponses(
        ApiResponse(
            responseCode = "200",
            description = "전체 상태 조회 성공",
            content = arrayOf(Content(schema = Schema(implementation = Array<InternalStatusResponse>::class)))
        )
    )
    /**
     * 모든 지원자의 상태 정보 목록을 조회합니다.
     */
    fun getAllStatus(): List<InternalStatusResponse>

    @Operation(
        summary = "수험번호 업데이트",
        description = "특정 접수번호의 수험번호를 업데이트합니다."
    )
    @ApiResponses(
        ApiResponse(
            responseCode = "204",
            description = "수험번호 업데이트 성공",
            content = arrayOf(Content())
        ),
        ApiResponse(
            responseCode = "404",
            description = "상태를 찾을 수 없음 - Status Not Found",
            content = arrayOf(Content())
        )
    )
    /**
     * 지원자의 수험번호를 업데이트합니다.
     */
    fun updateExamCode(
        @Parameter(description = "접수번호", required = true)
        @PathVariable("receipt-code") receiptCode: Long,
        @Parameter(description = "새로운 수험번호", required = true)
        @RequestParam examCode: String
    )
}