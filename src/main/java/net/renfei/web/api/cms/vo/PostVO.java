package net.renfei.web.api.cms.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author renfei
 */
@Data
public class PostVO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private Long categoryId;
    private Boolean isOriginal;
    private Long views;
    private Long thumbsUp;
    private Long thumbsDown;
    private Date releaseTime;
    private Date addTime;
    private Boolean isComment;
    private Double avgViews;
    private Double avgComment;
    private Double pageRank;
    private String featuredImage;
    private String title;
    private String content;
    private String sourceUrl;
    private String sourceName;
    private String describes;
    private String keyword;
}
