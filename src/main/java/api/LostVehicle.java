package api;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.persistence.Id;

@Entity
@Table(name = "lost_vehicle")
public class LostVehicle{
    @Id
    private String chassisNumber;
    @NotNull
    private String licenseNumber;
    @NotNull
    private long mobileNumber;
    private String model;
    private String company;
    private long lostPincode;

    protected LostVehicle(){}   //used internally, do not modify

    public LostVehicle(String chassisNumber,String licenseNumber,long mobileNumber){
        //only important fields set in constructor
        this.chassisNumber = chassisNumber;
        this.licenseNumber = licenseNumber;
        this.mobileNumber = mobileNumber;
    }
    
    //getter and setter methods
    public String getChassisNumber(){
        return chassisNumber;
    }

    public String getLicenseNumber(){
        return licenseNumber;
    }

    public long getMobileNumber(){
        return mobileNumber;
    }

    public String getModel(){
        return model;
    }

    public String getCompany(){
        return company;
    }

    public long getLostPincode(){
        return lostPincode;
    }

    public void setModel(String model){
        this.model = model;
    }

    public void setCompany(String company){
        this.company = company;
    }

    public void setLostPincode(long lostPincode){
        this.lostPincode = lostPincode;
    }
}
