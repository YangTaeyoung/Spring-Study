package com.example.exception.advice;

import com.example.exception.controller.ApiController;
import com.example.exception.dto.Error;
import com.example.exception.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import javax.validation.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

// 일반 컨트롤러의 경우 @ControllerAdvice 어노테이션 사용.
// RestController인 경우 @RestControllerAdvice 어노테이션을 사용하여 예외를 처리할 수 있다.
// basePackageClasses에 클래스를 넣으면 해당 클래스에서 발생하는 예외만 처리하게 할 수 있다.
@RestControllerAdvice(basePackageClasses = ApiController.class)
public class ApiControllerAdvice {
    // 모든 에러를 처리하고 싶을 때 사용
    // @ExceptHandler: value에 처리할 예외를 기재한다.
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity exception(Exception e) {
        System.out.println(e.getClass().getName());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("");
    }

    // 특정 에러를 잡고 싶을 때 사용.
    // Exception Handler를 특정 클래스 내에 넣으면 해당 클래스 내에서 발생하는 오류만 잡게 된다.
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity methodArgumentNotValidException(MethodArgumentNotValidException e, HttpServletRequest httpServletRequest) {
        BindingResult bindingResult = e.getBindingResult();
        List<Error> errorList = new ArrayList<>();

        bindingResult.getAllErrors().forEach(error -> {
            FieldError field = (FieldError) error;
            String fieldName = field.getField();
            String message = field.getDefaultMessage();
            String value = field.getRejectedValue().toString();

            Error errorMessage = new Error();
            errorMessage.setField(fieldName);
            errorMessage.setMessage(message);
            errorMessage.setInvalidValue(value);
            errorList.add(errorMessage);
        });

        ErrorResponse errorResponse = new ErrorResponse();

        errorResponse.setMessage(e.getMessage());
        errorResponse.setRequestUrl(httpServletRequest.getRequestURI());
        errorResponse.setStatusCode(HttpStatus.BAD_REQUEST.toString());
        errorResponse.setErrorList(errorList);
        errorResponse.setResultCode("FAIL");

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    // get 방식 중 파라미터를 valid한지 검사하는 과정에서 에러가 난 경우 처리하는 것,
    @ExceptionHandler(value = ConstraintViolationException.class)
    public ResponseEntity constraintViolationException(ConstraintViolationException e, HttpServletRequest httpServletRequest) {
        List<Error> errorList = new ArrayList<>();
        // ConstraintViolationException 클래스의 인스턴스의 경우 어떤 필드에서 에러가 났는지에 대한 정보를 갖고 있음.
        e.getConstraintViolations().forEach(error -> {
            Stream<Path.Node> stream = StreamSupport.stream(error.getPropertyPath().spliterator(), false);
            List<Path.Node> list = stream.collect(Collectors.toList());
            String field = list.get(list.size() - 1).getName();
            String message = error.getMessage();
            String invalidValue = error.getInvalidValue().toString();

            Error errorMessage = new Error();
            errorMessage.setField(field);
            errorMessage.setMessage(message);
            errorMessage.setInvalidValue(invalidValue);
            errorList.add(errorMessage);
        });

        ErrorResponse errorResponse = new ErrorResponse();

        errorResponse.setMessage(e.getMessage());
        errorResponse.setRequestUrl(httpServletRequest.getRequestURI());
        errorResponse.setStatusCode(HttpStatus.BAD_REQUEST.toString());
        errorResponse.setErrorList(errorList);
        errorResponse.setResultCode("FAIL");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(value = MissingServletRequestParameterException.class)
    public ResponseEntity missingServletRequestParameterException(MissingServletRequestParameterException e, HttpServletRequest httpServletRequest) {
        List<Error> errorList = new ArrayList<>();
        String fieldName = e.getParameterName();
        String type = e.getParameterType();
        String invalidValue = e.getMessage();

        Error errorMessage = new Error();
        errorMessage.setField(fieldName);
        errorMessage.setMessage(e.getMessage());
        errorList.add(errorMessage);

        ErrorResponse errorResponse = new ErrorResponse();

        errorResponse.setMessage(e.getMessage());
        errorResponse.setRequestUrl(httpServletRequest.getRequestURI());
        errorResponse.setStatusCode(HttpStatus.BAD_REQUEST.toString());
        errorResponse.setErrorList(errorList);
        errorResponse.setResultCode("FAIL");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

}
