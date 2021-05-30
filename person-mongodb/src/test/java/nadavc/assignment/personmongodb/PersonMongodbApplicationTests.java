package nadavc.assignment.personmongodb;

import nadavc.assignment.personmongodb.models.Address;
import nadavc.assignment.personmongodb.models.Gender;
import nadavc.assignment.personmongodb.models.Person;
import nadavc.assignment.personmongodb.models.PersonsDetails;
import nadavc.assignment.personmongodb.repository.PersonRepository;
import nadavc.assignment.personmongodb.services.PersonService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class PersonMongodbApplicationTests {

	@InjectMocks
	private PersonService service;

	@Mock
	private PersonRepository repo;

	@Test
	void getAllPersons() {
		List<Person> testPersons = new ArrayList<>(Arrays.asList(
				new Person("342340", "Amir", 30, Gender.MALE, 180, 73, new Address("Isreal", "TLV", "Rotchild", "123123")),
				new Person("146340", "Sima", 30, Gender.FEMALE, 170, 58, new Address("Isreal", "Ramat-Gan", "Hamorad", "345345")),
				new Person("835340", "Karim", 30, Gender.MALE, 168, 64, new Address("Isreal", "Givatayim", "Bloch", "678678"))
			)
		);

		when(repo.findAll()).thenReturn(testPersons);
		assertEquals(testPersons.size(), service.getAllPersons().getPersonList().size());
	}

	@Test
	void getPersonByIdPositive() {
		final String id = "3456";
		Person p = new Person(id, "Amir", 30, Gender.MALE, 180, 73, new Address("Isreal", "TLV", "Rotchild", "123123"));
		when(repo.existsById(id)).thenReturn(true);
		when(repo.findById(id)).thenReturn(java.util.Optional.of(p));
		assertEquals(p, service.getPerson(id));
	}

	@Test
	void addPersonPositive() {
		final String id = "3456";
		Person p = new Person(id , "Amir", 30, Gender.MALE, 180, 73, new Address("Isreal", "TLV", "Rotchild", "123123"));
		when(repo.existsById(id)).thenReturn(false);
		when(service.addPerson(p)).thenReturn(p);
		assertEquals(p, service.addPerson(p));
	}

	@Test
	void addPersonNegative() {
		final String id = "3456";
		Person p = new Person(id , "Amir", 30, Gender.MALE, 180, 73, new Address("Isreal", "TLV", "Rotchild", "123123"));
		when(repo.existsById(id)).thenReturn(true);
		try{
			service.addPerson(p);
		}catch (Exception e) {
			return;
		}
		Assertions.fail();
	}

	@Test
	void updatePersonPositive() {
		final String id = "3456";
		Person p = new Person(id , "Amir", 30, Gender.MALE, 180, 73, new Address("Isreal", "TLV", "Rotchild", "123123"));
		when(repo.existsById(id)).thenReturn(true);
		when(service.updatePerson(p)).thenReturn(p);
		assertEquals(p, service.updatePerson(p));
	}

	@Test
	void updatePersonNegative() {
		final String id = "3456";
		Person p = new Person(id , "Amir", 30, Gender.MALE, 180, 73, new Address("Isreal", "TLV", "Rotchild", "123123"));
		when(repo.existsById(id)).thenReturn(false);
		try{
			service.updatePerson(p);
		}catch (Exception e) {
			return;
		}
		Assertions.fail();
	}

	@Test
	void deletePersonPositive() {
		final String id = "3456";
		when(repo.existsById(id)).thenReturn(true);
		assertEquals(id ,service.deletePerson(id));
	}

	@Test
	void deletePersonNegative() {
		final String id = "3456";
		when(repo.existsById(id)).thenReturn(false);

		try{
			service.deletePerson(id);
		}catch (Exception e) {
			return;
		}
		Assertions.fail();
	}

}
