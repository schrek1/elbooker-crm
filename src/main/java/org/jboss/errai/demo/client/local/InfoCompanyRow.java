package org.jboss.errai.demo.client.local;

import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.Composite;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import org.jboss.errai.databinding.client.api.DataBinder;
import org.jboss.errai.demo.client.shared.companyEntity.Company;
import org.jboss.errai.ui.client.widget.HasModel;
import org.jboss.errai.ui.shared.api.annotations.AutoBound;
import org.jboss.errai.ui.shared.api.annotations.Bound;
import org.jboss.errai.ui.shared.api.annotations.DataField;
import org.jboss.errai.ui.shared.api.annotations.Templated;

@Templated("InfoCompanyRow.html#infoTr")
@ApplicationScoped
public class InfoCompanyRow extends Composite implements HasModel<Company>{

  @Inject
  @AutoBound
  private DataBinder<Company> company;

  @DataField
  @Bound
  private Element name = DOM.createSpan();

  @DataField
  @Bound
  private Element web = DOM.createSpan();

  @DataField
  @Bound(property = "phone.countryPrefix")
  private Element countryPrefix = DOM.createSpan();

  @DataField
  @Bound(property = "phone.number")
  private Element phoneNumber = DOM.createSpan();

  @DataField
  @Bound(property = "contactPerson.firstname")
  private Element firstname = DOM.createSpan();

  @DataField
  @Bound(property = "contactPerson.surename")
  private Element surename = DOM.createSpan();

  @DataField
  @Bound(property = "contactPerson.phone.countryPrefix")
  private Element CPcountryPrefix = DOM.createSpan();

  @DataField
  @Bound(property = "contactPerson.phone.number")
  private Element CPphoneNumber = DOM.createSpan();

  @DataField
  @Bound(property = "address.street")
  private Element street = DOM.createSpan();

  @DataField
  @Bound(property = "address.town")
  private Element town = DOM.createSpan();

  @DataField
  @Bound(property = "address.postalCode")
  private Element postalCode = DOM.createSpan();

  @DataField
  @Bound(property = "address.country")
  private Element country = DOM.createSpan();

  @DataField
  @Bound(property = "billingInfo.idNum")
  private Element ic = DOM.createSpan();

  @DataField
  @Bound(property = "billingInfo.vatNum")
  private Element dic = DOM.createSpan();

  @Override
  public Company getModel(){
    return company.getModel();
  }

  @Override
  public void setModel(Company model){
    company.setModel(model);
  }

}
