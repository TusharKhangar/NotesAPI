package org.example.notesapi.misc;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class ConnectionCheck implements CommandLineRunner{
    Environment env   ;
    public ConnectionCheck(Environment env) {
        this.env = env;
    }
    @Override
    public void run(String... args) throws Exception {
        System.out.println("ConnectionCheck started "+ "DB Username : "+ env.getProperty("DB_USERNAME"));
    }
}
