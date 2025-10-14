package hs.kr.entrydsm.status.infrastructure.kafka.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding

/**
 * 카프카 서버 관련 설정
 */
@ConfigurationPropertiesBinding
@ConfigurationProperties("kafka")
class KafkaProperty(
    val serverAddress: String
)
