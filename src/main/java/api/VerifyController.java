package api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.jaunt.NotFound;
import com.jaunt.ResponseException;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;

@Controller
public class VerifyController{

    @Autowired
    VerifyRepository verifyRepository;

    @RequestMapping("/sendotp")
    @ResponseBody
    public String sendotp(long mobileNumber) throws ResponseException, NotFound{
        //get a random number
        int min = 100000;
        int max = 999999;
        Random rand = new Random();
        int otp = rand.nextInt((max - min) + 1) + min;

        //add to table
        Verify vsms = new Verify(mobileNumber,otp);
        verifyRepository.save(vsms);

        //send message
        String message = "your otp for autofinder is: " + otp;
        M160by2 m160by2=new M160by2();
        m160by2.login("9876543210","password");
        m160by2.sendSMS(message,String.valueOf(mobileNumber));
        return "Success";
    }

    @RequestMapping("/notify")
    @ResponseBody
    public String notify(long mobileNumber,String licenseNumber,String rtoOffice) throws ResponseException, NotFound{
        String message = "Your vehicle "+licenseNumber+" has been found at "+rtoOffice+". Please recover it at your earliest. Regards, --autofinder";
        M160by2 m160by2 = new M160by2();
        m160by2.login("9876543210","password");
        m160by2.sendSMS(message,String.valueOf(mobileNumber));
        return "Success";
    }
}
