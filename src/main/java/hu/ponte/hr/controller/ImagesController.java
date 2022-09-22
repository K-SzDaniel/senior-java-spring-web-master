package hu.ponte.hr.controller;


import hu.ponte.hr.domain.Image;
import hu.ponte.hr.domain.dto.ImageListItem;
import hu.ponte.hr.services.ImageStoreService;
import org.apache.coyote.Response;
import org.h2.util.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;
import java.util.List;

@RestController()
@RequestMapping("api/images")
public class ImagesController {

    private ImageStoreService imageStoreService;

    @Autowired
    public ImagesController(ImageStoreService imageStoreService) {
        this.imageStoreService = imageStoreService;
    }

    @GetMapping("meta")
    public List<ImageListItem> listImages() {
        return imageStoreService.getAllImage();
    }

    @GetMapping("preview/{id}")
    public void getImage(@PathVariable("id") Long id, HttpServletResponse response) {
//        Image image = imageStoreService.findImageById(id);


    }

}
