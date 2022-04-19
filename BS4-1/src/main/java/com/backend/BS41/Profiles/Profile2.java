package com.backend.BS41.Profiles;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("profile2")
public class Profile2 implements Profiles {
    private String profile = "Student";

    @Override
    public String myFunction() {
        return profile;
    }
}
