package hu.ponte.hr.services;

import hu.ponte.hr.domain.Image;
import hu.ponte.hr.domain.dto.ImageCommand;
import hu.ponte.hr.domain.dto.ImageListItem;
import hu.ponte.hr.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    public List<ImageListItem> getAllImage() {
        return imageRepository.findAll().stream().map(ImageListItem::new).collect(Collectors.toList());
    }
}
