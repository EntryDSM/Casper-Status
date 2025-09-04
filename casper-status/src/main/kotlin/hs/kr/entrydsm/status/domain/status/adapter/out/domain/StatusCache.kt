package hs.kr.entrydsm.status.domain.status.adapter.out.domain

import hs.kr.entrydsm.status.domain.status.model.ApplicationStatus
import hs.kr.entrydsm.status.domain.status.model.Status
import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash
import org.springframework.data.redis.core.TimeToLive

/**
 * Redis 캐시용 상태 엔티티 클래스입니다.
 * 상태 조회 성능 향상을 위한 캐시 데이터를 관리합니다.
 *
 * @property receiptCode 접수번호 (Redis 키로 사용)
 * @property applicationStatus 지원 상태
 * @property examCode 수험번호
 * @property isFirstRoundPass 1차 전형 합격 여부
 * @property isSecondRoundPass 2차 전형 합격 여부
 * @property ttl Time To Live (캐시 만료 시간, 초 단위)
 */
@RedisHash(value = "status_cache")
class StatusCache(
    @Id
    val receiptCode: Long,
    val applicationStatus: ApplicationStatus,
    val examCode: String?,
    val isFirstRoundPass: Boolean,
    val isSecondRoundPass: Boolean,
    @TimeToLive
    var ttl: Long,
) {
    companion object {
        /**
         * Status 도메인 모델로부터 StatusCache 인스턴스를 생성합니다.
         *
         * @param status 도메인 모델 Status 인스턴스
         * @return StatusCache 인스턴스 (TTL 10분으로 설정)
         */
        fun from(status: Status): StatusCache {
            return StatusCache(
                receiptCode = status.receiptCode,
                applicationStatus = status.applicationStatus,
                examCode = status.examCode,
                isFirstRoundPass = status.isFirstRoundPass,
                isSecondRoundPass = status.isSecondRoundPass,
                ttl = 60 * 10,
            )
        }
    }
}
