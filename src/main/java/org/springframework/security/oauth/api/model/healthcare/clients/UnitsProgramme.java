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
public class UnitsProgramme {
    
    String entryno, programme, progname;

    public String getEntryno() {
        return entryno;
    }

    public void setEntryno(String entryno) {
        this.entryno = entryno;
    }

    public String getProgramme() {
        return programme;
    }

    public String getProgname() {
        return progname;
    }

    public void setProgname(String progname) {
        this.progname = progname;
    }

    public void setProgramme(String programme) {
        this.programme = programme;
    }
    
    public UnitsProgramme(String entryno, String programme, String progname){
        this.entryno=entryno;
        this.programme=programme;
        this.progname=progname;
    }
    
}
