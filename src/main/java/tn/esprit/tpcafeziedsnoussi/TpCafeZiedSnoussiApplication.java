package tn.esprit.tpcafeziedsnoussi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class TpCafeZiedSnoussiApplication {

    public static void main(String[] args) {
        SpringApplication.run(TpCafeZiedSnoussiApplication.class, args);
    }

}
