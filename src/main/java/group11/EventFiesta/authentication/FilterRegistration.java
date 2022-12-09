package group11.EventFiesta.authentication;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;

@Configuration
public class FilterRegistration {

    @Bean
    public FilterRegistrationBean registerAuthenticationFilter() {

        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(authenticationFilter());
        registration.addUrlPatterns("/*");
        // add the urls which does not need authentication here
        registration.addInitParameter("EXCLUDE_URL_PATTERN",
                "/.*.jpg|/.*.css|/home|/organizer/login|/handleOrganizerLogin|/signup|/handleUserSignUp|/org/signup|/script/signup.js|/handleOrgSignUp|/favicon.ico|/model/Service/");
        registration.setName("authenticationFilter");
        registration.setOrder(1);
        return registration;
    }

    public Filter authenticationFilter() {
        return new AuthenticationFilter();
    }

}
