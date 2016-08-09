package org.jboss.errai.demo.client.shared.userEntity;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import org.jboss.errai.common.client.api.annotations.MapsTo;
import org.jboss.errai.common.client.api.annotations.Portable;
import org.jboss.errai.databinding.client.api.Bindable;
import org.jboss.errai.security.shared.api.Group;
import org.jboss.errai.security.shared.api.Role;
import org.jboss.errai.security.shared.api.identity.UserImpl;

@Portable
@Bindable
@Entity
public class User extends UserImpl{

  @Id
  @GeneratedValue
  private int idUser;

  private String email;

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
          @MapsTo("properties") final Map<String, String> properties,
          @MapsTo("email")final String email){
    super(name, roles, groups, properties);
    this.email = email;
  }

  public void setEmail(String email){
    this.email = email;
  }

  public String getEmail(){
    return email;
  }

  @Override
  public String toString(){
    return "User{" + super.toString() + '}';
  }

}
