package priv.nick.cbs.topgun;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"priv.nick.cbs.topgun"})
@MapperScan("priv.nick.cbs.topgun.dao")
public class TopgunApplication{
	public static void main(String[] args) {
		SpringApplication.run(TopgunApplication.class, args);
	}
}
