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
public class CourseworkTimetable {
    
    String time, venue, unitname, unitcode;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public String getUnitname() {
        return unitname;
    }

    public void setUnitname(String unitname) {
        this.unitname = unitname;
    }

    public String getUnitcode() {
        return unitcode;
    }

    public void setUnitcode(String unitcode) {
        this.unitcode = unitcode;
    }
    
   public CourseworkTimetable(String time, String venue, String unitname, String unitcode){
       this.time=time;
       this.venue=venue;
       this.unitname=unitname;
       this.unitcode=unitcode;
   } 
   
   public CourseworkTimetable(String venue, String unitname, String unitcode){
       this.venue=venue;
       this.unitname=unitname;
       this.unitcode=unitcode;
   } 
   
   public CourseworkTimetable(){
       this.time=time;
       this.venue=venue;
       this.unitname=unitname;
       this.unitcode=unitcode;
   } 
    
}
