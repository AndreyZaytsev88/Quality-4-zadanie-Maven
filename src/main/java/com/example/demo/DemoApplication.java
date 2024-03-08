package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@SpringBootApplication
@RestController
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @PostMapping("/ok")
    @ResponseBody
    
    public String ok(@RequestBody String json) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            jsonParser jsonParser = objectMapper.readValue(json, jsonParser.class);
            System.out.println(jsonParser.getname());

            ObjectMapper mapper = new ObjectMapper();
            ObjectNode jsonObj = mapper.createObjectNode();
            jsonObj.put("name", jsonParser.getname());
            jsonObj.put("status", "ok");
            jsonObj.put("code", "200");
            String jsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonObj);
            System.out.println(jsonString);
            return jsonString;
        } catch (JsonProcessingException e) {
        System.out.println(e);
            return null;
        }
       
    }
    @JsonIgnoreProperties(ignoreUnknown = true)
    static class jsonParser {
        private String name;
        
        public String getname() {
            return name;
        }
    }

}