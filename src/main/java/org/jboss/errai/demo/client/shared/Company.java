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
