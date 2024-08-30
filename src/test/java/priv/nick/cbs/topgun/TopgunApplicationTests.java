package priv.nick.cbs.topgun;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = { TopgunApplicationTests.class })
class TopgunApplicationTests {

	@Test
	void contextLoads() {
	}

	public static void main(String[] args) {
		Integer a = null;
		Assert.notNull(a,"Integer a must not be null");
	}
}
