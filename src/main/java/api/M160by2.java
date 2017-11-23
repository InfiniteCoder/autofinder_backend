package api;

/*
 * Copyright 2016  sachin
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import com.jaunt.NotFound;
import com.jaunt.ResponseException;
import com.jaunt.UserAgent;
import com.jaunt.component.Form;

public class M160by2 {

    public static String Token;
    Form sms;
    UserAgent agent;

    /**
     * Used to login at http://www.160by2.com bu using username and password
     * @param username
     * @param Password
     * @throws ResponseException
     * @throws NotFound
     */
    public void login(String username,String Password) throws ResponseException, NotFound {

        agent=new UserAgent();
        agent.visit("http://www.160by2.com/Index");
        Form form=agent.doc.getForm(0);
        form.setTextField("username",username);
        form.setPassword("password",Password);
        form.submit();

        Token=agent.getLocation().substring(agent.getLocation().indexOf("?id=")+4);
        agent.visit("http://www.160by2.com/SendSMS?id="+Token);
        sms=agent.doc.getForm(0);

    }

    /**
     * Used to send msg to specified phone number.
     * @param message
     * @param Phone_No
     * @throws NotFound
     * @throws ResponseException
     */
    public void sendSMS(String message,String Phone_No) throws NotFound, ResponseException {

        sms.setTextField(sms.getElement().findFirst("<input type=\"text\" placeholder=\"Enter Mobile Number or Name\"").getAt("name"),Phone_No);
        sms.setTextArea("sendSMSMsg",message);
        sms.setHidden("maxwellapps",Token);
        sms.setHidden("hid_exists","no");
        sms.setAction("http://www.160by2.com/"+sms.getElement().findFirst("<input type=\"hidden\" id=\"fkapps\"").getAt("value"));
        sms.submit();
    }

}
