package priv.nick.cbs.topgun.config.exception;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import priv.nick.cbs.topgun.dto.response.ExceptionDTO;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.text.MessageFormat;

/**
 * @author nick.sui
 * @since 6/25/2024
 * 全局异常处理handler
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ExceptionDTO> defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
        if(AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class) != null) {
            throw e;
        }

        String localizedMessage = e.getCause()!=null?e.getCause().getLocalizedMessage():"";
        Writer buffer = new StringWriter();
        PrintWriter pw = new PrintWriter(buffer);
        e.printStackTrace(pw);
        logger.error(MessageFormat.format("An Error occurred and caught by {0}.defaultErrorHandler, source from:{1}, exception message:{2},\nstack trace:{3}" ,
                GlobalExceptionHandler.class.getSimpleName(), req.getRequestURL(),e.getMessage(),buffer));
        ExceptionDTO exceptionDTO = new ExceptionDTO(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(), localizedMessage);
        return new ResponseEntity<>(exceptionDTO, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = DataNotFoundException.class)
    public ResponseEntity<ExceptionDTO> dataNotFoundExceptionHandler(HttpServletRequest req, Exception e) throws Exception {
        if(AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class) != null) {
            throw e;
        }
        String localizedMessage = e.getCause()!=null?e.getCause().getLocalizedMessage():"";
        logger.error(MessageFormat.format("An Error occurred and caught by {0}.dataNotFoundExceptionHandler, source from:{1}, exception message:{2},\nstack trace:{3}" ,
                GlobalExceptionHandler.class.getSimpleName(), req.getRequestURL(),e.getMessage(),e));
        ExceptionDTO exceptionDTO = new ExceptionDTO(HttpStatus.NOT_FOUND.value(), e.getMessage(), localizedMessage);
        return new ResponseEntity<>(exceptionDTO, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionDTO> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException ex) throws Exception {
        BindingResult bindingResult = ex.getBindingResult();
        StringBuilder sb = new StringBuilder("Parameter Validation Failed:");
        for(FieldError fieldError : bindingResult.getFieldErrors()) {
            sb.append(fieldError.getField()).append(fieldError.getDefaultMessage()).append(", ");
        }
        String msg = sb.toString();
        return new ResponseEntity<>(new ExceptionDTO(HttpStatus.BAD_REQUEST.value(), msg, msg), HttpStatus.BAD_REQUEST);
    }
}
