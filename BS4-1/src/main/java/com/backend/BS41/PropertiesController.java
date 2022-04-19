package com.backend.BS41;

import com.backend.BS41.Profiles.Profiles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PropertiesController {
    @Value("${url}")
    private String url;

    @Value("${password}")
    private String passwd;

    @Autowired
    Configuration myConfiguration;

    @Autowired
    Profiles profiles;

    @GetMapping("/parameters")
    public String getUrlAndPassword() {
        return String.format("URL: %s \nPASSWORD: %s", url, passwd);
    }

    @GetMapping("/myconfiguration")
    public String getMyConfiguration() {
        return String.format("VALUE 1: %s \nVALUE 2: %s", myConfiguration.getValue1(), myConfiguration.getValue2());
    }

    @GetMapping("/profile")
    public String getProfile() {
        return profiles.myFunction();
    }
}
