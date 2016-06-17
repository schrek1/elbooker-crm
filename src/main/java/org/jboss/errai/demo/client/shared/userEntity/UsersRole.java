package org.jboss.errai.demo.client.shared.userEntity;

import com.google.common.collect.Lists;
import com.google.gwt.user.client.Window;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.jboss.errai.common.client.api.annotations.Portable;

@Portable
public enum UsersRole{
 ADMIN("administrátor"), COMPANY("firma");

  private String nameOfRole;

  private UsersRole(String nameOfRole){
    this.nameOfRole = nameOfRole;
  }

  public String getNameOfRole(){
    return this.nameOfRole;
  }


//  public static List<String> getAllRoles(){
//    List<String> roleNames = new ArrayList<String>();
//    for(UsersRole role : Arrays.asList(UsersRole.values())){
//      roleNames.add(role.getNameOfRole());
//    }
//    return roleNames;
//  }
}
