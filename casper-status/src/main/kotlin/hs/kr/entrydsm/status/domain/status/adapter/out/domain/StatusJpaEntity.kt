package hs.kr.entrydsm.status.domain.status.adapter.out.domain

import hs.kr.entrydsm.status.domain.status.model.ApplicationStatus
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

/**
 * 지원 상태 정보를 저장하는 JPA 엔티티 클래스입니다.
 * 데이터베이스의 status 테이블과 매핑되며, 지원자의 전형 진행 상태를 관리합니다.
 *
 * @property applicationStatus 현재 지원 상태
 * @property examCode 수험번호 (고유값)
 * @property isFirstRoundPass 1차 전형 합격 여부
 * @property isSecondRoundPass 2차 전형 합격 여부
 * @property receiptCode 접수번호
 * @property id 데이터베이스 기본키 (자동 생성)
 */
@Entity(name = "status")
class StatusJpaEntity(
    @Enumerated(EnumType.STRING)
    @Column(name = "application_status")
    var applicationStatus: ApplicationStatus = ApplicationStatus.NOT_APPLIED,
    @Column(unique = true)
    var examCode: String? = null,
    var isFirstRoundPass: Boolean = false,
    var isSecondRoundPass: Boolean = false,
    val receiptCode: Long
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0

}
