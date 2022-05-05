package com.backend.EJ31_CRUD.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "feign", url = "http://localhost:8080")
public interface Feign {
    @GetMapping("/teacher/{id}")
    public ResponseEntity<?> getTeacherById(@PathVariable String id);
}
