package hs.kr.entrydsm.status.domain.status.adapter.out.persistence.repository

import hs.kr.entrydsm.status.domain.status.adapter.out.domain.StatusCache
import org.springframework.data.repository.CrudRepository

/**
 * 상태 캐시를 위한 Redis 저장소 인터페이스입니다.
 * Spring Data Redis를 통해 상태 정보의 캐시 관리를 담당합니다.
 */
interface StatusCacheRepository : CrudRepository<StatusCache, Long>
