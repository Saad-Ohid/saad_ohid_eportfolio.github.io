package Grazioso;

/**
 * Represents a Monkey object in the Rescue Animal System.
 * 
 * @author Saad Ohiduzzaman
 * @date 10/19/2024
 */
public class Monkey extends RescueAnimal {
    // Instance variables for monkey-specific attributes
    private String breed;
    private double tailLength; // Tail length in centimeters
    private double height; // Height in centimeters
    private double bodyLength; // Body length in centimeters

    // Array of valid monkey breeds to ensure valid entries
    public final static String[] VALID_BREEDS = {
            "Capuchin",
            "Guenon",
            "Macaque",
            "Marmoset",
            "Squirrel monkey",
            "Tamarin"
    };

    // Constructor for creating a new Monkey object
    public Monkey(String name, String breed, String gender, String age,
                  String weight, String acquisitionDate, String acquisitionCountry,
                  String trainingStatus, boolean reserved, String inServiceCountry,
                  double tailLength, double height, double bodyLength) {
        // Set the properties inherited from the RescueAnimal class
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
        setAnimalType("monkey"); // Set the type of animal to "monkey"
        
        // Set monkey-specific attributes
        this.tailLength = tailLength;
        this.height = height;
        this.bodyLength = bodyLength;
    }

    // Accessor and mutator methods for monkey-specific attributes
    public double getTailLength() {
        return tailLength;
    }

    public void setTailLength(double tailLength) {
        this.tailLength = tailLength;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getBodyLength() {
        return bodyLength;
    }

    public void setBodyLength(double bodyLength) {
        this.bodyLength = bodyLength;
    }

    // Accessor method to get the breed of the monkey
    public String getBreed() {
        return breed;
    }

    // Mutator method to set the breed of the monkey
    public void setBreed(String monkeyBreed) {
        this.breed = monkeyBreed; // Update the breed variable
    }

    @Override
    public String toString() {
        return String.format("Monkey - Name: %s, Breed: %s, Gender: %s, Age: %s, Weight: %s, Tail Length: %.2f cm, Height: %.2f cm, Body Length: %.2f cm, Acquisition Date: %s, Acquisition Country: %s, Training Status: %s, Reserved: %s, In Service Country: %s",
                getName(), breed, getGender(), getAge(), getWeight(), tailLength, height, bodyLength, getAcquisitionDate(), getAcquisitionLocation(), getTrainingStatus(), getReserved(), getInServiceLocation());
    }
}