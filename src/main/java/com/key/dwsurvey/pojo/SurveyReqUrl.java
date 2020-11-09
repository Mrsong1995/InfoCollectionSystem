package com.key.dwsurvey.pojo;

import java.io.Serializable;

public class SurveyReqUrl implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_survey_req_url.id
     *
     * @mbggenerated
     */
    private String id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_survey_req_url.sid
     *
     * @mbggenerated
     */
    private String sid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_survey_req_url.state
     *
     * @mbggenerated
     */
    private Integer state;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_survey_req_url.survey_id
     *
     * @mbggenerated
     */
    private String surveyId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_survey_req_url
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_survey_req_url.id
     *
     * @return the value of t_survey_req_url.id
     *
     * @mbggenerated
     */
    public String getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_survey_req_url.id
     *
     * @param id the value for t_survey_req_url.id
     *
     * @mbggenerated
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_survey_req_url.sid
     *
     * @return the value of t_survey_req_url.sid
     *
     * @mbggenerated
     */
    public String getSid() {
        return sid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_survey_req_url.sid
     *
     * @param sid the value for t_survey_req_url.sid
     *
     * @mbggenerated
     */
    public void setSid(String sid) {
        this.sid = sid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_survey_req_url.state
     *
     * @return the value of t_survey_req_url.state
     *
     * @mbggenerated
     */
    public Integer getState() {
        return state;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_survey_req_url.state
     *
     * @param state the value for t_survey_req_url.state
     *
     * @mbggenerated
     */
    public void setState(Integer state) {
        this.state = state;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_survey_req_url.survey_id
     *
     * @return the value of t_survey_req_url.survey_id
     *
     * @mbggenerated
     */
    public String getSurveyId() {
        return surveyId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_survey_req_url.survey_id
     *
     * @param surveyId the value for t_survey_req_url.survey_id
     *
     * @mbggenerated
     */
    public void setSurveyId(String surveyId) {
        this.surveyId = surveyId;
    }
}