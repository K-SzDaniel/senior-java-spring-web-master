package hu.ponte.hr.domain;

import hu.ponte.hr.domain.dto.ImageCommand;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String mimeType;
    private long size;
    @Column(length = 1000)
    private String digitalSign;
    @Column(length = 10_000_000)
    private byte[] fileData;


    public Image() {

    }

    public Image(ImageCommand imageCommand) {
        this.name = imageCommand.getName();
        this.mimeType = imageCommand.getMimeType();
        this.size = imageCommand.getSize();
        this.digitalSign = imageCommand.getDigitalSign();
        this.fileData = imageCommand.getFileData();
    }
}
