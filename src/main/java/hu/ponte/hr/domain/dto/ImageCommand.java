package hu.ponte.hr.domain.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ImageCommand {

    private String id;
    private String name;
    private String mimeType;
    private long size;
    private String digitalSign;
    private byte[] fileData;


    public ImageCommand(String name, String mimeType, long size, byte[] fileData) {
        this.name = name;
        this.mimeType = mimeType;
        this.size = size;
        this.fileData = fileData;
    }
}
