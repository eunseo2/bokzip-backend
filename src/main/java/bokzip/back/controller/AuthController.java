package bokzip.back.controller;

import bokzip.back.config.SuccessResponse;
import bokzip.back.dto.Token;
import bokzip.back.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequiredArgsConstructor
@RestController
public class AuthController {
    private final TokenService tokenService;

    @GetMapping("/token/refresh")
    public String refreshAuth(HttpServletRequest request, HttpServletResponse response) {
        String token = request.getHeader("Refresh");

        if (token != null && tokenService.verifyToken(token)) {
            String email = tokenService.getUid(token);
            Token newToken = tokenService.generateToken(email, "USER");

            response.addHeader("Auth", newToken.getToken());
            response.addHeader("Refresh", newToken.getRefreshToken());
            response.setContentType("application/json;charset=UTF-8");

            return "NEW TOKEN";
        }

        throw new RuntimeException("500");
    }

    // @see : 우선 OAuth2.0 인증 실패 시 해당 url을 redirect 하여 401 에러를 반환하도록 구현
    @GetMapping("/oauth2/failed")
    public void failedOAuth2Login() {
        throw new RuntimeException("401");
    }

}
