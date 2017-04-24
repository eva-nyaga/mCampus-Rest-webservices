/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.springframework.security.oauth.api.model.healthcare.clients;

/**
 *
 * @author Eva
 */
public class CompletedUnits {
    String ttunitscompleted, ttmarks, avgmarks, avggrade, year, course, unitname, marks, grade, gpv;

    public String getTtunitscompleted() {
        return ttunitscompleted;
    }

    public void setTtunitscompleted(String ttunitscompleted) {
        this.ttunitscompleted = ttunitscompleted;
    }

    public String getTtmarks() {
        return ttmarks;
    }

    public void setTtmarks(String ttmarks) {
        this.ttmarks = ttmarks;
    }

    public String getAvgmarks() {
        return avgmarks;
    }

    public void setAvgmarks(String avgmarks) {
        this.avgmarks = avgmarks;
    }

    public String getAvggrade() {
        return avggrade;
    }

    public void setAvggrade(String avggrade) {
        this.avggrade = avggrade;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getUnitname() {
        return unitname;
    }

    public void setUnitname(String unitname) {
        this.unitname = unitname;
    }

    public String getMarks() {
        return marks;
    }

    public void setMarks(String marks) {
        this.marks = marks;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getGpv() {
        return gpv;
    }

    public void setGpv(String gpv) {
        this.gpv = gpv;
    }
    
   
    
      public CompletedUnits(String year, String course, String unitname, String marks, String grade, String gpv, 
              String ttunitscompleted, String ttmarks, String avgmarks, String avggrade){
        this.year=year;
        this.course=course;
        this.unitname=unitname;
        this.marks=marks;
        this.grade=grade;
        this.gpv=gpv;
        this.ttunitscompleted=ttunitscompleted;
        this.ttmarks=ttmarks;
        this.avgmarks=avgmarks;
        this.avggrade=avggrade;
    }
    
}
