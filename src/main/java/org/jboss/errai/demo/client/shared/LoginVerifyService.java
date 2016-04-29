package org.jboss.errai.demo.client.shared;

import org.jboss.errai.bus.server.annotations.Remote;

/**
 *
 * @author ondra
 */

@Remote
public interface LoginVerifyService{
  public LoginResponse authentication(UserAccount recieveUserAccount);
}
