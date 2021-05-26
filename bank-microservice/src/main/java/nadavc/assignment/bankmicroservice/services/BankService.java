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
import java.util.OptionalDouble;
import java.util.function.Predicate;
import java.util.stream.Collectors;


@Service
public class BankService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${person.ms.basic.url}")
    private String basicUrl;


    public Person getCostumerDetails(String costumerId) throws URISyntaxException {
        return getSpecificPersonFromMicroservice(costumerId);
    }


    public PersonsDetails getAllCostumer() throws URISyntaxException {
        return getAllPersonsFromMicroservice();
    }


    public PersonsDetails getCostumersFromIsrael() throws URISyntaxException {
        PersonsDetails allCostumers = getAllPersonsFromMicroservice();
        double avgWeight = getAvgWeight(allCostumers.getPersonList());
        //System.out.println("the avg is "+ avgWeight);

        List<Person> aboveAvgWeight = filterCostumersByPredicate(allCostumers.getPersonList(), aboveSpecifecWeight(avgWeight));
        List<Person> filterdList = getAllCostumersFromSpecificState(aboveAvgWeight, "Israel");

        allCostumers.setPersonList(filterdList);

        return allCostumers;
    }

    public PersonsDetails getCostumersStartsWithAOrK() throws URISyntaxException {
        PersonsDetails allCostumers = getAllPersonsFromMicroservice();
        List<Person> filterdList = filterCostumersByPredicate(allCostumers.getPersonList(), FirstLetterPredicate("A"));
        filterdList.addAll(filterCostumersByPredicate(allCostumers.getPersonList(), FirstLetterPredicate("K")));

        allCostumers.setPersonList(filterdList);
        return allCostumers;
    }


    /*-------------------------private functions-----------------------*/

    private PersonsDetails getAllPersonsFromMicroservice() throws URISyntaxException {
        String uri = basicUrl + "persons";
        RequestEntity request = new RequestEntity(HttpMethod.GET, new URI(uri));
        ResponseEntity<PersonsDetails> postEntity = restTemplate.exchange(request, PersonsDetails.class);
        return postEntity.getBody();
    }

    private Person getSpecificPersonFromMicroservice(String personId) throws URISyntaxException {
        String uri = basicUrl + "persons/" + personId;
        RequestEntity request = new RequestEntity(HttpMethod.GET, new URI(uri));
        ResponseEntity<Person> postEntity = restTemplate.exchange(request, Person.class);
        return postEntity.getBody();
    }

    private List<Person> filterCostumersByPredicate(List<Person> costumers, Predicate<Person> condition) {
        return costumers.stream().filter(person -> condition.test(person)).collect(Collectors.toList());
    }

    private Predicate<Person> FirstLetterPredicate(String letter) {
        return person -> person.getName().toUpperCase().startsWith(letter.toUpperCase());

    }

    private Predicate<Person> aboveSpecifecWeight(double weight) {
        return person -> person.getWeightInKg() > weight;

    }

    private List<Person> getAllCostumersFromSpecificState(List<Person> costumers,String stateName) {
        return filterCostumersByPredicate(costumers, p -> p.getAddress().getState().equalsIgnoreCase(stateName));
    }

    private double getAvgWeight(List<Person> costumers) {
        int counter = 0;
        OptionalDouble avg = costumers.stream().mapToInt(p -> p.getWeightInKg()).average();

        return avg.getAsDouble();
    }
}
