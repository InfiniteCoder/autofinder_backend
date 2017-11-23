package api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class FoundVehicleController{

    final static int maxAttempts = 10;

    @Autowired
    FoundVehicleRepository foundVehicleRepository;
    @Autowired
    VerifyRepository verifyRepository;
    @Autowired
    AdminRepository adminRepository;

    @RequestMapping("/addfound")
    @ResponseBody
    public String add(String chassisNumber,String licenseNumber,String foundRtoOffice,long mobileNumber,int otp){
        //check if otp is valid and mobile number in admin
        Verify verify = verifyRepository.findByMobileNumber(mobileNumber);
        if(verify.getOtp()  == otp && adminRepository.findByMobileNumber(mobileNumber)!=null)   //check null as it's not null if match is found
        {
            //remove otp entry
            verifyRepository.delete(verify);
            //add vehicle to db
            FoundVehicle vehicle = new FoundVehicle(chassisNumber,licenseNumber,foundRtoOffice);

            try{
                foundVehicleRepository.save(vehicle);
            }
            catch (Exception e) {
                return  e.toString();
            }
            return "Vehicle added to found database";
        }
        else
        {
            verify.incrAttempts();
            if(verify.getAttempts()< maxAttempts)
            {
                verifyRepository.save(verify);  //save increased attempt count
            }
            else
            {
                verifyRepository.delete(verify);    //max attempts crossed, delete otp entry
            }
            return "otp or mobileNumber not matched, or this is not an admin";
        }

    }

    @RequestMapping("/searchfound")
    @ResponseBody
    public FoundVehicle find(String chassisNumber){
        return foundVehicleRepository.findOne(chassisNumber);
    }
}
