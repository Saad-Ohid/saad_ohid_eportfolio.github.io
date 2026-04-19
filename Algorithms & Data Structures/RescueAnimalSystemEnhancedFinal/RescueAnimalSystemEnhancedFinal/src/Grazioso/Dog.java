package Grazioso;

/**
 * ****************************************************************************
 * Class: Dog
 * Inherits: RescueAnimal
 * * Specialized class for managing Canine assets. Adds breed-specific logic
 * and formatted output for system-wide auditing.
 * ****************************************************************************
 */
public class Dog extends RescueAnimal {
    
    private String breed;

    // Full Parameterized Constructor
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
        setAnimalType("Dog");
    }

    // Accessor and Mutator
    public String getBreed() { return breed; }

    public void setBreed(String dogBreed) {
        if (dogBreed != null) { this.breed = dogBreed; }
    }

    /**
     * Specific search-format output for Canine records.
     */
    @Override
    public String toString() {
        return String.format("\"%s\", \"%s\", \"%s\", \"%s\", \"%s\", \"%s\", \"%s\", \"%s\", %b, \"%s\"",
                getName(), breed, getGender(), getAge(), getWeight(), 
                getAcquisitionDate(), getAcquisitionLocation(), 
                getTrainingStatus(), getReserved(), getInServiceLocation());
    }
}