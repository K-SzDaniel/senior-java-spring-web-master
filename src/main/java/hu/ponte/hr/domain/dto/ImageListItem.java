package hu.ponte.hr.domain.dto;

import hu.ponte.hr.domain.Image;
import lombok.Builder;
import lombok.Getter;

/**
 * @author zoltan
 */
@Getter
public class ImageListItem
{
	private Long id;
	private String name;
	private String mimeType;
	private String digitalSign;


	public ImageListItem(Image image) {
		this.id = image.getId();
		this.name = image.getName();
		this.mimeType = image.getMimeType();
		this.digitalSign = image.getDigitalSign();
	}
}
