package io.herald.projectSpring.Controller;

import io.herald.projectSpring.Model.UserTable;
import io.herald.projectSpring.Repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private PasswordEncoder passwordEncoder;

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

//        if(uRepo.existsByUserNameAndPassword(username, hashPassword)) {

        try {
            UserTable user = uRepo.findByUsername(username);

            if (user != null && passwordEncoder.matches(password, user.getPassword())) {

                List<UserTable> userList = uRepo.findAll();
                m.addAttribute("userList", userList);

                HttpSession session = request.getSession();
                //Session revolves around the http requests, we are trying to get a running session with the help of above code.
                //After a successful signing, a username is provided a session account to their username
                session.setAttribute("username", username);
                return "homePage";
            }

        } catch (Exception e) {
            //message lai model ko attribute bhaninxa
            m.addAttribute("message", "Too many Username!!!");
        }

        return "loginPage";
    }
    @PostMapping("/signup")
    public String signupPost(HttpServletRequest request, Model m) {

        String username, password, email;

        username = request.getParameter("username");
        password = request.getParameter("password");
        email = request.getParameter("email");

        //String hashPassword= DigestUtils.md5DigestAsHex(password.getBytes());

        String hashPassword = passwordEncoder.encode(password);
        UserTable ut = new UserTable();
        ut.setPassword(hashPassword);
        ut.setUsername(username);

        uRepo.save(ut);

        //Mail Sender
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Signed Up Successfully");
        message.setText("Welcome to the club:" + username + "!!!");
//      mailSender.send(message);


        m.addAttribute("signupSuccess", "Successfully Signed Up. Please login");
        return "loginPage";
    }

    @GetMapping("/home")
    public String homePage(HttpServletRequest request, Model m) {

        HttpSession session = request.getSession();

        if (session.getAttribute("username")==null) {

            m.addAttribute("message", "You are not logged in. Please login!!");
            return "loginPage";
        }

        List<UserTable> userList = uRepo.findAll();
        m.addAttribute("userList", userList);
        return "homePage";
    }
}
