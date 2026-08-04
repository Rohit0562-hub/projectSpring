package io.herald.projectSpring.RController;

import io.herald.projectSpring.Model.UserTable;
import io.herald.projectSpring.Repository.ImageRepository;
import io.herald.projectSpring.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/getId/{id}")
    public ResponseEntity<?> getId(@PathVariable int id) {

        if (uRepo.findById(id).isPresent()) {

            return ResponseEntity.ok(uRepo.findById(id).get());
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Id Not Found");
    }


}


