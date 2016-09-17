package org.pyouells;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.boot.test.web.client.TestRestTemplate;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CatalyticEngineeringTestApplicationTests {

	@Test
	public void contextLoads() {
	}

	@Bean
	public TestRestTemplate testRestTemplate() {
		return new TestRestTemplate();
	}

}
