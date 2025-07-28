package hs.kr.entrydsm.status.domain.status.application.port.`in`

import hs.kr.entrydsm.status.infrastructure.grpc.server.dto.response.InternalStatusResponse

/**
 * 접수번호로 상태 조회 유스케이스 인터페이스입니다.
 * 특정 접수번호의 상태 정보를 조회하는 기능을 정의합니다.
 */
interface GetStatusByReceiptCodeUseCase {

    /**
     * 접수번호로 상태 정보를 조회합니다.
     *
     * @param receiptCode 조회할 접수번호
     * @return 해당 접수번호의 상태 정보 응답
     */
    fun execute(receiptCode: Long): InternalStatusResponse
}
