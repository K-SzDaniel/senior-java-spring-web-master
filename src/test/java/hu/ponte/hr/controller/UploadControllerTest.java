package hu.ponte.hr.controller;

import hu.ponte.hr.domain.dto.ImageMeta;
import hu.ponte.hr.services.ImageStoreService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;

@SpringBootTest
@Transactional
@Rollback
@AutoConfigureTestDatabase
class UploadControllerTest {


    private final ImageStoreService imageStoreService;
    private UploadController uploadController;

    @Autowired
    public UploadControllerTest(ImageStoreService imageStoreService) {
        this.imageStoreService = imageStoreService;
    }

    @BeforeEach
    void setUp() {
        this.uploadController = new UploadController(imageStoreService);
    }


    @Test
    void handleFormUpload() throws IOException, NoSuchAlgorithmException, InvalidKeySpecException, SignatureException, InvalidKeyException {
        MockMultipartFile file = new MockMultipartFile("test", "test", "test",
                Files.readAllBytes(Paths.get("src/test/resources/images/cat.jpg")));

        this.uploadController.handleFormUpload(file);

        ImageMeta image = imageStoreService.getAllImage().get(0);
        Assertions.assertEquals("test", image.getName());
        Assertions.assertEquals("test", image.getMimeType());
        Assertions.assertEquals(1, image.getId());
        Assertions.assertNotNull(image.getDigitalSign());

    }

}
