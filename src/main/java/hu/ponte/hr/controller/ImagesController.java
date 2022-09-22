package hu.ponte.hr.controller;


import hu.ponte.hr.domain.dto.ImageMeta;
import hu.ponte.hr.services.ImageStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
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
    public ResponseEntity<List<ImageMeta>> listImages() {
        return new ResponseEntity<>(imageStoreService.getAllImage(), HttpStatus.OK);
    }

    @GetMapping("preview/{id}")
    public ResponseEntity<byte[]> getImage(@PathVariable("id") Long id, HttpServletResponse response) {
        return new ResponseEntity<>(imageStoreService.getByteArrayById(id), HttpStatus.OK);
    }

}
