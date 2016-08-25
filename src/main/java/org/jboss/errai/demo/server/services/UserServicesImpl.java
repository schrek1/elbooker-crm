package org.jboss.errai.demo.server.services;

import org.jboss.errai.demo.server.daos.UsersDAO;
import com.google.common.collect.Iterables;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import org.jboss.errai.bus.server.annotations.Service;
import org.jboss.errai.demo.client.shared.services.UserServices;
import org.jboss.errai.demo.client.shared.userEntity.User;
import org.jboss.errai.demo.client.shared.userEntity.UserWithPass;
import org.jboss.errai.demo.client.shared.userEntity.UsersRoles;
import org.jboss.errai.security.shared.api.Role;

@ApplicationScoped
@Service
public class UserServicesImpl implements UserServices{

  @Inject
  private UsersDAO usersDAO;


  //TODO presunout do DAO
  public UserWithPass getUserWPByLogin(String login) throws Exception{
    List<UserWithPass> users = this.usersDAO.getUserList();

    for(UserWithPass user : users){
      if(user.getIdentifier().equals(login)){
        return user;
      }
    }
    throw new Exception("user not found");
  }

  //TODO presunout do DAO
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

  @Override
  public boolean changeUsername(String username, String newname){
    System.err.println("rename user(" + username + ") to >" + newname);
    return false;
  }


  //TODO presunout do DAO
  public List<User> getListOfCompanyUsers(){
    List<UserWithPass> users = this.usersDAO.getUserList();
    List<User> companies = new ArrayList<User>();
    for(UserWithPass user : users){
      Role role = Iterables.get(user.getRoles(), 0);
      if(role != null && role.getName().equals(UsersRoles.COMPANY.name())){
        companies.add(user.makeUserWithoutPassword());
      }
    }
    return companies;
  }
}
