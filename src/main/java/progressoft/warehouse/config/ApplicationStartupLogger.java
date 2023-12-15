package progressoft.warehouse.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class ApplicationStartupLogger implements CommandLineRunner {

    private final Environment environment;

    public ApplicationStartupLogger(Environment environment) {
        this.environment = environment;
    }

    @Override
    public void run(String... args) {
        // Access and log specific application properties
        String dataSourceUrl = environment.getProperty("spring.datasource.url");
        String dataSourceUsername = environment.getProperty("spring.datasource.username");

        System.out.println("Datasource URL: " + dataSourceUrl);
        System.out.println("Datasource Username: " + dataSourceUsername);
    }
}