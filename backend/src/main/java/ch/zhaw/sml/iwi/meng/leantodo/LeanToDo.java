package ch.zhaw.sml.iwi.meng.leantodo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import ch.zhaw.sml.iwi.meng.leantodo.entity.Portfolio;
import ch.zhaw.sml.iwi.meng.leantodo.entity.PortfolioRepository;
import ch.zhaw.sml.iwi.meng.leantodo.entity.Transactions;
import ch.zhaw.sml.iwi.meng.leantodo.entity.TransactionsRepository;
import ch.zhaw.sml.iwi.meng.leantodo.entity.User;
import ch.zhaw.sml.iwi.meng.leantodo.entity.UserRepository;

@SpringBootApplication
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class LeanToDo implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(LeanToDo.class, args);
        
    }

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PortfolioRepository roleRepository;

    
    @Autowired
    private TransactionsRepository toDoRepository;

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                    // This is only really relevant for development, where we have different servers for frontend and backend
                    .allowedOrigins("http://localhost:8100")
                    .allowedMethods("GET", "PUT", "POST", "DELETE")
                    // AllowCredentials is necessary, as it sets 'Access-Control-Allow-Credentials'. 
                    // Otherwise Angular's HttpClient will not pass the Cookie back.
                    .allowCredentials(true);
            }
        };
    }

    @Override
    public void run(String... args) throws Exception {
        User u = new User();
        u.setLoginName("user");
        u.setPasswordHash(new BCryptPasswordEncoder().encode("user"));
        Portfolio r = new Portfolio();
        r.setRoleName("ROLE_USER");
        roleRepository.save(r);
        u.getRoles().add(r);
        userRepository.save(u);

        Transactions toDo = new Transactions();
        toDo.setTitle("Finish This app");
        toDo.setOwner("user");
        toDoRepository.save(toDo);

        toDo = new Transactions();
        toDo.setTitle("Reply to student");
        toDo.setOwner("user");
        toDoRepository.save(toDo);
    }
}
