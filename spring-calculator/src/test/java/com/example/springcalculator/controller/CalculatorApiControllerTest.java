package com.example.springcalculator.controller;

import com.example.springcalculator.component.Calculater;
import com.example.springcalculator.component.DollarCalculator;
import com.example.springcalculator.component.MarketApi;
import com.example.springcalculator.dto.Req;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
// import org.springframework.boot.test.context.SpringBootTest;

@WebMvcTest(CalculatorApiController.class) // Web에 필요한 것만 등록
// @SpringBootTest // 모든 Bean을 등록: 자원소비가 많음.
@AutoConfigureWebMvc
@Import({Calculater.class, DollarCalculator.class})
public class CalculatorApiControllerTest {
    @MockBean
    private MarketApi marketApi;

    @Autowired
    private MockMvc mockMvc; // Mvc를 mocking으로 테스트 할 때 사용

    @BeforeEach
    public void init() {
        Mockito.when(marketApi.connect()).thenReturn(3000);
    }

    // Get 방식의 테스트
    @Test
    public void sumTest() throws Exception {

        mockMvc.perform(
                MockMvcRequestBuilders.get("http://localhost:8080/api/sum")
                        .queryParam("x", "10")
                        .queryParam("y", "10")
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        ).andExpect(
                MockMvcResultMatchers.content().string("60000")
        ).andDo(MockMvcResultHandlers.print());
    }

    // Post 방식의 테스트
    @Test
    public void minusTest() throws Exception {
        Req req = new Req(10, 10);
        String json = new ObjectMapper().writeValueAsString(req);
        mockMvc.perform(
                MockMvcRequestBuilders.post("http://localhost:8080/api/minus")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.result").value("0") // json 을 테스트 하기 위한 용도
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.response.resultCode").value("OK") // depth가 있는 json을 테스트 하기 위한 용도
        ).andDo(
                MockMvcResultHandlers.print()
        );
    }

}
