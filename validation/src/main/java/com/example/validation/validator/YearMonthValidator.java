package com.example.validation.validator;

import com.example.validation.annotation.YearMonth;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class YearMonthValidator implements ConstraintValidator<YearMonth, String> {

    private String pattern;

    // 패턴을 검증하고자 한다면 패턴을 초기화 해줘야 한다.
    @Override
    public void initialize(YearMonth constraintAnnotation) {
        this.pattern = constraintAnnotation.pattern();
    }

    // 실제로 유효한지 안 유효한지 검사하는 함수.
    // 입력 value는 어노테이션이 붙은 변수 값을 의미함.
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        try {
            LocalDate localDate = LocalDate.parse(value+"01", DateTimeFormatter.ofPattern(this.pattern));
        }catch(Exception e){
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }
}
