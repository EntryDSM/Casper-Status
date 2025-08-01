package hs.kr.entrydsm.status.global.security.jwt

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.context.SecurityContextHolder.clearContext
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.web.filter.OncePerRequestFilter

/**
 * JWT 토큰 인증을 처리하는 필터 클래스입니다.
 * HTTP 요청 헤더에서 사용자 정보를 추출하여 Spring Security 컨텍스트에 설정합니다.
 */
class JwtFilter : OncePerRequestFilter() {
    
    /**
     * 요청마다 실행되는 필터 로직입니다.
     * 요청 헤더에서 사용자 ID와 역할을 추출하여 인증 컨텍스트를 설정합니다.
     *
     * @param request HTTP 요청
     * @param response HTTP 응답
     * @param filterChain 필터 체인
     */
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val userId: String? = request.getHeader("Request-User-Id")
        val role: UserRole? = request.getHeader("Request-User-Role")?.let { UserRole.valueOf(it) }

        if ((userId == null) || (role == null)) {
            filterChain.doFilter(request, response)
            return
        }

        val authorities = mutableListOf(SimpleGrantedAuthority("ROLE_${role.name}"))
        val userDetails: UserDetails = User(userId, "", authorities)
        val authentication: Authentication =
            UsernamePasswordAuthenticationToken(userDetails, "", userDetails.authorities)

        clearContext()
        SecurityContextHolder.getContext().authentication = authentication
        filterChain.doFilter(request, response)
    }
}

/**
 * 사용자 역할을 나타내는 열거형 클래스입니다.
 */
enum class UserRole {
    /** 최고 관리자 */
    ROOT,
    /** 관리자 */
    ADMIN,
    /** 일반 사용자 */
    USER
}
