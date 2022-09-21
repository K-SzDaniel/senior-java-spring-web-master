package hu.ponte.hr.services;

import hu.ponte.hr.domain.Image;
import hu.ponte.hr.domain.dto.ImageCommand;
import hu.ponte.hr.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImageStoreService {

    private ImageRepository imageRepository;

    @Autowired
    public ImageStoreService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    public void saveImageAtByteArray(ImageCommand imageCommand) {
        this.imageRepository.save(new Image(imageCommand));
    }
}
