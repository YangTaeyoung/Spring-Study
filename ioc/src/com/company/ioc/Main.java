package com.company.ioc;

public class Main {

    public static void main(String[] args) {
        String url = "www.naver.com/books/it?page=10&size=20&name=spring-boot";

        // Base 64 encoding
//        IEncoder encoder = new Base64Encoder();
//        String result = encoder.encode(url);
//
//        System.out.println(result);
//
//        UrlEncoder urlEncoder = new UrlEncoder();
//        String urlResult = urlEncoder.encode(url);
//
//        System.out.println(urlResult);

        // - 의존성 주입 (Dependency Injection)
        // > 외부에서 사용하는 객체를 주입받는 것
        Encoder encoder = new Encoder(new UrlEncoder());
        String result = encoder.encode(url);
        System.out.println(result);

    }
}
