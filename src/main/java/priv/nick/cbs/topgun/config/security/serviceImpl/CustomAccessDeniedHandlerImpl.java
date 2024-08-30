package priv.nick.cbs.topgun.config.security.serviceImpl;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import priv.nick.cbs.topgun.dto.response.ResponseInfo;

import java.io.IOException;

/**
 * @author nick.sui
 * @since 2024-08-26
 * 自定义拒绝访问处理类
 */
public class CustomAccessDeniedHandlerImpl implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        response.setContentType("application/json;charset=utf-8");
        response.setStatus(HttpStatus.FORBIDDEN.value());

        ResponseInfo responseInfo = new ResponseInfo();
        responseInfo.setMessage("Access Denied");
        responseInfo.setStatus(403);
        ObjectMapper objectMapper = new ObjectMapper();

        objectMapper.writeValue(response.getWriter(), responseInfo);
    }
}
