package io.herald.projectSpring.Controller;

import io.herald.projectSpring.Model.UserTable;
import io.herald.projectSpring.Repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

// Controller -> http request handler like get, post etc..
@Controller
public class TotalController {


    //Autowired helps in dependency injection, provides all the required functions and API's to a class/interface obj no new keyword is  required
    @Autowired
    private UserRepository uRepo;

    @GetMapping("/")
    public String firstPage() {
        return "firstPage"; //returns index.html page
    }

    @GetMapping("/signup")
    public String signupPage() {
        return "signupPage";
    }

    @GetMapping("/login")
    public String loginGet() {
        return "loginPage";
    }

    @PostMapping("/loginPost")
    public String loginPost(HttpServletRequest request, Model m) {

        String username, password;

        username = request.getParameter("username");
        password = request.getParameter("password");

        String hashPassword = DigestUtils.md5DigestAsHex(password.getBytes());
        //Static Login
//        if (username.equals("admin") && password.equals("admin")) {
//            return "homePage";
//        }

        //RepositoryLogin

        if(uRepo.existsByUserNameAndPassword(username, hashPassword)) {

            List<UserTable> userList = uRepo.findAll();
            m.addAttribute("userList", userList);
            return "homePage";
        }


        //message lai model ko attribute bhaninxa
        m.addAttribute("loginError", "Username or password incorrect");
        return "loginPage";

    }

    @PostMapping("/signup")
    public String signupPost(HttpServletRequest request, Model m) {

        String username, password;

        username = request.getParameter("username");
        password = request.getParameter("password");

        String hashPassword= DigestUtils.md5DigestAsHex(password.getBytes());
        UserTable ut = new UserTable();
        ut.setPassword(hashPassword);
        ut.setUserName(username);

        uRepo.save(ut);

        m.addAttribute("signupSuccess", "Successfully Signed Up. Please login");
        return "loginPage";
    }
}
