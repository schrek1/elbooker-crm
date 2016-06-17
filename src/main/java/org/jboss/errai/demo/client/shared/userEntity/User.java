package org.jboss.errai.demo.client.shared.userEntity;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;
import org.jboss.errai.common.client.api.annotations.MapsTo;
import org.jboss.errai.common.client.api.annotations.Portable;
import org.jboss.errai.databinding.client.api.Bindable;
import org.jboss.errai.security.shared.api.Group;
import org.jboss.errai.security.shared.api.Role;
import org.jboss.errai.security.shared.api.identity.UserImpl;

@Portable
@Bindable
public class User extends UserImpl{

  public User(){
    super("");
  }

  public User(String name, Collection<? extends Role> roles){
    super(name, roles);
  }

  public User(
          @MapsTo("name") final String name,
          @MapsTo("roles") final Collection<? extends Role> roles,
          @MapsTo("groups") final Collection<? extends Group> groups,
          @MapsTo("properties") final Map<String, String> properties){
    super(name, roles, groups, properties);

  }

  @Override
  public String toString(){
    return "User{" + super.toString() + '}';
  }

}
