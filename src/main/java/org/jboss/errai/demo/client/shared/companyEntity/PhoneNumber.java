package org.jboss.errai.demo.client.shared.companyEntity;

import org.jboss.errai.common.client.api.annotations.Portable;
import org.jboss.errai.databinding.client.api.Bindable;

@Portable
public class PhoneNumber{

  public PhoneNumber(){

  }

  private String countryPrefix;
  private String number;

  public String getCountryPrefix(){
    return countryPrefix;
  }

  public String getNumber(){
    return number;
  }

  public void setCountryPrefix(String countryPrefix){
    this.countryPrefix = countryPrefix;
  }

  public void setNumber(String number){
    this.number = number;
  }

  @Override
  public String toString(){
    return "PhoneNumber{" + "countryPrefix=" + countryPrefix + ", number=" + number + '}';
  }


}
