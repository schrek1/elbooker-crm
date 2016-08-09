package org.jboss.errai.demo.client.shared.companyEntity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import org.jboss.errai.common.client.api.annotations.Portable;
import org.jboss.errai.databinding.client.api.Bindable;

@Portable
@Bindable
@Entity
public class BillingInfo{

  @Id
  @GeneratedValue
  private int idBillingInfo;
  private String idNum;
  private String vatNum;

  public BillingInfo(){
  }

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

  @Override
  public String toString(){
    return "BillingInfo{" + "idNum=" + idNum + ", vatNum=" + vatNum + '}';
  }

}
