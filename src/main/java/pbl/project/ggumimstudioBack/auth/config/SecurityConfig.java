package pbl.project.ggumimstudioBack.auth.config;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import pbl.project.ggumimstudioBack.auth.jwt.filter.JwtAuthenticationFilter;

import java.util.Collections;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig
{
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
    {
        http
                .csrf(AbstractHttpConfigurer::disable)                                    // CSRF 비활성화
                .formLogin(AbstractHttpConfigurer::disable)                               // Form 로그인 비활성화
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)  // 필터에 JWT 필터 추가
                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers("/api/v1/admin/**").hasAuthority("ROLE_ADMIN") // /admin/api/** 는 ROLE_ADMIN 권한 필요
//                        .requestMatchers("/api/v1/shop/**").hasAuthority("ROLE_SHOP")   // /shop/api/** 는 ROLE_SHOP 권한 필요
//                        .requestMatchers("/api/v1/user/**").hasAuthority("ROLE_USER")   // /user/api/** 는 ROLE_USER 권한 필요
                        .anyRequest().permitAll()                                         // 그 외 요청은 모두 허용
                )
                .cors((corsCustomizer -> corsCustomizer.configurationSource(new CorsConfigurationSource() {
                    @Override
                    public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {

                        CorsConfiguration configuration = new CorsConfiguration();

                        configuration.setAllowedOriginPatterns(Collections.singletonList("*"));
                        configuration.setAllowedMethods(Collections.singletonList("*"));
                        configuration.setAllowCredentials(true);
                        configuration.setAllowedHeaders(Collections.singletonList("*"));
                        configuration.setMaxAge(3600L);

                        return configuration;
                    }
                })));

        ;

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder(); // 비밀번호 암호화
    }
}
