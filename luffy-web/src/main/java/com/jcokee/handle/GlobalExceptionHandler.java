package com.jcokee.handle;

import com.jcokee.pojo.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler({Exception.class})
    @ResponseBody
    public ResultVO exception(Exception e) {
        log.error(e.getMessage(), e);
        return new ResultVO(ResultVO.Status.ERROR, e.getMessage(),Constants.ERROR_CODE_OTHER);
    }

    @ExceptionHandler({NullPointerException.class})
    @ResponseBody
    public ResultVO nullException(Exception e) {
        log.error(e.getMessage(), e);
        return new ResultVO(ResultVO.Status.ERROR, "java.lang.NullPointerException",Constants.ERROR_CODE_OTHER);
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseBody
    public ResultVO methodArgumentNotValidException(MethodArgumentNotValidException e) {
        BindingResult result = e.getBindingResult();
        String errorMsg = null;
        if (result.hasErrors()) {
            List<FieldError> fieldErrors = result.getFieldErrors();
            errorMsg = fieldErrors.stream().map(FieldError::getDefaultMessage).collect(Collectors.joining("|"));
        }
        return new ResultVO(ResultVO.Status.ERROR, errorMsg, Constants.ERROR_CODE_BIZ);
    }

    static class Constants {
        public static final Integer ERROR_CODE_BIZ = 10000;
        public static final Integer ERROR_CODE_OTHER = 10001;
    }
}
