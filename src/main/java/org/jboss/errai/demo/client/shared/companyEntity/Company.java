package org.jboss.errai.demo.client.shared.companyEntity;

import java.util.HashMap;
import org.jboss.errai.common.client.api.annotations.Portable;
import org.jboss.errai.databinding.client.api.Bindable;

/**
 *
 * @author ondra
 */
@Portable
@Bindable
public class Company{

  private int id;
  private String name;
  private String web;

  private ContactPerson contactPerson;
//  private PhoneNumber phone;
//  private Address address;
//  private BillingInfo billingInfo;


  public Company(){
  }

  public int getId(){
    return id;
  }

  public String getName(){
    return name;
  }

  public String getWeb(){
    return web;
  }

  public void setId(int id){
    this.id = id;
  }

  public void setName(String name){
    this.name = name;
  }

  public void setWeb(String web){
    this.web = web;
  }

  public ContactPerson getContactPerson(){
    return contactPerson;
  }

  public void setContactPerson(ContactPerson contactPerson){
    this.contactPerson = contactPerson;
  }

  @Override
  public String toString(){
    return "Company{" + "id=" + id + ", name=" + name + ", web=" + web + ", contactPerson=" + contactPerson + '}';
  }


 

}
