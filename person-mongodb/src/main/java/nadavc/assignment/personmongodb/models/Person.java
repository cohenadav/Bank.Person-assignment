package nadavc.assignment.personmongodb.models;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;



@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Document
public class Person {

    @Id
    private String id;
    private String name;
    private int age;
    private Gender gender;
    private int heightInCm;
    private int weightInKg;
    private Address address;

}

