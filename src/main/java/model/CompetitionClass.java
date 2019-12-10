package model;

import com.google.gson.JsonObject;

import java.io.Serializable;

public class CompetitionClass implements Serializable {

    String P360_ID;
    String P330_EKP_NUM;
    String P330_COMPETITION_TYPE;
    String P330_DISCIPLINE;
    String P330_DISCIPLINE_TO_NAME;
    String P330_ENTITY;
    String P330_PART_COMPETITION_TYPE;
    String P330_AGE_LIMIT_FROM;
    String P330_AGE_LIMIT_TO;
    String P330_START_DATE;
    String P330_END_DATE;
    String P330_SEASON;
    String P330_COMPETITION_NAME;
    String P330_GENDER;
    String P330_GENDER_TO_NAME;

    public CompetitionClass() {
        this.P360_ID = P360_ID;
        this.P330_EKP_NUM = P330_EKP_NUM;
        this.P330_COMPETITION_TYPE = P330_COMPETITION_TYPE;
        this.P330_DISCIPLINE = P330_DISCIPLINE;
        this.P330_DISCIPLINE_TO_NAME = P330_DISCIPLINE_TO_NAME;
        this.P330_ENTITY = P330_ENTITY;
        this.P330_PART_COMPETITION_TYPE = P330_PART_COMPETITION_TYPE;
        this.P330_AGE_LIMIT_FROM = P330_AGE_LIMIT_FROM;
        this.P330_AGE_LIMIT_TO = P330_AGE_LIMIT_TO;
        this.P330_START_DATE = P330_START_DATE;
        this.P330_END_DATE = P330_END_DATE;
        this.P330_SEASON = P330_SEASON;
        this.P330_COMPETITION_NAME = P330_COMPETITION_NAME;
        this.P330_GENDER = P330_GENDER;
        this.P330_GENDER_TO_NAME = P330_GENDER_TO_NAME;
    }

    public String getTournamentId() {
        return P360_ID;
    }

    public String getEkpNumber() {
        return P330_EKP_NUM;
    }

    public String getCompetitionType() {
        return P330_COMPETITION_TYPE;
    }

    public String getDiscipline() {
        return P330_DISCIPLINE;
    }

    public String getDisciplineToName() {
        return P330_DISCIPLINE_TO_NAME;
    }

    public String getOrganizator() {
        return P330_ENTITY;
    }

    public String getParticipationType() {
        return P330_PART_COMPETITION_TYPE;
    }

    public String getAgeStartDate() {
        return P330_AGE_LIMIT_FROM;
    }

    public String getAgeEndDate() {
        return P330_AGE_LIMIT_TO;
    }

    public String getCompetitionStartDate() {
        return P330_START_DATE;
    }

    public String getCompetitionName() {
        return P330_COMPETITION_NAME;
    }

    public String getCompetitionEndDate() {
        return P330_END_DATE;
    }

    public String getSeason() {
        return P330_SEASON;
    }

    public String getGender() {
        return P330_GENDER;
    }

    public String getGenderToName() {
        return P330_GENDER_TO_NAME;
    }


    public void setTournamentId(String P360_ID) {
        this.P360_ID = P360_ID;
    }

    public void setEkpNumber(String P330_EKP_NUM) {
        this.P330_EKP_NUM = P330_EKP_NUM;
    }

    public void setCompetitionType(String P330_COMPETITION_TYPE) {
        this.P330_COMPETITION_TYPE = P330_COMPETITION_TYPE;
    }

    public void setDiscipline(String P330_DISCIPLINE) {
        this.P330_DISCIPLINE = P330_DISCIPLINE;
    }

    public void setDisciplineToName(String P330_DISCIPLINE_TO_NAME) {
        this.P330_DISCIPLINE_TO_NAME = P330_DISCIPLINE_TO_NAME;
    }

    public void setOrganizator(String P330_ENTITY) {
        this.P330_ENTITY = P330_ENTITY;
    }

    public void setParticipatitonType(String P330_PART_COMPETITION_TYPE) {
        this.P330_PART_COMPETITION_TYPE = P330_PART_COMPETITION_TYPE;
    }

    public void setAgeStartDate(String P330_AGE_LIMIT_FROM) {
        this.P330_AGE_LIMIT_FROM = P330_AGE_LIMIT_FROM;
    }

    public void setAgeEndDate(String P330_AGE_LIMIT_TO) {
        this.P330_AGE_LIMIT_TO = P330_AGE_LIMIT_TO;
    }

    public void setCompetitionStartDate(String P330_START_DATE) {
        this.P330_START_DATE = P330_START_DATE;
    }

    public void setCompetitionEndDate(String P330_END_DATE) {
        this.P330_END_DATE = P330_END_DATE;
    }

    public void setSeason(String P330_SEASON) {
        this.P330_SEASON = P330_SEASON;
    }

    public void setCompetitionName(String P330_COMPETITION_NAME) {
        this.P330_COMPETITION_NAME = P330_COMPETITION_NAME;
    }

    public void setGender(String P330_GENDER) {
        this.P330_GENDER = P330_GENDER;
    }

    public void setGenderToName(String P330_GENDER_TO_NAME) {
        this.P330_GENDER_TO_NAME = P330_GENDER_TO_NAME;
    }


    @Override
    public String toString() {
        return "CompetitionClass{" +
                " EKP_NUM='" + P330_EKP_NUM + '\'' +
                " COMPETITION_TYPE='" + P330_COMPETITION_TYPE + '\'' +
                " DISCIPLINE='" + P330_DISCIPLINE + '\'' +
                " ENTITY='" + P330_ENTITY + '\'' +
                " ART_COMPETITION_TYPE='" + P330_PART_COMPETITION_TYPE + '\'' +
                " AGE_LIMIT_FROM='" + P330_AGE_LIMIT_FROM + '\'' +
                " AGE_LIMIT_TO='" + P330_AGE_LIMIT_TO + '\'' +
                " START_DATE='" + P330_START_DATE + '\'' +
                " END_DATE='" + P330_END_DATE + '\'' +
                " SEASON='" + P330_SEASON + '\'' +
                " COMPETITION_NAME='" + P330_COMPETITION_NAME + '\'' +
                '}';
    }
}