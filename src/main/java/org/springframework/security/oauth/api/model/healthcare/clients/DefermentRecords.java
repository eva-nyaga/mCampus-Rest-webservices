/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.springframework.security.oauth.api.model.healthcare.clients;

import java.util.Date;

/**
 *
 * @author Eva
 */
public class DefermentRecords {
    String status, period;
    Date startdate;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public Date getStartdate() {
        return startdate;
    }

    public void setStartdate(Date startdate) {
        this.startdate = startdate;
    }
    
    public DefermentRecords(String status, String period, Date startdate){
        this.status=status;
        this.period=period;
        this.startdate=startdate;
    }
}
