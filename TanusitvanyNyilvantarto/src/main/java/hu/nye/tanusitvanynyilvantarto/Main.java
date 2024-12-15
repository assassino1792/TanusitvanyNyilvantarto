package hu.nye.tanusitvanynyilvantarto;

import hu.nye.tanusitvanynyilvantarto.service.RiasztasService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.Map;


@SpringBootApplication
@EnableScheduling
public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
        logger.info("Alkalmazás sikeresen elindult!");

    }
}
