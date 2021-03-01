package lux.pe.na.market;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MarketApplicationTests {

	@Test
	void sum() {
		int number = 5;
		Assertions.assertEquals(10,number*2);
	}

}
