package oslomet.webprog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class WebprogApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebprogApplication.class, args);
    }

    @PostConstruct
    private void init() {
        // Denne kjører ved oppstart. Kan legge inn kode her!
        // ...
    }
}
