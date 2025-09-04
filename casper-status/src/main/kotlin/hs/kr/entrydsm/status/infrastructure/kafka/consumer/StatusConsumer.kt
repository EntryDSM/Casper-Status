package hs.kr.entrydsm.status.infrastructure.kafka.consumer

import com.fasterxml.jackson.databind.ObjectMapper
import hs.kr.entrydsm.status.domain.status.application.port.`in`.CreateStatusUseCase
import hs.kr.entrydsm.status.domain.status.application.port.`in`.DeleteStatusUseCase
import hs.kr.entrydsm.status.domain.status.application.port.`in`.UpdateStatusUseCase
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
    private val updateStatusUseCase: UpdateStatusUseCase,
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

    /**
     * 최종 제출된 원서의 상태를 변경합니다.
     *
     * @param message 최종 제출된 원서의 접수 번호
     */
    @KafkaListener(
        topics = [KafkaTopics.SUBMIT_APPLICATION_FINAL],
        groupId = "update-status",
        containerFactory = "kafkaListenerContainerFactory",
    )
    fun updateStatus(message: String) {
        val receiptCode = mapper.readValue(message, Long::class.java)
        updateStatusUseCase.execute(receiptCode)
    }

    /**
     * 탈퇴한 유저의 원서 상태를 삭제합니다.
     *
     * @param message 탈퇴한 유저의 접수 번호
     */
    @KafkaListener(
        topics = [KafkaTopics.DELETE_USER],
        groupId = "delete-status",
        containerFactory = "kafkaListenerContainerFactory",
    )
    fun deleteStatus(message: String) {
        val receiptCode = mapper.readValue(message, Long::class.java)
        deleteStatusUseCase.execute(receiptCode)
    }
}
