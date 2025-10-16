package hs.kr.entrydsm.status.domain.status.adapter.out.mapper

import hs.kr.entrydsm.status.domain.status.adapter.out.domain.StatusJpaEntity
import hs.kr.entrydsm.status.domain.status.model.Status
import hs.kr.entrydsm.status.global.mapper.GenericMapper
import org.springframework.stereotype.Component

/**
 * Status 도메인 모델과 StatusJpaEntity 간의 변환을 담당하는 매퍼 클래스입니다.
 * 수동 구현으로 모든 필드를 명시적으로 매핑합니다.
 */
@Component
class StatusMapper : GenericMapper<StatusJpaEntity, Status> {
    
    /**
     * Status 도메인 모델을 StatusJpaEntity로 변환합니다.
     *
     * @param model 변환할 Status 도메인 모델
     * @return 변환된 StatusJpaEntity
     */
    override fun toEntity(model: Status): StatusJpaEntity {
        val entity = StatusJpaEntity(
            applicationStatus = model.applicationStatus,
            examCode = model.examCode,
            isFirstRoundPass = model.isFirstRoundPass,
            isSecondRoundPass = model.isSecondRoundPass,
            receiptCode = model.receiptCode
        )
        if (model.id != null) {
            entity.id = model.id
        }
        return entity
    }

    /**
     * StatusJpaEntity를 Status 도메인 모델로 변환합니다.
     *
     * @param entity 변환할 StatusJpaEntity (null 가능)
     * @return 변환된 Status 도메인 모델 (null 가능)
     */
    override fun toModel(entity: StatusJpaEntity?): Status? {
        if (entity == null) return null
        
        return Status(
            id = entity.id,
            applicationStatus = entity.applicationStatus,
            examCode = entity.examCode,
            isFirstRoundPass = entity.isFirstRoundPass,
            isSecondRoundPass = entity.isSecondRoundPass,
            receiptCode = entity.receiptCode
        )
    }

    /**
     * StatusJpaEntity를 Status 도메인 모델로 변환합니다.
     * null이 아닌 entity를 받아 null이 아닌 모델을 반환합니다.
     *
     * @param entity 변환할 StatusJpaEntity (null이 아님)
     * @return 변환된 Status 도메인 모델 (null이 아님)
     */
    override fun toModelNotNull(entity: StatusJpaEntity): Status {
        return Status(
            id = entity.id,
            applicationStatus = entity.applicationStatus,
            examCode = entity.examCode,
            isFirstRoundPass = entity.isFirstRoundPass,
            isSecondRoundPass = entity.isSecondRoundPass,
            receiptCode = entity.receiptCode
        )
    }
}
