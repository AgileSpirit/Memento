package io.memento.infra.config;

import javax.sql.DataSource;
import java.net.URISyntaxException;

public interface EnvironmentConfig {

    DataSource dataSource() throws URISyntaxException;

}
