package com.example.animalassist2;

public class OrgHelper {
    String nmorg, phorg, emorg, psorg;
    
    public OrgHelper(){
        
    }
    public OrgHelper(String nmorg, String phorg, String emorg, String psorg){
        this.phorg= phorg;
        this.emorg=emorg;
        this.nmorg= nmorg;
        this.psorg = psorg;

    }

    public String getPhorg() {
        return phorg;
    }

    public void setPhorg(String phorg) {
        this.phorg = phorg;
    }

    public String getEmorg() {
        return emorg;
    }

    public void setEmorg(String emorg) {
        this.emorg = emorg;
    }

    public String getNmorg() {
        return nmorg;
    }

    public void setNmorg(String nmorg) {
        this.nmorg = nmorg;
    }

    public String getPsorg() {
        return psorg;
    }

    public void setPsorg(String psorg) {
        this.psorg = psorg;
    }

}
