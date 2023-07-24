package com.fse.moviebooking;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = MoviebookingApplicationTests.class)
class MoviebookingApplicationTests {

	@Mock
	MoviebookingApplication movieBookingApplication;
	@Test
	void contextLoads() {
		assertNotNull(movieBookingApplication);
	}

}
