package org.jboss.errai.demo.server;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import org.jboss.errai.bus.server.annotations.Service;
import org.jboss.errai.demo.client.shared.services.UserServices;
import org.jboss.errai.demo.client.shared.userEntity.Role;
import org.jboss.errai.demo.client.shared.userEntity.User;
import org.jboss.errai.demo.client.shared.userEntity.UserWithPass;
import org.jboss.errai.demo.client.shared.userEntity.UsersRole;
import org.slf4j.Logger;

@ApplicationScoped
@Service
public class UserServicesImpl implements UserServices{

  @Inject
  private UsersDAO usersDAO;

  public UserWithPass getUserWPByLogin(String login) throws Exception{
    List<UserWithPass> users = this.usersDAO.getUserList();

    for(UserWithPass user : users){
      if(user.getIdentifier().equals(login)){
        return user;
      }
    }
    throw new Exception("user not found");
  }

  @Override
  public List<User> getListOfUsersNP(){
    List<User> nopassUsers = new ArrayList<User>();
    List<UserWithPass> passUsers = this.usersDAO.getUserList();

    for(UserWithPass user : passUsers){
      nopassUsers.add(user.makeUserWithoutPassword());
    }

    return nopassUsers;
  }

  @Override
  public boolean setNewPassword(String newPass, String username){
    System.err.println("set pass(" + newPass + ") to user>" + username);
    return false;
  }

  @Override
  public boolean deleteUserAccount(String username){
    System.err.println("delete user>" + username);
    return false;
  }

  @Override
  public boolean addNewUser(String username, String password, String role){
    System.err.println("add new user(" + username + ") with pass(" + password + ") in role>" + role);
    return false;
  }
}
