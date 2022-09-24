package hu.ponte.hr.services;

import hu.ponte.hr.domain.Image;
import hu.ponte.hr.domain.dto.ImageCommand;
import hu.ponte.hr.domain.dto.ImageMeta;
import hu.ponte.hr.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.io.IOException;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class ImageStoreService {

    private final ImageRepository imageRepository;
    private final SignService signService;

    @Autowired
    public ImageStoreService(ImageRepository imageRepository, SignService signService) {
        this.imageRepository = imageRepository;
        this.signService = signService;
    }

    public void saveImageAtByteArray(ImageCommand imageCommand) throws NoSuchAlgorithmException, InvalidKeySpecException, SignatureException, InvalidKeyException, IOException {
        String key = signService.makeDigitalSign(imageCommand.getName());
        imageCommand.setDigitalSign(key);
        this.imageRepository.save(new Image(imageCommand));
    }

    public List<ImageMeta> getAllImage() {
        return imageRepository.findAll().stream().map(ImageMeta::new).collect(Collectors.toList());
    }

    public byte[] getByteArrayById(Long id) {
        return imageRepository.findById(id).orElseThrow(EntityNotFoundException::new).getFileData();
    }





}
