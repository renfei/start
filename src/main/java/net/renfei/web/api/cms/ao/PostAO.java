package net.renfei.web.api.cms.ao;

import lombok.Data;
import net.renfei.security.ConfidentialRankEnum;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author renfei
 */
@Data
public class PostAO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private Long categoryId;
    private Boolean isOriginal;
    private Date releaseTime;
    private Boolean isComment;
    private MultipartFile featuredImageFile;
    private String title;
    private String content;
    private String sourceUrl;
    private String sourceName;
    private String describes;
    private String keyword;
    private List<Long> tags;
    private ConfidentialRankEnum confidentialRank;
}
