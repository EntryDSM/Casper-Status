package hs.kr.entrydsm.status.domain.outbox.application.port.out

import hs.kr.entrydsm.status.domain.outbox.model.OutboxEvent

/**
 * Outbox 이벤트 저장 포트
 *
 * Domain 계층에서 Infrastructure 계층으로의 의존성 역전을 위한 포트입니다.
 * 도메인 엔티티와 동일 트랜잭션으로 Outbox 이벤트를 저장합니다.
 */
interface SaveOutboxEventPort {
    /**
     * Outbox 이벤트를 저장합니다.
     *
     * @param outboxEvent 저장할 Outbox 이벤트
     * @return 저장된 Outbox 이벤트 (ID 포함)
     */
    fun save(outboxEvent: OutboxEvent): OutboxEvent
}
