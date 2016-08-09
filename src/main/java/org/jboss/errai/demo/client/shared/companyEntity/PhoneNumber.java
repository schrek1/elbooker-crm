package org.jboss.errai.demo.client.shared.companyEntity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import org.jboss.errai.common.client.api.annotations.Portable;
import org.jboss.errai.databinding.client.api.Bindable;

@Portable
@Bindable
@Entity
public class PhoneNumber{

  @Id
  @GeneratedValue
  private int idPhoneNumber;

  private String countryPrefix;
  private String number;

  public PhoneNumber(){
  }

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
