package com.example.animalassist2;

public class UserHelper {
String ph, em, nm, ps;
    public UserHelper() {

    }
    public UserHelper(String ph, String em, String nm, String ps){
        this.ph= ph;
        this.em=em;
        this.nm= nm;
        this.ps = ps;

    }

    public String getPh() {
        return ph;
    }

    public void setPh(String ph) {
        this.ph = ph;
    }

    public String getEm() {
        return em;
    }

    public void setEm(String em) {
        this.em = em;
    }

    public String getNm() {
        return nm;
    }

    public void setNm(String nm) {
        this.nm = nm;
    }

    public String getPs() {
        return ps;
    }

    public void setPs(String ps) {
        this.ps = ps;
    }
}