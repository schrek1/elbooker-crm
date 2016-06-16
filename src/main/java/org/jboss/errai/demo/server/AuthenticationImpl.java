package org.jboss.errai.demo.server;

import org.jboss.errai.demo.client.shared.userEntity.UserWithPass;
import com.google.gwt.core.shared.GWT;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import org.jboss.errai.bus.server.annotations.Service;
import org.jboss.errai.security.shared.api.identity.User;
import org.jboss.errai.security.shared.exception.AuthenticationException;
import org.jboss.errai.security.shared.service.AuthenticationService;
import org.jboss.weld.servlet.SessionHolder;
import org.slf4j.Logger;

@Service
public class AuthenticationImpl implements AuthenticationService{

  @Inject
  private UsersDAO userDAO;

  @Override
  public User login(String username, String password){
    UserWithPass recieveUWP;

    try{//null pointer
      recieveUWP = this.userDAO.getUserWPByLogin(username);
    }catch(Exception ex){
      throw new AuthenticationException();
    }

    if(recieveUWP.getPasswordHash().equals(password)){
      final User plainUser = recieveUWP.makeUserWithoutPassword();
      SessionHolder.getSessionIfExists().setAttribute("logedUser", plainUser);
      return plainUser;
    }else{
      throw new AuthenticationException();
    }
  }

  @Override
  public boolean isLoggedIn(){
    return SessionHolder.getSessionIfExists().getAttribute("logedUser") != User.ANONYMOUS;
  }

  @Override
  public void logout(){
    SessionHolder.getSessionIfExists().setAttribute("logedUser", User.ANONYMOUS);
  }

  @Override
  public User getUser(){
    HttpSession session = SessionHolder.getSessionIfExists();
    if(session != null){
      User usr = (User)session.getAttribute("logedUser");
      return (usr != null) ? usr : User.ANONYMOUS;
    }
    return User.ANONYMOUS;
  }

}
