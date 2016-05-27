package org.jboss.errai.demo.client.shared.userEntity;

import org.jboss.errai.common.client.api.annotations.Portable;
import org.jboss.errai.security.shared.api.RoleImpl;

@Portable
public class Role extends RoleImpl{

  public Role(){
    super("not_set_yet");
  }

  public Role(UsersRole name){
    super(name.toString());
  }

}
