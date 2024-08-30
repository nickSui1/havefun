package priv.nick.cbs.topgun;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @MapperScan This annotation will be able to scan the specific package and generate bean require for access database.
 */
@SpringBootApplication(scanBasePackages = {"priv.nick.cbs.topgun"})
@MapperScan("priv.nick.cbs.topgun.dao")
public class TopgunApplication{
	public static void main(String[] args) {
		SpringApplication.run(TopgunApplication.class, args);
	}
}
