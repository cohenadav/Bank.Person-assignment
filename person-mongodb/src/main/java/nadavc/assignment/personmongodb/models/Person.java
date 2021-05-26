package nadavc.assignment.personmongodb.models;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;



@NoArgsConstructor
@Getter
@Setter
@Document
public class Person {

    @Id
    private int id;
    private String name;
    private int age;
    private Gender gender;
    private int heightInCm;
    private int weightInKg;
    private Address address;

}

