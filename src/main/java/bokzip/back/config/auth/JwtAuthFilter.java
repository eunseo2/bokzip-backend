package bokzip.back.config.auth;

import bokzip.back.dto.Provider;
import bokzip.back.dto.UserDto;
import bokzip.back.service.TokenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;

@Slf4j
@RequiredArgsConstructor
public class JwtAuthFilter extends GenericFilterBean {

    private final TokenService tokenService;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String token = ((HttpServletRequest)request).getHeader("Auth");

        // 토큰이 존재하거나 토큰이 유효하다면
        if (token != null && tokenService.verifyToken(token)) {
            String email = tokenService.getUid(token);
            String name = tokenService.getUid(token);
            String profile = tokenService.getUid(token);

            UserDto userDto = UserDto.builder()
                    .email(email)
                    .name(name)
                    .profile(profile).build();

            log.info(userDto.toString());

            Authentication auth = getAuthentication(userDto); //인증 객체 생성
            SecurityContextHolder.getContext().setAuthentication(auth); //인증 정보를 보관(set~)
            // @see : 꺼내올 때 아래 방법으로 꺼내야 함
            //Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        }
        chain.doFilter(request, response);
    }
    public Authentication getAuthentication(UserDto member) {
        return new UsernamePasswordAuthenticationToken(member, "",
                Arrays.asList(new SimpleGrantedAuthority(Provider.GOOGLE.getTitle())));
    }
}
