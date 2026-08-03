package io.herald.projectSpring.RController;

import io.herald.projectSpring.Model.UserTable;
import io.herald.projectSpring.Repository.ImageRepository;
import io.herald.projectSpring.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RControllerClass {

    @Autowired
    private ImageRepository imgRepo;
    @Autowired
    private UserRepository uRepo;


    @GetMapping("/api/hello")
    public String hello() {

        return "Hello World";
    }

    @GetMapping("/api/getAllUsers")
    public List<UserTable> getAllUsers() {

        return uRepo.findAll();
    }

    @PostMapping("/api/saveUser")
    public String saveUser(@RequestBody UserTable user) {

        //@RequestBody -> JSON ma data aako cha bhane, request body lekhna parxa

        uRepo.save(user);

        return "Saved Successfully";
    }
}
