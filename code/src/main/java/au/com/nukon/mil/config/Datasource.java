package au.com.nukon.mil.config;

import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
public class Datasource {

    @Bean
    @Primary
    @ConfigurationProperties(prefix = "datasource.model")
    public DataSource primaryDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "camundaBpmDataSource")
    @ConfigurationProperties(prefix = "datasource.bpm")
    public DataSource secondaryDataSource() {
        return DataSourceBuilder.create().build();
    }
}
