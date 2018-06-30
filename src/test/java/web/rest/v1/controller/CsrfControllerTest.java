package web.rest.v1.controller;

import static open.schoolmanagement.contacts.importantcontactsservice.config.Constants.API_V1;
import static open.schoolmanagement.contacts.importantcontactsservice.config.Constants.CONTACT_SERVICE;
import static open.schoolmanagement.contacts.importantcontactsservice.config.Constants.CSRF_URI;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.anonymous;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import open.schoolmanagement.contacts.importantcontactsservice.ImportantContactsServiceApplication;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
//@ContextConfiguration(classes = {ImportantContactsServiceApplication.class})
@SpringBootTest(classes = ImportantContactsServiceApplication.class)
@WebAppConfiguration
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class CsrfControllerTest {
  @Autowired
  private WebApplicationContext context;

  private MockMvc mvc;

  @Before
  public void setupTestcase() {
    mvc = MockMvcBuilders.webAppContextSetup(context).build();
  }

  @Test
  public void unauthorized_access_fails() throws Exception {
    MockHttpServletResponse response = mvc
        .perform(
            get(API_V1 + CONTACT_SERVICE + CSRF_URI)
                .with(anonymous())
                .header("X-CSRF-Token", "Fetch"))
        .andReturn()
        .getResponse();

    assertThat(response.getStatus()).isEqualTo(HttpStatus.NO_CONTENT.value());
    //assertThat(response.getHeaderNames()).contains("X-CSRF-Token");
  }
}
