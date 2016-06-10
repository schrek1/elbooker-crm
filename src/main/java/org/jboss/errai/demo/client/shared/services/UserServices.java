package org.jboss.errai.demo.client.shared.services;

import java.util.List;
import org.jboss.errai.demo.client.shared.userEntity.User;


public interface UserServices {
  public List<User> getListOfUsersNP();
}
