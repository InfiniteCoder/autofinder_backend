package api;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "admin")
public class Admin{
    @Id
    long mobileNumber;

    protected Admin(){}

    public Admin(long mobileNumber){
        this.mobileNumber = mobileNumber;
    }

    public long getMobileNumber(){
        return this.mobileNumber;
    }
}
