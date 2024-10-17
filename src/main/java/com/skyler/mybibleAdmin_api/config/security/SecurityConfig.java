package com.skyler.mybibleAdmin_api.config.security;


import lombok.Getter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
public class SecurityConfig {

    @Getter
    private static final String[] permitAllAntPatterns = {
            "/swagger-ui/**",
            "/v3/api-docs/**",
            "/health-check"
//            "/api/auth/login",
    };

    @Bean
    public SecurityFilterChain filterChain (HttpSecurity http) throws Exception {

        http
                .csrf(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .authorizeHttpRequests(authorizeRequest ->
                        authorizeRequest
                                .requestMatchers(permitAllAntPatterns).permitAll()
                                .requestMatchers(AntPathRequestMatcher.antMatcher("/api/auth/**")).permitAll()
                                .anyRequest().authenticated()
                )
                /**
                 * Spring security에서 세션 관리 설정을 미사용으로 설정 (서버가 client 상태를 저장하지 않음. 매 요청마다 client가 필요한 정보를 제공해야 함.
                 * - 일반적으로 JWT와 같은 토큰 기반 인증 시스템에서 사용. (client가 서버에 요청을 보낼 때 JWT를 함께 전송하여 인증 정보를 전달)
                 * - 스케일링 용이함 (서버가 client 세션 정보를 저장하지 않기 때문에, 여러 서버에 분산 요청할 때 유리)
                 * - 보안 강화 (서버가 client 세션 정보를 저장하지 않기 때문에, 세션 고정 공격과 같은 취쟉점에 대한 공격 노출이 줄어듦.
                 */

                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                //h2 설정
                .headers(
                        headersConfigurer ->
                                headersConfigurer
                                        .frameOptions(
                                                HeadersConfigurer.FrameOptionsConfig::sameOrigin
                                        )
                )
//                .addFilterBefore(jwtAuthorizationFilter, BasicAuthenticationFilter.class)
        ;

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        /**
         * cors 설정: 웹 애플리케이션이 다른 출처(도메일, 프로토콜, 포트)의 리소스에 접근할 수 있도록 허용하는 방법
         * api 대한 요청을 다른 도메인에서 받을 수 있음.
         */
        CorsConfiguration config = new CorsConfiguration();

        config.setAllowCredentials(true);
        config.setAllowedOrigins(List.of("*"));
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS"));
        /**
         * client가 인증 정보를 포함하여 요청을 보낼 수 있도록 허용
         * - 쿠키나 http 인증 헤더를 포함한 요청이 가능해짐
         * - 이게 있어야 jwt 토큰이나 쿠키를 같이 보낼 수 있는 건가?
         */
        config.setAllowCredentials(true);
        /**
         * preflight에 대한 캐싱 시간 지정
         * - 브라우저가 preflight 요청을 한 후에 이 요청을 얼마나 오랫동안 캐시할지 설정
         * - 3600초 (1시간) 동안 같은 요청을 다시 보내지 않아도 됨을 의미
         */
        config.setMaxAge(3600L);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }

}
