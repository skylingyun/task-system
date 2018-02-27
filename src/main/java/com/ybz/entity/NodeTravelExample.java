package com.ybz.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class NodeTravelExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer startRow;

    protected Integer pageSize;

    public NodeTravelExample() {
        oredCriteria = new ArrayList<Criteria>();
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
            criteria = new ArrayList<Criterion>();
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

        protected void addCriterionForJDBCDate(String condition, Date value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value.getTime()), property);
        }

        protected void addCriterionForJDBCDate(String condition, List<Date> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            List<java.sql.Date> dateList = new ArrayList<java.sql.Date>();
            Iterator<Date> iter = values.iterator();
            while (iter.hasNext()) {
                dateList.add(new java.sql.Date(iter.next().getTime()));
            }
            addCriterion(condition, dateList, property);
        }

        protected void addCriterionForJDBCDate(String condition, Date value1, Date value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value1.getTime()), new java.sql.Date(value2.getTime()), property);
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andPkIsNull() {
            addCriterion("pk is null");
            return (Criteria) this;
        }

        public Criteria andPkIsNotNull() {
            addCriterion("pk is not null");
            return (Criteria) this;
        }

        public Criteria andPkEqualTo(String value) {
            addCriterion("pk =", value, "pk");
            return (Criteria) this;
        }

        public Criteria andPkNotEqualTo(String value) {
            addCriterion("pk <>", value, "pk");
            return (Criteria) this;
        }

        public Criteria andPkGreaterThan(String value) {
            addCriterion("pk >", value, "pk");
            return (Criteria) this;
        }

        public Criteria andPkGreaterThanOrEqualTo(String value) {
            addCriterion("pk >=", value, "pk");
            return (Criteria) this;
        }

        public Criteria andPkLessThan(String value) {
            addCriterion("pk <", value, "pk");
            return (Criteria) this;
        }

        public Criteria andPkLessThanOrEqualTo(String value) {
            addCriterion("pk <=", value, "pk");
            return (Criteria) this;
        }

        public Criteria andPkLike(String value) {
            addCriterion("pk like", value, "pk");
            return (Criteria) this;
        }

        public Criteria andPkNotLike(String value) {
            addCriterion("pk not like", value, "pk");
            return (Criteria) this;
        }

        public Criteria andPkIn(List<String> values) {
            addCriterion("pk in", values, "pk");
            return (Criteria) this;
        }

        public Criteria andPkNotIn(List<String> values) {
            addCriterion("pk not in", values, "pk");
            return (Criteria) this;
        }

        public Criteria andPkBetween(String value1, String value2) {
            addCriterion("pk between", value1, value2, "pk");
            return (Criteria) this;
        }

        public Criteria andPkNotBetween(String value1, String value2) {
            addCriterion("pk not between", value1, value2, "pk");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNull() {
            addCriterion("user_id is null");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNotNull() {
            addCriterion("user_id is not null");
            return (Criteria) this;
        }

        public Criteria andUserIdEqualTo(String value) {
            addCriterion("user_id =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(String value) {
            addCriterion("user_id <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(String value) {
            addCriterion("user_id >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(String value) {
            addCriterion("user_id >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(String value) {
            addCriterion("user_id <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(String value) {
            addCriterion("user_id <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLike(String value) {
            addCriterion("user_id like", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotLike(String value) {
            addCriterion("user_id not like", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<String> values) {
            addCriterion("user_id in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<String> values) {
            addCriterion("user_id not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(String value1, String value2) {
            addCriterion("user_id between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(String value1, String value2) {
            addCriterion("user_id not between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andMoneyIsNull() {
            addCriterion("money is null");
            return (Criteria) this;
        }

        public Criteria andMoneyIsNotNull() {
            addCriterion("money is not null");
            return (Criteria) this;
        }

        public Criteria andMoneyEqualTo(Double value) {
            addCriterion("money =", value, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyNotEqualTo(Double value) {
            addCriterion("money <>", value, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyGreaterThan(Double value) {
            addCriterion("money >", value, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyGreaterThanOrEqualTo(Double value) {
            addCriterion("money >=", value, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyLessThan(Double value) {
            addCriterion("money <", value, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyLessThanOrEqualTo(Double value) {
            addCriterion("money <=", value, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyIn(List<Double> values) {
            addCriterion("money in", values, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyNotIn(List<Double> values) {
            addCriterion("money not in", values, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyBetween(Double value1, Double value2) {
            addCriterion("money between", value1, value2, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyNotBetween(Double value1, Double value2) {
            addCriterion("money not between", value1, value2, "money");
            return (Criteria) this;
        }

        public Criteria andFromCityIsNull() {
            addCriterion("from_city is null");
            return (Criteria) this;
        }

        public Criteria andFromCityIsNotNull() {
            addCriterion("from_city is not null");
            return (Criteria) this;
        }

        public Criteria andFromCityEqualTo(String value) {
            addCriterion("from_city =", value, "fromCity");
            return (Criteria) this;
        }

        public Criteria andFromCityNotEqualTo(String value) {
            addCriterion("from_city <>", value, "fromCity");
            return (Criteria) this;
        }

        public Criteria andFromCityGreaterThan(String value) {
            addCriterion("from_city >", value, "fromCity");
            return (Criteria) this;
        }

        public Criteria andFromCityGreaterThanOrEqualTo(String value) {
            addCriterion("from_city >=", value, "fromCity");
            return (Criteria) this;
        }

        public Criteria andFromCityLessThan(String value) {
            addCriterion("from_city <", value, "fromCity");
            return (Criteria) this;
        }

        public Criteria andFromCityLessThanOrEqualTo(String value) {
            addCriterion("from_city <=", value, "fromCity");
            return (Criteria) this;
        }

        public Criteria andFromCityLike(String value) {
            addCriterion("from_city like", value, "fromCity");
            return (Criteria) this;
        }

        public Criteria andFromCityNotLike(String value) {
            addCriterion("from_city not like", value, "fromCity");
            return (Criteria) this;
        }

        public Criteria andFromCityIn(List<String> values) {
            addCriterion("from_city in", values, "fromCity");
            return (Criteria) this;
        }

        public Criteria andFromCityNotIn(List<String> values) {
            addCriterion("from_city not in", values, "fromCity");
            return (Criteria) this;
        }

        public Criteria andFromCityBetween(String value1, String value2) {
            addCriterion("from_city between", value1, value2, "fromCity");
            return (Criteria) this;
        }

        public Criteria andFromCityNotBetween(String value1, String value2) {
            addCriterion("from_city not between", value1, value2, "fromCity");
            return (Criteria) this;
        }

        public Criteria andToCityIsNull() {
            addCriterion("to_city is null");
            return (Criteria) this;
        }

        public Criteria andToCityIsNotNull() {
            addCriterion("to_city is not null");
            return (Criteria) this;
        }

        public Criteria andToCityEqualTo(String value) {
            addCriterion("to_city =", value, "toCity");
            return (Criteria) this;
        }

        public Criteria andToCityNotEqualTo(String value) {
            addCriterion("to_city <>", value, "toCity");
            return (Criteria) this;
        }

        public Criteria andToCityGreaterThan(String value) {
            addCriterion("to_city >", value, "toCity");
            return (Criteria) this;
        }

        public Criteria andToCityGreaterThanOrEqualTo(String value) {
            addCriterion("to_city >=", value, "toCity");
            return (Criteria) this;
        }

        public Criteria andToCityLessThan(String value) {
            addCriterion("to_city <", value, "toCity");
            return (Criteria) this;
        }

        public Criteria andToCityLessThanOrEqualTo(String value) {
            addCriterion("to_city <=", value, "toCity");
            return (Criteria) this;
        }

        public Criteria andToCityLike(String value) {
            addCriterion("to_city like", value, "toCity");
            return (Criteria) this;
        }

        public Criteria andToCityNotLike(String value) {
            addCriterion("to_city not like", value, "toCity");
            return (Criteria) this;
        }

        public Criteria andToCityIn(List<String> values) {
            addCriterion("to_city in", values, "toCity");
            return (Criteria) this;
        }

        public Criteria andToCityNotIn(List<String> values) {
            addCriterion("to_city not in", values, "toCity");
            return (Criteria) this;
        }

        public Criteria andToCityBetween(String value1, String value2) {
            addCriterion("to_city between", value1, value2, "toCity");
            return (Criteria) this;
        }

        public Criteria andToCityNotBetween(String value1, String value2) {
            addCriterion("to_city not between", value1, value2, "toCity");
            return (Criteria) this;
        }

        public Criteria andTravelDateIsNull() {
            addCriterion("travel_date is null");
            return (Criteria) this;
        }

        public Criteria andTravelDateIsNotNull() {
            addCriterion("travel_date is not null");
            return (Criteria) this;
        }

        public Criteria andTravelDateEqualTo(Date value) {
            addCriterionForJDBCDate("travel_date =", value, "travelDate");
            return (Criteria) this;
        }

        public Criteria andTravelDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("travel_date <>", value, "travelDate");
            return (Criteria) this;
        }

        public Criteria andTravelDateGreaterThan(Date value) {
            addCriterionForJDBCDate("travel_date >", value, "travelDate");
            return (Criteria) this;
        }

        public Criteria andTravelDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("travel_date >=", value, "travelDate");
            return (Criteria) this;
        }

        public Criteria andTravelDateLessThan(Date value) {
            addCriterionForJDBCDate("travel_date <", value, "travelDate");
            return (Criteria) this;
        }

        public Criteria andTravelDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("travel_date <=", value, "travelDate");
            return (Criteria) this;
        }

        public Criteria andTravelDateIn(List<Date> values) {
            addCriterionForJDBCDate("travel_date in", values, "travelDate");
            return (Criteria) this;
        }

        public Criteria andTravelDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("travel_date not in", values, "travelDate");
            return (Criteria) this;
        }

        public Criteria andTravelDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("travel_date between", value1, value2, "travelDate");
            return (Criteria) this;
        }

        public Criteria andTravelDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("travel_date not between", value1, value2, "travelDate");
            return (Criteria) this;
        }

        public Criteria andTravelWayIsNull() {
            addCriterion("travel_way is null");
            return (Criteria) this;
        }

        public Criteria andTravelWayIsNotNull() {
            addCriterion("travel_way is not null");
            return (Criteria) this;
        }

        public Criteria andTravelWayEqualTo(String value) {
            addCriterion("travel_way =", value, "travelWay");
            return (Criteria) this;
        }

        public Criteria andTravelWayNotEqualTo(String value) {
            addCriterion("travel_way <>", value, "travelWay");
            return (Criteria) this;
        }

        public Criteria andTravelWayGreaterThan(String value) {
            addCriterion("travel_way >", value, "travelWay");
            return (Criteria) this;
        }

        public Criteria andTravelWayGreaterThanOrEqualTo(String value) {
            addCriterion("travel_way >=", value, "travelWay");
            return (Criteria) this;
        }

        public Criteria andTravelWayLessThan(String value) {
            addCriterion("travel_way <", value, "travelWay");
            return (Criteria) this;
        }

        public Criteria andTravelWayLessThanOrEqualTo(String value) {
            addCriterion("travel_way <=", value, "travelWay");
            return (Criteria) this;
        }

        public Criteria andTravelWayLike(String value) {
            addCriterion("travel_way like", value, "travelWay");
            return (Criteria) this;
        }

        public Criteria andTravelWayNotLike(String value) {
            addCriterion("travel_way not like", value, "travelWay");
            return (Criteria) this;
        }

        public Criteria andTravelWayIn(List<String> values) {
            addCriterion("travel_way in", values, "travelWay");
            return (Criteria) this;
        }

        public Criteria andTravelWayNotIn(List<String> values) {
            addCriterion("travel_way not in", values, "travelWay");
            return (Criteria) this;
        }

        public Criteria andTravelWayBetween(String value1, String value2) {
            addCriterion("travel_way between", value1, value2, "travelWay");
            return (Criteria) this;
        }

        public Criteria andTravelWayNotBetween(String value1, String value2) {
            addCriterion("travel_way not between", value1, value2, "travelWay");
            return (Criteria) this;
        }

        public Criteria andExpenseIsNull() {
            addCriterion("expense is null");
            return (Criteria) this;
        }

        public Criteria andExpenseIsNotNull() {
            addCriterion("expense is not null");
            return (Criteria) this;
        }

        public Criteria andExpenseEqualTo(Boolean value) {
            addCriterion("expense =", value, "expense");
            return (Criteria) this;
        }

        public Criteria andExpenseNotEqualTo(Boolean value) {
            addCriterion("expense <>", value, "expense");
            return (Criteria) this;
        }

        public Criteria andExpenseGreaterThan(Boolean value) {
            addCriterion("expense >", value, "expense");
            return (Criteria) this;
        }

        public Criteria andExpenseGreaterThanOrEqualTo(Boolean value) {
            addCriterion("expense >=", value, "expense");
            return (Criteria) this;
        }

        public Criteria andExpenseLessThan(Boolean value) {
            addCriterion("expense <", value, "expense");
            return (Criteria) this;
        }

        public Criteria andExpenseLessThanOrEqualTo(Boolean value) {
            addCriterion("expense <=", value, "expense");
            return (Criteria) this;
        }

        public Criteria andExpenseIn(List<Boolean> values) {
            addCriterion("expense in", values, "expense");
            return (Criteria) this;
        }

        public Criteria andExpenseNotIn(List<Boolean> values) {
            addCriterion("expense not in", values, "expense");
            return (Criteria) this;
        }

        public Criteria andExpenseBetween(Boolean value1, Boolean value2) {
            addCriterion("expense between", value1, value2, "expense");
            return (Criteria) this;
        }

        public Criteria andExpenseNotBetween(Boolean value1, Boolean value2) {
            addCriterion("expense not between", value1, value2, "expense");
            return (Criteria) this;
        }

        public Criteria andValidIsNull() {
            addCriterion("valid is null");
            return (Criteria) this;
        }

        public Criteria andValidIsNotNull() {
            addCriterion("valid is not null");
            return (Criteria) this;
        }

        public Criteria andValidEqualTo(Boolean value) {
            addCriterion("valid =", value, "valid");
            return (Criteria) this;
        }

        public Criteria andValidNotEqualTo(Boolean value) {
            addCriterion("valid <>", value, "valid");
            return (Criteria) this;
        }

        public Criteria andValidGreaterThan(Boolean value) {
            addCriterion("valid >", value, "valid");
            return (Criteria) this;
        }

        public Criteria andValidGreaterThanOrEqualTo(Boolean value) {
            addCriterion("valid >=", value, "valid");
            return (Criteria) this;
        }

        public Criteria andValidLessThan(Boolean value) {
            addCriterion("valid <", value, "valid");
            return (Criteria) this;
        }

        public Criteria andValidLessThanOrEqualTo(Boolean value) {
            addCriterion("valid <=", value, "valid");
            return (Criteria) this;
        }

        public Criteria andValidIn(List<Boolean> values) {
            addCriterion("valid in", values, "valid");
            return (Criteria) this;
        }

        public Criteria andValidNotIn(List<Boolean> values) {
            addCriterion("valid not in", values, "valid");
            return (Criteria) this;
        }

        public Criteria andValidBetween(Boolean value1, Boolean value2) {
            addCriterion("valid between", value1, value2, "valid");
            return (Criteria) this;
        }

        public Criteria andValidNotBetween(Boolean value1, Boolean value2) {
            addCriterion("valid not between", value1, value2, "valid");
            return (Criteria) this;
        }

        public Criteria andImagesystemtypeIsNull() {
            addCriterion("imagesystemtype is null");
            return (Criteria) this;
        }

        public Criteria andImagesystemtypeIsNotNull() {
            addCriterion("imagesystemtype is not null");
            return (Criteria) this;
        }

        public Criteria andImagesystemtypeEqualTo(String value) {
            addCriterion("imagesystemtype =", value, "imagesystemtype");
            return (Criteria) this;
        }

        public Criteria andImagesystemtypeNotEqualTo(String value) {
            addCriterion("imagesystemtype <>", value, "imagesystemtype");
            return (Criteria) this;
        }

        public Criteria andImagesystemtypeGreaterThan(String value) {
            addCriterion("imagesystemtype >", value, "imagesystemtype");
            return (Criteria) this;
        }

        public Criteria andImagesystemtypeGreaterThanOrEqualTo(String value) {
            addCriterion("imagesystemtype >=", value, "imagesystemtype");
            return (Criteria) this;
        }

        public Criteria andImagesystemtypeLessThan(String value) {
            addCriterion("imagesystemtype <", value, "imagesystemtype");
            return (Criteria) this;
        }

        public Criteria andImagesystemtypeLessThanOrEqualTo(String value) {
            addCriterion("imagesystemtype <=", value, "imagesystemtype");
            return (Criteria) this;
        }

        public Criteria andImagesystemtypeLike(String value) {
            addCriterion("imagesystemtype like", value, "imagesystemtype");
            return (Criteria) this;
        }

        public Criteria andImagesystemtypeNotLike(String value) {
            addCriterion("imagesystemtype not like", value, "imagesystemtype");
            return (Criteria) this;
        }

        public Criteria andImagesystemtypeIn(List<String> values) {
            addCriterion("imagesystemtype in", values, "imagesystemtype");
            return (Criteria) this;
        }

        public Criteria andImagesystemtypeNotIn(List<String> values) {
            addCriterion("imagesystemtype not in", values, "imagesystemtype");
            return (Criteria) this;
        }

        public Criteria andImagesystemtypeBetween(String value1, String value2) {
            addCriterion("imagesystemtype between", value1, value2, "imagesystemtype");
            return (Criteria) this;
        }

        public Criteria andImagesystemtypeNotBetween(String value1, String value2) {
            addCriterion("imagesystemtype not between", value1, value2, "imagesystemtype");
            return (Criteria) this;
        }

        public Criteria andCategoryIsNull() {
            addCriterion("category is null");
            return (Criteria) this;
        }

        public Criteria andCategoryIsNotNull() {
            addCriterion("category is not null");
            return (Criteria) this;
        }

        public Criteria andCategoryEqualTo(String value) {
            addCriterion("category =", value, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryNotEqualTo(String value) {
            addCriterion("category <>", value, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryGreaterThan(String value) {
            addCriterion("category >", value, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryGreaterThanOrEqualTo(String value) {
            addCriterion("category >=", value, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryLessThan(String value) {
            addCriterion("category <", value, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryLessThanOrEqualTo(String value) {
            addCriterion("category <=", value, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryLike(String value) {
            addCriterion("category like", value, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryNotLike(String value) {
            addCriterion("category not like", value, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryIn(List<String> values) {
            addCriterion("category in", values, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryNotIn(List<String> values) {
            addCriterion("category not in", values, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryBetween(String value1, String value2) {
            addCriterion("category between", value1, value2, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryNotBetween(String value1, String value2) {
            addCriterion("category not between", value1, value2, "category");
            return (Criteria) this;
        }

        public Criteria andTsIsNull() {
            addCriterion("ts is null");
            return (Criteria) this;
        }

        public Criteria andTsIsNotNull() {
            addCriterion("ts is not null");
            return (Criteria) this;
        }

        public Criteria andTsEqualTo(Date value) {
            addCriterion("ts =", value, "ts");
            return (Criteria) this;
        }

        public Criteria andTsNotEqualTo(Date value) {
            addCriterion("ts <>", value, "ts");
            return (Criteria) this;
        }

        public Criteria andTsGreaterThan(Date value) {
            addCriterion("ts >", value, "ts");
            return (Criteria) this;
        }

        public Criteria andTsGreaterThanOrEqualTo(Date value) {
            addCriterion("ts >=", value, "ts");
            return (Criteria) this;
        }

        public Criteria andTsLessThan(Date value) {
            addCriterion("ts <", value, "ts");
            return (Criteria) this;
        }

        public Criteria andTsLessThanOrEqualTo(Date value) {
            addCriterion("ts <=", value, "ts");
            return (Criteria) this;
        }

        public Criteria andTsIn(List<Date> values) {
            addCriterion("ts in", values, "ts");
            return (Criteria) this;
        }

        public Criteria andTsNotIn(List<Date> values) {
            addCriterion("ts not in", values, "ts");
            return (Criteria) this;
        }

        public Criteria andTsBetween(Date value1, Date value2) {
            addCriterion("ts between", value1, value2, "ts");
            return (Criteria) this;
        }

        public Criteria andTsNotBetween(Date value1, Date value2) {
            addCriterion("ts not between", value1, value2, "ts");
            return (Criteria) this;
        }

        public Criteria andExpensetypeIsNull() {
            addCriterion("expensetype is null");
            return (Criteria) this;
        }

        public Criteria andExpensetypeIsNotNull() {
            addCriterion("expensetype is not null");
            return (Criteria) this;
        }

        public Criteria andExpensetypeEqualTo(String value) {
            addCriterion("expensetype =", value, "expensetype");
            return (Criteria) this;
        }

        public Criteria andExpensetypeNotEqualTo(String value) {
            addCriterion("expensetype <>", value, "expensetype");
            return (Criteria) this;
        }

        public Criteria andExpensetypeGreaterThan(String value) {
            addCriterion("expensetype >", value, "expensetype");
            return (Criteria) this;
        }

        public Criteria andExpensetypeGreaterThanOrEqualTo(String value) {
            addCriterion("expensetype >=", value, "expensetype");
            return (Criteria) this;
        }

        public Criteria andExpensetypeLessThan(String value) {
            addCriterion("expensetype <", value, "expensetype");
            return (Criteria) this;
        }

        public Criteria andExpensetypeLessThanOrEqualTo(String value) {
            addCriterion("expensetype <=", value, "expensetype");
            return (Criteria) this;
        }

        public Criteria andExpensetypeLike(String value) {
            addCriterion("expensetype like", value, "expensetype");
            return (Criteria) this;
        }

        public Criteria andExpensetypeNotLike(String value) {
            addCriterion("expensetype not like", value, "expensetype");
            return (Criteria) this;
        }

        public Criteria andExpensetypeIn(List<String> values) {
            addCriterion("expensetype in", values, "expensetype");
            return (Criteria) this;
        }

        public Criteria andExpensetypeNotIn(List<String> values) {
            addCriterion("expensetype not in", values, "expensetype");
            return (Criteria) this;
        }

        public Criteria andExpensetypeBetween(String value1, String value2) {
            addCriterion("expensetype between", value1, value2, "expensetype");
            return (Criteria) this;
        }

        public Criteria andExpensetypeNotBetween(String value1, String value2) {
            addCriterion("expensetype not between", value1, value2, "expensetype");
            return (Criteria) this;
        }

        public Criteria andOverStandardIsNull() {
            addCriterion("over_standard is null");
            return (Criteria) this;
        }

        public Criteria andOverStandardIsNotNull() {
            addCriterion("over_standard is not null");
            return (Criteria) this;
        }

        public Criteria andOverStandardEqualTo(Boolean value) {
            addCriterion("over_standard =", value, "overStandard");
            return (Criteria) this;
        }

        public Criteria andOverStandardNotEqualTo(Boolean value) {
            addCriterion("over_standard <>", value, "overStandard");
            return (Criteria) this;
        }

        public Criteria andOverStandardGreaterThan(Boolean value) {
            addCriterion("over_standard >", value, "overStandard");
            return (Criteria) this;
        }

        public Criteria andOverStandardGreaterThanOrEqualTo(Boolean value) {
            addCriterion("over_standard >=", value, "overStandard");
            return (Criteria) this;
        }

        public Criteria andOverStandardLessThan(Boolean value) {
            addCriterion("over_standard <", value, "overStandard");
            return (Criteria) this;
        }

        public Criteria andOverStandardLessThanOrEqualTo(Boolean value) {
            addCriterion("over_standard <=", value, "overStandard");
            return (Criteria) this;
        }

        public Criteria andOverStandardIn(List<Boolean> values) {
            addCriterion("over_standard in", values, "overStandard");
            return (Criteria) this;
        }

        public Criteria andOverStandardNotIn(List<Boolean> values) {
            addCriterion("over_standard not in", values, "overStandard");
            return (Criteria) this;
        }

        public Criteria andOverStandardBetween(Boolean value1, Boolean value2) {
            addCriterion("over_standard between", value1, value2, "overStandard");
            return (Criteria) this;
        }

        public Criteria andOverStandardNotBetween(Boolean value1, Boolean value2) {
            addCriterion("over_standard not between", value1, value2, "overStandard");
            return (Criteria) this;
        }

        public Criteria andSeatIsNull() {
            addCriterion("seat is null");
            return (Criteria) this;
        }

        public Criteria andSeatIsNotNull() {
            addCriterion("seat is not null");
            return (Criteria) this;
        }

        public Criteria andSeatEqualTo(String value) {
            addCriterion("seat =", value, "seat");
            return (Criteria) this;
        }

        public Criteria andSeatNotEqualTo(String value) {
            addCriterion("seat <>", value, "seat");
            return (Criteria) this;
        }

        public Criteria andSeatGreaterThan(String value) {
            addCriterion("seat >", value, "seat");
            return (Criteria) this;
        }

        public Criteria andSeatGreaterThanOrEqualTo(String value) {
            addCriterion("seat >=", value, "seat");
            return (Criteria) this;
        }

        public Criteria andSeatLessThan(String value) {
            addCriterion("seat <", value, "seat");
            return (Criteria) this;
        }

        public Criteria andSeatLessThanOrEqualTo(String value) {
            addCriterion("seat <=", value, "seat");
            return (Criteria) this;
        }

        public Criteria andSeatLike(String value) {
            addCriterion("seat like", value, "seat");
            return (Criteria) this;
        }

        public Criteria andSeatNotLike(String value) {
            addCriterion("seat not like", value, "seat");
            return (Criteria) this;
        }

        public Criteria andSeatIn(List<String> values) {
            addCriterion("seat in", values, "seat");
            return (Criteria) this;
        }

        public Criteria andSeatNotIn(List<String> values) {
            addCriterion("seat not in", values, "seat");
            return (Criteria) this;
        }

        public Criteria andSeatBetween(String value1, String value2) {
            addCriterion("seat between", value1, value2, "seat");
            return (Criteria) this;
        }

        public Criteria andSeatNotBetween(String value1, String value2) {
            addCriterion("seat not between", value1, value2, "seat");
            return (Criteria) this;
        }

        public Criteria andSeatpkIsNull() {
            addCriterion("seatpk is null");
            return (Criteria) this;
        }

        public Criteria andSeatpkIsNotNull() {
            addCriterion("seatpk is not null");
            return (Criteria) this;
        }

        public Criteria andSeatpkEqualTo(String value) {
            addCriterion("seatpk =", value, "seatpk");
            return (Criteria) this;
        }

        public Criteria andSeatpkNotEqualTo(String value) {
            addCriterion("seatpk <>", value, "seatpk");
            return (Criteria) this;
        }

        public Criteria andSeatpkGreaterThan(String value) {
            addCriterion("seatpk >", value, "seatpk");
            return (Criteria) this;
        }

        public Criteria andSeatpkGreaterThanOrEqualTo(String value) {
            addCriterion("seatpk >=", value, "seatpk");
            return (Criteria) this;
        }

        public Criteria andSeatpkLessThan(String value) {
            addCriterion("seatpk <", value, "seatpk");
            return (Criteria) this;
        }

        public Criteria andSeatpkLessThanOrEqualTo(String value) {
            addCriterion("seatpk <=", value, "seatpk");
            return (Criteria) this;
        }

        public Criteria andSeatpkLike(String value) {
            addCriterion("seatpk like", value, "seatpk");
            return (Criteria) this;
        }

        public Criteria andSeatpkNotLike(String value) {
            addCriterion("seatpk not like", value, "seatpk");
            return (Criteria) this;
        }

        public Criteria andSeatpkIn(List<String> values) {
            addCriterion("seatpk in", values, "seatpk");
            return (Criteria) this;
        }

        public Criteria andSeatpkNotIn(List<String> values) {
            addCriterion("seatpk not in", values, "seatpk");
            return (Criteria) this;
        }

        public Criteria andSeatpkBetween(String value1, String value2) {
            addCriterion("seatpk between", value1, value2, "seatpk");
            return (Criteria) this;
        }

        public Criteria andSeatpkNotBetween(String value1, String value2) {
            addCriterion("seatpk not between", value1, value2, "seatpk");
            return (Criteria) this;
        }

        public Criteria andStartDateIsNull() {
            addCriterion("start_date is null");
            return (Criteria) this;
        }

        public Criteria andStartDateIsNotNull() {
            addCriterion("start_date is not null");
            return (Criteria) this;
        }

        public Criteria andStartDateEqualTo(Date value) {
            addCriterionForJDBCDate("start_date =", value, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("start_date <>", value, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateGreaterThan(Date value) {
            addCriterionForJDBCDate("start_date >", value, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("start_date >=", value, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateLessThan(Date value) {
            addCriterionForJDBCDate("start_date <", value, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("start_date <=", value, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateIn(List<Date> values) {
            addCriterionForJDBCDate("start_date in", values, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("start_date not in", values, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("start_date between", value1, value2, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("start_date not between", value1, value2, "startDate");
            return (Criteria) this;
        }

        public Criteria andEndDateIsNull() {
            addCriterion("end_date is null");
            return (Criteria) this;
        }

        public Criteria andEndDateIsNotNull() {
            addCriterion("end_date is not null");
            return (Criteria) this;
        }

        public Criteria andEndDateEqualTo(Date value) {
            addCriterionForJDBCDate("end_date =", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("end_date <>", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateGreaterThan(Date value) {
            addCriterionForJDBCDate("end_date >", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("end_date >=", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateLessThan(Date value) {
            addCriterionForJDBCDate("end_date <", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("end_date <=", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateIn(List<Date> values) {
            addCriterionForJDBCDate("end_date in", values, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("end_date not in", values, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("end_date between", value1, value2, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("end_date not between", value1, value2, "endDate");
            return (Criteria) this;
        }

        public Criteria andItemPkIsNull() {
            addCriterion("item_pk is null");
            return (Criteria) this;
        }

        public Criteria andItemPkIsNotNull() {
            addCriterion("item_pk is not null");
            return (Criteria) this;
        }

        public Criteria andItemPkEqualTo(String value) {
            addCriterion("item_pk =", value, "itemPk");
            return (Criteria) this;
        }

        public Criteria andItemPkNotEqualTo(String value) {
            addCriterion("item_pk <>", value, "itemPk");
            return (Criteria) this;
        }

        public Criteria andItemPkGreaterThan(String value) {
            addCriterion("item_pk >", value, "itemPk");
            return (Criteria) this;
        }

        public Criteria andItemPkGreaterThanOrEqualTo(String value) {
            addCriterion("item_pk >=", value, "itemPk");
            return (Criteria) this;
        }

        public Criteria andItemPkLessThan(String value) {
            addCriterion("item_pk <", value, "itemPk");
            return (Criteria) this;
        }

        public Criteria andItemPkLessThanOrEqualTo(String value) {
            addCriterion("item_pk <=", value, "itemPk");
            return (Criteria) this;
        }

        public Criteria andItemPkLike(String value) {
            addCriterion("item_pk like", value, "itemPk");
            return (Criteria) this;
        }

        public Criteria andItemPkNotLike(String value) {
            addCriterion("item_pk not like", value, "itemPk");
            return (Criteria) this;
        }

        public Criteria andItemPkIn(List<String> values) {
            addCriterion("item_pk in", values, "itemPk");
            return (Criteria) this;
        }

        public Criteria andItemPkNotIn(List<String> values) {
            addCriterion("item_pk not in", values, "itemPk");
            return (Criteria) this;
        }

        public Criteria andItemPkBetween(String value1, String value2) {
            addCriterion("item_pk between", value1, value2, "itemPk");
            return (Criteria) this;
        }

        public Criteria andItemPkNotBetween(String value1, String value2) {
            addCriterion("item_pk not between", value1, value2, "itemPk");
            return (Criteria) this;
        }

        public Criteria andItemNameIsNull() {
            addCriterion("item_name is null");
            return (Criteria) this;
        }

        public Criteria andItemNameIsNotNull() {
            addCriterion("item_name is not null");
            return (Criteria) this;
        }

        public Criteria andItemNameEqualTo(String value) {
            addCriterion("item_name =", value, "itemName");
            return (Criteria) this;
        }

        public Criteria andItemNameNotEqualTo(String value) {
            addCriterion("item_name <>", value, "itemName");
            return (Criteria) this;
        }

        public Criteria andItemNameGreaterThan(String value) {
            addCriterion("item_name >", value, "itemName");
            return (Criteria) this;
        }

        public Criteria andItemNameGreaterThanOrEqualTo(String value) {
            addCriterion("item_name >=", value, "itemName");
            return (Criteria) this;
        }

        public Criteria andItemNameLessThan(String value) {
            addCriterion("item_name <", value, "itemName");
            return (Criteria) this;
        }

        public Criteria andItemNameLessThanOrEqualTo(String value) {
            addCriterion("item_name <=", value, "itemName");
            return (Criteria) this;
        }

        public Criteria andItemNameLike(String value) {
            addCriterion("item_name like", value, "itemName");
            return (Criteria) this;
        }

        public Criteria andItemNameNotLike(String value) {
            addCriterion("item_name not like", value, "itemName");
            return (Criteria) this;
        }

        public Criteria andItemNameIn(List<String> values) {
            addCriterion("item_name in", values, "itemName");
            return (Criteria) this;
        }

        public Criteria andItemNameNotIn(List<String> values) {
            addCriterion("item_name not in", values, "itemName");
            return (Criteria) this;
        }

        public Criteria andItemNameBetween(String value1, String value2) {
            addCriterion("item_name between", value1, value2, "itemName");
            return (Criteria) this;
        }

        public Criteria andItemNameNotBetween(String value1, String value2) {
            addCriterion("item_name not between", value1, value2, "itemName");
            return (Criteria) this;
        }

        public Criteria andSzitemPkIsNull() {
            addCriterion("szitem_pk is null");
            return (Criteria) this;
        }

        public Criteria andSzitemPkIsNotNull() {
            addCriterion("szitem_pk is not null");
            return (Criteria) this;
        }

        public Criteria andSzitemPkEqualTo(String value) {
            addCriterion("szitem_pk =", value, "szitemPk");
            return (Criteria) this;
        }

        public Criteria andSzitemPkNotEqualTo(String value) {
            addCriterion("szitem_pk <>", value, "szitemPk");
            return (Criteria) this;
        }

        public Criteria andSzitemPkGreaterThan(String value) {
            addCriterion("szitem_pk >", value, "szitemPk");
            return (Criteria) this;
        }

        public Criteria andSzitemPkGreaterThanOrEqualTo(String value) {
            addCriterion("szitem_pk >=", value, "szitemPk");
            return (Criteria) this;
        }

        public Criteria andSzitemPkLessThan(String value) {
            addCriterion("szitem_pk <", value, "szitemPk");
            return (Criteria) this;
        }

        public Criteria andSzitemPkLessThanOrEqualTo(String value) {
            addCriterion("szitem_pk <=", value, "szitemPk");
            return (Criteria) this;
        }

        public Criteria andSzitemPkLike(String value) {
            addCriterion("szitem_pk like", value, "szitemPk");
            return (Criteria) this;
        }

        public Criteria andSzitemPkNotLike(String value) {
            addCriterion("szitem_pk not like", value, "szitemPk");
            return (Criteria) this;
        }

        public Criteria andSzitemPkIn(List<String> values) {
            addCriterion("szitem_pk in", values, "szitemPk");
            return (Criteria) this;
        }

        public Criteria andSzitemPkNotIn(List<String> values) {
            addCriterion("szitem_pk not in", values, "szitemPk");
            return (Criteria) this;
        }

        public Criteria andSzitemPkBetween(String value1, String value2) {
            addCriterion("szitem_pk between", value1, value2, "szitemPk");
            return (Criteria) this;
        }

        public Criteria andSzitemPkNotBetween(String value1, String value2) {
            addCriterion("szitem_pk not between", value1, value2, "szitemPk");
            return (Criteria) this;
        }

        public Criteria andSzitemNameIsNull() {
            addCriterion("szitem_name is null");
            return (Criteria) this;
        }

        public Criteria andSzitemNameIsNotNull() {
            addCriterion("szitem_name is not null");
            return (Criteria) this;
        }

        public Criteria andSzitemNameEqualTo(String value) {
            addCriterion("szitem_name =", value, "szitemName");
            return (Criteria) this;
        }

        public Criteria andSzitemNameNotEqualTo(String value) {
            addCriterion("szitem_name <>", value, "szitemName");
            return (Criteria) this;
        }

        public Criteria andSzitemNameGreaterThan(String value) {
            addCriterion("szitem_name >", value, "szitemName");
            return (Criteria) this;
        }

        public Criteria andSzitemNameGreaterThanOrEqualTo(String value) {
            addCriterion("szitem_name >=", value, "szitemName");
            return (Criteria) this;
        }

        public Criteria andSzitemNameLessThan(String value) {
            addCriterion("szitem_name <", value, "szitemName");
            return (Criteria) this;
        }

        public Criteria andSzitemNameLessThanOrEqualTo(String value) {
            addCriterion("szitem_name <=", value, "szitemName");
            return (Criteria) this;
        }

        public Criteria andSzitemNameLike(String value) {
            addCriterion("szitem_name like", value, "szitemName");
            return (Criteria) this;
        }

        public Criteria andSzitemNameNotLike(String value) {
            addCriterion("szitem_name not like", value, "szitemName");
            return (Criteria) this;
        }

        public Criteria andSzitemNameIn(List<String> values) {
            addCriterion("szitem_name in", values, "szitemName");
            return (Criteria) this;
        }

        public Criteria andSzitemNameNotIn(List<String> values) {
            addCriterion("szitem_name not in", values, "szitemName");
            return (Criteria) this;
        }

        public Criteria andSzitemNameBetween(String value1, String value2) {
            addCriterion("szitem_name between", value1, value2, "szitemName");
            return (Criteria) this;
        }

        public Criteria andSzitemNameNotBetween(String value1, String value2) {
            addCriterion("szitem_name not between", value1, value2, "szitemName");
            return (Criteria) this;
        }

        public Criteria andReductionAmountIsNull() {
            addCriterion("reduction_amount is null");
            return (Criteria) this;
        }

        public Criteria andReductionAmountIsNotNull() {
            addCriterion("reduction_amount is not null");
            return (Criteria) this;
        }

        public Criteria andReductionAmountEqualTo(Double value) {
            addCriterion("reduction_amount =", value, "reductionAmount");
            return (Criteria) this;
        }

        public Criteria andReductionAmountNotEqualTo(Double value) {
            addCriterion("reduction_amount <>", value, "reductionAmount");
            return (Criteria) this;
        }

        public Criteria andReductionAmountGreaterThan(Double value) {
            addCriterion("reduction_amount >", value, "reductionAmount");
            return (Criteria) this;
        }

        public Criteria andReductionAmountGreaterThanOrEqualTo(Double value) {
            addCriterion("reduction_amount >=", value, "reductionAmount");
            return (Criteria) this;
        }

        public Criteria andReductionAmountLessThan(Double value) {
            addCriterion("reduction_amount <", value, "reductionAmount");
            return (Criteria) this;
        }

        public Criteria andReductionAmountLessThanOrEqualTo(Double value) {
            addCriterion("reduction_amount <=", value, "reductionAmount");
            return (Criteria) this;
        }

        public Criteria andReductionAmountIn(List<Double> values) {
            addCriterion("reduction_amount in", values, "reductionAmount");
            return (Criteria) this;
        }

        public Criteria andReductionAmountNotIn(List<Double> values) {
            addCriterion("reduction_amount not in", values, "reductionAmount");
            return (Criteria) this;
        }

        public Criteria andReductionAmountBetween(Double value1, Double value2) {
            addCriterion("reduction_amount between", value1, value2, "reductionAmount");
            return (Criteria) this;
        }

        public Criteria andReductionAmountNotBetween(Double value1, Double value2) {
            addCriterion("reduction_amount not between", value1, value2, "reductionAmount");
            return (Criteria) this;
        }

        public Criteria andReductionInfoIsNull() {
            addCriterion("reduction_info is null");
            return (Criteria) this;
        }

        public Criteria andReductionInfoIsNotNull() {
            addCriterion("reduction_info is not null");
            return (Criteria) this;
        }

        public Criteria andReductionInfoEqualTo(String value) {
            addCriterion("reduction_info =", value, "reductionInfo");
            return (Criteria) this;
        }

        public Criteria andReductionInfoNotEqualTo(String value) {
            addCriterion("reduction_info <>", value, "reductionInfo");
            return (Criteria) this;
        }

        public Criteria andReductionInfoGreaterThan(String value) {
            addCriterion("reduction_info >", value, "reductionInfo");
            return (Criteria) this;
        }

        public Criteria andReductionInfoGreaterThanOrEqualTo(String value) {
            addCriterion("reduction_info >=", value, "reductionInfo");
            return (Criteria) this;
        }

        public Criteria andReductionInfoLessThan(String value) {
            addCriterion("reduction_info <", value, "reductionInfo");
            return (Criteria) this;
        }

        public Criteria andReductionInfoLessThanOrEqualTo(String value) {
            addCriterion("reduction_info <=", value, "reductionInfo");
            return (Criteria) this;
        }

        public Criteria andReductionInfoLike(String value) {
            addCriterion("reduction_info like", value, "reductionInfo");
            return (Criteria) this;
        }

        public Criteria andReductionInfoNotLike(String value) {
            addCriterion("reduction_info not like", value, "reductionInfo");
            return (Criteria) this;
        }

        public Criteria andReductionInfoIn(List<String> values) {
            addCriterion("reduction_info in", values, "reductionInfo");
            return (Criteria) this;
        }

        public Criteria andReductionInfoNotIn(List<String> values) {
            addCriterion("reduction_info not in", values, "reductionInfo");
            return (Criteria) this;
        }

        public Criteria andReductionInfoBetween(String value1, String value2) {
            addCriterion("reduction_info between", value1, value2, "reductionInfo");
            return (Criteria) this;
        }

        public Criteria andReductionInfoNotBetween(String value1, String value2) {
            addCriterion("reduction_info not between", value1, value2, "reductionInfo");
            return (Criteria) this;
        }

        public Criteria andReductionTimeIsNull() {
            addCriterion("reduction_time is null");
            return (Criteria) this;
        }

        public Criteria andReductionTimeIsNotNull() {
            addCriterion("reduction_time is not null");
            return (Criteria) this;
        }

        public Criteria andReductionTimeEqualTo(Date value) {
            addCriterion("reduction_time =", value, "reductionTime");
            return (Criteria) this;
        }

        public Criteria andReductionTimeNotEqualTo(Date value) {
            addCriterion("reduction_time <>", value, "reductionTime");
            return (Criteria) this;
        }

        public Criteria andReductionTimeGreaterThan(Date value) {
            addCriterion("reduction_time >", value, "reductionTime");
            return (Criteria) this;
        }

        public Criteria andReductionTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("reduction_time >=", value, "reductionTime");
            return (Criteria) this;
        }

        public Criteria andReductionTimeLessThan(Date value) {
            addCriterion("reduction_time <", value, "reductionTime");
            return (Criteria) this;
        }

        public Criteria andReductionTimeLessThanOrEqualTo(Date value) {
            addCriterion("reduction_time <=", value, "reductionTime");
            return (Criteria) this;
        }

        public Criteria andReductionTimeIn(List<Date> values) {
            addCriterion("reduction_time in", values, "reductionTime");
            return (Criteria) this;
        }

        public Criteria andReductionTimeNotIn(List<Date> values) {
            addCriterion("reduction_time not in", values, "reductionTime");
            return (Criteria) this;
        }

        public Criteria andReductionTimeBetween(Date value1, Date value2) {
            addCriterion("reduction_time between", value1, value2, "reductionTime");
            return (Criteria) this;
        }

        public Criteria andReductionTimeNotBetween(Date value1, Date value2) {
            addCriterion("reduction_time not between", value1, value2, "reductionTime");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

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

    public Integer getStartRow() {
        return startRow;
    }

    public void setStartRow(Integer startRow) {
        this.startRow = startRow;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}