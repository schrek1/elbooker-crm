package org.jboss.errai.demo.client.shared.companyEntity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.jboss.errai.common.client.api.annotations.Portable;
import org.jboss.errai.databinding.client.api.Bindable;

@Portable
@Bindable
@Entity
public class Address{

  @Id
  @GeneratedValue
  private int idAddress;

  private String country;
  private String street;
  private String postalCode;
  private String town;

  public Address(){
  }

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

  @Override
  public String toString(){
    return "Address{" + "country=" + country + ", street=" + street + ", postalCode=" + postalCode + ", town=" + town + '}';
  }

}
