package hu.ponte.hr.domain.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ImageCommand {
    private Long id;
    private String name;
    private String MimeType;
    private String sign;


    public ImageCommand(String name, String mimeType) {
        this.name = name;
        MimeType = mimeType;
    }


}
