//package com.aquawealth.controller;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//
//@Controller
//public class HomeController {
//
//    @GetMapping("/")
//    public String home() {
//        return "index"; // This should match "index.jsp" inside /WEB-INF/views/
//    }
//}
package com.aquawealth.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "index"; // This maps to /WEB-INF/views/index.jsp
    }

    @GetMapping("/applyInsurance")
    public String applyInsurance() {
        return "applyInsurance"; // Maps to applyInsurance.jsp
    }

    @GetMapping("/claimInsurance")
    public String claimInsurance() {
        return "claimInsurance"; // Maps to claimInsurance.jsp
    }
}
