package controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import repository.*;
import service.Service;


import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

@Configuration
public class Config {
    @Bean
    Properties getProps() {
        Properties props = new Properties();
        try{
            System.out.println("Searching bd.config in directory " + ((new File(".")).getAbsolutePath()));
            props.load(new FileReader("C:\\iss\\i\\src\\bd.config"));
        } catch (IOException e) {
            System.out.println("Configuration file bd.config not found " + e);
        }
        return props;
    }

    @Bean
    RepositoryAngajat repositoryAngajat(){
        return new RepoAngajat(getProps());
    }


    @Bean
    Service services(){
        return new Service(repositoryAngajat());
    }

}
