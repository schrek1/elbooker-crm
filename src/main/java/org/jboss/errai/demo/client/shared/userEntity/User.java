package org.jboss.errai.demo.client.shared.userEntity;

import java.util.Collection;
import javax.enterprise.context.ApplicationScoped;
import org.jboss.errai.common.client.api.annotations.Portable;
import org.jboss.errai.security.shared.api.Role;
import org.jboss.errai.security.shared.api.identity.UserImpl;

@ApplicationScoped
@Portable
public class User extends UserImpl{

  public User(){
    super("not_set_yet");
  }

  public User(String name, Collection<? extends Role> roles){
    super(name, roles);
  }

}
