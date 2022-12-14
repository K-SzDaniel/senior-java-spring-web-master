package hu.ponte.hr.domain.dto;

import hu.ponte.hr.domain.Image;
import lombok.Getter;
import lombok.Setter;

/**
 * @author zoltan
 */
@Getter
@Setter
public class ImageMeta
{
	private Long id;
	private String name;
	private String mimeType;
	private String digitalSign;


	public ImageMeta(Image image) {
		this.id = image.getId();
		this.name = image.getName();
		this.mimeType = image.getMimeType();
		this.digitalSign = image.getDigitalSign();
	}
}
