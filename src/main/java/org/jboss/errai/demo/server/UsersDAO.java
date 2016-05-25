package org.jboss.errai.demo.server;

import java.util.Arrays;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Singleton;
import org.jboss.as.domain.management.security.UserNotFoundException;
import org.jboss.errai.demo.client.shared.Role;
import org.jboss.errai.demo.client.shared.User;
import org.jboss.errai.demo.client.shared.UserAccount;


/**
 * Class which make query to JPA DB of users
 * Class is singleton
 * @author ondra
 */
public class UsersDAO{

  /**
   * connect to JPA DB of users and make query
   *
   * @param userName username what we want find
   *
   * @return VerifyResponse, if user not found, method return response with
   *         queryStatus: UNKNOW_ACCOUNT, and userAccount: null
   *
   *
   */
  public VerifyResponse getUserByName(String userName){
    if(userName.equals("admin")){
      return new VerifyResponse(VerifyResponse.Status.ACCOUNT_FOUND, new UserAccount("admin", "1234"));
    }
    if(userName.equals("ondra")){
      return new VerifyResponse(VerifyResponse.Status.ACCOUNT_FOUND, new UserAccount("ondra", "4321"));
    }
    if(userName.equals("nemo")){
      return new VerifyResponse(VerifyResponse.Status.ACCOUNT_FOUND, new UserAccount("nemo", "nemo"));
    }
    return new VerifyResponse(VerifyResponse.Status.UNKNOW_ACCOUNT, null);
  }


  public User getUserByLogin(String login) throws Exception{
    if(login.equals("admin"))
    {
      return new User("admin", Arrays.asList(new Role("admin")));
    }
    throw new Exception();
  }
}
