package hu.ponte.hr.services;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;

@Service
public class SignService {


    public String makeDigitalSign(String fileName) throws NoSuchAlgorithmException, SignatureException, InvalidKeyException, IOException, InvalidKeySpecException {
        byte[] key = Files.readAllBytes(Paths.get("src/main/resources/config/keys/key.private"));
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(key);
        PrivateKey finalKey = keyFactory.generatePrivate(keySpec);

        Signature shaRsa = Signature.getInstance("SHA256withRSA");
        shaRsa.initSign(finalKey);

        shaRsa.update(fileName.getBytes(StandardCharsets.UTF_8));
        byte[] signature = shaRsa.sign();
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(signature);

        return Base64.getEncoder().encodeToString(spec.getEncoded());
    }
}
