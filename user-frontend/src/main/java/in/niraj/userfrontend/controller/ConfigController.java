package in.niraj.userfrontend.controller;

import com.fasterxml.jackson.databind.JsonNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

/**
 * created by nxd on Feb, 2019
 */
@CrossOrigin
@RestController
@RefreshScope
@RequestMapping("/external")
public class ConfigController {

    /**
     * The Constant logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(ConfigController.class);


    //@Value("${base.url:http://localhost:8080}")
    private String baseUrl = "http://localhost:8080";

    @GetMapping("/config")
    @ResponseBody
    public ResponseEntity getConfig() {
        logger.info("baseurl for backend is {} ", baseUrl);
        return new ResponseEntity<>(baseUrl, HttpStatus.OK);
    }

    @GetMapping("/status")
    @ResponseBody
    public ResponseEntity getStatus() {
        logger.info("check status for  backend  {} ", baseUrl);
        return new ResponseEntity<>(getResponse("actuator/health"), HttpStatus.OK);
    }


    @PostMapping("/save")
    public ResponseEntity<JsonNode> saveEmployee(@RequestBody Object o) {
        System.out.println("Save Employee");
        final String uri = baseUrl+ "save";
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity(o, headers);
        ResponseEntity<JsonNode> result = restTemplate.exchange(uri, HttpMethod.POST, entity, JsonNode.class);
        return result;
    }

    @GetMapping("/employee/{id}")
    public ResponseEntity<JsonNode> getEmployee(@PathVariable("id") String id) {
        System.out.println("Save Employee");
        final String uri = baseUrl+ "employee"+ "/"+ id;
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity( headers);
        ResponseEntity<JsonNode> result = restTemplate.exchange(uri, HttpMethod.GET, entity, JsonNode.class);
        return result;
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<JsonNode> deleteEmployee(@PathVariable("id") String id) {
        System.out.println("delete Employee");
        final String uri = baseUrl+ "delete"+ "/"+ id;
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity( headers);
        ResponseEntity<JsonNode> result = restTemplate.exchange(uri, HttpMethod.DELETE, entity, JsonNode.class);
        return result;
    }


    @GetMapping(value = "/employees", produces = MediaType.APPLICATION_JSON_VALUE)
    public JsonNode getAllEmployeesJSON() {
        return getResponse("employees");
    }

    private JsonNode getResponse(String apiPath) {
        final String uri = baseUrl+ apiPath;

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity(headers);

        ResponseEntity<JsonNode> result = restTemplate.exchange(uri, HttpMethod.GET, entity, JsonNode.class);

        System.out.println(result.getBody());
        return result.getBody();

    }
}

