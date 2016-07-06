package uz.alano.notebook.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
@Import({DbConfigProperties.class, SecurityConfig.class})
public class ApplicationConfig {
    private @Value("${jdbc.driverClassName}") String driverClassName;
    private @Value("${jdbc.url}") String jdbcUrl;
    private @Value("${jdbc.username}") String username;
    private @Value("${jdbc.password}") String password;

    @Bean
    public DriverManagerDataSource dataSource(){
        DriverManagerDataSource source = new DriverManagerDataSource();
        source.setDriverClassName(driverClassName);
        source.setUsername(username);
        source.setPassword(password);
        source.setUrl(jdbcUrl);

        return source;
    }

    @Bean
    public JdbcTemplate jdbcTemplate(){
        return new JdbcTemplate(dataSource());
    }
}