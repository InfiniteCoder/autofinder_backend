package api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@Controller
public class LostVehicleController{

    @Autowired
    LostVehicleRepository lostVehicleRepository;
    @Autowired
    VerifyRepository verifyRepository;
    final static int maxAttempts = 10;

    @RequestMapping("/addlost")
    @ResponseBody
    public String add(String chassisNumber, String licenseNumber, long mobileNumber, String model, String company, long lostPincode,int otp){
        //check if otp is valid
        Verify verify = verifyRepository.findByMobileNumber(mobileNumber);

        //add lost vehicle only if otp matched
        if(verify.getOtp() == otp)
        {
            //create and add new vehicle
            LostVehicle vehicle = new LostVehicle(chassisNumber,licenseNumber,mobileNumber);
            vehicle.setModel(model);
            vehicle.setCompany(company);
            vehicle.setLostPincode(lostPincode);

            try{
                lostVehicleRepository.save(vehicle);
            }
            catch(Exception ex){
                return "Vehicle not added to database: " + ex;
            }
            //if vehicle addition successful, remove verify entry
            verifyRepository.delete(verify);
            return "Vehicle added to lost database";
        }
        else
        {
            verify.incrAttempts();
            if(verify.getAttempts() < maxAttempts){
                verifyRepository.save(verify);  //replace previous entry
            }
            else{
                //10 attempts done, remove entry
                verifyRepository.delete(verify);
            }
            return "otp not matched or invalid mobile number";
        }


    }


    @RequestMapping("/searchlost")
    @ResponseBody
    public LostVehicle search(String chassisNumber){
        return lostVehicleRepository.findOne(chassisNumber);
    }

    @RequestMapping("/findcount")
    @ResponseBody
    public List<CountVehicle> findCount(){
        Pageable pageable = new PageRequest(0,10);
        return lostVehicleRepository.findcount(pageable);
    }
}
