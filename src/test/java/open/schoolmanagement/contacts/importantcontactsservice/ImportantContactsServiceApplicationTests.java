package open.schoolmanagement.contacts.importantcontactsservice;

import static org.mockito.MockitoAnnotations.initMocks;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = { ImportantContactsServiceApplication.class })
public class ImportantContactsServiceApplicationTests {

  @Before
	public void beforeTestCase() {
		initMocks(this);
	}

	@Test
  @Ignore
	public void contextLoads() {
	}

}
