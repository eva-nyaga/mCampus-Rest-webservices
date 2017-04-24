/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.springframework.security.oauth.api.model.healthcare.clients;

import org.json.JSONArray;
import com.fasterxml.jackson.annotation.JsonIgnore;
/**
 *
 * @author Eva
 */


public class CourseworkMarks {
    String unitcode, extype, unitname, score;
    JSONArray json ;
    //json.setVisibility(JsonMethod.FIELD, Visibility.ANY);
    public JSONArray getJson() {
        return json;
    }

    public void setJson(JSONArray json) {
        this.json = json;
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

    public String getExtype() {
        return extype;
    }

    public void setExtype(String extype) {
        this.extype = extype;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

   
    public CourseworkMarks(String unitcode, String unitname, String extype, String score){
        this.unitcode=unitcode;
        this.unitname=unitname;
       this.extype=extype;
       this.score=score;
    }
     public CourseworkMarks(){
        this.unitcode=unitcode;
        this.unitname=unitname;
        this.extype=extype;
        this.score=score;
    }
      public CourseworkMarks(JSONArray json){
        this.json=json;
    }
    
}
