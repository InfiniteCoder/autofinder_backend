package api;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.persistence.Id;

@Entity
@Table(name = "found_vehicle")
public class FoundVehicle{
    @Id
    private String chassisNumber;
    private String licenseNumber;
    @NotNull
    private String foundRtoOffice;

    protected FoundVehicle(){}  //for internal use

    public FoundVehicle(String chassisNumber,String licenseNumber, String foundRtoOffice){
        this.chassisNumber = chassisNumber;
        this.licenseNumber = licenseNumber;
        this.foundRtoOffice = foundRtoOffice;
    }

    public String getChassisNumber(){
        return this.chassisNumber;
    }

    public String getLicenseNumber(){
        return this.licenseNumber;
    }

    public String getFoundRtoOffice(){
        return this.foundRtoOffice;
    }
}
