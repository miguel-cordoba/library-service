package com.miguelcordoba.LibraryService.init;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.Statement;

@Component
public class DataInitializer  { //implements ApplicationRunner

    private final DataSource dataSource;

    public DataInitializer(DataSource dataSource) {
        this.dataSource = dataSource;
    }
/*
    //We are using this class to make sure that data is not injected before the JPA schema is fully initialized
    @Override
    public void run(ApplicationArguments args) throws Exception {
        Resource resource = new ClassPathResource("data.sql");
        String sql = new String(Files.readAllBytes(resource.getFile().toPath()), StandardCharsets.UTF_8);

        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            statement.execute(sql);
        }
    }*/
}
