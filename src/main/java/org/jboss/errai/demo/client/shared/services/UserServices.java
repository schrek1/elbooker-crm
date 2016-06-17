package org.jboss.errai.demo.client.shared.services;

import java.util.List;
import org.jboss.errai.bus.server.annotations.Remote;
import org.jboss.errai.demo.client.shared.userEntity.User;

@Remote
public interface UserServices {
  public List<User> getListOfUsersNP();

  public boolean setNewPassword(String newPass, String username);

  public boolean deleteUserAccount(String username);

  public boolean addNewUser(String username, String password, String role);
}
