package net.renfei.service.cms.dto;

import lombok.Data;
import net.renfei.security.ConfidentialRankEnum;

import java.io.Serializable;
import java.util.Date;

/**
 * 文章数据传输对象
 *
 * @author renfei
 */
@Data
public class PostDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private Long categoryId;
    private Boolean isOriginal;
    private Long views;
    private Long thumbsUp;
    private Long thumbsDown;
    private Date releaseTime;
    private Date addTime;
    private Boolean isDelete;
    private Boolean isComment;
    private Double avgViews;
    private Double avgComment;
    private Double pageRank;
    private Date pageRankUpdateTime;
    private String featuredImage;
    private String title;
    private String content;
    private String sourceUrl;
    private String sourceName;
    private String describes;
    private String keyword;
    private ConfidentialRankEnum confidentialRank;
}
