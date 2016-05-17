package org.jboss.errai.demo.client.shared;

import org.jboss.errai.bus.server.annotations.Remote;

/**
 * Declare all function for users accounts what we want call in client code.
 *
 * @author ondra
 */
@Remote
public interface UsersAccountServices{

  /**
   * Autenthize user
   *
   * @param recieveUserAccount account which will be autenthized
   *
   * @return response status and message
   */
  public LoginResponse authentication(UserAccount recieveUserAccount);

//  /**
//   * Get user which is currently logged in seesion of CRM
//   *
//   * @return acount wich logged in this session
//   */
//  public UserAccount getUser();
}
