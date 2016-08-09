package org.jboss.errai.demo.server.daos;

import com.google.gwt.core.shared.GWT;
import org.jboss.errai.demo.client.shared.userEntity.UserWithPass;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.jboss.errai.demo.client.shared.userEntity.Role;
import org.jboss.errai.demo.client.shared.userEntity.UsersRoles;
import org.slf4j.Logger;

/**
 * Class which make query to JPA DB of users Class is singleton
 *
 * @author ondra
 */
@ApplicationScoped
public class UsersDAO{

  @Inject
  Logger logger;

  private List<UserWithPass> dataSource = new ArrayList<UserWithPass>();

  @PersistenceContext(unitName = "jpa-example")
  private EntityManager entitymanager;

  public List<UserWithPass> getUserList(){
    if(this.dataSource.isEmpty()){
      this.dataSource.add(new UserWithPass("admin", Arrays.asList(new Role(UsersRoles.ADMIN)), "1"));
      this.dataSource.add(new UserWithPass("firma", Arrays.asList(new Role(UsersRoles.COMPANY)), "2"));
      this.dataSource.add(new UserWithPass("company", Arrays.asList(new Role(UsersRoles.COMPANY)), "3"));
      this.dataSource.add(new UserWithPass("sekretarka", Arrays.asList(new Role(UsersRoles.ADMIN)), "4"));
    }


//    EntityManagerFactory factory = Persistence.createEntityManagerFactory("jpa-example");
//    EntityManager manager = factory.createEntityManager();

    this.logger.error(this.entitymanager.toString() + "");


//    GWT.log(">>>>>>>>>>>>>>>>>>");
    UserWithPass uwp = new UserWithPass("admin", Arrays.asList(new Role(UsersRoles.ADMIN)), "1");
    entitymanager.persist(uwp);
    entitymanager.flush();
//    Query query = entitymanager.createQuery("SELECT u FROM UserWithPass u");
//    uwp = null;
//    try{
//      uwp = (UserWithPass)query.getSingleResult();
//    }catch(NoResultException ex){
//      ex.printStackTrace();
//    }
//    System.err.println(uwp);
    return this.dataSource;
  }

}
