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
public class Reply {
    String reply, code;

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
    
  
    public Reply(String code, String reply){
        this.code=code;
        this.reply=reply;
    }
    

    

   
    
}
