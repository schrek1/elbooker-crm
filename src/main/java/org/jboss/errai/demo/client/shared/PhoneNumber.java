package org.jboss.errai.demo.client.shared;


class PhoneNumber {

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

}
