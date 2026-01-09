package hs.kr.entrydsm.status.domain.outbox.application.port.out

/**
 * Outbox 이벤트 발행자 포트
 *
 * UseCase에서 이벤트를 발행할 때 사용하는 포트입니다.
 * 페이로드 객체를 자동으로 JSON 직렬화하여 Outbox 테이블에 저장합니다.
 */
interface OutboxEventPublisherPort {
    /**
     * 이벤트를 Outbox 테이블에 저장합니다.
     *
     * 페이로드 객체를 자동으로 JSON으로 변환합니다.
     * 도메인 엔티티와 동일 트랜잭션으로 저장되어 원자성을 보장합니다.
     *
     * @param T 페이로드 타입
     * @param aggregateType 집계 루트 타입
     * @param aggregateId 집계 식별자
     * @param eventType Kafka Topic 이름
     * @param payload 이벤트 데이터 객체
     */
    fun <T> publish(
        aggregateType: String,
        aggregateId: String,
        eventType: String,
        payload: T,
    )
}
