package io.herald.projectSpring.Configuration;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CloudinaryConfig {
    @Bean
    public Cloudinary cloudinary() {

        return new Cloudinary(ObjectUtils.asMap(

                "cloud_name", "dhanqcdo",
                "api_key", "655895386915255",
                "api_secret", "Ni7oaCm3_YVOvZ3bEUmr1MzfM74",
                "secure", true
        ));
    }
}
