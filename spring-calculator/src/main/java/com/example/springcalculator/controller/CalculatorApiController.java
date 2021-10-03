package com.example.springcalculator.controller;

import com.example.springcalculator.component.Calculater;
import com.example.springcalculator.component.ICalculator;
import com.example.springcalculator.dto.Req;
import com.example.springcalculator.dto.Res;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CalculatorApiController {
    private final Calculater calculater;

    @GetMapping("/sum")
    public int sum(@RequestParam int x, @RequestParam int y) {
        return calculater.sum(x, y);
    }

    @PostMapping("/minus")
    public Res minus(@RequestBody Req req) {
        int result = calculater.minus(req.getX(), req.getY());
        Res res = new Res();
        res.setResult(result);
        res.setResponse(new Res.Body());

        return res;
    }

}
