package bokzip.back.config.auth;

import bokzip.back.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @see : Spring Security2 버전은 기본적으로 {도메인}/login/oauth2/code/{소셜서비스코드}로 리다이렉트 URL을 지원
 *        -> 별도의 리다이렉트 URL을 지원하는 Controller를 만들 필요 X
 */

@Configuration
@EnableWebSecurity //Spring Security 설정 활성화
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final OAuth2SuccessHandler oAuth2SuccessHandler;
    private final OAuth2FailureHandler oAuth2FailureHandler;
    private final TokenService tokenService;

    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()

                //세션 설정하지 않음
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                //api
                .and()
                .authorizeRequests() //url별 권한 관리 옵션 시작 //이게 있어야 antMatchers() 옵션 사용 가능
                    .antMatchers( "/token/**", "/post/center/**", "/post/centers", "/post/general/**", "/post/generals").permitAll() //해당 api는 권한 없어도 전체열람 가능
                //@TODO : 현재 구글로그인 후 scrap 불러오기 api 호출 시 새 토큰을 발급하는 url로 넘어가는데, 새 토큰은 받지 않고 에러 발생
                    .antMatchers("/post/scraps").hasAnyRole("GOOGLE", "KAKAO") //내부적으로 접두어 "ROLE_"이 붙음 (ROLE_로 붙어야 security가 접근)//@todo 거주지 url 추가
                    .anyRequest().authenticated()//설정된 값들 외 나머지 URL들은 인증된 사용자(로그인 유저)에게 허용
//                    .expressionHandler() //@todo Provider에 등록되지 않은 유저가 /post/scraps에 접근할 경우 예외 발생

                //oauth 로그인 시도
                .and()
                    .oauth2Login()
                    .successHandler(oAuth2SuccessHandler) //로그인 성공시 -> 이 안에서 토큰 생성
                    .failureHandler(oAuth2FailureHandler) //로그인 실패시 -> /oauth2/failed로 redirect되어 exception 호출
                    .userInfoEndpoint().userService(customOAuth2UserService);

        http.httpBasic();

        http.logout()
                .logoutSuccessUrl("/") //로그아웃 성공 시 /주소로 이동
                .invalidateHttpSession(true) //http 세션 초기화
                .deleteCookies("JSEESIONID");

        http.addFilterBefore(new JwtAuthFilter(tokenService), UsernamePasswordAuthenticationFilter.class);

        // @param : 예외처리 설정
//        http.exceptionHandling();
//        super.configure(http);
    }
}
