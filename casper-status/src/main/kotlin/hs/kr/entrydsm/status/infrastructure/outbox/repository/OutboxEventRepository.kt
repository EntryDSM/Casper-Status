package hs.kr.entrydsm.status.infrastructure.outbox.repository

import hs.kr.entrydsm.status.infrastructure.outbox.entity.OutboxEventEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

/**
 * Outbox 이벤트 JPA Repository
 */
@Repository
interface OutboxEventRepository : JpaRepository<OutboxEventEntity, Long>
