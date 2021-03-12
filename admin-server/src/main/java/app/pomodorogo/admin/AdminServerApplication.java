package app.pomodorogo.admin;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@EnableAdminServer
public class AdminServerApplication {
    public static void main(String[] args) {
//        SpringApplication.run(AdminServerApplication.class, args);
        new SpringApplicationBuilder(AdminServerApplication.class)
                .web(WebApplicationType.REACTIVE)
                .run(args);
    }
}
