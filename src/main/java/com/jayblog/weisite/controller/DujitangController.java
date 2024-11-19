package com.jayblog.weisite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/jayBlog")
public class DujitangController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/dujitang/index")
    public ResponseEntity<?> getDujitang() {
        String url = "https://apis.tianapi.com/dujitang/index?key=01fa7878b499ab53f6b6fe8ab77c1d2c";
        try {
            ResponseEntity<Map> response = restTemplate.postForEntity(url, null, Map.class);
            return ResponseEntity.ok(response.getBody());
        } catch (Exception e) {
            e.printStackTrace();
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "Internal Server Error");
            return ResponseEntity.status(500).body(errorResponse);
        }
    }
}