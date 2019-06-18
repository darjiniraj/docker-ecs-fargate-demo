package in.niraj.userfrontend.controller;

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


    @Value("${base.url}")
    private String baseUrl;

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


    @GetMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getAllEmployeesJSON() {
        return getResponse("users");
    }

    private String getResponse(String apiPath) {
        final String uri = baseUrl+ apiPath;

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity(headers);

        ResponseEntity<String> result = restTemplate.exchange(uri, HttpMethod.GET, entity, String.class);
        String userList = result.getBody();

        System.out.println(userList);
        return userList;

    }
}

