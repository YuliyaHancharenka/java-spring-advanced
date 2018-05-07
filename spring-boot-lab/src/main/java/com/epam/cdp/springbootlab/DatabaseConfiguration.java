package com.epam.cdp.springbootlab;

import com.mysql.cj.jdbc.MysqlDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@Configuration
public class DatabaseConfiguration {

    @Bean
    DataSource dataSource() throws SQLException {
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setUser("root");
        dataSource.setPassword("");
        dataSource.setServerName("localhost");
        dataSource.setServerTimezone("UTC");
        dataSource.setDatabaseName("my-cart");
        return dataSource;
    }

    @Bean
    Boolean bugGenerator(DataSource dataSource) throws SQLException {
        Statement statement = dataSource.getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery("select count(*) from good");
        resultSet.next();
        Long result = resultSet.getLong(1);
        if(result > 1000) {
            throw new RuntimeException("bug generator");
        }
        return true;
    }
}
