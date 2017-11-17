package edu.learn.rest.restcontroller;

import edu.learn.rest.utils.CommonResponsesCodes;
import edu.learn.rest.utils.CustomResponse;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api")
public class DashboardRestController {

    @RequestMapping(value = "dashboard", method = { RequestMethod.GET } ,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public CustomResponse dashboard(){
        CustomResponse customResponse = new CustomResponse();
        customResponse.setCode(CommonResponsesCodes.GOOD_RESPONSE_CODE);
        customResponse.setMessage("Dashboard is loaded ...");

        return customResponse;
    }
}
