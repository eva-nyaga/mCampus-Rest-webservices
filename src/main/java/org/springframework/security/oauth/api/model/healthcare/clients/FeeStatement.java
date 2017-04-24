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
public class FeeStatement {
    
    String doctype, docno, debit, credit, balance;
    Date postingdate;

    public String getDoctype() {
        return doctype;
    }

    public void setDoctype(String doctype) {
        this.doctype = doctype;
    }

    public String getDocno() {
        return docno;
    }

    public void setDocno(String docno) {
        this.docno = docno;
    }

    public String getDebit() {
        return debit;
    }

    public void setDebit(String debit) {
        this.debit = debit;
    }

    public String getCredit() {
        return credit;
    }

    public void setCredit(String credit) {
        this.credit = credit;
    }

    public Date getPostingdate() {
        return postingdate;
    }

    public void setPostingdate(Date postingdate) {
        this.postingdate = postingdate;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }
    
    
    
    public FeeStatement(String doctype, String docno, String debit, String credit, Date postingdate, String balance){
        this.doctype=doctype;
        this.docno=docno;
        this.debit=debit;
        this.credit=credit;
        this.postingdate=postingdate;
        this.balance=balance;
    }
    
}
