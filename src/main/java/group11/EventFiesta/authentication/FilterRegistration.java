package group11.EventFiesta.authentication;

import group11.EventFiesta.application.PropertiesReader;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.Properties;

@Configuration
public class FilterRegistration {

    @Bean
    public FilterRegistrationBean registerAuthenticationFilter() {

        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(authenticationFilter());
        registration.addUrlPatterns("/*");
        // add the urls which does not need authentication here
        PropertiesReader propertiesReader = PropertiesReader.getInstance();
        Properties applicationProperties = propertiesReader.getProperties();
        System.out.println(applicationProperties);
        registration.addInitParameter("EXCLUDE_URL_PATTERN", applicationProperties.getProperty("event_fiesta.excluded_url"));
        registration.setName("authenticationFilter");
        registration.setOrder(1);
        return registration;
    }

    public Filter authenticationFilter() {
        return new AuthenticationFilter();
    }

}
