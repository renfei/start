package net.renfei.repository.dao.cms.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TCmsPostsExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TCmsPostsExample() {
        oredCriteria = new ArrayList<>();
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("`id` is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("`id` is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("`id` =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("`id` <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("`id` >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("`id` >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("`id` <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("`id` <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("`id` in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("`id` not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("`id` between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("`id` not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andCategoryIdIsNull() {
            addCriterion("`category_id` is null");
            return (Criteria) this;
        }

        public Criteria andCategoryIdIsNotNull() {
            addCriterion("`category_id` is not null");
            return (Criteria) this;
        }

        public Criteria andCategoryIdEqualTo(Long value) {
            addCriterion("`category_id` =", value, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdNotEqualTo(Long value) {
            addCriterion("`category_id` <>", value, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdGreaterThan(Long value) {
            addCriterion("`category_id` >", value, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdGreaterThanOrEqualTo(Long value) {
            addCriterion("`category_id` >=", value, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdLessThan(Long value) {
            addCriterion("`category_id` <", value, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdLessThanOrEqualTo(Long value) {
            addCriterion("`category_id` <=", value, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdIn(List<Long> values) {
            addCriterion("`category_id` in", values, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdNotIn(List<Long> values) {
            addCriterion("`category_id` not in", values, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdBetween(Long value1, Long value2) {
            addCriterion("`category_id` between", value1, value2, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdNotBetween(Long value1, Long value2) {
            addCriterion("`category_id` not between", value1, value2, "categoryId");
            return (Criteria) this;
        }

        public Criteria andIsOriginalIsNull() {
            addCriterion("`is_original` is null");
            return (Criteria) this;
        }

        public Criteria andIsOriginalIsNotNull() {
            addCriterion("`is_original` is not null");
            return (Criteria) this;
        }

        public Criteria andIsOriginalEqualTo(Boolean value) {
            addCriterion("`is_original` =", value, "isOriginal");
            return (Criteria) this;
        }

        public Criteria andIsOriginalNotEqualTo(Boolean value) {
            addCriterion("`is_original` <>", value, "isOriginal");
            return (Criteria) this;
        }

        public Criteria andIsOriginalGreaterThan(Boolean value) {
            addCriterion("`is_original` >", value, "isOriginal");
            return (Criteria) this;
        }

        public Criteria andIsOriginalGreaterThanOrEqualTo(Boolean value) {
            addCriterion("`is_original` >=", value, "isOriginal");
            return (Criteria) this;
        }

        public Criteria andIsOriginalLessThan(Boolean value) {
            addCriterion("`is_original` <", value, "isOriginal");
            return (Criteria) this;
        }

        public Criteria andIsOriginalLessThanOrEqualTo(Boolean value) {
            addCriterion("`is_original` <=", value, "isOriginal");
            return (Criteria) this;
        }

        public Criteria andIsOriginalIn(List<Boolean> values) {
            addCriterion("`is_original` in", values, "isOriginal");
            return (Criteria) this;
        }

        public Criteria andIsOriginalNotIn(List<Boolean> values) {
            addCriterion("`is_original` not in", values, "isOriginal");
            return (Criteria) this;
        }

        public Criteria andIsOriginalBetween(Boolean value1, Boolean value2) {
            addCriterion("`is_original` between", value1, value2, "isOriginal");
            return (Criteria) this;
        }

        public Criteria andIsOriginalNotBetween(Boolean value1, Boolean value2) {
            addCriterion("`is_original` not between", value1, value2, "isOriginal");
            return (Criteria) this;
        }

        public Criteria andViewsIsNull() {
            addCriterion("`views` is null");
            return (Criteria) this;
        }

        public Criteria andViewsIsNotNull() {
            addCriterion("`views` is not null");
            return (Criteria) this;
        }

        public Criteria andViewsEqualTo(Long value) {
            addCriterion("`views` =", value, "views");
            return (Criteria) this;
        }

        public Criteria andViewsNotEqualTo(Long value) {
            addCriterion("`views` <>", value, "views");
            return (Criteria) this;
        }

        public Criteria andViewsGreaterThan(Long value) {
            addCriterion("`views` >", value, "views");
            return (Criteria) this;
        }

        public Criteria andViewsGreaterThanOrEqualTo(Long value) {
            addCriterion("`views` >=", value, "views");
            return (Criteria) this;
        }

        public Criteria andViewsLessThan(Long value) {
            addCriterion("`views` <", value, "views");
            return (Criteria) this;
        }

        public Criteria andViewsLessThanOrEqualTo(Long value) {
            addCriterion("`views` <=", value, "views");
            return (Criteria) this;
        }

        public Criteria andViewsIn(List<Long> values) {
            addCriterion("`views` in", values, "views");
            return (Criteria) this;
        }

        public Criteria andViewsNotIn(List<Long> values) {
            addCriterion("`views` not in", values, "views");
            return (Criteria) this;
        }

        public Criteria andViewsBetween(Long value1, Long value2) {
            addCriterion("`views` between", value1, value2, "views");
            return (Criteria) this;
        }

        public Criteria andViewsNotBetween(Long value1, Long value2) {
            addCriterion("`views` not between", value1, value2, "views");
            return (Criteria) this;
        }

        public Criteria andThumbsUpIsNull() {
            addCriterion("`thumbs_up` is null");
            return (Criteria) this;
        }

        public Criteria andThumbsUpIsNotNull() {
            addCriterion("`thumbs_up` is not null");
            return (Criteria) this;
        }

        public Criteria andThumbsUpEqualTo(Long value) {
            addCriterion("`thumbs_up` =", value, "thumbsUp");
            return (Criteria) this;
        }

        public Criteria andThumbsUpNotEqualTo(Long value) {
            addCriterion("`thumbs_up` <>", value, "thumbsUp");
            return (Criteria) this;
        }

        public Criteria andThumbsUpGreaterThan(Long value) {
            addCriterion("`thumbs_up` >", value, "thumbsUp");
            return (Criteria) this;
        }

        public Criteria andThumbsUpGreaterThanOrEqualTo(Long value) {
            addCriterion("`thumbs_up` >=", value, "thumbsUp");
            return (Criteria) this;
        }

        public Criteria andThumbsUpLessThan(Long value) {
            addCriterion("`thumbs_up` <", value, "thumbsUp");
            return (Criteria) this;
        }

        public Criteria andThumbsUpLessThanOrEqualTo(Long value) {
            addCriterion("`thumbs_up` <=", value, "thumbsUp");
            return (Criteria) this;
        }

        public Criteria andThumbsUpIn(List<Long> values) {
            addCriterion("`thumbs_up` in", values, "thumbsUp");
            return (Criteria) this;
        }

        public Criteria andThumbsUpNotIn(List<Long> values) {
            addCriterion("`thumbs_up` not in", values, "thumbsUp");
            return (Criteria) this;
        }

        public Criteria andThumbsUpBetween(Long value1, Long value2) {
            addCriterion("`thumbs_up` between", value1, value2, "thumbsUp");
            return (Criteria) this;
        }

        public Criteria andThumbsUpNotBetween(Long value1, Long value2) {
            addCriterion("`thumbs_up` not between", value1, value2, "thumbsUp");
            return (Criteria) this;
        }

        public Criteria andThumbsDownIsNull() {
            addCriterion("`thumbs_down` is null");
            return (Criteria) this;
        }

        public Criteria andThumbsDownIsNotNull() {
            addCriterion("`thumbs_down` is not null");
            return (Criteria) this;
        }

        public Criteria andThumbsDownEqualTo(Long value) {
            addCriterion("`thumbs_down` =", value, "thumbsDown");
            return (Criteria) this;
        }

        public Criteria andThumbsDownNotEqualTo(Long value) {
            addCriterion("`thumbs_down` <>", value, "thumbsDown");
            return (Criteria) this;
        }

        public Criteria andThumbsDownGreaterThan(Long value) {
            addCriterion("`thumbs_down` >", value, "thumbsDown");
            return (Criteria) this;
        }

        public Criteria andThumbsDownGreaterThanOrEqualTo(Long value) {
            addCriterion("`thumbs_down` >=", value, "thumbsDown");
            return (Criteria) this;
        }

        public Criteria andThumbsDownLessThan(Long value) {
            addCriterion("`thumbs_down` <", value, "thumbsDown");
            return (Criteria) this;
        }

        public Criteria andThumbsDownLessThanOrEqualTo(Long value) {
            addCriterion("`thumbs_down` <=", value, "thumbsDown");
            return (Criteria) this;
        }

        public Criteria andThumbsDownIn(List<Long> values) {
            addCriterion("`thumbs_down` in", values, "thumbsDown");
            return (Criteria) this;
        }

        public Criteria andThumbsDownNotIn(List<Long> values) {
            addCriterion("`thumbs_down` not in", values, "thumbsDown");
            return (Criteria) this;
        }

        public Criteria andThumbsDownBetween(Long value1, Long value2) {
            addCriterion("`thumbs_down` between", value1, value2, "thumbsDown");
            return (Criteria) this;
        }

        public Criteria andThumbsDownNotBetween(Long value1, Long value2) {
            addCriterion("`thumbs_down` not between", value1, value2, "thumbsDown");
            return (Criteria) this;
        }

        public Criteria andReleaseTimeIsNull() {
            addCriterion("`release_time` is null");
            return (Criteria) this;
        }

        public Criteria andReleaseTimeIsNotNull() {
            addCriterion("`release_time` is not null");
            return (Criteria) this;
        }

        public Criteria andReleaseTimeEqualTo(Date value) {
            addCriterion("`release_time` =", value, "releaseTime");
            return (Criteria) this;
        }

        public Criteria andReleaseTimeNotEqualTo(Date value) {
            addCriterion("`release_time` <>", value, "releaseTime");
            return (Criteria) this;
        }

        public Criteria andReleaseTimeGreaterThan(Date value) {
            addCriterion("`release_time` >", value, "releaseTime");
            return (Criteria) this;
        }

        public Criteria andReleaseTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("`release_time` >=", value, "releaseTime");
            return (Criteria) this;
        }

        public Criteria andReleaseTimeLessThan(Date value) {
            addCriterion("`release_time` <", value, "releaseTime");
            return (Criteria) this;
        }

        public Criteria andReleaseTimeLessThanOrEqualTo(Date value) {
            addCriterion("`release_time` <=", value, "releaseTime");
            return (Criteria) this;
        }

        public Criteria andReleaseTimeIn(List<Date> values) {
            addCriterion("`release_time` in", values, "releaseTime");
            return (Criteria) this;
        }

        public Criteria andReleaseTimeNotIn(List<Date> values) {
            addCriterion("`release_time` not in", values, "releaseTime");
            return (Criteria) this;
        }

        public Criteria andReleaseTimeBetween(Date value1, Date value2) {
            addCriterion("`release_time` between", value1, value2, "releaseTime");
            return (Criteria) this;
        }

        public Criteria andReleaseTimeNotBetween(Date value1, Date value2) {
            addCriterion("`release_time` not between", value1, value2, "releaseTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeIsNull() {
            addCriterion("`add_time` is null");
            return (Criteria) this;
        }

        public Criteria andAddTimeIsNotNull() {
            addCriterion("`add_time` is not null");
            return (Criteria) this;
        }

        public Criteria andAddTimeEqualTo(Date value) {
            addCriterion("`add_time` =", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeNotEqualTo(Date value) {
            addCriterion("`add_time` <>", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeGreaterThan(Date value) {
            addCriterion("`add_time` >", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("`add_time` >=", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeLessThan(Date value) {
            addCriterion("`add_time` <", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeLessThanOrEqualTo(Date value) {
            addCriterion("`add_time` <=", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeIn(List<Date> values) {
            addCriterion("`add_time` in", values, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeNotIn(List<Date> values) {
            addCriterion("`add_time` not in", values, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeBetween(Date value1, Date value2) {
            addCriterion("`add_time` between", value1, value2, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeNotBetween(Date value1, Date value2) {
            addCriterion("`add_time` not between", value1, value2, "addTime");
            return (Criteria) this;
        }

        public Criteria andIsDeleteIsNull() {
            addCriterion("`is_delete` is null");
            return (Criteria) this;
        }

        public Criteria andIsDeleteIsNotNull() {
            addCriterion("`is_delete` is not null");
            return (Criteria) this;
        }

        public Criteria andIsDeleteEqualTo(Boolean value) {
            addCriterion("`is_delete` =", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteNotEqualTo(Boolean value) {
            addCriterion("`is_delete` <>", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteGreaterThan(Boolean value) {
            addCriterion("`is_delete` >", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteGreaterThanOrEqualTo(Boolean value) {
            addCriterion("`is_delete` >=", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteLessThan(Boolean value) {
            addCriterion("`is_delete` <", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteLessThanOrEqualTo(Boolean value) {
            addCriterion("`is_delete` <=", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteIn(List<Boolean> values) {
            addCriterion("`is_delete` in", values, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteNotIn(List<Boolean> values) {
            addCriterion("`is_delete` not in", values, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteBetween(Boolean value1, Boolean value2) {
            addCriterion("`is_delete` between", value1, value2, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteNotBetween(Boolean value1, Boolean value2) {
            addCriterion("`is_delete` not between", value1, value2, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsCommentIsNull() {
            addCriterion("`is_comment` is null");
            return (Criteria) this;
        }

        public Criteria andIsCommentIsNotNull() {
            addCriterion("`is_comment` is not null");
            return (Criteria) this;
        }

        public Criteria andIsCommentEqualTo(Boolean value) {
            addCriterion("`is_comment` =", value, "isComment");
            return (Criteria) this;
        }

        public Criteria andIsCommentNotEqualTo(Boolean value) {
            addCriterion("`is_comment` <>", value, "isComment");
            return (Criteria) this;
        }

        public Criteria andIsCommentGreaterThan(Boolean value) {
            addCriterion("`is_comment` >", value, "isComment");
            return (Criteria) this;
        }

        public Criteria andIsCommentGreaterThanOrEqualTo(Boolean value) {
            addCriterion("`is_comment` >=", value, "isComment");
            return (Criteria) this;
        }

        public Criteria andIsCommentLessThan(Boolean value) {
            addCriterion("`is_comment` <", value, "isComment");
            return (Criteria) this;
        }

        public Criteria andIsCommentLessThanOrEqualTo(Boolean value) {
            addCriterion("`is_comment` <=", value, "isComment");
            return (Criteria) this;
        }

        public Criteria andIsCommentIn(List<Boolean> values) {
            addCriterion("`is_comment` in", values, "isComment");
            return (Criteria) this;
        }

        public Criteria andIsCommentNotIn(List<Boolean> values) {
            addCriterion("`is_comment` not in", values, "isComment");
            return (Criteria) this;
        }

        public Criteria andIsCommentBetween(Boolean value1, Boolean value2) {
            addCriterion("`is_comment` between", value1, value2, "isComment");
            return (Criteria) this;
        }

        public Criteria andIsCommentNotBetween(Boolean value1, Boolean value2) {
            addCriterion("`is_comment` not between", value1, value2, "isComment");
            return (Criteria) this;
        }

        public Criteria andAvgViewsIsNull() {
            addCriterion("`avg_views` is null");
            return (Criteria) this;
        }

        public Criteria andAvgViewsIsNotNull() {
            addCriterion("`avg_views` is not null");
            return (Criteria) this;
        }

        public Criteria andAvgViewsEqualTo(Double value) {
            addCriterion("`avg_views` =", value, "avgViews");
            return (Criteria) this;
        }

        public Criteria andAvgViewsNotEqualTo(Double value) {
            addCriterion("`avg_views` <>", value, "avgViews");
            return (Criteria) this;
        }

        public Criteria andAvgViewsGreaterThan(Double value) {
            addCriterion("`avg_views` >", value, "avgViews");
            return (Criteria) this;
        }

        public Criteria andAvgViewsGreaterThanOrEqualTo(Double value) {
            addCriterion("`avg_views` >=", value, "avgViews");
            return (Criteria) this;
        }

        public Criteria andAvgViewsLessThan(Double value) {
            addCriterion("`avg_views` <", value, "avgViews");
            return (Criteria) this;
        }

        public Criteria andAvgViewsLessThanOrEqualTo(Double value) {
            addCriterion("`avg_views` <=", value, "avgViews");
            return (Criteria) this;
        }

        public Criteria andAvgViewsIn(List<Double> values) {
            addCriterion("`avg_views` in", values, "avgViews");
            return (Criteria) this;
        }

        public Criteria andAvgViewsNotIn(List<Double> values) {
            addCriterion("`avg_views` not in", values, "avgViews");
            return (Criteria) this;
        }

        public Criteria andAvgViewsBetween(Double value1, Double value2) {
            addCriterion("`avg_views` between", value1, value2, "avgViews");
            return (Criteria) this;
        }

        public Criteria andAvgViewsNotBetween(Double value1, Double value2) {
            addCriterion("`avg_views` not between", value1, value2, "avgViews");
            return (Criteria) this;
        }

        public Criteria andAvgCommentIsNull() {
            addCriterion("`avg_comment` is null");
            return (Criteria) this;
        }

        public Criteria andAvgCommentIsNotNull() {
            addCriterion("`avg_comment` is not null");
            return (Criteria) this;
        }

        public Criteria andAvgCommentEqualTo(Double value) {
            addCriterion("`avg_comment` =", value, "avgComment");
            return (Criteria) this;
        }

        public Criteria andAvgCommentNotEqualTo(Double value) {
            addCriterion("`avg_comment` <>", value, "avgComment");
            return (Criteria) this;
        }

        public Criteria andAvgCommentGreaterThan(Double value) {
            addCriterion("`avg_comment` >", value, "avgComment");
            return (Criteria) this;
        }

        public Criteria andAvgCommentGreaterThanOrEqualTo(Double value) {
            addCriterion("`avg_comment` >=", value, "avgComment");
            return (Criteria) this;
        }

        public Criteria andAvgCommentLessThan(Double value) {
            addCriterion("`avg_comment` <", value, "avgComment");
            return (Criteria) this;
        }

        public Criteria andAvgCommentLessThanOrEqualTo(Double value) {
            addCriterion("`avg_comment` <=", value, "avgComment");
            return (Criteria) this;
        }

        public Criteria andAvgCommentIn(List<Double> values) {
            addCriterion("`avg_comment` in", values, "avgComment");
            return (Criteria) this;
        }

        public Criteria andAvgCommentNotIn(List<Double> values) {
            addCriterion("`avg_comment` not in", values, "avgComment");
            return (Criteria) this;
        }

        public Criteria andAvgCommentBetween(Double value1, Double value2) {
            addCriterion("`avg_comment` between", value1, value2, "avgComment");
            return (Criteria) this;
        }

        public Criteria andAvgCommentNotBetween(Double value1, Double value2) {
            addCriterion("`avg_comment` not between", value1, value2, "avgComment");
            return (Criteria) this;
        }

        public Criteria andPageRankIsNull() {
            addCriterion("`page_rank` is null");
            return (Criteria) this;
        }

        public Criteria andPageRankIsNotNull() {
            addCriterion("`page_rank` is not null");
            return (Criteria) this;
        }

        public Criteria andPageRankEqualTo(Double value) {
            addCriterion("`page_rank` =", value, "pageRank");
            return (Criteria) this;
        }

        public Criteria andPageRankNotEqualTo(Double value) {
            addCriterion("`page_rank` <>", value, "pageRank");
            return (Criteria) this;
        }

        public Criteria andPageRankGreaterThan(Double value) {
            addCriterion("`page_rank` >", value, "pageRank");
            return (Criteria) this;
        }

        public Criteria andPageRankGreaterThanOrEqualTo(Double value) {
            addCriterion("`page_rank` >=", value, "pageRank");
            return (Criteria) this;
        }

        public Criteria andPageRankLessThan(Double value) {
            addCriterion("`page_rank` <", value, "pageRank");
            return (Criteria) this;
        }

        public Criteria andPageRankLessThanOrEqualTo(Double value) {
            addCriterion("`page_rank` <=", value, "pageRank");
            return (Criteria) this;
        }

        public Criteria andPageRankIn(List<Double> values) {
            addCriterion("`page_rank` in", values, "pageRank");
            return (Criteria) this;
        }

        public Criteria andPageRankNotIn(List<Double> values) {
            addCriterion("`page_rank` not in", values, "pageRank");
            return (Criteria) this;
        }

        public Criteria andPageRankBetween(Double value1, Double value2) {
            addCriterion("`page_rank` between", value1, value2, "pageRank");
            return (Criteria) this;
        }

        public Criteria andPageRankNotBetween(Double value1, Double value2) {
            addCriterion("`page_rank` not between", value1, value2, "pageRank");
            return (Criteria) this;
        }

        public Criteria andPageRankUpdateTimeIsNull() {
            addCriterion("`page_rank_update_time` is null");
            return (Criteria) this;
        }

        public Criteria andPageRankUpdateTimeIsNotNull() {
            addCriterion("`page_rank_update_time` is not null");
            return (Criteria) this;
        }

        public Criteria andPageRankUpdateTimeEqualTo(Date value) {
            addCriterion("`page_rank_update_time` =", value, "pageRankUpdateTime");
            return (Criteria) this;
        }

        public Criteria andPageRankUpdateTimeNotEqualTo(Date value) {
            addCriterion("`page_rank_update_time` <>", value, "pageRankUpdateTime");
            return (Criteria) this;
        }

        public Criteria andPageRankUpdateTimeGreaterThan(Date value) {
            addCriterion("`page_rank_update_time` >", value, "pageRankUpdateTime");
            return (Criteria) this;
        }

        public Criteria andPageRankUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("`page_rank_update_time` >=", value, "pageRankUpdateTime");
            return (Criteria) this;
        }

        public Criteria andPageRankUpdateTimeLessThan(Date value) {
            addCriterion("`page_rank_update_time` <", value, "pageRankUpdateTime");
            return (Criteria) this;
        }

        public Criteria andPageRankUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("`page_rank_update_time` <=", value, "pageRankUpdateTime");
            return (Criteria) this;
        }

        public Criteria andPageRankUpdateTimeIn(List<Date> values) {
            addCriterion("`page_rank_update_time` in", values, "pageRankUpdateTime");
            return (Criteria) this;
        }

        public Criteria andPageRankUpdateTimeNotIn(List<Date> values) {
            addCriterion("`page_rank_update_time` not in", values, "pageRankUpdateTime");
            return (Criteria) this;
        }

        public Criteria andPageRankUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("`page_rank_update_time` between", value1, value2, "pageRankUpdateTime");
            return (Criteria) this;
        }

        public Criteria andPageRankUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("`page_rank_update_time` not between", value1, value2, "pageRankUpdateTime");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {
        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private final String condition;
        private final String typeHandler;
        private Object value;
        private Object secondValue;
        private boolean noValue;
        private boolean singleValue;
        private boolean betweenValue;
        private boolean listValue;

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }
    }
}