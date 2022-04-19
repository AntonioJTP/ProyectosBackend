package com.backend.BS4;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("values")
public class PropertiesController {

    @Value("${VAR1}")
    private String var1;

    @Value("${My.VAR2}")
    private int var2;

    @Value("${VAR3:no tiene valor}")
    private String var3;

    @GetMapping
    public String getPropertiesValues()
    {
        return String.format("Valor de var1 es: %s\nValor de var2 es: %s", var1, var2);
    }

    @GetMapping("/var3")
    public String getVar3()
    {
        return "Valor de var3 es: " + var3;
    }
}
