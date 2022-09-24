package hu.ponte.hr.services;

import hu.ponte.hr.domain.dto.ImageCommand;
import hu.ponte.hr.domain.dto.ImageMeta;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

@SpringBootTest
@Transactional
@Rollback
@AutoConfigureTestDatabase
class ImageStoreServiceTest {


    private final ImageStoreService imageStoreService;


    @Autowired
    ImageStoreServiceTest(ImageStoreService imageStoreService) {
        this.imageStoreService = imageStoreService;
    }


    @Test
    void listImages() throws IOException, NoSuchAlgorithmException, InvalidKeySpecException, SignatureException, InvalidKeyException {
        ImageCommand imageCommand = new ImageCommand("test", "test", 1L,
                Files.readAllBytes(Paths.get("src/test/resources/images/cat.jpg")));

        this.imageStoreService.saveImage(imageCommand);
        this.imageStoreService.saveImage(imageCommand);

        List<ImageMeta> imageMetas = this.imageStoreService.getAllImage();

        Assertions.assertTrue(imageMetas.size() > 0);
        Assertions.assertEquals("test", imageMetas.get(0).getName());
        Assertions.assertEquals(2, imageMetas.get(0).getId());
        Assertions.assertEquals(3, imageMetas.get(1).getId());
        Assertions.assertEquals("test", imageMetas.get(1).getName());
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> imageMetas.get(2));


    }

    @Test
    void getImage() throws IOException, NoSuchAlgorithmException, InvalidKeySpecException, SignatureException, InvalidKeyException {
        ImageCommand imageCommand = new ImageCommand("test", "test", 1L,
                Files.readAllBytes(Paths.get("src/test/resources/images/cat.jpg")));

        this.imageStoreService.saveImage(imageCommand);

        byte[] imageByteArray = this.imageStoreService.getByteArrayByImageId(4L);


        Assertions.assertNotNull(imageByteArray);


    }
}
