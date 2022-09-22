package hu.ponte.hr.services;

import hu.ponte.hr.domain.Image;
import hu.ponte.hr.domain.dto.ImageCommand;
import hu.ponte.hr.domain.dto.ImageMeta;
import hu.ponte.hr.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.persistence.EntityNotFoundException;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class ImageStoreService {

    private final ImageRepository imageRepository;

    @Autowired
    public ImageStoreService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    public void saveImageAtByteArray(ImageCommand imageCommand) throws NoSuchAlgorithmException, InvalidKeySpecException, SignatureException, InvalidKeyException {
        String key = makeKey(imageCommand.getName() + imageCommand.getMimeType());
        imageCommand.setDigitalSign(key);
        this.imageRepository.save(new Image(imageCommand));
    }

    public List<ImageMeta> getAllImage() {
        return imageRepository.findAll().stream().map(ImageMeta::new).collect(Collectors.toList());
    }

    public byte[] getByteArrayById(Long id) {
        return imageRepository.findById(id).orElseThrow(EntityNotFoundException::new).getFileData();
    }

    private static String makeKey(String originalString) throws NoSuchAlgorithmException, SignatureException, InvalidKeyException {

        KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
        kpg.initialize(2048);
        KeyPair kp = kpg.generateKeyPair();
        PublicKey rsaPublicKey = kp.getPublic();
        PrivateKey rsaPrivateKey = kp.getPrivate();


                Signature sha_rsa = Signature.getInstance("SHA256withRSA");
        sha_rsa.initSign(rsaPrivateKey);

        sha_rsa.update("encodedhash".getBytes(StandardCharsets.UTF_8));
        byte[] signature = sha_rsa.sign();
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(signature);


        return Base64.getEncoder().encodeToString(spec.getEncoded());
    }

    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeySpecException, InvalidKeyException, SignatureException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
        System.out.println(makeKey("dani"));

    }

}
