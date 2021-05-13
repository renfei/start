package net.renfei.repository.dao.cms.model;

import java.io.Serializable;
import java.util.Date;

public class TCmsPosts implements Serializable {
    private Long id;

    private Long categoryId;

    private Boolean isOriginal;

    private Long views;

    private Long thumbsUp;

    private Long thumbsDown;

    private Date releaseTime;

    private Date addTime;

    private Boolean isDelete;

    private static final long serialVersionUID = 1L;

    private Boolean isComment;

    private Double avgViews;

    private Double avgComment;

    private Double pageRank;

    private Date pageRankUpdateTime;
    private Integer confidentialRank;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Boolean getIsOriginal() {
        return isOriginal;
    }

    public void setIsOriginal(Boolean isOriginal) {
        this.isOriginal = isOriginal;
    }

    public Long getViews() {
        return views;
    }

    public void setViews(Long views) {
        this.views = views;
    }

    public Long getThumbsUp() {
        return thumbsUp;
    }

    public void setThumbsUp(Long thumbsUp) {
        this.thumbsUp = thumbsUp;
    }

    public Long getThumbsDown() {
        return thumbsDown;
    }

    public void setThumbsDown(Long thumbsDown) {
        this.thumbsDown = thumbsDown;
    }

    public Date getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(Date releaseTime) {
        this.releaseTime = releaseTime;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public Boolean getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
    }

    public Integer getConfidentialRank() {
        return confidentialRank;
    }

    public void setConfidentialRank(Integer confidentialRank) {
        this.confidentialRank = confidentialRank;
    }

    public Boolean getIsComment() {
        return isComment;
    }

    public void setIsComment(Boolean isComment) {
        this.isComment = isComment;
    }

    public Double getAvgViews() {
        return avgViews;
    }

    public void setAvgViews(Double avgViews) {
        this.avgViews = avgViews;
    }

    public Double getAvgComment() {
        return avgComment;
    }

    public void setAvgComment(Double avgComment) {
        this.avgComment = avgComment;
    }

    public Double getPageRank() {
        return pageRank;
    }

    public void setPageRank(Double pageRank) {
        this.pageRank = pageRank;
    }

    public Date getPageRankUpdateTime() {
        return pageRankUpdateTime;
    }

    public void setPageRankUpdateTime(Date pageRankUpdateTime) {
        this.pageRankUpdateTime = pageRankUpdateTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", categoryId=").append(categoryId);
        sb.append(", isOriginal=").append(isOriginal);
        sb.append(", views=").append(views);
        sb.append(", thumbsUp=").append(thumbsUp);
        sb.append(", thumbsDown=").append(thumbsDown);
        sb.append(", releaseTime=").append(releaseTime);
        sb.append(", addTime=").append(addTime);
        sb.append(", isDelete=").append(isDelete);
        sb.append(", confidentialRank=").append(confidentialRank);
        sb.append(", isComment=").append(isComment);
        sb.append(", avgViews=").append(avgViews);
        sb.append(", avgComment=").append(avgComment);
        sb.append(", pageRank=").append(pageRank);
        sb.append(", pageRankUpdateTime=").append(pageRankUpdateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}