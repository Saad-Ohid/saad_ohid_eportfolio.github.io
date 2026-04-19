package Grazioso;

public class Dog extends RescueAnimal {
    // Instance variable
    private String breed;

    // Constructor
    public Dog(String name, String breed, String gender, String age,
               String weight, String acquisitionDate, String acquisitionCountry,
               String trainingStatus, boolean reserved, String inServiceCountry) {
        setName(name);
        setBreed(breed);
        setGender(gender);
        setAge(age);
        setWeight(weight);
        setAcquisitionDate(acquisitionDate);
        setAcquisitionLocation(acquisitionCountry);
        setTrainingStatus(trainingStatus);
        setReserved(reserved);
        setInServiceCountry(inServiceCountry);
    }

    // Accessor Method
    public String getBreed() {
        return breed;
    }

    // Mutator Method
    public void setBreed(String dogBreed) {
        breed = dogBreed;
    }

    // Override toString method for better output
    @Override
    public String toString() {
        return String.format("Dog - Name: %s, Breed: %s, Gender: %s, Age: %s, Weight: %s, Acquisition Date: %s, Acquisition Country: %s, Training Status: %s, Reserved: %s, In Service Country: %s",
                getName(), breed, getGender(), getAge(), getWeight(), getAcquisitionDate(), getAcquisitionLocation(), getTrainingStatus(), getReserved(), getInServiceLocation());
    }
}
