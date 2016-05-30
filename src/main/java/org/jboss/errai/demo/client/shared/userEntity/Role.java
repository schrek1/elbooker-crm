package org.jboss.errai.demo.client.shared.userEntity;

import org.jboss.errai.common.client.api.annotations.MapsTo;
import org.jboss.errai.common.client.api.annotations.Portable;
import org.jboss.errai.security.shared.api.RoleImpl;

@Portable
public class Role extends RoleImpl{

  public Role(@MapsTo("name") UsersRole name){
    super(name.toString());
  }

}
