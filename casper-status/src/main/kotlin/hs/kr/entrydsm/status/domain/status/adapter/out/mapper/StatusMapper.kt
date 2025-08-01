package hs.kr.entrydsm.status.domain.status.adapter.out.mapper

import hs.kr.entrydsm.status.domain.status.adapter.out.domain.StatusJpaEntity
import hs.kr.entrydsm.status.domain.status.model.Status
import hs.kr.entrydsm.status.global.mapper.GenericMapper
import org.mapstruct.Mapper

/**
 * Status 도메인 모델과 StatusJpaEntity 간의 변환을 담당하는 매퍼 클래스입니다.
 * MapStruct를 사용하여 도메인 계층과 인프라스트럭처 계층 간의 데이터 변환을 처리합니다.
 */
@Mapper(componentModel = "spring")
abstract class StatusMapper: GenericMapper<StatusJpaEntity, Status> {

    /**
     * Status 도메인 모델을 StatusJpaEntity로 변환합니다.
     *
     * @param model 변환할 Status 도메인 모델
     * @return 변환된 StatusJpaEntity
     */
    abstract override fun toEntity(model: Status): StatusJpaEntity

    /**
     * StatusJpaEntity를 Status 도메인 모델로 변환합니다.
     *
     * @param entity 변환할 StatusJpaEntity (null 가능)
     * @return 변환된 Status 도메인 모델 (null 가능)
     */
    abstract override fun toModel(entity: StatusJpaEntity?): Status?

    /**
     * StatusJpaEntity를 Status 도메인 모델로 변환합니다.
     * null이 아닌 entity를 받아 null이 아닌 모델을 반환합니다.
     *
     * @param entity 변환할 StatusJpaEntity (null이 아님)
     * @return 변환된 Status 도메인 모델 (null이 아님)
     */
    abstract override fun toModelNotNull(entity: StatusJpaEntity): Status
}
