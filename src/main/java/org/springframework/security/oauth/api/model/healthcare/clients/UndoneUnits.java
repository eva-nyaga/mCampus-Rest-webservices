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
public class UndoneUnits {
    String year, unitcode, unitname, credits;

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getUnitcode() {
        return unitcode;
    }

    public void setUnitcode(String unitcode) {
        this.unitcode = unitcode;
    }

    public String getUnitname() {
        return unitname;
    }

    public void setUnitname(String unitname) {
        this.unitname = unitname;
    }

    public String getCredits() {
        return credits;
    }

    public void setCredits(String credits) {
        this.credits = credits;
    }
    
     public UndoneUnits(String year, String unitcode, String unitname, String credits){
        this.year=year;
        this.unitcode=unitcode;
        this.unitname=unitname;
        this.credits=credits;
    }
       
}
