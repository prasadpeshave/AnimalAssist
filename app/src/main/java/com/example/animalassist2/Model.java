package com.example.animalassist2;

public class Model {

    private String imgUrl, anim, urgency,location, descp, itemm;
    public Model(){

    }

    public Model(String imgUrl, String anim, String location, String itemm, String descp, String urgency ){
        this.imgUrl= imgUrl;
        this.descp= descp;
        this.location= location;
        this.anim= anim;
        this.urgency= urgency;
        this.itemm= itemm;


    }

    public String getImgUrl(){
        return imgUrl;
    }
    public void setImgUrl(String imgUrl){
        this.imgUrl=imgUrl;
    }

    public String getDescp(){
        return descp;
    }
    public void setDescp(String descp){
        this.descp= descp;
    }

    public String getitemm(){
        return itemm;
    }
    public void setitemm(String itemm){
        this.itemm= itemm;
    }

    public String getLocation(){
        return location;
    }
    public void setLocation(String location){
        this.location= location;
    }

    public String getAnim(){
        return anim;
    }
    public void setAnim(String anim){
        this.anim= anim;
    }

    public String getUrgency(){
        return urgency;
    }
    public void setUrgency(String urgency){
        this.urgency= urgency;
    }
}
