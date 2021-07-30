package net.renfei.repository.dao.start.model;

import java.util.ArrayList;
import java.util.List;

public class TStartSysMenuExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TStartSysMenuExample() {
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

        public Criteria andMenuNameIsNull() {
            addCriterion("`menu_name` is null");
            return (Criteria) this;
        }

        public Criteria andMenuNameIsNotNull() {
            addCriterion("`menu_name` is not null");
            return (Criteria) this;
        }

        public Criteria andMenuNameEqualTo(String value) {
            addCriterion("`menu_name` =", value, "menuName");
            return (Criteria) this;
        }

        public Criteria andMenuNameNotEqualTo(String value) {
            addCriterion("`menu_name` <>", value, "menuName");
            return (Criteria) this;
        }

        public Criteria andMenuNameGreaterThan(String value) {
            addCriterion("`menu_name` >", value, "menuName");
            return (Criteria) this;
        }

        public Criteria andMenuNameGreaterThanOrEqualTo(String value) {
            addCriterion("`menu_name` >=", value, "menuName");
            return (Criteria) this;
        }

        public Criteria andMenuNameLessThan(String value) {
            addCriterion("`menu_name` <", value, "menuName");
            return (Criteria) this;
        }

        public Criteria andMenuNameLessThanOrEqualTo(String value) {
            addCriterion("`menu_name` <=", value, "menuName");
            return (Criteria) this;
        }

        public Criteria andMenuNameLike(String value) {
            addCriterion("`menu_name` like", value, "menuName");
            return (Criteria) this;
        }

        public Criteria andMenuNameNotLike(String value) {
            addCriterion("`menu_name` not like", value, "menuName");
            return (Criteria) this;
        }

        public Criteria andMenuNameIn(List<String> values) {
            addCriterion("`menu_name` in", values, "menuName");
            return (Criteria) this;
        }

        public Criteria andMenuNameNotIn(List<String> values) {
            addCriterion("`menu_name` not in", values, "menuName");
            return (Criteria) this;
        }

        public Criteria andMenuNameBetween(String value1, String value2) {
            addCriterion("`menu_name` between", value1, value2, "menuName");
            return (Criteria) this;
        }

        public Criteria andMenuNameNotBetween(String value1, String value2) {
            addCriterion("`menu_name` not between", value1, value2, "menuName");
            return (Criteria) this;
        }

        public Criteria andMenuLinkIsNull() {
            addCriterion("`menu_link` is null");
            return (Criteria) this;
        }

        public Criteria andMenuLinkIsNotNull() {
            addCriterion("`menu_link` is not null");
            return (Criteria) this;
        }

        public Criteria andMenuLinkEqualTo(String value) {
            addCriterion("`menu_link` =", value, "menuLink");
            return (Criteria) this;
        }

        public Criteria andMenuLinkNotEqualTo(String value) {
            addCriterion("`menu_link` <>", value, "menuLink");
            return (Criteria) this;
        }

        public Criteria andMenuLinkGreaterThan(String value) {
            addCriterion("`menu_link` >", value, "menuLink");
            return (Criteria) this;
        }

        public Criteria andMenuLinkGreaterThanOrEqualTo(String value) {
            addCriterion("`menu_link` >=", value, "menuLink");
            return (Criteria) this;
        }

        public Criteria andMenuLinkLessThan(String value) {
            addCriterion("`menu_link` <", value, "menuLink");
            return (Criteria) this;
        }

        public Criteria andMenuLinkLessThanOrEqualTo(String value) {
            addCriterion("`menu_link` <=", value, "menuLink");
            return (Criteria) this;
        }

        public Criteria andMenuLinkLike(String value) {
            addCriterion("`menu_link` like", value, "menuLink");
            return (Criteria) this;
        }

        public Criteria andMenuLinkNotLike(String value) {
            addCriterion("`menu_link` not like", value, "menuLink");
            return (Criteria) this;
        }

        public Criteria andMenuLinkIn(List<String> values) {
            addCriterion("`menu_link` in", values, "menuLink");
            return (Criteria) this;
        }

