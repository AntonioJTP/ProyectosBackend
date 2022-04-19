package com.backend.BS41.Profiles;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("profile1")
public class Profile1 implements Profiles {
    private String profile = "Teacher";

    @Override
    public String myFunction() {
        return profile;
    }
}
