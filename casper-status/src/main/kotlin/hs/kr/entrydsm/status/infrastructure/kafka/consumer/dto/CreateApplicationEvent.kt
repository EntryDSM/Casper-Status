package hs.kr.entrydsm.status.infrastructure.kafka.consumer.dto

import java.util.UUID

data class CreateApplicationEvent(
    val receiptCode: Long,
    val userId: UUID,
)
