package org.jboss.errai.demo.client.shared.companyEntity;

import java.util.HashMap;
import org.jboss.errai.common.client.api.annotations.Portable;
import org.jboss.errai.databinding.client.api.Bindable;

@Portable
@Bindable
public class ContactPerson {

  private String firstname;
  private String surename;
  private PhoneNumber phone;
  private HashMap<String, String> comments;

  public ContactPerson(){
  }

  public String getFirstname(){
    return firstname;
  }

  public String getSurename(){
    return surename;
  }

  public PhoneNumber getPhone(){
    return phone;
  }

  public void setFirstname(String name){
    this.firstname = name;
  }

  public void setSurename(String surename){
    this.surename = surename;
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

  @Override
  public String toString(){
    return "ContactPerson{" + "firstname=" + firstname + ", surename=" + surename + ", phone=" + phone + ", comments=" + comments + '}';
  }

}
