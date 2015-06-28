package org.iona.currency;


import org.iona.currency.nbt.NbtGet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ImportResource;

/**
 * Created by Bakhtiyor Ruziev on 21.06.15.
 * <p>
 * App
 */
@EnableAutoConfiguration
@SpringBootApplication
@ImportResource("context.xml")
public class App {


    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(App.class, args);
        NbtGet nbtGet = (NbtGet) context.getBean("nbtGet");
        nbtGet.setStartDate(2001);
        nbtGet.run();


    }


}
