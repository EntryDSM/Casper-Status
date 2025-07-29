package hs.kr.entrydsm.status.global.error

import hs.kr.entrydsm.status.global.error.exception.CasperException
import io.github.resilience4j.circuitbreaker.CallNotPermittedException
import org.springframework.context.MessageSource
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

/**
 * 애플리케이션의 전역 예외 처리를 담당하는 클래스입니다.
 * 모든 컨트롤러에서 발생하는 예외를 처리하여 일관된 오류 응답을 제공합니다.
 *
 * @property messageSource 국제화 메시지 소스
 */
@RestControllerAdvice
class GlobalExceptionHandler(
    private val messageSource: MessageSource
) {

    /**
     * Casper 애플리케이션의 커스텀 예외를 처리합니다.
     *
     * @param e CasperException 인스턴스
     * @return 에러 코드에 따른 응답 엔티티
     */
    @ExceptionHandler(CasperException::class)
    fun handlingCasperException(e: CasperException): ResponseEntity<ErrorResponse> {
        val code = e.errorCode
        return ResponseEntity(
            ErrorResponse(code.status, code.message),
            HttpStatus.valueOf(code.status)
        )
    }

    /**
     * 호출 불가 예외를 처리합니다.
     *
     * @param e CallNotPermittedException 인스턴스
     * @return 500 에러 응답
     */
    @ExceptionHandler(CallNotPermittedException::class)
    fun handleCallNotPermittedException(e: CallNotPermittedException): ResponseEntity<ErrorResponse> {
        return ResponseEntity(
            ErrorResponse(500, "server error"),
            HttpStatus.valueOf(500)
        )
    }

    /**
     * 유효성 검증 실패 예외를 처리합니다.
     *
     * @param e MethodArgumentNotValidException 인스턴스
     * @return 400 에러 응답
     */
    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun validatorExceptionHandler(e: MethodArgumentNotValidException): ResponseEntity<ErrorResponse> {
        return ResponseEntity(
            ErrorResponse(
                400,
                e.bindingResult.allErrors[0].defaultMessage
            ),
            HttpStatus.BAD_REQUEST
        )
    }
}
