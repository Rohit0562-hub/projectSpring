package io.herald.projectSpring.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

// Controller -> http request handler like get, post etc..
@Controller
public class TotalController {

    @GetMapping("/")
    public String firstPage() {
        return "firstPage"; //returns index.html page
    }
}
