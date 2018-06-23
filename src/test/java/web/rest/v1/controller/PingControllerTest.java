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

package web.rest.v1.controller;

import static open.schoolmanagement.contacts.importantcontactsservice.config.Constants.API_V1;
import static open.schoolmanagement.contacts.importantcontactsservice.config.Constants.CONTACT_SERVICE;
import static open.schoolmanagement.contacts.importantcontactsservice.config.Constants.PING_URI;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import open.schoolmanagement.contacts.importantcontactsservice.web.rest.v1.controller.PingController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {PingController.class})
@WebAppConfiguration
public class PingControllerTest {
  @Autowired
  private WebApplicationContext context;

  private MockMvc mvc;

  @Before
  public void setupTestcase() {
    mvc = MockMvcBuilders.webAppContextSetup(context).build();
  }

  @Test
  public void calling_ping_is_successful() throws Exception {
    MockHttpServletResponse response = mvc
        .perform(get(API_V1 + CONTACT_SERVICE + PING_URI))
        .andReturn()
        .getResponse();

    assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    assertThat(response.getContentAsString()).isEqualTo("42");
  }

  @Test
  public void calling_ping_with_csrf_is_not_returning_token() throws Exception {
    MockHttpServletResponse response = mvc
        .perform(get(API_V1 + CONTACT_SERVICE + PING_URI).with(csrf().asHeader()))
        .andReturn()
        .getResponse();

    assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    assertThat(response.getContentAsString()).isEqualTo("42");
    assertThat(response.getHeaderNames()).doesNotContain("X-CSRF-Token");
  }
}
