package hs.kr.entrydsm.status.domain.outbox.model

import java.time.LocalDateTime

/**
 * Outbox 패턴을 위한 도메인 이벤트 모델
 *
 * 도메인 엔티티와 동일 트랜잭션으로 저장되어 원자성을 보장합니다.
 * Debezium이 이 이벤트를 감지하여 Kafka로 발행합니다.
 *
 * @property id 이벤트 고유 식별자
 * @property aggregateType 집계 루트 타입 (예: "Status")
 * @property aggregateId 집계 식별자 (예: receiptCode)
 * @property eventType Kafka Topic 이름 (예: "delete-application-failed")
 * @property payload 이벤트 데이터 (JSON 직렬화)
 * @property createdAt 이벤트 생성 시각
 */
data class OutboxEvent(
    val id: Long? = null,
    val aggregateType: String,
    val aggregateId: String,
    val eventType: String,
    val payload: String,
    val createdAt: LocalDateTime = LocalDateTime.now(),
) {

    companion object {
        /**
         * Outbox 이벤트를 생성합니다.
         *
         * @param aggregateType 집계 루트 타입
         * @param aggregateId 집계 식별자
         * @param eventType Kafka Topic 이름
         * @param payload JSON 직렬화된 이벤트 데이터
         * @return 생성된 Outbox 이벤트
         */
        fun create(
            aggregateType: String,
            aggregateId: String,
            eventType: String,
            payload: String,
        ): OutboxEvent {
            return OutboxEvent(
                aggregateType = aggregateType,
                aggregateId = aggregateId,
                eventType = eventType,
                payload = payload,
            )
        }
    }
}
