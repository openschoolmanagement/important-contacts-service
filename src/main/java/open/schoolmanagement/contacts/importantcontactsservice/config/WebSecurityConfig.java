/*
   Copyright 2018 Open School Management

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
 */

package open.schoolmanagement.contacts.importantcontactsservice.config;

import open.schoolmanagement.contacts.importantcontactsservice.web.rest.v1.controller.PingController;
import open.schoolmanagement.contacts.importantcontactsservice.web.rest.v1.controller.cqrs.contact.command.CreateContact;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
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
  private static final RequestMatcher PUBLIC_URLS =
      new OrRequestMatcher(
          new AntPathRequestMatcher(PingController.URI, HttpMethod.GET.toString()),
          // TODO currently all endpoints are open, must be closes once user service is available
          new AntPathRequestMatcher(CreateContact.URI, HttpMethod.POST.toString()));

  @Override
  public void configure(WebSecurity web) throws Exception {
    web.ignoring().requestMatchers(PUBLIC_URLS);
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    // TODO currently all endpoints are open, must be closes once user service is available
  }
}