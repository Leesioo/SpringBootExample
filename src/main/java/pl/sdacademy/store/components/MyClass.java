package pl.sdacademy.store.components;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class MyClass {

    private final MyPlainClass myPlainClass;

    @Autowired
    public MyClass(MyPlainClass myPlainClass) {
        this.myPlainClass = myPlainClass;
        System.out.println("MyClass constructor");
    }

    @PostConstruct
    public void normalMethodWhichDoNothing() {
        myPlainClass.hello();
    }
}
