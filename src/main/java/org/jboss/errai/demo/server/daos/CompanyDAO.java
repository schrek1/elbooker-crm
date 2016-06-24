package org.jboss.errai.demo.server.daos;

import com.google.gwt.core.shared.GWT;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import org.hibernate.mapping.Collection;
import org.jboss.errai.bus.server.annotations.Service;
import org.jboss.errai.demo.client.shared.companyEntity.Address;
import org.jboss.errai.demo.client.shared.companyEntity.BillingInfo;
import org.jboss.errai.demo.client.shared.companyEntity.Company;
import org.jboss.errai.demo.client.shared.companyEntity.ContactPerson;
import org.jboss.errai.demo.client.shared.companyEntity.PhoneNumber;
import org.jboss.errai.demo.client.shared.services.CompanyServices;
import org.jboss.errai.demo.client.shared.userEntity.User;
import org.jboss.errai.demo.server.services.UserServicesImpl;

@ApplicationScoped
public class CompanyDAO{

  @Inject
  private UserServicesImpl userService;

  //TODO dodělat napojeni na DB a plnění z DB
  public List<Company> getAllCompanies(){
    List<User> userList = this.userService.getListOfUsersNP();

    //fill dummy company method
    List<Company> companies = new ArrayList<Company>();
    Company company = new Company();

    //ADD COMPANY 1
    company.setId(1);
    Address address = new Address();
    address.setCountry("CZ");
    address.setPostalCode("58601");
    address.setStreet("Brezinova 32");
    address.setTown("Jihlava");
    company.setAddress(address);

    BillingInfo billing = new BillingInfo();
    billing.setIdNum("123456789");
    billing.setVatNum("CZ46789321");
    company.setBillingInfo(billing);

    ContactPerson contactPerson = new ContactPerson();
    contactPerson.setFirstname("Jan");
    contactPerson.setSurename("Novak");
    PhoneNumber phone = new PhoneNumber();
    phone.setCountryPrefix("+420");
    phone.setNumber("987456321");
    contactPerson.setPhone(phone);
    company.setContactPerson(contactPerson);

    company.setName("elBooker s.r.o");

    phone = new PhoneNumber();
    phone.setCountryPrefix("+420");
    phone.setNumber("321598746");
    company.setPhone(phone);

    company.setWeb("www.elbooker.eu");

    company.addAccess(userList.get(1));
    company.addAccess(userList.get(2));

    companies.add(company);
    // !ADD COMPANY 1

    //--------------------------------------------------------------------------
    //ADD COMPANY 2
    company = new Company();
    company.setId(2);
    address = new Address();
    address.setCountry("CZ");
    address.setPostalCode("48796");
    address.setStreet("Na Pankraci 1");
    address.setTown("Praha");
    company.setAddress(address);

    billing = new BillingInfo();
    billing.setIdNum("123456789");
    billing.setVatNum("CZ46789321");
    company.setBillingInfo(billing);

    contactPerson = new ContactPerson();
    contactPerson.setFirstname("Petr");
    contactPerson.setSurename("Knize");
    phone = new PhoneNumber();
    phone.setCountryPrefix("+420");
    phone.setNumber("987456321");
    contactPerson.setPhone(phone);
    company.setContactPerson(contactPerson);

    company.setName("Firmicka s.r.o");

    phone = new PhoneNumber();
    phone.setCountryPrefix("+420");
    phone.setNumber("321598746");
    company.setPhone(phone);

    company.setWeb("www.firmicka.com");

    company.addAccess(userList.get(1));

    companies.add(company);
    // !ADD COMPANY 2

    //--------------------------------------------------------------------------
    //ADD COMPANY 3
    company = new Company();
    company.setId(3);
    address = new Address();
    address.setCountry("CZ");
    address.setPostalCode("58601");
    address.setStreet("Vysoka 20");
    address.setTown("Brno");
    company.setAddress(address);

    billing = new BillingInfo();
    billing.setIdNum("123456789");
    billing.setVatNum("CZ46789321");
    company.setBillingInfo(billing);

    contactPerson = new ContactPerson();
    contactPerson.setFirstname("Pavel");
    contactPerson.setSurename("Benes");
    phone = new PhoneNumber();
    phone.setCountryPrefix("+420");
    phone.setNumber("987456321");
    contactPerson.setPhone(phone);
    company.setContactPerson(contactPerson);

    company.setName("Okna a.s.");

    phone = new PhoneNumber();
    phone.setCountryPrefix("+420");
    phone.setNumber("321598746");
    company.setPhone(phone);

    company.setWeb("www.okna.com");

    company.addAccess(userList.get(2));

    companies.add(company);
    // !ADD COMPANY 3

    //--------------------------------------------------------------------------
    //ADD COMPANY 4
    company = new Company();
    company.setId(4);
    address = new Address();
    address.setCountry("CZ");
    address.setPostalCode("58601");
    address.setStreet("Lipova 32");
    address.setTown("Jihlava");
    company.setAddress(address);

    billing = new BillingInfo();
    billing.setIdNum("123456789");
    billing.setVatNum("CZ46789321");
    company.setBillingInfo(billing);

    contactPerson = new ContactPerson();
    contactPerson.setFirstname("Karel");
    contactPerson.setSurename("Simonedes");
    phone = new PhoneNumber();
    phone.setCountryPrefix("+420");
    phone.setNumber("987456321");
    contactPerson.setPhone(phone);
    company.setContactPerson(contactPerson);

    company.setName("autoopravna s.r.o");

    phone = new PhoneNumber();
    phone.setCountryPrefix("+420");
    phone.setNumber("321598746");
    company.setPhone(phone);

    company.setWeb("www.auto-opravna.cz");

    companies.add(company);
    // !ADD COMPANY 4

    return companies;
  }

  //TODO doplnit kod na odstranovani firmy z DB
  public boolean removeCompany(int id){
    return false;
  }

  //TODO dodelat update Company
  public boolean updateCompany(Company editCompany, int id){
    return false;
  }

  //TODO dodelat update Company
  public boolean addCompany(Company addCompany){
    return false;
  }
  
}
