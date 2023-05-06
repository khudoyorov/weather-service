package bek.ws.weatherservice.configuration;

import io.r2dbc.postgresql.PostgresqlConnectionConfiguration;
import io.r2dbc.postgresql.PostgresqlConnectionFactory;
import io.r2dbc.spi.ConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class DatabaseConfiguration extends AbstractR2dbcConfiguration {

    @Value("${database.name}")
    private String database;

    @Value("${database.host}")
    private String host;

    @Value("${database.port}")
    private int port;

    @Value("${database.username}")
    private String username;

    @Value("${database.password}")
    private String password;


    @Override
    public ConnectionFactory connectionFactory() {
        return new PostgresqlConnectionFactory(PostgresqlConnectionConfiguration.builder()
                .host(this.host)
                .port(this.port)
                .database(this.database)
                .username(this.username)
                .password(this.password)
                .build());
    }

    @Bean
    @Primary
    public DataSource postgres() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setUrl("jdbc:postgresql://" + host + ":" + port + "/" + database);
        return dataSource;
    }

}
