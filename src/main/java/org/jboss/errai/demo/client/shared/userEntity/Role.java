package org.jboss.errai.demo.client.shared.userEntity;

import org.jboss.errai.common.client.api.annotations.MapsTo;
import org.jboss.errai.common.client.api.annotations.Portable;
import org.jboss.errai.security.shared.api.RequiredRolesProvider;
import org.jboss.errai.security.shared.api.RoleImpl;

@Portable
public class Role extends RoleImpl{

  public Role(@MapsTo("name") UsersRole name){
    super(name.toString());
  }

  @Override
  public boolean equals(Object o){
    Role role = (Role)o;
    return this.getName().equals(role.getName());
  }

  @Override
  public String getName(){
    return super.getName(); //To change body of generated methods, choose Tools | Templates.
  }



}
