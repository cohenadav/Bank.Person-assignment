package nadavc.assignment.bankmicroservice.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@Getter
@Setter
public class Person {

    private String id;
    private String name;
    private int age;
    private Gender gender;
    private int heightInCm;
    private int weightInKg;
    private Address address;

}


