package nadavc.assignment.bankmicroservice.services;

import nadavc.assignment.bankmicroservice.models.Person;
import nadavc.assignment.bankmicroservice.models.PersonsDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;


@Service
public class BankService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${person.ms.basic.url}")
    private String basicUrl;
    

    public Person getCostumerDetails(int costumerId) throws URISyntaxException {
        String uri = basicUrl + "persons/" + costumerId;
        RequestEntity request = new RequestEntity(HttpMethod.GET, new URI(uri));
        ResponseEntity<Person> postEntity = restTemplate.exchange( request , Person.class);
        return postEntity.getBody();
    }


    public PersonsDetails getAllCostumer() throws URISyntaxException {
        String uri = basicUrl + "persons";
        RequestEntity request = new RequestEntity(HttpMethod.GET, new URI(uri));
        ResponseEntity<PersonsDetails> postEntity = restTemplate.exchange( request , PersonsDetails.class);
        return postEntity.getBody();
    }


    public PersonsDetails getCostumersFromIsrael() throws URISyntaxException {
        String uri = basicUrl + "persons";
        RequestEntity request = new RequestEntity(HttpMethod.GET, new URI(uri));
        ResponseEntity<PersonsDetails> postEntity = restTemplate.exchange( request , PersonsDetails.class);
        List<Person> filterdList = postEntity.getBody().getPersonList().stream().filter( new Predicate<Person>() {
            @Override
            public boolean test(Person person) {
                return person.getAddress().getState().equals("Israel");
            }
        }).collect(Collectors.toList());

        postEntity.getBody().setPersonList(filterdList);

        return postEntity.getBody();
    }

    public PersonsDetails getCostumersStartsWithAOrK() throws URISyntaxException {
        String uri = basicUrl + "persons";
        RequestEntity request = new RequestEntity(HttpMethod.GET, new URI(uri));
        ResponseEntity<PersonsDetails> postEntity = restTemplate.exchange( request , PersonsDetails.class);
        List<Person> filterdList = postEntity.getBody().getPersonList().stream().filter( new Predicate<Person>() {
            @Override
            public boolean test(Person person) {
                return person.getName().toUpperCase().startsWith("A") || person.getName().toUpperCase().startsWith("K") ;
            }
        }).collect(Collectors.toList());

        postEntity.getBody().setPersonList(filterdList);

        return postEntity.getBody();
    }

}
