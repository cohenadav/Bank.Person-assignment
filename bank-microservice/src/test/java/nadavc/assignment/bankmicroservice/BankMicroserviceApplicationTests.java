package nadavc.assignment.bankmicroservice;

import nadavc.assignment.bankmicroservice.services.BankService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
class BankMicroserviceApplicationTests {

	@Autowired
	private BankService bankService;

	//@MockBean
	//private Person

	@Test
	void contextLoads() {
	}

}
