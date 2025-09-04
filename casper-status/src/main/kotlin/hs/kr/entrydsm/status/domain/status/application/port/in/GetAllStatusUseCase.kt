package hs.kr.entrydsm.status.domain.status.application.port.`in`

import hs.kr.entrydsm.status.infrastructure.grpc.server.dto.response.InternalStatusResponse

/**
 * 모든 상태 조회 유스케이스 인터페이스입니다.
 * 전체 지원자의 상태 정보를 조회하는 기능을 정의합니다.
 */
interface GetAllStatusUseCase {
    /**
     * 모든 상태 정보를 조회합니다.
     *
     * @return 모든 상태 정보를 포함한 응답 리스트
     */
    fun execute(): List<InternalStatusResponse>
}