        public Criteria andMenuLinkNotIn(List<String> values) {
            addCriterion("`menu_link` not in", values, "menuLink");
            return (Criteria) this;
        }

        public Criteria andMenuLinkBetween(String value1, String value2) {
            addCriterion("`menu_link` between", value1, value2, "menuLink");
            return (Criteria) this;
        }

        public Criteria andMenuLinkNotBetween(String value1, String value2) {
            addCriterion("`menu_link` not between", value1, value2, "menuLink");
            return (Criteria) this;
        }

        public Criteria andOrderNumIsNull() {
            addCriterion("`order_num` is null");
            return (Criteria) this;
        }

        public Criteria andOrderNumIsNotNull() {
            addCriterion("`order_num` is not null");
            return (Criteria) this;
        }

        public Criteria andOrderNumEqualTo(Integer value) {
            addCriterion("`order_num` =", value, "orderNum");
            return (Criteria) this;
        }

        public Criteria andOrderNumNotEqualTo(Integer value) {
            addCriterion("`order_num` <>", value, "orderNum");
            return (Criteria) this;
        }

        public Criteria andOrderNumGreaterThan(Integer value) {
            addCriterion("`order_num` >", value, "orderNum");
            return (Criteria) this;
        }

        public Criteria andOrderNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("`order_num` >=", value, "orderNum");
            return (Criteria) this;
        }

        public Criteria andOrderNumLessThan(Integer value) {
            addCriterion("`order_num` <", value, "orderNum");
            return (Criteria) this;
        }

        public Criteria andOrderNumLessThanOrEqualTo(Integer value) {
            addCriterion("`order_num` <=", value, "orderNum");
            return (Criteria) this;
        }

        public Criteria andOrderNumIn(List<Integer> values) {
            addCriterion("`order_num` in", values, "orderNum");
            return (Criteria) this;
        }

        public Criteria andOrderNumNotIn(List<Integer> values) {
            addCriterion("`order_num` not in", values, "orderNum");
            return (Criteria) this;
        }

        public Criteria andOrderNumBetween(Integer value1, Integer value2) {
            addCriterion("`order_num` between", value1, value2, "orderNum");
            return (Criteria) this;
        }

        public Criteria andOrderNumNotBetween(Integer value1, Integer value2) {
            addCriterion("`order_num` not between", value1, value2, "orderNum");
            return (Criteria) this;
        }

        public Criteria andMenuIconIsNull() {
            addCriterion("`menu_icon` is null");
            return (Criteria) this;
        }

        public Criteria andMenuIconIsNotNull() {
            addCriterion("`menu_icon` is not null");
            return (Criteria) this;
        }

        public Criteria andMenuIconEqualTo(String value) {
            addCriterion("`menu_icon` =", value, "menuIcon");
            return (Criteria) this;
        }

        public Criteria andMenuIconNotEqualTo(String value) {
            addCriterion("`menu_icon` <>", value, "menuIcon");
            return (Criteria) this;
        }

        public Criteria andMenuIconGreaterThan(String value) {
            addCriterion("`menu_icon` >", value, "menuIcon");
            return (Criteria) this;
        }

        public Criteria andMenuIconGreaterThanOrEqualTo(String value) {
            addCriterion("`menu_icon` >=", value, "menuIcon");
            return (Criteria) this;
        }

        public Criteria andMenuIconLessThan(String value) {
            addCriterion("`menu_icon` <", value, "menuIcon");
            return (Criteria) this;
        }

        public Criteria andMenuIconLessThanOrEqualTo(String value) {
            addCriterion("`menu_icon` <=", value, "menuIcon");
            return (Criteria) this;
        }

        public Criteria andMenuIconLike(String value) {
            addCriterion("`menu_icon` like", value, "menuIcon");
            return (Criteria) this;
        }

        public Criteria andMenuIconNotLike(String value) {
            addCriterion("`menu_icon` not like", value, "menuIcon");
            return (Criteria) this;
        }

        public Criteria andMenuIconIn(List<String> values) {
            addCriterion("`menu_icon` in", values, "menuIcon");
            return (Criteria) this;
        }

        public Criteria andMenuIconNotIn(List<String> values) {
            addCriterion("`menu_icon` not in", values, "menuIcon");
            return (Criteria) this;
        }

        public Criteria andMenuIconBetween(String value1, String value2) {
            addCriterion("`menu_icon` between", value1, value2, "menuIcon");
            return (Criteria) this;
        }

        public Criteria andMenuIconNotBetween(String value1, String value2) {
            addCriterion("`menu_icon` not between", value1, value2, "menuIcon");
            return (Criteria) this;
        }

        public Criteria andIsNewWindowIsNull() {
            addCriterion("`is_new_window` is null");
            return (Criteria) this;
        }

        public Criteria andIsNewWindowIsNotNull() {
            addCriterion("`is_new_window` is not null");
            return (Criteria) this;
        }

        public Criteria andIsNewWindowEqualTo(Boolean value) {
            addCriterion("`is_new_window` =", value, "isNewWindow");
            return (Criteria) this;
        }

        public Criteria andIsNewWindowNotEqualTo(Boolean value) {
            addCriterion("`is_new_window` <>", value, "isNewWindow");
            return (Criteria) this;
        }

        public Criteria andIsNewWindowGreaterThan(Boolean value) {
            addCriterion("`is_new_window` >", value, "isNewWindow");
            return (Criteria) this;
        }

        public Criteria andIsNewWindowGreaterThanOrEqualTo(Boolean value) {
            addCriterion("`is_new_window` >=", value, "isNewWindow");
            return (Criteria) this;
        }

        public Criteria andIsNewWindowLessThan(Boolean value) {
            addCriterion("`is_new_window` <", value, "isNewWindow");
            return (Criteria) this;
        }

        public Criteria andIsNewWindowLessThanOrEqualTo(Boolean value) {
            addCriterion("`is_new_window` <=", value, "isNewWindow");
            return (Criteria) this;
        }

        public Criteria andIsNewWindowIn(List<Boolean> values) {
            addCriterion("`is_new_window` in", values, "isNewWindow");
            return (Criteria) this;
        }

        public Criteria andIsNewWindowNotIn(List<Boolean> values) {
            addCriterion("`is_new_window` not in", values, "isNewWindow");
            return (Criteria) this;
        }

        public Criteria andIsNewWindowBetween(Boolean value1, Boolean value2) {
            addCriterion("`is_new_window` between", value1, value2, "isNewWindow");
            return (Criteria) this;
        }

        public Criteria andIsNewWindowNotBetween(Boolean value1, Boolean value2) {
            addCriterion("`is_new_window` not between", value1, value2, "isNewWindow");
            return (Criteria) this;
        }

        public Criteria andParentIdIsNull() {
            addCriterion("`parent_id` is null");
            return (Criteria) this;
        }

        public Criteria andParentIdIsNotNull() {
            addCriterion("`parent_id` is not null");
            return (Criteria) this;
        }

        public Criteria andParentIdEqualTo(Long value) {
            addCriterion("`parent_id` =", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdNotEqualTo(Long value) {
            addCriterion("`parent_id` <>", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdGreaterThan(Long value) {
            addCriterion("`parent_id` >", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdGreaterThanOrEqualTo(Long value) {
            addCriterion("`parent_id` >=", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdLessThan(Long value) {
            addCriterion("`parent_id` <", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdLessThanOrEqualTo(Long value) {
            addCriterion("`parent_id` <=", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdIn(List<Long> values) {
            addCriterion("`parent_id` in", values, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdNotIn(List<Long> values) {
            addCriterion("`parent_id` not in", values, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdBetween(Long value1, Long value2) {
            addCriterion("`parent_id` between", value1, value2, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdNotBetween(Long value1, Long value2) {
            addCriterion("`parent_id` not between", value1, value2, "parentId");
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