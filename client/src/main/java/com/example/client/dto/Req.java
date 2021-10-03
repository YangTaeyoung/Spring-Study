package com.example.client.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Req<T> {
    private Header header;
    private T resBody;

    @Data
    public static class Header{
        private String responseCode;
    }
}
