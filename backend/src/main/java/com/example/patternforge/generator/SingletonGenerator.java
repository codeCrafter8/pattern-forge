package com.example.patternforge.generator;

import lombok.Getter;
import org.springframework.stereotype.Component;

@Getter
@Component
public class SingletonGenerator implements PatternGenerator {

    String name = "singleton";

    @Override
    public String generateCode() {
        return """
                public class Singleton {
                    private static volatile Singleton instance;
                
                    private Singleton() {}
                
                    public static Singleton getInstance() {
                        if (instance == null) {
                            synchronized (Singleton.class) {
                                if (instance == null) {
                                    instance = new Singleton();
                                }
                            }
                        }
                        return instance;
                    }
                
                    public void doSomething() {
                        System.out.println("Singleton działa!");
                    }
                }
                """;
    }
}
