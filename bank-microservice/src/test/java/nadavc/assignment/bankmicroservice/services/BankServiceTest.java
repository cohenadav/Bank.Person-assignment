package nadavc.assignment.bankmicroservice.services;

import nadavc.assignment.bankmicroservice.models.Address;
import nadavc.assignment.bankmicroservice.models.Gender;
import nadavc.assignment.bankmicroservice.models.Person;
import nadavc.assignment.bankmicroservice.models.PersonsDetails;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;


@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
class BankServiceTest {

    @InjectMocks
    private BankService service;

    @Mock
    private RestTemplate restTemplate;


    @Test
    void getAllCostumer() throws URISyntaxException {
        List<Person> persons = new ArrayList<>(Arrays.asList(
                new Person("3056" , "Amir", 30, Gender.MALE, 180, 73, new Address("Isreal", "TLV", "Rotchild", "123123")),
                new Person("394" , "kaka", 41, Gender.MALE, 186, 78, new Address("Brazil", "Rio", "El Mondo", "321321"))
            )
        );

        PersonsDetails allPersons = new PersonsDetails(persons);

        ResponseEntity<PersonsDetails> responseEntity
                = new ResponseEntity<PersonsDetails>(allPersons,HttpStatus.ACCEPTED);

        when(restTemplate.exchange(
                Matchers.anyObject(),
                Matchers.<Class<PersonsDetails>> any())
        ).thenReturn(responseEntity);

        assertEquals(allPersons.getPersonList(), service.getAllCostumer().getPersonList());
        assertEquals(persons.size(), service.getAllCostumer().getPersonList().size());

    }


    @Test
    void getCostumersFromIsraelAboveAVGWeight() throws URISyntaxException {
        List<Person> persons = new ArrayList<>(Arrays.asList(
                new Person("3056" , "Amir", 30, Gender.MALE, 180, 60, new Address("Isreal", "TLV", "Rotchild", "123123")),
                new Person("394" , "kaka", 41, Gender.MALE, 186, 78, new Address("israel", "Ramat-Hasharon", "Zabutinsky", "321321")),
                new Person("736" , "Amig", 41, Gender.MALE, 186, 78, new Address("israel", "Petach-Tikva", "Arlozorov", "989898")),
                new Person("985" , "Guy", 30, Gender.MALE, 186, 78, new Address("Israel", "Holon", "Borochov", "6767676"))
            )
        );

        PersonsDetails allPersons = new PersonsDetails(persons);

        ResponseEntity<PersonsDetails> responseEntity
                = new ResponseEntity<PersonsDetails>(allPersons,HttpStatus.ACCEPTED);

        when(restTemplate.exchange(
                Matchers.anyObject(),
                Matchers.<Class<PersonsDetails>> any())
        ).thenReturn(responseEntity);

        //assertEquals(allPersons.getPersonList(), service.getAllCostumer().getPersonList());
        assertEquals(3, service.getCostumersFromIsrael().getPersonList().size());

    }

    @Test
    void getCostumersFromIsraelAboveAVGWeightNegative() throws URISyntaxException {
        List<Person> persons = new ArrayList<>(Arrays.asList(
                new Person("3056" , "Amir", 30, Gender.MALE, 180, 78, new Address("Isreal", "TLV", "Rotchild", "123123")),
                new Person("394" , "kaka", 41, Gender.MALE, 186, 78, new Address("israel", "Ramat-Hasharon", "Zabutinsky", "321321")),
                new Person("736" , "Amig", 41, Gender.MALE, 186, 78, new Address("israel", "Petach-Tikva", "Arlozorov", "989898")),
                new Person("985" , "Guy", 30, Gender.MALE, 186, 78, new Address("Israel", "Holon", "Borochov", "6767676"))
        )
        );

        PersonsDetails allPersons = new PersonsDetails(persons);

        ResponseEntity<PersonsDetails> responseEntity
                = new ResponseEntity<PersonsDetails>(allPersons,HttpStatus.ACCEPTED);

        when(restTemplate.exchange(
                Matchers.anyObject(),
                Matchers.<Class<PersonsDetails>> any())
        ).thenReturn(responseEntity);

        //assertEquals(allPersons.getPersonList(), service.getAllCostumer().getPersonList());
        assertEquals(0, service.getCostumersFromIsrael().getPersonList().size());

    }

    @Test
    void getCostumersStartsWithAOrK() throws URISyntaxException {
        List<Person> persons = new ArrayList<>(Arrays.asList(
                new Person("3056" , "Amir", 30, Gender.MALE, 180, 78, new Address("Isreal", "TLV", "Rotchild", "123123")),
                new Person("394" , "kaka", 41, Gender.MALE, 186, 78, new Address("israel", "Ramat-Hasharon", "Zabutinsky", "321321")),
                new Person("736" , "Amig", 41, Gender.MALE, 186, 78, new Address("israel", "Petach-Tikva", "Arlozorov", "989898")),
                new Person("985" , "Guy", 30, Gender.MALE, 186, 78, new Address("Israel", "Holon", "Borochov", "6767676"))
            )
        );

        PersonsDetails allPersons = new PersonsDetails(persons);

        ResponseEntity<PersonsDetails> responseEntity
                = new ResponseEntity<PersonsDetails>(allPersons,HttpStatus.ACCEPTED);

        when(restTemplate.exchange(
                Matchers.anyObject(),
                Matchers.<Class<PersonsDetails>> any())
        ).thenReturn(responseEntity);

        //assertEquals(allPersons.getPersonList(), service.getAllCostumer().getPersonList());
        assertEquals(3, service.getCostumersStartsWithAOrK().getPersonList().size());

    }

    @Test
    void getCostumersStartsWithAOrKNegative() throws URISyntaxException {
        List<Person> persons = new ArrayList<>(Arrays.asList(
                new Person("3056" , "Gorge", 30, Gender.MALE, 180, 78, new Address("Isreal", "TLV", "Rotchild", "123123")),
                new Person("394" , "Boni", 41, Gender.MALE, 186, 78, new Address("israel", "Ramat-Hasharon", "Zabutinsky", "321321")),
                new Person("736" , "Nadin", 41, Gender.FEMALE, 173, 63, new Address("israel", "Petach-Tikva", "Arlozorov", "989898")),
                new Person("985" , "Guy", 30, Gender.MALE, 186, 78, new Address("Israel", "Holon", "Borochov", "6767676"))
            )
        );

        PersonsDetails allPersons = new PersonsDetails(persons);

        ResponseEntity<PersonsDetails> responseEntity =
                new ResponseEntity<PersonsDetails>(allPersons,HttpStatus.ACCEPTED);

        when(restTemplate.exchange(
                Matchers.anyObject(),
                Matchers.<Class<PersonsDetails>> any())
        ).thenReturn(responseEntity);

        //assertEquals(allPersons.getPersonList(), service.getAllCostumer().getPersonList());
        assertEquals(0, service.getCostumersStartsWithAOrK().getPersonList().size());

    }

}