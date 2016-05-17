package org.jboss.errai.demo.server;

import com.google.gwt.core.shared.GWT;
import java.io.Serializable;
import javax.ejb.Stateful;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import org.jboss.errai.bus.server.annotations.Service;
import org.jboss.errai.demo.client.shared.UserAccount;
import org.jboss.errai.demo.client.shared.LoginResponse;
import org.jboss.errai.demo.client.shared.UsersAccountServices;

/**
 * Class which make implementation of all Users Account Services.
 *
 * @author ondra
 */
@Service
public class UsersAccountServicesImpl implements UsersAccountServices, Serializable{

  private static final long serialVersionUID = 1L;

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

  public static long getSerialVersionUID(){
    return serialVersionUID;
  }

  public UsersDAO getDataSource(){
    return dataSource;
  }

  public void setDataSource(UsersDAO dataSource){
    this.dataSource = dataSource;
  }


}
