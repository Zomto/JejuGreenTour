package com.jejugreentour.jgt.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
//@Bean
//    public PasswordEncoder getPasswordEncoder(){
//        return new BCryptPasswordEncoder();    //인증과 인가에 대해 설정 메소드
//    }
@Bean
    public SecurityFilterChain filterChain(HttpSecurity security) throws Exception{
        security.csrf(csrf -> csrf.disable()) //csrf 공격에 대한 보안해지
                .authorizeHttpRequests(authorize ->
                            authorize.requestMatchers(
                                    "/"
                                    ,"/**"
                                    ,"/member/joinForm"
                                    ,"/member/loginForm"
                                    ,"/member/login"
                                    ,"/member/join"
                                    ,"/member/joinMember"
                                    ,"/member/checkId"
                                    ,"/member/verifyCode"
                                    ,"/member/editMember1"
                                    ,"/member/editMember2"
                                    ,"/member/changePw"
                                    ,"/member/changePwForm"
                                    ,"/member/find_idForm"
                                    ,"/member/findId"
                                    ,"/member/checkInfo"
                                    ,"/member/find_PwForm"
                                    ,"/member/findPw"
                                    ).permitAll()
                                    .anyRequest().authenticated()
                )
                //로그인 실행 방법
                .formLogin(login ->
                        login.loginPage("/member/loginForm")   //인증을 위한 로그인페이지 설정
                                .loginProcessingUrl("/member/login")//로그인을 처리하는url 설정
                                .usernameParameter("memberId") //로그인 시 id로 쓰이는 input
                                .passwordParameter("memberPw")
                                .defaultSuccessUrl("/member/loginResult", true) // 로그인 성공시 이동 url
                                .failureUrl("/member/loginResult") //로그인 실패시 이동 url
                ).logout(logout ->
                        // /logout 요청이 발생하면 logout을 시켜준다
                        logout.logoutUrl("/member/logout")
                                //로그아웃시 세션 정보를 초기화
                                .invalidateHttpSession(true)
                                .logoutSuccessUrl("/")
                );
                    return security.build();
    }
    @Bean
    public WebSecurityCustomizer configure(){
        return (web) -> web.ignoring()
                .requestMatchers(
                        "/js/**"
                        ,"/css/**"
                        ,"/img/**"
                        ,"/dist/**"
                        ,"/packages/interaction/**"
                );
    }
}
