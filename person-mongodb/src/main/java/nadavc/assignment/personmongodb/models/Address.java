package nadavc.assignment.personmongodb.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class Address {
    private String state;
    private String city;
    private String street;
    private String zipCode;

}
