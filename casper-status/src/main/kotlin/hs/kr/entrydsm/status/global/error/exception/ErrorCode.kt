package hs.kr.entrydsm.status.global.error.exception

/**
 * 애플리케이션에서 발생하는 오류 코드를 정의하는 열거형 클래스입니다.
 * HTTP 상태 코드와 에러 메시지를 함께 관리합니다.
 *
 * @property status HTTP 상태 코드
 * @property message 에러 메시지
 */
enum class ErrorCode(
    val status: Int,
    val message: String,
) {
    /** 내부 서버 오류 */
    INTERNAL_SERVER_ERROR(500, "Internal Server Error"),

    /** 유효하지 않은 토큰 */
    INVALID_TOKEN(401, "Invalid Token"),

    /** 만료된 토큰 */
    EXPIRED_TOKEN(401, "Expired Token"),

    /** 상태를 찾을 수 없음 */
    STATUS_NOT_FOUND(404, "Status Not Found"),
}
