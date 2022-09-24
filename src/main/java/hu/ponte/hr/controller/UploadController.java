package hu.ponte.hr.controller;

import hu.ponte.hr.domain.dto.ImageCommand;
import hu.ponte.hr.services.ImageStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Component
@RequestMapping("api/file")
public class UploadController {
    private final ImageStoreService imageStoreService;

    @Autowired
    public UploadController(ImageStoreService imageStoreService) {
        this.imageStoreService = imageStoreService;
    }

    @RequestMapping(value = "post", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Void> handleFormUpload(@RequestParam("file") MultipartFile file) throws IOException {

        this.imageStoreService.saveImage(new ImageCommand(file.getOriginalFilename(),
                file.getContentType(), file.getSize(), file.getBytes()));

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
