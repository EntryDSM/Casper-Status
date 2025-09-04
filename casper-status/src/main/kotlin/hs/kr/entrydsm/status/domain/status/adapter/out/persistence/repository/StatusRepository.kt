package hs.kr.entrydsm.status.domain.status.adapter.out.persistence.repository

import hs.kr.entrydsm.status.domain.status.adapter.out.domain.StatusJpaEntity
import org.springframework.data.repository.CrudRepository

/**
 * 상태 JPA 엔티티를 위한 저장소 인터페이스입니다.
 * Spring Data JPA를 통해 기본 CRUD 작업과 커스텀 쿼리를 제공합니다.
 */
interface StatusRepository : CrudRepository<StatusJpaEntity, Long> {
    /**
     * 접수번호로 상태 엔티티를 조회합니다.
     *
     * @param receiptCode 조회할 접수번호
     * @return 조회된 상태 엔티티 (없으면 null)
     */
    fun findByReceiptCode(receiptCode: Long): StatusJpaEntity?

    /**
     * 접수번호로 상태 엔티티를 삭제합니다.
     *
     * @param receiptCode 삭제할 접수번호
     */
    fun deleteByReceiptCode(receiptCode: Long)
}
