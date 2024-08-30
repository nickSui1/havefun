package priv.nick.cbs.topgun.config.security.serviceImpl;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import priv.nick.cbs.topgun.dto.response.ResponseInfo;

import java.io.IOException;

/**
 * @author nick.sui
 * @since 2024-08-27
 * 自定义身份验证失败返回
 */
public class CustomAuthenticationEntryPointImpl implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType("application/json;charset=utf-8");

        ResponseInfo responseInfo = new ResponseInfo();
        responseInfo.setMessage("Authentication Failed");
        responseInfo.setStatus(401);
        ObjectMapper objectMapper = new ObjectMapper();

        objectMapper.writeValue(response.getWriter(), responseInfo);
    }
}
