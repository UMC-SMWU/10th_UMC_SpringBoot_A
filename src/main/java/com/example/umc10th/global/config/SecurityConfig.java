package com.example.umc10th.global.config;

import com.example.umc10th.global.security.handler.AccessDeniedHandlerImpl;
import com.example.umc10th.global.security.handler.AuthenticationEntryPointImpl;
import com.example.umc10th.global.security.handler.LoginFailureHandler;
import com.example.umc10th.global.security.handler.LoginSuccessHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final AuthenticationEntryPointImpl authenticationEntryPoint;
    private final AccessDeniedHandlerImpl accessDeniedHandler;
    private final LoginSuccessHandler loginSuccessHandler;
    private final LoginFailureHandler loginFailureHandler;

    // BCrypt 비밀번호 인코더 Bean (솔트 자동 생성)
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)

                // ✅ Public / Private API 설정
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/api/users/signup",   // Public: 회원가입
                                "/api/auth/login",     // Public: 로그인
                                "/v3/api-docs/**",     // Public: Swagger
                                "/swagger-ui/**",
                                "/swagger-ui.html"
                        ).permitAll()
                        .anyRequest().authenticated() // Private: 그 외 모든 요청
                )

                // ✅ 폼 로그인 설정 (email/password)
                .formLogin(form -> form
                        .loginProcessingUrl("/api/auth/login")
                        .usernameParameter("email")
                        .passwordParameter("password")
                        .successHandler(loginSuccessHandler)
                        .failureHandler(loginFailureHandler)
                )

                // 로그아웃
                .logout(logout -> logout
                        .logoutUrl("/api/auth/logout")
                        .logoutSuccessHandler((req, res, auth) -> {
                            res.setStatus(200);
                            res.setContentType("application/json;charset=UTF-8");
                            res.getWriter().write(
                                    "{\"isSuccess\":true,\"code\":\"COMMON_200\",\"message\":\"로그아웃 성공\",\"result\":null}"
                            );
                        })
                )

                // ✅ 인증/인가 실패 시 통일된 JSON 응답
                .exceptionHandling(ex -> ex
                        .authenticationEntryPoint(authenticationEntryPoint) // 401
                        .accessDeniedHandler(accessDeniedHandler)            // 403
                );

        return http.build();
    }
}