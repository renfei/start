package net.renfei.service.cms.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author renfei
 */
@Data
public class CategoryDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String enName;
    private String zhName;
    private String featuredImage;
}
