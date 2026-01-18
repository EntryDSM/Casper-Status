package hs.kr.entrydsm.status.infrastructure.kafka.consumer

import com.fasterxml.jackson.databind.ObjectMapper
import hs.kr.entrydsm.status.domain.status.application.port.`in`.CreateStatusUseCase
import hs.kr.entrydsm.status.domain.status.application.port.`in`.DeleteStatusUseCase
import hs.kr.entrydsm.status.infrastructure.kafka.config.KafkaTopics
import hs.kr.entrydsm.status.infrastructure.kafka.consumer.dto.CreateApplicationEvent
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

/**
 * 입학 원서 상태 관련 메시지를 수신하는 Consumer
 */
@Component
class StatusConsumer(
    private val mapper: ObjectMapper,
    private val createStatusUseCase: CreateStatusUseCase,
    private val deleteStatusUseCase: DeleteStatusUseCase,
) {
    /**
     * 원서가 생성되면, 원서 상태를 생성합니다.
     *
     * @param message 원서 생성 이벤트
     */
    @KafkaListener(
        topics = [KafkaTopics.CREATE_APPLICATION],
        groupId = "create-status",
        containerFactory = "kafkaListenerContainerFactory",
    )
    fun createStatus(message: String) {
        val createApplicationEvent = mapper.readValue(message, CreateApplicationEvent::class.java)
        createStatusUseCase.execute(createApplicationEvent.receiptCode)
    }

    @KafkaListener(
        topics = [KafkaTopics.DELETE_USER, KafkaTopics.CANCEL_SUBMITTED_APPLICATION, KafkaTopics.USER_RECEIPT_CODE_UPDATE_FAILED],
        groupId = "delete-status",
        containerFactory = "kafkaListenerContainerFactory",
    )
    fun deleteStatus(message: String) {
        val receiptCode = mapper.readValue(message, Long::class.java)
        deleteStatusUseCase.execute(receiptCode)
    }
}
