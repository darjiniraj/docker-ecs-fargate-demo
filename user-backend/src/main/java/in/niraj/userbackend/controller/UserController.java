package in.niraj.userbackend.controller;

import in.niraj.userbackend.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * created by pc on Jun, 2019
 */

@RestController
public class UserController {

    Logger logger = LoggerFactory.getLogger(UserController.class);

    @Value("${hello.world}")
    private String helloWorldMsg;

    @GetMapping("/hello")
    private String sayHello() {
        logger.info("Check config server property.");
        return helloWorldMsg;
    }


    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUser() {
        logger.info("getAllUser() invoked");
        return new ResponseEntity<>(staticUserList(), HttpStatus.OK);

    }

    private List<User> staticUserList() {
        List<User> userList = new ArrayList<>();
        User user = new User("1", "Niraj", "niraj.darji@email.com");
        User user1 = new User("2", "Umang", "umang.kagathara@email.com");
        User user2 = new User("3", "Supan", "niraj.shah@email.com");
        User user3 = new User("4", "jay", "jay.patel@email.com");

        userList.add(user);
        userList.add(user1);
        userList.add(user2);
        userList.add(user3);

        return userList;

    }


}
