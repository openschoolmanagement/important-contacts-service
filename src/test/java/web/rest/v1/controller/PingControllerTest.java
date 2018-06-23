package web.rest.v1.controller;

import static open.schoolmanagement.contacts.importantcontactsservice.config.Constants.API_V1;
import static open.schoolmanagement.contacts.importantcontactsservice.config.Constants.CONTACT_SERVICE;
import static open.schoolmanagement.contacts.importantcontactsservice.config.Constants.PING_URI;
import static org.assertj.core.api.Assertions.assertThat;
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
}
