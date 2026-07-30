package io.herald.projectSpring.Controller;

import io.herald.projectSpring.Model.ImageTable;
import io.herald.projectSpring.Repository.ImageRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;

@Controller
public class GalleryController {

    @Autowired
    private ImageRepository imgRepo;

    @GetMapping("/gallery")
    public String galleryGet(HttpServletRequest request, Model m) {

        HttpSession session = request.getSession();

        if(session.getAttribute("username")==null) {

            m.addAttribute("message", "You are not logged in");
            return "loginPage";
        }
        return "galleryPage";
    }

    @PostMapping("/galleryPost")
    public String galleryPost(@RequestParam("imgFile")MultipartFile imgFile, HttpSession session) {

        try {

            byte[] imgBytes = imgFile.getBytes();

            String imgString = Base64.getEncoder().encodeToString(imgBytes);

            ImageTable img = new ImageTable();

            img.setImage(imgString);
            imgRepo.save(img);

        } catch (IOException e) {
            e.printStackTrace();
        }
        session.setAttribute("totalImages", imgRepo.findAll());
        return "galleryPage";
    }
}
