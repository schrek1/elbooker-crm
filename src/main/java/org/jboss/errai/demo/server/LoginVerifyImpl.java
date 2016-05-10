package org.jboss.errai.demo.server;

import javax.enterprise.context.ApplicationScoped;
import org.jboss.errai.bus.server.annotations.Service;
import org.jboss.errai.demo.client.shared.UserAccount;
import org.jboss.errai.demo.client.shared.LoginResponse;
import org.jboss.errai.demo.client.shared.LoginVerifyService;

/**
 *
 * @author ondra
 */
@ApplicationScoped
@Service
public class LoginVerifyImpl implements LoginVerifyService{


  private UsersDAO dataSource = new UsersDAO();

  @Override
  public LoginResponse authentication(UserAccount recieveUserAccount){
    System.err.println(recieveUserAccount.toString());
    VerifyResponse response = this.dataSource.getUserByName(recieveUserAccount.getUsername());
    UserAccount foundAccount = response.getUserAccount();

    if(response.getQueryStatus() == VerifyResponse.Status.ACCOUNT_FOUND){
      if(recieveUserAccount.getPassword().equals(foundAccount.getPassword())){
        return new LoginResponse(LoginResponse.Response.ALL_OK, "vitejte na webu");
      }else{
        return new LoginResponse(LoginResponse.Response.PASS_BAD, "spatne heslo");
      }
    }else{
      return new LoginResponse(LoginResponse.Response.USER_BAD, "spatny uzivtelsky ucet");
    }
  }

}
