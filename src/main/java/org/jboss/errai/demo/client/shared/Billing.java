package org.jboss.errai.demo.client.shared;


class Billing {

  private String idNum;
  private String vatNum;

  public String getIdNum(){
    return idNum;
  }

  public String getVatNum(){
    return vatNum;
  }

  public void setIdNum(String idNum){
    this.idNum = idNum;
  }

  public void setVatNum(String vatNum){
    this.vatNum = vatNum;
  }

}
