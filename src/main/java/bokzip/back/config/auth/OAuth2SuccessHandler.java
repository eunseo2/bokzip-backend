package bokzip.back.config.auth;

import bokzip.back.config.CommonResponse;
import bokzip.back.dto.Token;
import bokzip.back.dto.UserDto;
import bokzip.back.dto.UserRequestMapper;
import bokzip.back.service.TokenService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Slf4j
@RequiredArgsConstructor
@Component
public class OAuth2SuccessHandler implements AuthenticationSuccessHandler {

    private final TokenService tokenService;
    private final UserRequestMapper userRequestMapper;
    private final ObjectMapper objectMapper;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        OAuth2User oAuth2User = (OAuth2User)authentication.getPrincipal(); //보호받는 OAuth2.0 resource에 접근
        UserDto userDto = userRequestMapper.toDto(oAuth2User); //로그인한 OAuth2.0 정보를 이용하여 userDto 생성

        // @param : 최초 로그인이라면 토큰을 생성
        Token token = tokenService.generateToken(userDto.getEmail(), "USER");
        log.info("{}", token); //@deprecated 확인용

//        writeTokenResponse(response, token);

        new ResponseEntity<>(CommonResponse.res("로그인을 성공하였습니다.", token.getToken(), token.getRefreshToken()), HttpStatus.OK); //안뜸 ...

        // @see : 성공시 Spring Security가 자동으로 {도메인}/login/oauth2/code/{소셜서비스코드}로 redirect 함
    }

//    // @param : 화면에 display용
//    // @deprecated
//    private void writeTokenResponse(HttpServletResponse response, Token token) throws IOException{
//        response.setContentType("text/html;charset=UTF-8");
//
//        response.addHeader("Auth", token.getToken());
//        response.addHeader("Refresh", token.getRefreshToken());
//        response.setContentType("application/json;charset=UTF-8");
//
//
//        PrintWriter writer = response.getWriter(); //클라이언트에 응답하기 위한 출력 스트림을 반환
//        writer.println(objectMapper.writeValueAsString(token));
//        writer.flush();
//        writer.close();
//    }
}
