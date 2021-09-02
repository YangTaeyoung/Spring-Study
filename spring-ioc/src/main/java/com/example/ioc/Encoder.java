package com.example.ioc;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

//@Component

public class Encoder implements IEncoder {
    private IEncoder iEncoder;

    // Qualifier가 없으면 어떤 인코더인지 모르기 때문에 에러가 발생한다.
    // 이 경우 컴포넌트로 등록하지 않고 직접 bean에 등록할 수 있다.
    public Encoder(@Qualifier("base74Encoder") IEncoder iEncoder) {
        this.iEncoder = iEncoder;
    }
    public void setiEncoder(IEncoder iEncoder)
    {
        this.iEncoder = iEncoder;
    }
    @Override
    public String encode(String message) {
        return iEncoder.encode(message);
    }
}
