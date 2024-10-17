package com.skyler.mybibleAdmin_api.common.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "000. COMMON")
@RestController
public class CommonController {

//    @Value("${server.env")
    private String env;
//    @Value("${server.port}")
//    private String port;
//    @Value("${server.serverAddress}")
//    private String serverAddress;
//    @Value("${serverName}")
//    private String serverName;

    @GetMapping("/health-check")
    public ResponseEntity<?> healthCheck() {
//        Map<String, String> responseData = new HashMap<>();
//        responseData.put("port", port);
//        responseData.put("serverAddress", serverAddress);
//        responseData.put("serverName", serverName);
//        return ResponseEntity.ok(responseData);
        return ResponseEntity.ok("OK");
    }

//    @GetMapping("/env")
//    public ResponseEntity<?> getEnv() {
//        return ResponseEntity.ok(env);
//    }
}
