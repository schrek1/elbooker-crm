package org.jboss.errai.demo.client.local;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.TextBox;
import com.google.inject.Inject;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Dependent;
import javax.faces.bean.ViewScoped;
import org.jboss.errai.databinding.client.api.DataBinder;
import org.jboss.errai.demo.client.shared.companyEntity.Company;
import org.jboss.errai.ui.client.widget.HasModel;
import org.jboss.errai.ui.shared.api.annotations.AutoBound;
import org.jboss.errai.ui.shared.api.annotations.Bound;
import org.jboss.errai.ui.shared.api.annotations.DataField;
import org.jboss.errai.ui.shared.api.annotations.EventHandler;
import org.jboss.errai.ui.shared.api.annotations.Templated;

@Templated("#editPopUp")
@ApplicationScoped
public class EditCompanyPopup extends Composite implements HasModel<Company>{

  @Inject
  @AutoBound
  private DataBinder<Company> company;

  @Inject
  @DataField
  @Bound(property = "name")
  private TextBox inputCName;

  @Inject
  @DataField
  @Bound(property = "web")
  private TextBox inputCWeb;

  @Inject
  @DataField
  @Bound(property = "phone.countryPrefix")
  private TextBox inputCPhonePrefix;

  @Inject
  @DataField
  @Bound(property = "phone.number")
  private TextBox inputCPhoneNumber;

  @Inject
  @DataField
  @Bound(property = "contactPerson.firstname")
  private TextBox inputCPName;

  @Inject
  @DataField
  @Bound(property = "contactPerson.surename")
  private TextBox inputCPSurename;

  @Inject
  @DataField
  @Bound(property = "contactPerson.phone.countryPrefix")
  private TextBox inputCPPhonePrefix;

  @Inject
  @DataField
  @Bound(property = "contactPerson.phone.number")
  private TextBox inputCPPhoneNumber;

  @Inject
  @DataField
  @Bound(property = "address.street")
  private TextBox inputCStreet;

  @Inject
  @DataField
  @Bound(property = "address.town")
  private TextBox inputCTown;

  @Inject
  @DataField
  @Bound(property = "address.postalCode")
  private TextBox inputCPostalCode;

  @Inject
  @DataField
  @Bound(property = "address.country")
  private TextBox inputCCountry;

  @Inject
  @DataField
  @Bound(property = "billingInfo.idNum")
  private TextBox inputIC;

  @Inject
  @DataField
  @Bound(property = "billingInfo.vatNum")
  private TextBox inputDIC;

  @Inject
  @DataField
  private Button editPopCloseBut;

  @Inject
  @DataField
  private Button editPopXBut;

  @EventHandler("editPopXBut")
  private void editXButClick(ClickEvent ce){
    this.closeModal();
  }

  @EventHandler("editPopCloseBut")
  private void editCloseButClick(ClickEvent ce){
    this.closeModal();
  }

  private void closeModal(){
    GWT.log("log:handler");
    this.setVisible(false);
  }

  @Override
  public void setVisible(boolean visible){
    GWT.log("log:setVisible");
    if(visible){
      this.getElement().getStyle().setDisplay(Style.Display.BLOCK);
    }else{
      this.getElement().getStyle().setDisplay(Style.Display.NONE);
    }
  }

  @Override
  public Company getModel(){
    return company.getModel();
  }

  @Override
  public void setModel(Company model){
    company.setModel(model);
  }
  
}
