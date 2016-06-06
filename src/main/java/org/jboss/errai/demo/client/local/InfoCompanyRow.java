package org.jboss.errai.demo.client.local;

import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.Composite;
import javax.inject.Inject;
import org.jboss.errai.databinding.client.api.DataBinder;
import org.jboss.errai.demo.client.shared.companyEntity.Company;
import org.jboss.errai.ui.shared.api.annotations.AutoBound;
import org.jboss.errai.ui.shared.api.annotations.Bound;
import org.jboss.errai.ui.shared.api.annotations.DataField;
import org.jboss.errai.ui.shared.api.annotations.Templated;

@Templated
public class InfoCompanyRow extends Composite{

  @Inject
  @AutoBound
  DataBinder<Company> company;

  @DataField
  @Bound
  private Element name = DOM.createSpan();

  @DataField
  @Bound
  private Element web = DOM.createSpan();

  @DataField
  @Bound("contactPerson.firstname")
  private Element firstname = DOM.createSpan();

  @DataField
  @Bound("contactPerson.surename")
  private Element surename = DOM.createSpan();

  @DataField
  @Bound
  private Element Name = DOM.createSpan();

  @DataField
  @Bound
  private Element Name = DOM.createSpan();

  @DataField
  @Bound
  private Element Name = DOM.createSpan();

  @DataField
  @Bound
  private Element Name = DOM.createSpan();

  @DataField
  @Bound
  private Element Name = DOM.createSpan();

  @DataField
  @Bound
  private Element Name = DOM.createSpan();

  @DataField
  @Bound
  private Element Name = DOM.createSpan();

  private void fillInfo(){
    int companyID = this.company.getModel().getId();
    this.companyCaller.call(new RemoteCallback<Company>(){
      @Override
      public void callback(Company response){

        Document.get().getElementById("iName").setInnerText(response.getName());
        Document.get().getElementById("iWeb").setInnerText(response.getWeb());
        Document.get().getElementById("iPhone").setInnerText(response.getPhone().getCountryPrefix() + " " + response.getPhone().getNumber());

        Address adr = response.getAddress();
        Document.get().getElementById("iaStreet").setInnerText(adr.getStreet());
        Document.get().getElementById("iaTown").setInnerText(adr.getTown());
        Document.get().getElementById("iaPostalCode").setInnerText(adr.getPostalCode());
        Document.get().getElementById("iaCountry").setInnerText(adr.getCountry());

        ContactPerson cp = response.getContactPerson();
        Document.get().getElementById("icpName").setInnerText(cp.getName() + " " + cp.getSurname());
        Document.get().getElementById("icpPhone").setInnerText(cp.getPhone().getCountryPrefix() + " " + cp.getPhone().getNumber());

        BillingInfo bi = response.getBillingInfo();
        Document.get().getElementById("ibIC").setInnerText(bi.getIdNum());
        Document.get().getElementById("ibDIC").setInnerText(bi.getVatNum());

        setLoadingInTable(false);
      }
    }).getCompanyById(companyID);
  }
}
