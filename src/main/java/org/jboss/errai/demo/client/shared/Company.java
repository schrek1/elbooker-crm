package org.jboss.errai.demo.client.shared;

import java.util.HashMap;
import org.jboss.errai.common.client.api.annotations.Portable;
import org.jboss.errai.databinding.client.api.Bindable;

/**
 *
 * @author ondra
 */
@Bindable
@Portable
public class Company{

  class Address{

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

  class PhoneNumber{

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

  class ContactPerson{

    private String name;
    private String surname;
    private PhoneNumber phone;
    private HashMap<String, String> comments;

    public String getName(){
      return name;
    }

    public String getSurname(){
      return surname;
    }

    public PhoneNumber getPhone(){
      return phone;
    }

    public void setName(String name){
      this.name = name;
    }

    public void setSurname(String surname){
      this.surname = surname;
    }

    public void setPhone(PhoneNumber phone){
      this.phone = phone;
    }

    public String getSelectedComment(String findComment){
      String result = this.comments.get(findComment);

      if(result == null){
        return "";
      }else{
        return result;
      }
    }

    public void addComment(String key, String value){
      this.comments.put(key, value);
    }

    public HashMap<String, String> getAllComments(){
      return this.comments;
    }

  }

  class Billing{
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

  private String name;
  private ContactPerson contactPerson;
  private PhoneNumber phone;
  private Address address;
  private Billing billingInfo;
  private String web;

  public Company(){
  }

  public String getName(){
    return name;
  }

  public ContactPerson getContactPerson(){
    return contactPerson;
  }

  public PhoneNumber getPhone(){
    return phone;
  }

  public Address getAddress(){
    return address;
  }

  public Billing getBillingInfo(){
    return billingInfo;
  }

  public String getWeb(){
    return web;
  }

  public void setName(String name){
    this.name = name;
  }

  public void setContactPerson(ContactPerson contactPerson){
    this.contactPerson = contactPerson;
  }

  public void setPhone(PhoneNumber phone){
    this.phone = phone;
  }

  public void setAddress(Address address){
    this.address = address;
  }

  public void setBillingInfo(Billing billingInfo){
    this.billingInfo = billingInfo;
  }

  public void setWeb(String web){
    this.web = web;
  }

}
