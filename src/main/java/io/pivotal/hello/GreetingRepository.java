package io.pivotal.hello;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GreetingRepository extends JpaRepository<Greeting, Long> {
    List<Greeting> findByLanguage(@Param("language") String language);
}
