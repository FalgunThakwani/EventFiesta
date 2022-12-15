package group11.EventFiesta.authentication;

import group11.EventFiesta.application.PropertiesReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.servlet.FilterRegistrationBean;

import java.util.Properties;

@SpringBootTest
public class FilterRegistrationTest {

    @Test
    public void registerAuthenticationTest() {

        FilterRegistration filterRegistration = new FilterRegistration();
        FilterRegistrationBean filterRegistrationBean = filterRegistration.registerAuthenticationFilter();

        Assertions.assertNotNull(filterRegistrationBean);
        Assertions.assertNotNull(filterRegistrationBean.getFilter());

        Assertions.assertEquals(true, filterRegistrationBean.getUrlPatterns().contains("/*"));

        String excludedParams = filterRegistrationBean.getInitParameters().getOrDefault("EXCLUDE_URL_PATTERN", "").toString();
        Properties applicationProperties = PropertiesReader.getInstance().getProperties();
        String expectedProperty = applicationProperties.getProperty("event_fiesta.excluded_url");
        Assertions.assertEquals(expectedProperty, excludedParams);

    }
}
