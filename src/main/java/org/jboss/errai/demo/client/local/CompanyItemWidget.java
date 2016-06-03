package org.jboss.errai.demo.client.local;

import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Style;
import com.google.gwt.dom.client.Style.Display;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.MouseEvent;
import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.i18n.client.LocaleInfo;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import org.jboss.errai.common.client.api.Caller;
import org.jboss.errai.common.client.api.RemoteCallback;
import org.jboss.errai.databinding.client.api.DataBinder;
import org.jboss.errai.databinding.client.api.PropertyChangeEvent;
import org.jboss.errai.databinding.client.api.PropertyChangeHandler;
import org.jboss.errai.demo.client.shared.companyEntity.Company;
import org.jboss.errai.demo.client.shared.services.CompanyServices;
import org.jboss.errai.demo.client.shared.companyEntity.ContactPerson;
import org.jboss.errai.ui.client.widget.HasModel;
import org.jboss.errai.ui.client.widget.ValueImage;
import org.jboss.errai.ui.shared.api.annotations.AutoBound;
import org.jboss.errai.ui.shared.api.annotations.Bound;
import org.jboss.errai.ui.shared.api.annotations.DataField;
import org.jboss.errai.ui.shared.api.annotations.EventHandler;
import org.jboss.errai.ui.shared.api.annotations.Templated;

@Templated("Dashboard.html#company")
public class CompanyItemWidget extends Composite implements HasModel<Company>{

  // nabinduje poskytnutou instanci modelu vsem polozkam anotovanym bound
  @Inject
  @AutoBound
  private DataBinder<Company> company;

  @Inject
  private Caller<CompanyServices> companyCaller;

  // You can also choose to instantiate your own widgets. Injection is not
  // required. In case of Element, direct injection is not supported.
  @Bound
  @DataField
  private final Element id = DOM.createTD();

  @Bound
  @DataField
  private final Element name = DOM.createTD();

  @Bound
  @DataField
  private final Element web = DOM.createTD();

  @DataField
  private final Button infoBut = new Button();

  @DataField
  private final Button editBut = new Button();

  @DataField
  private final Button removeBut = new Button();

  private boolean isOpen = false;

  private Element editPopUp = Document.get().getElementById("editPopUp");
  private Element infoTr = Document.get().getElementById("infoTr");

  @Override
  public Company getModel(){
    return company.getModel();
  }

  @Override
  public void setModel(Company model){
    company.setModel(model);
  }

  @EventHandler("infoBut")
  private void infoButClick(ClickEvent ce){

    this.infoTr.getStyle().setDisplay(Display.NONE);
    Element parentTR = this.infoBut.getParent().getParent().getElement();
    Element nextTr = parentTR.getNextSiblingElement();

    if(nextTr == null || !nextTr.isOrHasChild(this.infoTr)){
      Document.get().getElementById("companyTable").insertAfter(this.infoTr, parentTR);
      this.fillInfo();
    }else{
      this.infoTr.removeFromParent();
    }
    
    this.infoBut.setFocus(false);
  }

  @EventHandler("editBut")
  private void editButClick(ClickEvent ce){
    this.editPopUp.getStyle().setDisplay(Display.INLINE);
    this.editBut.setFocus(false);
  }

  @EventHandler("removeBut")
  private void removeButClick(ClickEvent ce){
    boolean confirm;

    confirm = Window.confirm("Opravdu chcete smazat firmu "+this.name.getInnerText()+"?");
    if(confirm){
      this.getElement().removeFromParent();
      this.infoTr.getStyle().setDisplay(Display.NONE);
    }
    this.removeBut.setFocus(false);
  }

  private void fillInfo(){
    int companyID = this.company.getModel().getId();
    this.companyCaller.call(new RemoteCallback<Company>(){
      @Override
      public void callback(Company response){
        Document.get().getElementById("iName").setInnerText(response.getName());
        Document.get().getElementById("iWeb").setInnerText(response.getWeb());
        Document.get().getElementById("iContactPerson").setInnerText(response.getContactPerson().toString());
        Document.get().getElementById("iPhone").setInnerText(response.getPhone().toString());
        Document.get().getElementById("iAddress").setInnerText(response.getAddress().toString());
        Document.get().getElementById("iBillingInfo").setInnerText(response.getBillingInfo().toString());
        infoTr.getStyle().setDisplay(Display.TABLE_ROW);
      }
    }).getCompanyById(companyID);
  }

}
