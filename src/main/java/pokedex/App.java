package pokedex;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import pokedex.ui.AppUI;

import javax.swing.*;
import java.awt.*;

@SpringBootApplication
public class App {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = new SpringApplicationBuilder(App.class).headless(false).web(WebApplicationType.NONE).run(args);
    }
}