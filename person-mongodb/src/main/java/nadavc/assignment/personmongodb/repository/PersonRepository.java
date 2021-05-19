package nadavc.assignment.personmongodb.repository;

import nadavc.assignment.personmongodb.models.Person;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PersonRepository extends MongoRepository<Person, Integer> {

}
