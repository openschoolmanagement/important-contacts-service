package open.schoolmanagement.contacts.importantcontactsservice;

import static org.mockito.MockitoAnnotations.initMocks;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ImportantContactsServiceApplication.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class ImportantContactsServiceApplicationTests {

  @Before
  public void beforeTestCase() {
    initMocks(this);
  }

  @Test
  public void contextLoads() {
  }

}
