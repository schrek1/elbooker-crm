package org.jboss.errai.demo.server;

import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import org.jboss.errai.bus.server.annotations.Service;
import org.jboss.errai.demo.client.shared.companyEntity.Company;
import org.jboss.errai.demo.client.shared.services.CompanyServices;

@ApplicationScoped
@Service
public class CompanyServicesImpl implements CompanyServices{

  @Inject
  private CompanyDAO companyDAO;

  @Override
  public List<Company> getListOfCompanies(){
    return this.companyDAO.fillCompanies();
  }

  @Override
  public Company getCompanyById(int id){
    List<Company> listOfCompany = this.companyDAO.fillCompanies();
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
