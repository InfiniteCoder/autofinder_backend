package api;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "verify")
public class Verify{
    @Id
    private long mobileNumber;
    private int otp;
    private int attempts;

    protected Verify(){}

    public Verify(long mobileNumber,int otp){
        this.mobileNumber = mobileNumber;
        this.otp = otp;
        this.attempts = 0;
    }

    public long getMobileNumber(){
        return mobileNumber;
    }

    public int getOtp(){
        return otp;
    }

    public int getAttempts(){
        return attempts;
    }

    public void incrAttempts(){
        ++attempts;
    }
}
