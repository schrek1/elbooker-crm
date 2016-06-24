package org.jboss.errai.demo.server.services;

import java.util.Collections;
import org.jboss.errai.demo.server.daos.CompanyDAO;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import org.hibernate.mapping.Collection;
import org.jboss.errai.bus.server.annotations.Service;
import org.jboss.errai.demo.client.shared.companyEntity.Company;
import org.jboss.errai.demo.client.shared.services.CompanyServices;
import org.jboss.errai.demo.client.shared.userEntity.User;

@ApplicationScoped
@Service
public class CompanyServicesImpl implements CompanyServices{

  @Inject
  private CompanyDAO companyDAO;

  @Override
  public List<Company> getListOfCompanies(){
    return this.companyDAO.getAllCompanies();
  }

  //TODO dodelat vraceni listu pro konkretniho uzivatele
  public List<Company> getAccessibleCompanies(User user){
    return Collections.EMPTY_LIST;
  }

  //TODO presunout implementaci do DAO
  @Override
  public Company getCompanyById(int id){
    List<Company> listOfCompany = this.companyDAO.getAllCompanies();
    for(Company company : listOfCompany){
      if(company.getId() == id){
        return company;
      }
    }
    return null;
  }

  @Override
  public boolean removeCompayById(int id){
    System.err.println("!remove>" + id);
    return true;
  }

  @Override
  public boolean editCompany(Company editCompany, int id){
    System.err.println("!update>" + id + " " + editCompany.toString());
    return false;
  }

}
