package hs.kr.entrydsm.status.global.security

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain
import org.springframework.web.cors.CorsConfiguration

/**
 * Spring Security 설정 클래스입니다.
 * 애플리케이션의 보안 정책과 인증/인가 규칙을 정의합니다.
 *
 * @property objectMapper JSON 직렬화/역직렬화를 위한 ObjectMapper
 */
@Configuration
class SecurityConfig(
    private val objectMapper: ObjectMapper,
) {

    companion object {
        private const val ADMIN_ROLE = "ADMIN"
    }

    /**
     * Spring Security 필터 체인을 구성합니다.
     * HTTP 보안 설정 및 경로별 접근 권한을 정의합니다.
     *
     * @param http HttpSecurity 객체
     * @return 구성된 SecurityFilterChain
     */
    @Bean
    protected fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .csrf { it.disable() }
            .cors { }
            .formLogin { it.disable() }
            .sessionManagement {
                it.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            }
            .authorizeHttpRequests { auth ->
                auth
                    .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                    .requestMatchers("/internal/status/**").hasRole(ADMIN_ROLE)
                    .requestMatchers("/admin/**").hasRole(ADMIN_ROLE)
                    .requestMatchers("/swagger-ui/**").permitAll()
                    .requestMatchers("/v3/api-docs/**").permitAll()
                    .requestMatchers("/swagger-resources/**").permitAll()
                    .requestMatchers("/webjars/**").permitAll()
                    .anyRequest().authenticated()
            }
            .with(FilterConfig(objectMapper)) { }

        return http.build()
    }
}
