import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class DollarCalculatorTest {

    // - Mock
    // > 테스트 시 매번 결과가 바뀌어야 하는 테스트가 있음.
    // > 그런 테스트는 값이 매번 바뀌기 떄문에 assertEqual로 검사하면 에러가 날 수 있음.
    // > 이를 방지하기 위해 실제 함수는 그대로 동작하게 두고 테스트 시에만 일정 함수의 리턴을 고정시킬 수 있는데
    // > 이를 mockito를 이용해서 함.
    // > 실행하기 위해서는 maven repository에서 mockito core와 mocket juit jupitor 의존성을 gradle에 설치하여야 함.
    @Mock
    MarketApi marketApi;

    @BeforeEach
    public void init(){
        Mockito.lenient().when(marketApi.connect()).thenReturn(3000);
    }

    @Test
    public void testHello(){
        System.out.println("test Hello");
    }

    @Test
    public void mockTest(){
        DollarCalculator dollarCalculator = new DollarCalculator(marketApi);
        dollarCalculator.init();

        Calculater calculater = new Calculater(dollarCalculator);
        Assertions.assertEquals(60000, calculater.sum(10,10));
        Assertions.assertEquals(0, calculater.minus(10,10));
    }
}
