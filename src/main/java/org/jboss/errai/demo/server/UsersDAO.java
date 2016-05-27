package org.jboss.errai.demo.server;

import java.util.Arrays;
import javassist.NotFoundException;
import org.jboss.errai.demo.client.shared.userEntity.Role;
import org.jboss.errai.demo.client.shared.userEntity.User;
import org.jboss.errai.demo.client.shared.userEntity.UsersRole;

/**
 * Class which make query to JPA DB of users Class is singleton
 *
 * @author ondra
 */
public class UsersDAO{

  public UserWithPass getUserByLogin(String login) throws Exception{
    UserWithPass uwp;

    if(login.equals("admin")){
      uwp = new UserWithPass("admin", Arrays.asList(new Role(UsersRole.ADMIN)), "1234");
      return uwp;
    }
    if(login.equals("ondra")){
      uwp = new UserWithPass("ondra", Arrays.asList(new Role(UsersRole.USER)), "4321");
      return uwp;
    }
    if(login.equals("firma")){
      uwp = new UserWithPass("firma", Arrays.asList(new Role(UsersRole.COMPANY)), "firma1");
      return uwp;
    }

    throw new Exception("user not found");
  }

}
