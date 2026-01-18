package hs.kr.entrydsm.status.infrastructure.kafka.producer

interface StatusEventProducer {
    fun sendStatusCreatedCompleted(receiptCode: Long)
    fun sendStatusCreatedFailed(receiptCode: Long)
}