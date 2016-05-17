package org.jboss.errai.demo.client.shared;

import java.util.HashMap;
import org.jboss.errai.common.client.api.annotations.Portable;
import org.jboss.errai.databinding.client.api.Bindable;

@Portable
@Bindable
public class ContactPerson {

  private String name;
  private String surname;
  private PhoneNumber phone;
  private HashMap<String, String> comments;

  public ContactPerson(){
  }

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
