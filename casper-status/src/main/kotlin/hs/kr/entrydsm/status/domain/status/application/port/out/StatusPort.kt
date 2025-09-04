package hs.kr.entrydsm.status.domain.status.application.port.out

/**
 * 상태 관련 모든 포트 인터페이스를 통합한 포트입니다.
 * 상태 데이터의 CRUD 작업을 위한 모든 인터페이스를 상속받습니다.
 */
interface StatusPort : DeleteStatusPort, QueryStatusPort, SaveStatusPort
