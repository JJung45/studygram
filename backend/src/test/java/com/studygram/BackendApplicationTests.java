package com.studygram;

import com.studygram.service.TestService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BackendApplicationTests {

	@Autowired
	private TestService testService;

	@Test
	void 데이터베이스_연결_테스트() {
		testService.selectAll().stream()
				.map((test -> test.getTest()))
				.forEach(System.out::println);
	}

}
