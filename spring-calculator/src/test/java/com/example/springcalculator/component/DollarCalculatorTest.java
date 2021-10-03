package com.example.springcalculator.component;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;

@SpringBootTest // 테스트를 위해 클래스 앞에 어노테이션을 작성해야 함.
// @Import({MarketApi.class, DollarCalculator.class}) // 주입이 필요한 클래스를 Import를 통해 명시한다.
// SpringBootTest를 등록하면 모든 Bean이 등록되므로 Import는 필요없음
public class DollarCalculatorTest {
    @MockBean // Spring은 Bean으로 관리하기 때문에 @Mock가 아닌 @MockBean을 통해 명시하여야 한다.
    private MarketApi marketApi;
    @Autowired Calculater calculater;

    @Test
    public void dollarCalcultorTest(){

        Mockito.when(marketApi.connect()).thenReturn(3000);

        int sum = calculater.sum(10,10);
        int minus = calculater.minus(10,10);
        Assertions.assertEquals(60000, sum);
        Assertions.assertEquals(0,minus);
    }
}
