package group11.EventFiesta;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class EventFiestaApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	private void getAppNameTest() {
		String appName = EventFiestaApplication.getAppName();
		Assertions.assertEquals(appName, "Event Fiesta");
	}

}
