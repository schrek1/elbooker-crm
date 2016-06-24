package org.jboss.errai.demo.server.daos;

import com.google.gwt.thirdparty.common.css.compiler.passes.CollectConstantDefinitions;
import org.jboss.errai.demo.client.shared.userEntity.UserWithPass;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import javassist.NotFoundException;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import org.jboss.errai.bus.server.annotations.Service;
import org.jboss.errai.demo.client.shared.services.UserServices;
import org.jboss.errai.demo.client.shared.userEntity.Role;
import org.jboss.errai.demo.client.shared.userEntity.User;
import org.jboss.errai.demo.client.shared.userEntity.UsersRoles;
import org.slf4j.Logger;

/**
 * Class which make query to JPA DB of users Class is singleton
 *
 * @author ondra
 */
@ApplicationScoped
public class UsersDAO{

  private List<UserWithPass> dataSource = new ArrayList<UserWithPass>();

  public List<UserWithPass> getUserList(){
    if(this.dataSource.isEmpty()){
      this.dataSource.add(new UserWithPass("admin", Arrays.asList(new Role(UsersRoles.ADMIN)), "1"));
      this.dataSource.add(new UserWithPass("firma", Arrays.asList(new Role(UsersRoles.COMPANY)), "2"));
      this.dataSource.add(new UserWithPass("company", Arrays.asList(new Role(UsersRoles.COMPANY)), "3"));
      this.dataSource.add(new UserWithPass("sekretarka", Arrays.asList(new Role(UsersRoles.ADMIN)), "4"));
    }
    return this.dataSource;
  }

}
