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
public class ChangeCourse {
    String availablecourses, currentcourse, availablecoursescode, intake;

    public String getAvailablecourses() {
        return availablecourses;
    }

    public void setAvailablecourses(String availablecourses) {
        this.availablecourses = availablecourses;
    }

    public String getCurrentcourse() {
        return currentcourse;
    }

    public void setCurrentcourse(String currentcourse) {
        this.currentcourse = currentcourse;
    }

    public String getAvailablecoursescode() {
        return availablecoursescode;
    }

    public void setAvailablecoursescode(String availablecoursescode) {
        this.availablecoursescode = availablecoursescode;
    }

    public String getIntake() {
        return intake;
    }

    public void setIntake(String intake) {
        this.intake = intake;
    }
    
    public ChangeCourse(String availablecourses, String currentcourse,String availablecoursescode,String intake){
        this.availablecourses=availablecourses;
        this.currentcourse=currentcourse;
        this.availablecoursescode=availablecoursescode;
        this.intake=intake;
    }
    
}
