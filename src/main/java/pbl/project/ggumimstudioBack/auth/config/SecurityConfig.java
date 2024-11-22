package pbl.project.ggumimstudioBack.auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig
{
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
    {
        http
                .csrf(AbstractHttpConfigurer::disable)                                 // CSRF 비활성화
                .formLogin(AbstractHttpConfigurer::disable)                            // Form 로그인 비활성화
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/admin/api/**").hasAuthority("ROLE_ADMIN") // /admin/api/** 는 ROLE_ADMIN 권한 필요
                        .requestMatchers("/shop/api/**").hasAuthority("ROLE_SHOP")   // /shop/api/** 는 ROLE_SHOP 권한 필요
                        .requestMatchers("/user/api/**").hasAuthority("ROLE_USER")   // /user/api/** 는 ROLE_USER 권한 필요
                        .anyRequest().permitAll()                                      // 그 외 요청은 모두 허용
                );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder(); // 비밀번호 암호화
    }
}
