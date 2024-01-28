package mk.ukim.finki.wineryservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class WineryServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(WineryServiceApplication.class, args);
    }

}
