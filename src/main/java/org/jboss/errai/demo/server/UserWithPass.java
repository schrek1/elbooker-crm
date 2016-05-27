package org.jboss.errai.demo.server;

import java.util.Collection;
import org.jboss.errai.demo.client.shared.userEntity.User;
import org.jboss.errai.security.shared.api.Role;

public class UserWithPass extends User{

  public UserWithPass(){
  
  }

  private String passwordHash;

  public UserWithPass(String name, Collection<? extends Role> roles){
    super(name, roles);
  }

  public UserWithPass(String name, Collection<? extends Role> roles, String passwordHash){
    super(name, roles);
    this.passwordHash = passwordHash;
  }

  public String getPasswordHash(){
    return passwordHash;
  }

  public void setPasswordHash(String passwordHash){
    this.passwordHash = passwordHash;
  }

  public User makeUserWithoutPassword(){
    return new User(this.getIdentifier(), this.getRoles());
  }

}
