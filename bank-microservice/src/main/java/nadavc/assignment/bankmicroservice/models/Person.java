package nadavc.assignment.bankmicroservice.models;


public class Person {

    private int id;
    private String name;
    private int age;
    private Gender gender;
    private int heightInCm;
    private int weightInKg;
    private Address address;



    public Person() {

    }

    /*
    public Person(Person other) {
        this.id = other.id;
        this.name = other.name;
        this.age = other.age;
        this.gender = other.gender;
        this.heightInCm = other.heightInCm;
        this.weightInKg = other.weightInKg;
        this.address = other.address;
    }
*/
    public Person(int id, String name, int age, Gender gender, int heightInCm, int weightInKg, Address address) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.heightInCm = heightInCm;
        this.weightInKg = weightInKg;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public int getHeightInCm() {
        return heightInCm;
    }

    public void setHeightInCm(int heightInCm) {
        this.heightInCm = heightInCm;
    }

    public int getWeightInKg() {
        return weightInKg;
    }

    public void setWeightInKg(int weightInKg) {
        this.weightInKg = weightInKg;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
