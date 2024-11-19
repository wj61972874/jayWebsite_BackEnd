package com.jayblog.weisite.controller;

import com.jayblog.weisite.common.ResponseBean;
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
    public ResponseBean<String> getDujitang() {
        String url = "https://apis.tianapi.com/dujitang/index?key=01fa7878b499ab53f6b6fe8ab77c1d2c";
        try {
            ResponseEntity<Map> response = restTemplate.postForEntity(url, null, Map.class);
            Map<String, Object> responseBody = response.getBody();
            if (responseBody != null && (int) responseBody.get("code") == 200) {
                Map<String, String> result = (Map<String, String>) responseBody.get("result");
                String content = result.get("content");
                return ResponseBean.success(content);
            } else {
                return ResponseBean.success("用努力去喂养梦想，愿跌倒不哭，明媚如初，早安。");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseBean.error("接口异常~");
        }
    }
}