package org.jboss.errai.demo.client.shared;

import org.jboss.errai.common.client.api.annotations.Portable;
import org.jboss.errai.databinding.client.api.Bindable;

@Portable
@Bindable
public class BillingInfo{

  public BillingInfo(){

  }

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
