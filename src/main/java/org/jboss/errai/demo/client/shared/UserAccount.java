package org.jboss.errai.demo.client.shared;

import org.jboss.errai.common.client.api.annotations.Portable;
import org.jboss.errai.databinding.client.api.Bindable;

/**
 *
 * @author ondra
 */

@Bindable
@Portable
public class UserAccount{

  private String username;
  private String password;

  public UserAccount(){
  }

  public UserAccount(String username, String password){
    this.username = username;
    this.password = password;
  }

  public String getUsername(){
    return username;
  }

  public String getPassword(){
    return password;
  }

  public void setUsername(String username){
    this.username = username;
  }

  public void setPassword(String password){
    this.password = password;
  }

  @Override
  public String toString(){
    return "LoginRequest{" + "username=" + username + ", password=" + password + '}';
  }

}
