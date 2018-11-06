package io.pivotal.hello;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Log
@RefreshScope
@RestController
public class HelloController {

    @Value("${language}")
    private String language;

    private GreetingRepository repository;

    public HelloController(GreetingRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/")
    String get() {

        return "Greetings, your language is " + language;

    }

    @GetMapping("/greeting")
    String getGreeting() {
        List<Greeting> greetings = repository.findByLanguage(language);
        if(greetings.isEmpty()) {
            return "There was no greeting for language " + language;
        } else {
            return greetings.get(0).getText();
        }
    }

    @GetMapping("/language")
    String getLanguage() {
        log.info("Received request for language.  Returning: " + language);
        return language;
    }
}
