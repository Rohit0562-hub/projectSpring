package io.herald.projectSpring.Controller;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import io.herald.projectSpring.Model.ImageTable;
import io.herald.projectSpring.Model.ImageTable2;
import io.herald.projectSpring.Repository.Image2Repository;
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
import java.util.Map;

@Controller
public class GalleryController {

    @Autowired
    private ImageRepository imgRepo;
    @Autowired
    private Cloudinary cloudinary;
    @Autowired
    private Image2Repository img2Repo;

    @GetMapping("/gallery")
    public String galleryGet(HttpServletRequest request, Model m) {

        HttpSession session = request.getSession();

        if(session.getAttribute("username")==null) {

            m.addAttribute("message", "You are not logged in");
            return "loginPage";
        }

        m.addAttribute("totalImages", imgRepo.findAll());
        return "galleryPage";
    }
    @PostMapping("/galleryPost")
    public String galleryPost(@RequestParam("imgFile")MultipartFile imgFile, Model m) {

        try {

            byte[] imgBytes = imgFile.getBytes();

            String imgString = Base64.getEncoder().encodeToString(imgBytes);

            ImageTable img = new ImageTable();

            img.setImage(imgString);
            imgRepo.save(img);

        } catch (IOException e) {
            e.printStackTrace();
        }

        m.addAttribute("totalImages", imgRepo.findAll());
        return "galleryPage";
    }

    @GetMapping("/gallery2")
    public String gallery2Get(Model m)  {

        m.addAttribute("cloudImages", img2Repo.findAll());
        return "galleryPage2";
    }

    @PostMapping("/gallery2")
    public String gallery2Post(@RequestParam("imgFile") MultipartFile imgFile, Model m) {

        try {

            Map uploadResult = cloudinary.uploader().upload(imgFile.getBytes(), ObjectUtils.emptyMap());

            String imgUrl = uploadResult.get("secure_url").toString();

            ImageTable2 img = new ImageTable2();
            img.setImageUrl(imgUrl);
            img2Repo.save(img);

        } catch (IOException e) {
            e.printStackTrace();
        }

        m.addAttribute("cloudImages", img2Repo.findAll());
        return "galleryPage2";
    }
}
