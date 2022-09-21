package hu.ponte.hr.domain.dto;

import lombok.Builder;
import lombok.Getter;

/**
 * @author zoltan
 */
@Getter
@Builder
public class ImageListItem
{
	private String id;
	private String name;
	private String mimeType;
	private long size;
	private String digitalSign;
}
