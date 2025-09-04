package hs.kr.entrydsm.status.domain.status.adapter.out.persistence

import hs.kr.entrydsm.status.domain.status.adapter.out.mapper.StatusMapper
import hs.kr.entrydsm.status.domain.status.adapter.out.persistence.repository.StatusRepository
import hs.kr.entrydsm.status.domain.status.application.port.out.StatusPort
import hs.kr.entrydsm.status.domain.status.model.Status
import org.springframework.stereotype.Component

/**
 * 상태 데이터의 영속성 처리를 담당하는 어댑터 클래스입니다.
 * 헥사고날 아키텍처의 Driven Adapter 역할을 하며, 도메인 계층의 StatusPort를 구현합니다.
 *
 * @property statusRepository JPA 기반 상태 데이터 저장소
 * @property statusMapper 도메인 모델과 JPA 엔티티 간 변환 매퍼
 */
@Component
class StatusPersistenceAdapter(
    private val statusRepository: StatusRepository,
    private val statusMapper: StatusMapper,
) : StatusPort {
    /**
     * 상태 정보를 저장합니다.
     *
     * @param status 저장할 상태 도메인 모델
     */
    override fun save(status: Status) {
        statusRepository.save(statusMapper.toEntity(status))
    }

    /**
     * 접수번호로 상태 정보를 조회합니다.
     *
     * @param receiptCode 조회할 접수번호
     * @return 조회된 상태 도메인 모델 (없으면 null)
     */
    override fun findByReceiptCode(receiptCode: Long): Status? =
        statusRepository.findByReceiptCode(receiptCode)?.let { statusMapper.toModel(it) }

    /**
     * 모든 상태 정보를 조회합니다.
     *
     * @return 모든 상태 도메인 모델 리스트
     */
    override fun findAll(): List<Status> {
        return statusRepository.findAll()
            .map { statusMapper.toModelNotNull(it) }
    }

    /**
     * 접수번호로 상태 정보를 삭제합니다.
     *
     * @param receiptCode 삭제할 접수번호
     */
    override fun deleteByReceiptCode(receiptCode: Long) {
        statusRepository.deleteByReceiptCode(receiptCode)
    }
}
