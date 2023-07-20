package com.cos.blog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.cos.blog.config.auth.PrincipalDetailService;

import jakarta.servlet.DispatcherType;

@Configuration // IoC
public class SecurityConfig {

	//@Autowired
	//private PrincipalDetailService principalDetailService;
	
	@Bean
	public BCryptPasswordEncoder encodePWD() {
		return new BCryptPasswordEncoder();
	}
	
	//시큐리티가 대신 로그인해주는데 password를 가로채기 하는데
	//해당 password가 뭘로 해쉬가 되어 회원가입이 되었는지 알아야
	//같은 해쉬로 암호화해서 DB에 있는 해쉬랑 비교할 수 있음
	
	//principalDetailService와 통신
	//Authentication 객체를 만들어 Sessin의 Security영역에 배치함
    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.csrf((csrf) -> csrf
        		.disable()); //csrf 토큰 비활성화, 원래는 요청시 csrf 토큰이 없으면 막아버림
        
		http.authorizeHttpRequests((requests) -> requests
        		.dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll()
        		.requestMatchers("/", "/auth/**", "/js/**", "css/**", "/image/**", "/dummy/**").permitAll()
        		.anyRequest().authenticated());
        
        http.formLogin((login) -> login
        		.loginPage("/auth/loginForm")
        		.loginProcessingUrl("/auth/loginProc")
        		.defaultSuccessUrl("/")); // 스프링 시큐리티가 해당 주소로 요청오는 로그인을 가로채서 대신 로그인 해준다.
        
        

		return http.build();
	}

}