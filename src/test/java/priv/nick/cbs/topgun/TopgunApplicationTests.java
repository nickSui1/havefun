package priv.nick.cbs.topgun;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

import java.security.SecureRandom;
import java.util.Base64;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = { TopgunApplicationTests.class })
class TopgunApplicationTests {

	@Test
	void contextLoads() {
	}

	public static void main(String[] args) {
		SecureRandom random = new SecureRandom();
		byte[] bytes = new byte[32];
		random.nextBytes(bytes);
		String secret = Base64.getEncoder().encodeToString(bytes);
		System.out.println(secret);
	}

}
