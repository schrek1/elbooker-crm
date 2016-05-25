package org.jboss.errai.demo.client.shared;

import java.io.Serializable;
import java.util.Map;
import java.util.Set;
import javax.ejb.Stateful;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.SessionScoped;
import javax.persistence.Entity;
import javax.persistence.Id;
import org.jboss.errai.common.client.api.annotations.Portable;
import org.jboss.errai.databinding.client.api.Bindable;
import org.jboss.errai.security.shared.api.Group;
import org.jboss.errai.security.shared.api.identity.User;

/**
 * Class(POJO) which represent user account in CRM
 *
 * @author ondra
 */

@Bindable
@Portable
public class UserAccount  implements Serializable, User{

  private static final long serialVersionUID = 1L;

  @Override
  public String getIdentifier(){
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public Set<org.jboss.errai.security.shared.api.Role> getRoles(){
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public Set<Group> getGroups(){
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public Map<String, String> getProperties(){
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void setProperty(String name, String value){
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void removeProperty(String name){
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public String getProperty(String name){
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  public enum Role{
    USER, ADMIN, COMPANY
  }

  private String username;
  private String password;
  private Role groupRole;

  public UserAccount(){
  }

  public UserAccount(String username, String password){
    this.username = username;
    this.password = password;
  }

  public Role getGroupRole(){
    return groupRole;
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

  public void setGroupRole(Role groupRole){
    this.groupRole = groupRole;
  }

  @Override
  public String toString(){
    return "UserAccount{" + "username=" + username + ", password=" + password + ", groupRole=" + groupRole + '}';
  }

}
