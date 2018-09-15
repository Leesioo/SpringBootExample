package pl.sdacademy.store.components;

import org.springframework.stereotype.Component;

@Component
public class MyCounter {
    private Integer counter = 0;

    public Integer increment() {
        counter++;
        return counter;
    }

    public Integer setCounter(Integer newCounter) {
        counter = newCounter;
        return counter;
    }

    public Integer getCounter() {
        return counter;
    }
}
