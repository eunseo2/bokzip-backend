package bokzip.back.config.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                    .antMatchers("/","/css/**","/images/**","/js/**").permitAll()
                    .antMatchers("/scraps/**").hasRole("GOOGLE")
                    .anyRequest().permitAll()
                    .and()
                .formLogin()
                    .defaultSuccessUrl("/")
                    .permitAll()
                    .and()
                .logout()
                    .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                    .logoutSuccessUrl("/logoutPage")
                    .invalidateHttpSession(true)
                    .clearAuthentication(true)
                    .deleteCookies("JSEESIONID")
                    .and()
                .oauth2Login()
                    .userInfoEndpoint()
                    .userService(customOAuth2UserService);
        http.httpBasic();
    }
}
