package net.renfei.repository.dao.start.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TStartOperationLogExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TStartOperationLogExample() {
        oredCriteria = new ArrayList<>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
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

        public Criteria andOperDateIsNull() {
            addCriterion("`oper_date` is null");
            return (Criteria) this;
        }

        public Criteria andOperDateIsNotNull() {
            addCriterion("`oper_date` is not null");
            return (Criteria) this;
        }

        public Criteria andOperDateEqualTo(Date value) {
            addCriterion("`oper_date` =", value, "operDate");
            return (Criteria) this;
        }

        public Criteria andOperDateNotEqualTo(Date value) {
            addCriterion("`oper_date` <>", value, "operDate");
            return (Criteria) this;
        }

        public Criteria andOperDateGreaterThan(Date value) {
            addCriterion("`oper_date` >", value, "operDate");
            return (Criteria) this;
        }

        public Criteria andOperDateGreaterThanOrEqualTo(Date value) {
            addCriterion("`oper_date` >=", value, "operDate");
            return (Criteria) this;
        }

        public Criteria andOperDateLessThan(Date value) {
            addCriterion("`oper_date` <", value, "operDate");
            return (Criteria) this;
        }

        public Criteria andOperDateLessThanOrEqualTo(Date value) {
            addCriterion("`oper_date` <=", value, "operDate");
            return (Criteria) this;
        }

        public Criteria andOperDateIn(List<Date> values) {
            addCriterion("`oper_date` in", values, "operDate");
            return (Criteria) this;
        }

        public Criteria andOperDateNotIn(List<Date> values) {
            addCriterion("`oper_date` not in", values, "operDate");
            return (Criteria) this;
        }

        public Criteria andOperDateBetween(Date value1, Date value2) {
            addCriterion("`oper_date` between", value1, value2, "operDate");
            return (Criteria) this;
        }

        public Criteria andOperDateNotBetween(Date value1, Date value2) {
            addCriterion("`oper_date` not between", value1, value2, "operDate");
            return (Criteria) this;
        }

        public Criteria andOperUserUuidIsNull() {
            addCriterion("`oper_user_uuid` is null");
            return (Criteria) this;
        }

        public Criteria andOperUserUuidIsNotNull() {
            addCriterion("`oper_user_uuid` is not null");
            return (Criteria) this;
        }

        public Criteria andOperUserUuidEqualTo(String value) {
            addCriterion("`oper_user_uuid` =", value, "operUserUuid");
            return (Criteria) this;
        }

        public Criteria andOperUserUuidNotEqualTo(String value) {
            addCriterion("`oper_user_uuid` <>", value, "operUserUuid");
            return (Criteria) this;
        }

        public Criteria andOperUserUuidGreaterThan(String value) {
            addCriterion("`oper_user_uuid` >", value, "operUserUuid");
            return (Criteria) this;
        }

        public Criteria andOperUserUuidGreaterThanOrEqualTo(String value) {
            addCriterion("`oper_user_uuid` >=", value, "operUserUuid");
            return (Criteria) this;
        }

        public Criteria andOperUserUuidLessThan(String value) {
            addCriterion("`oper_user_uuid` <", value, "operUserUuid");
            return (Criteria) this;
        }

        public Criteria andOperUserUuidLessThanOrEqualTo(String value) {
            addCriterion("`oper_user_uuid` <=", value, "operUserUuid");
            return (Criteria) this;
        }

        public Criteria andOperUserUuidLike(String value) {
            addCriterion("`oper_user_uuid` like", value, "operUserUuid");
            return (Criteria) this;
        }

        public Criteria andOperUserUuidNotLike(String value) {
            addCriterion("`oper_user_uuid` not like", value, "operUserUuid");
            return (Criteria) this;
        }

        public Criteria andOperUserUuidIn(List<String> values) {
            addCriterion("`oper_user_uuid` in", values, "operUserUuid");
            return (Criteria) this;
        }

        public Criteria andOperUserUuidNotIn(List<String> values) {
            addCriterion("`oper_user_uuid` not in", values, "operUserUuid");
            return (Criteria) this;
        }

        public Criteria andOperUserUuidBetween(String value1, String value2) {
            addCriterion("`oper_user_uuid` between", value1, value2, "operUserUuid");
            return (Criteria) this;
        }

        public Criteria andOperUserUuidNotBetween(String value1, String value2) {
            addCriterion("`oper_user_uuid` not between", value1, value2, "operUserUuid");
            return (Criteria) this;
        }

        public Criteria andOperUserNameIsNull() {
            addCriterion("`oper_user_name` is null");
            return (Criteria) this;
        }

        public Criteria andOperUserNameIsNotNull() {
            addCriterion("`oper_user_name` is not null");
            return (Criteria) this;
        }

        public Criteria andOperUserNameEqualTo(String value) {
            addCriterion("`oper_user_name` =", value, "operUserName");
            return (Criteria) this;
        }

        public Criteria andOperUserNameNotEqualTo(String value) {
            addCriterion("`oper_user_name` <>", value, "operUserName");
            return (Criteria) this;
        }

        public Criteria andOperUserNameGreaterThan(String value) {
            addCriterion("`oper_user_name` >", value, "operUserName");
            return (Criteria) this;
        }

        public Criteria andOperUserNameGreaterThanOrEqualTo(String value) {
            addCriterion("`oper_user_name` >=", value, "operUserName");
            return (Criteria) this;
        }

        public Criteria andOperUserNameLessThan(String value) {
            addCriterion("`oper_user_name` <", value, "operUserName");
            return (Criteria) this;
        }

        public Criteria andOperUserNameLessThanOrEqualTo(String value) {
            addCriterion("`oper_user_name` <=", value, "operUserName");
            return (Criteria) this;
        }

        public Criteria andOperUserNameLike(String value) {
            addCriterion("`oper_user_name` like", value, "operUserName");
            return (Criteria) this;
        }

        public Criteria andOperUserNameNotLike(String value) {
            addCriterion("`oper_user_name` not like", value, "operUserName");
            return (Criteria) this;
        }

        public Criteria andOperUserNameIn(List<String> values) {
            addCriterion("`oper_user_name` in", values, "operUserName");
            return (Criteria) this;
        }

        public Criteria andOperUserNameNotIn(List<String> values) {
            addCriterion("`oper_user_name` not in", values, "operUserName");
            return (Criteria) this;
        }

        public Criteria andOperUserNameBetween(String value1, String value2) {
            addCriterion("`oper_user_name` between", value1, value2, "operUserName");
            return (Criteria) this;
        }

        public Criteria andOperUserNameNotBetween(String value1, String value2) {
            addCriterion("`oper_user_name` not between", value1, value2, "operUserName");
            return (Criteria) this;
        }

        public Criteria andOperTypeIsNull() {
            addCriterion("`oper_type` is null");
            return (Criteria) this;
        }

        public Criteria andOperTypeIsNotNull() {
            addCriterion("`oper_type` is not null");
            return (Criteria) this;
        }

        public Criteria andOperTypeEqualTo(String value) {
            addCriterion("`oper_type` =", value, "operType");
            return (Criteria) this;
        }

        public Criteria andOperTypeNotEqualTo(String value) {
            addCriterion("`oper_type` <>", value, "operType");
            return (Criteria) this;
        }

        public Criteria andOperTypeGreaterThan(String value) {
            addCriterion("`oper_type` >", value, "operType");
            return (Criteria) this;
        }

        public Criteria andOperTypeGreaterThanOrEqualTo(String value) {
            addCriterion("`oper_type` >=", value, "operType");
            return (Criteria) this;
        }

        public Criteria andOperTypeLessThan(String value) {
            addCriterion("`oper_type` <", value, "operType");
            return (Criteria) this;
        }

        public Criteria andOperTypeLessThanOrEqualTo(String value) {
            addCriterion("`oper_type` <=", value, "operType");
            return (Criteria) this;
        }

        public Criteria andOperTypeLike(String value) {
            addCriterion("`oper_type` like", value, "operType");
            return (Criteria) this;
        }

        public Criteria andOperTypeNotLike(String value) {
            addCriterion("`oper_type` not like", value, "operType");
            return (Criteria) this;
        }

        public Criteria andOperTypeIn(List<String> values) {
            addCriterion("`oper_type` in", values, "operType");
            return (Criteria) this;
        }

        public Criteria andOperTypeNotIn(List<String> values) {
            addCriterion("`oper_type` not in", values, "operType");
            return (Criteria) this;
        }

        public Criteria andOperTypeBetween(String value1, String value2) {
            addCriterion("`oper_type` between", value1, value2, "operType");
            return (Criteria) this;
        }

        public Criteria andOperTypeNotBetween(String value1, String value2) {
            addCriterion("`oper_type` not between", value1, value2, "operType");
            return (Criteria) this;
        }

        public Criteria andOperModelIsNull() {
            addCriterion("`oper_model` is null");
            return (Criteria) this;
        }

        public Criteria andOperModelIsNotNull() {
            addCriterion("`oper_model` is not null");
            return (Criteria) this;
        }

        public Criteria andOperModelEqualTo(String value) {
            addCriterion("`oper_model` =", value, "operModel");
            return (Criteria) this;
        }

        public Criteria andOperModelNotEqualTo(String value) {
            addCriterion("`oper_model` <>", value, "operModel");
            return (Criteria) this;
        }

        public Criteria andOperModelGreaterThan(String value) {
            addCriterion("`oper_model` >", value, "operModel");
            return (Criteria) this;
        }

        public Criteria andOperModelGreaterThanOrEqualTo(String value) {
            addCriterion("`oper_model` >=", value, "operModel");
            return (Criteria) this;
        }

        public Criteria andOperModelLessThan(String value) {
            addCriterion("`oper_model` <", value, "operModel");
            return (Criteria) this;
        }

        public Criteria andOperModelLessThanOrEqualTo(String value) {
            addCriterion("`oper_model` <=", value, "operModel");
            return (Criteria) this;
        }

        public Criteria andOperModelLike(String value) {
            addCriterion("`oper_model` like", value, "operModel");
            return (Criteria) this;
        }

        public Criteria andOperModelNotLike(String value) {
            addCriterion("`oper_model` not like", value, "operModel");
            return (Criteria) this;
        }

        public Criteria andOperModelIn(List<String> values) {
            addCriterion("`oper_model` in", values, "operModel");
            return (Criteria) this;
        }

        public Criteria andOperModelNotIn(List<String> values) {
            addCriterion("`oper_model` not in", values, "operModel");
            return (Criteria) this;
        }

        public Criteria andOperModelBetween(String value1, String value2) {
            addCriterion("`oper_model` between", value1, value2, "operModel");
            return (Criteria) this;
        }

        public Criteria andOperModelNotBetween(String value1, String value2) {
            addCriterion("`oper_model` not between", value1, value2, "operModel");
            return (Criteria) this;
        }

        public Criteria andOperIpIsNull() {
            addCriterion("`oper_ip` is null");
            return (Criteria) this;
        }

        public Criteria andOperIpIsNotNull() {
            addCriterion("`oper_ip` is not null");
            return (Criteria) this;
        }

        public Criteria andOperIpEqualTo(String value) {
            addCriterion("`oper_ip` =", value, "operIp");
            return (Criteria) this;
        }

        public Criteria andOperIpNotEqualTo(String value) {
            addCriterion("`oper_ip` <>", value, "operIp");
            return (Criteria) this;
        }

        public Criteria andOperIpGreaterThan(String value) {
            addCriterion("`oper_ip` >", value, "operIp");
            return (Criteria) this;
        }

        public Criteria andOperIpGreaterThanOrEqualTo(String value) {
            addCriterion("`oper_ip` >=", value, "operIp");
            return (Criteria) this;
        }

        public Criteria andOperIpLessThan(String value) {
            addCriterion("`oper_ip` <", value, "operIp");
            return (Criteria) this;
        }

        public Criteria andOperIpLessThanOrEqualTo(String value) {
            addCriterion("`oper_ip` <=", value, "operIp");
            return (Criteria) this;
        }

        public Criteria andOperIpLike(String value) {
            addCriterion("`oper_ip` like", value, "operIp");
            return (Criteria) this;
        }

        public Criteria andOperIpNotLike(String value) {
            addCriterion("`oper_ip` not like", value, "operIp");
            return (Criteria) this;
        }

        public Criteria andOperIpIn(List<String> values) {
            addCriterion("`oper_ip` in", values, "operIp");
            return (Criteria) this;
        }

        public Criteria andOperIpNotIn(List<String> values) {
            addCriterion("`oper_ip` not in", values, "operIp");
            return (Criteria) this;
        }

        public Criteria andOperIpBetween(String value1, String value2) {
            addCriterion("`oper_ip` between", value1, value2, "operIp");
            return (Criteria) this;
        }

        public Criteria andOperIpNotBetween(String value1, String value2) {
            addCriterion("`oper_ip` not between", value1, value2, "operIp");
            return (Criteria) this;
        }

        public Criteria andClassNameIsNull() {
            addCriterion("`class_name` is null");
            return (Criteria) this;
        }

        public Criteria andClassNameIsNotNull() {
            addCriterion("`class_name` is not null");
            return (Criteria) this;
        }

        public Criteria andClassNameEqualTo(String value) {
            addCriterion("`class_name` =", value, "className");
            return (Criteria) this;
        }

        public Criteria andClassNameNotEqualTo(String value) {
            addCriterion("`class_name` <>", value, "className");
            return (Criteria) this;
        }

        public Criteria andClassNameGreaterThan(String value) {
            addCriterion("`class_name` >", value, "className");
            return (Criteria) this;
        }

        public Criteria andClassNameGreaterThanOrEqualTo(String value) {
            addCriterion("`class_name` >=", value, "className");
            return (Criteria) this;
        }

        public Criteria andClassNameLessThan(String value) {
            addCriterion("`class_name` <", value, "className");
            return (Criteria) this;
        }

        public Criteria andClassNameLessThanOrEqualTo(String value) {
            addCriterion("`class_name` <=", value, "className");
            return (Criteria) this;
        }

        public Criteria andClassNameLike(String value) {
            addCriterion("`class_name` like", value, "className");
            return (Criteria) this;
        }

        public Criteria andClassNameNotLike(String value) {
            addCriterion("`class_name` not like", value, "className");
            return (Criteria) this;
        }

        public Criteria andClassNameIn(List<String> values) {
            addCriterion("`class_name` in", values, "className");
            return (Criteria) this;
        }

        public Criteria andClassNameNotIn(List<String> values) {
            addCriterion("`class_name` not in", values, "className");
            return (Criteria) this;
        }

        public Criteria andClassNameBetween(String value1, String value2) {
            addCriterion("`class_name` between", value1, value2, "className");
            return (Criteria) this;
        }

        public Criteria andClassNameNotBetween(String value1, String value2) {
            addCriterion("`class_name` not between", value1, value2, "className");
            return (Criteria) this;
        }

        public Criteria andMethodNameIsNull() {
            addCriterion("`method_name` is null");
            return (Criteria) this;
        }

        public Criteria andMethodNameIsNotNull() {
            addCriterion("`method_name` is not null");
            return (Criteria) this;
        }

        public Criteria andMethodNameEqualTo(String value) {
            addCriterion("`method_name` =", value, "methodName");
            return (Criteria) this;
        }

        public Criteria andMethodNameNotEqualTo(String value) {
            addCriterion("`method_name` <>", value, "methodName");
            return (Criteria) this;
        }

        public Criteria andMethodNameGreaterThan(String value) {
            addCriterion("`method_name` >", value, "methodName");
            return (Criteria) this;
        }

        public Criteria andMethodNameGreaterThanOrEqualTo(String value) {
            addCriterion("`method_name` >=", value, "methodName");
            return (Criteria) this;
        }

        public Criteria andMethodNameLessThan(String value) {
            addCriterion("`method_name` <", value, "methodName");
            return (Criteria) this;
        }

        public Criteria andMethodNameLessThanOrEqualTo(String value) {
            addCriterion("`method_name` <=", value, "methodName");
            return (Criteria) this;
        }

        public Criteria andMethodNameLike(String value) {
            addCriterion("`method_name` like", value, "methodName");
            return (Criteria) this;
        }

        public Criteria andMethodNameNotLike(String value) {
            addCriterion("`method_name` not like", value, "methodName");
            return (Criteria) this;
        }

        public Criteria andMethodNameIn(List<String> values) {
            addCriterion("`method_name` in", values, "methodName");
            return (Criteria) this;
        }

        public Criteria andMethodNameNotIn(List<String> values) {
            addCriterion("`method_name` not in", values, "methodName");
            return (Criteria) this;
        }

        public Criteria andMethodNameBetween(String value1, String value2) {
            addCriterion("`method_name` between", value1, value2, "methodName");
            return (Criteria) this;
        }

        public Criteria andMethodNameNotBetween(String value1, String value2) {
            addCriterion("`method_name` not between", value1, value2, "methodName");
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

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private final String typeHandler;

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
    }
}