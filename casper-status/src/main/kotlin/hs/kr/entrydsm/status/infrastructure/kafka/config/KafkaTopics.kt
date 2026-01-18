package hs.kr.entrydsm.status.infrastructure.kafka.config

/**
 * 카프카 토픽을 정의하는 객체
 */
object KafkaTopics {
    /**
     * 원서 생성 토픽
     */
    const val CREATE_APPLICATION = "create-application"

    /**
     * 유저 탈퇴 토픽
     */
    const val DELETE_USER = "delete-user"

    const val CANCEL_SUBMITTED_APPLICATION = "cancel-submitted-application"

    const val APPLICATION_STATUS_CREATE_COMPLETED = "application-status-create-completed"

    const val APPLICATION_STATUS_CREATE_FAILED = "application-status-create-failed"

    const val USER_RECEIPT_CODE_UPDATE_FAILED = "user-receipt-code-update-failed"
}
