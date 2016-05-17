package org.jboss.errai.demo.server;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Singleton;
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
}
