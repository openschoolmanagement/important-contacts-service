package open.schoolmanagement.contacts.importantcontactsservice.config;

import static open.schoolmanagement.contacts.importantcontactsservice.config.Constants.API_V1;
import static open.schoolmanagement.contacts.importantcontactsservice.config.Constants.CONTACT_SERVICE;
import static open.schoolmanagement.contacts.importantcontactsservice.config.Constants.PING_URI;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

/**
 * Security configuration.
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
  private static final RequestMatcher PUBLIC_URLS = new OrRequestMatcher(
      new AntPathRequestMatcher(API_V1 + CONTACT_SERVICE + PING_URI));

  @Override
  public void configure(WebSecurity web) throws Exception {
    web.ignoring().requestMatchers(PUBLIC_URLS);
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
        .authorizeRequests()
        .and()
        .csrf();
  }
}
