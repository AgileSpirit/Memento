package io.memento.infra.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;

import javax.sql.DataSource;
import java.net.URISyntaxException;

@Configuration
@PropertySource("classpath:environment/application-integration.properties")
@Profile("integration")
public class IntegrationConfig implements EnvironmentConfig {

    @Bean
    public DataSource dataSource() throws URISyntaxException {
        final JndiDataSourceLookup dsLookup = new JndiDataSourceLookup();
        dsLookup.setResourceRef(true);
        DataSource dataSource = dsLookup.getDataSource("java:comp/env/jdbc/mydb");
        return dataSource;
    }

}
