package org.jboss.errai.demo.server;

import org.jboss.errai.demo.client.shared.userEntity.UserWithPass;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javassist.NotFoundException;
import javax.enterprise.context.ApplicationScoped;
import org.jboss.errai.demo.client.shared.services.UserServices;
import org.jboss.errai.demo.client.shared.userEntity.Role;
import org.jboss.errai.demo.client.shared.userEntity.User;
import org.jboss.errai.demo.client.shared.userEntity.UsersRole;

/**
 * Class which make query to JPA DB of users Class is singleton
 *
 * @author ondra
 */
@ApplicationScoped
public class UsersDAO implements UserServices{

  public UserWithPass getUserWPByLogin(String login) throws Exception{
    UserWithPass uwp;
    if(login.equals("admin")){
      uwp = new UserWithPass("admin", Arrays.asList(new Role(UsersRole.ADMIN)), "1");
      return uwp;
    }
    if(login.equals("firma")){
      uwp = new UserWithPass("firma", Arrays.asList(new Role(UsersRole.COMPANY)), "2");
      return uwp;
    }
    if(login.equals("company")){
      uwp = new UserWithPass("company", Arrays.asList(new Role(UsersRole.COMPANY)), "3");
      return uwp;
    }

    throw new Exception("user not found");
  }

  @Override
  public List<User> getListOfUsersNP(){
    List<User> users = new ArrayList<User>();
    users.add(new User("admin", Arrays.asList(new Role(UsersRole.ADMIN))));
    users.add(new User("firma", Arrays.asList(new Role(UsersRole.COMPANY))));
    users.add(new User("company", Arrays.asList(new Role(UsersRole.COMPANY))));
    return users;
  }

}
