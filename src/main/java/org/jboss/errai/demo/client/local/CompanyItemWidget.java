package org.jboss.errai.demo.client.local;

import com.google.gwt.core.shared.GWT;
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
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import org.jboss.errai.common.client.api.Caller;
import org.jboss.errai.common.client.api.RemoteCallback;
import org.jboss.errai.databinding.client.api.DataBinder;
import org.jboss.errai.databinding.client.api.PropertyChangeEvent;
import org.jboss.errai.databinding.client.api.PropertyChangeHandler;
import org.jboss.errai.demo.client.shared.companyEntity.Address;
import org.jboss.errai.demo.client.shared.companyEntity.BillingInfo;
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

  private final Element loadingContainer = Document.get().getElementById("loadingContainer");

  private Element editPopUp = Document.get().getElementById("editPopUp");

  private Element infoTr = Document.get().getElementById("infoTr");

  private Element infoDiv = Document.get().getElementById("infoDiv");


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
    boolean isOpen = this.getElement().getNextSiblingElement() != null && this.getElement().getNextSiblingElement().isOrHasChild(this.infoTr);
    this.infoTr.removeFromParent();
    if(!isOpen){
      this.setLoading(true);
      HTMLPanel panel = (HTMLPanel)this.getParent();
      panel.getElement().insertAfter(this.infoTr, this.getElement());
      this.fillInfo();
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
    confirm = Window.confirm("Opravdu chcete smazat firmu " + this.name.getInnerText() + "?");
    if(confirm){
      if(this.getElement().getNextSiblingElement() != null && this.getElement().getNextSiblingElement().isOrHasChild(this.infoTr)){
        this.infoTr.removeFromParent();
      }
      this.removeFromParent();
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

        setLoading(false);
      }
    }).getCompanyById(companyID);
  }

  private void setLoading(boolean loading){
    if(loading){
      this.loadingContainer.getStyle().setDisplay(Display.BLOCK);
      this.infoDiv.getStyle().setDisplay(Display.NONE);
    }else{
      this.loadingContainer.getStyle().setDisplay(Display.NONE);
      this.infoDiv.getStyle().setDisplay(Display.BLOCK);
      this.infoTr.getStyle().setDisplay(Display.TABLE_ROW);
    }
  }

}
