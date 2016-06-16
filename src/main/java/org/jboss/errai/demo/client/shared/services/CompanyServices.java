package org.jboss.errai.demo.client.shared.services;

import org.jboss.errai.demo.client.shared.companyEntity.Company;
import java.util.List;
import org.jboss.errai.bus.server.annotations.Remote;

/**
 *
 * @author ondra
 */

@Remote
public interface CompanyServices{

  public List<Company> getListOfCompanies();

  public Company getCompanyById(int id);

  public boolean removeCompayById(int id);

  public boolean editCompany(Company editCompany, int id);
}
