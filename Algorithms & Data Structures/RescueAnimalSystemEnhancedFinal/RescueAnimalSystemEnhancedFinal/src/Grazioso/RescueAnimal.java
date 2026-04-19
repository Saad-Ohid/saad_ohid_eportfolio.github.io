package Grazioso;

import java.lang.String;

/**
 * ****************************************************************************
 * Class: RescueAnimal
 * Project: Grazioso Salvare Rescue Animal System
 * * Master template for all rescue animals defining common logistics attributes.
 * * Features:
 * - Full Encapsulation
 * - Availability Logic
 * ****************************************************************************
 */
public class RescueAnimal {

    private String name;
    private String animalType;
    private String gender;
    private String age;
    private String weight; // Standardized in lbs
    private String acquisitionDate;
    private String acquisitionCountry;
    private String trainingStatus;
    private boolean reserved;
    private String inServiceCountry;

    /**
     * Default constructor initializing data integrity defaults.
     */
    public RescueAnimal() {
        this.name = "PENDING_INTAKE";
        this.animalType = "Unknown";
        this.gender = "Unknown";
        this.age = "0";
        this.weight = "0";
        this.acquisitionDate = "01-01-2000";
        this.acquisitionCountry = "None";
        this.trainingStatus = "Intake";
        this.reserved = false;
        this.inServiceCountry = "None";
    }

    // Accessor Methods
    public String getName() { return name; }
    public String getAnimalType() { return animalType; }
    public String getGender() { return gender; }
    public String getAge() { return age; }
    public String getWeight() { return weight; }
    public String getAcquisitionDate() { return acquisitionDate; }
    public String getAcquisitionLocation() { return acquisitionCountry; }
    public boolean getReserved() { return reserved; }
    public String getInServiceLocation() { return inServiceCountry; }
    public String getTrainingStatus() { return trainingStatus; }

    // Mutator Methods
    public void setName(String name) {
        this.name = (name != null && !name.trim().isEmpty()) ? name : "INVALID_NAME_ENTRY";
    }

    public void setAnimalType(String animalType) { this.animalType = animalType; }
    public void setGender(String gender) { this.gender = gender; }
    public void setAge(String age) { this.age = age; }
    public void setWeight(String weight) { this.weight = weight; }
    public void setAcquisitionDate(String acquisitionDate) { this.acquisitionDate = acquisitionDate; }
    public void setAcquisitionLocation(String acquisitionCountry) { this.acquisitionCountry = acquisitionCountry; }
    public void setReserved(boolean reserved) { this.reserved = reserved; }
    public void setInServiceCountry(String inServiceCountry) { this.inServiceCountry = inServiceCountry; }
    public void setTrainingStatus(String trainingStatus) { this.trainingStatus = trainingStatus; }

    /**
     * Functional Check: Determines if an animal is ready for field work.
     * Criteria: Must be fully trained ("In Service") and NOT currently reserved.
     */
    public boolean isEligibleForDeployment() {
        boolean isTrained = this.trainingStatus.equalsIgnoreCase("in service");
        boolean isNotReserved = !this.reserved;
        return isTrained && isNotReserved;
    }
}