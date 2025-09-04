package hs.kr.entrydsm.status.global.security

import com.fasterxml.jackson.databind.ObjectMapper
import hs.kr.entrydsm.status.global.error.GlobalExceptionFilter
import hs.kr.entrydsm.status.global.security.jwt.JwtFilter
import org.springframework.security.config.annotation.SecurityConfigurerAdapter
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.web.DefaultSecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.stereotype.Component

/**
 * Spring Security 필터 설정을 담당하는 클래스입니다.
 * JWT 필터와 예외 처리 필터를 Spring Security 필터 체인에 추가합니다.
 *
 * @property objectMapper JSON 직렬화/역직렬화를 위한 ObjectMapper
 */
@Component
class FilterConfig(
    private val objectMapper: ObjectMapper,
) : SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity>() {
    /**
     * Spring Security 필터 체인에 커스텀 필터들을 추가합니다.
     * JWT 필터를 인증 필터 앞에, 예외 처리 필터를 JWT 필터 앞에 배치합니다.
     *
     * @param builder HttpSecurity 빌더
     */
    override fun configure(builder: HttpSecurity) {
        builder.addFilterBefore(
            JwtFilter(),
            UsernamePasswordAuthenticationFilter::class.java,
        )
        builder.addFilterBefore(GlobalExceptionFilter(objectMapper), JwtFilter::class.java)
    }
}
