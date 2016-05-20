package org.jboss.errai.demo.server;

import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import org.jboss.errai.bus.server.annotations.Service;
import org.jboss.errai.demo.client.shared.Company;
import org.jboss.errai.demo.client.shared.CompanyServices;

@ApplicationScoped
@Service
public class CompanyServicesImpl implements CompanyServices{

  private final CompanyDAO companyDAO = new CompanyDAO();

  @Override
  public List<Company> listOfCompany(){
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

}
