package org.jboss.errai.demo.client.shared;

import org.jboss.errai.common.client.api.annotations.Portable;
import org.jboss.errai.databinding.client.api.Bindable;

@Portable
@Bindable
public class Address {

  private String country;
  private String street;
  private String postalCode;
  private String town;


  public String getCountry(){
    return country;
  }

  public String getStreet(){
    return street;
  }

  public String getPostalCode(){
    return postalCode;
  }

  public String getTown(){
    return town;
  }

  public void setCountry(String country){
    this.country = country;
  }

  public void setStreet(String street){
    this.street = street;
  }

  public void setPostalCode(String postalCode){
    this.postalCode = postalCode;
  }

  public void setTown(String town){
    this.town = town;
  }

}
