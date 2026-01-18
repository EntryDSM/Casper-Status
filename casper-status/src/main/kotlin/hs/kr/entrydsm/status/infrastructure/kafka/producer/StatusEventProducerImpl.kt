package hs.kr.entrydsm.status.infrastructure.kafka.producer

import hs.kr.entrydsm.status.infrastructure.kafka.config.KafkaTopics
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component

@Component
class StatusEventProducerImpl(
    private val sendStatusCreatedCompletedTemplate: KafkaTemplate<String, Any>,
    private val sendStatusCreatedFailedTemplate: KafkaTemplate<String, Any>
) : StatusEventProducer {

    override fun sendStatusCreatedCompleted(receiptCode: Long) {
        sendStatusCreatedCompletedTemplate.send(
            KafkaTopics.APPLICATION_STATUS_CREATE_COMPLETED,
            receiptCode
        )
    }

    override fun sendStatusCreatedFailed(receiptCode: Long) {
        sendStatusCreatedFailedTemplate.send(
            KafkaTopics.APPLICATION_STATUS_CREATE_FAILED,
            receiptCode
        )
    }
}