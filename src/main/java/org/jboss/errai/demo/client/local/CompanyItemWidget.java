package org.jboss.errai.demo.client.local;

import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import org.jboss.errai.common.client.api.Caller;
import org.jboss.errai.common.client.api.RemoteCallback;

import org.jboss.errai.databinding.client.api.DataBinder;
import org.jboss.errai.demo.client.shared.companyEntity.Company;
import org.jboss.errai.demo.client.shared.services.CompanyServices;
import org.jboss.errai.ui.client.widget.HasModel;
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
  private InfoCompanyRow infoTableRow;

  @Inject
  private EditCompanyPopup editPopUp;

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

  @Inject
  private Caller<CompanyServices> companyCaller;

  @Override
  public Company getModel(){
    return company.getModel();
  }

  @Override
  public void setModel(Company model){
    company.setModel(model);
  }

  @PostConstruct
  private void init(){
    
  }

  public void removeRemoveBut(){
    this.removeBut.getElement().removeFromParent();
  }

  @EventHandler("infoBut")
  private void infoButClick(ClickEvent ce){
    boolean isOpen = this.getElement().getNextSiblingElement() != null && this.getElement().getNextSiblingElement().isOrHasChild(this.infoTableRow.getElement());
    this.infoTableRow.getElement().removeFromParent();
    if(!isOpen){
      HTMLPanel panel = (HTMLPanel)this.getParent();
      panel.getElement().insertAfter(this.infoTableRow.getElement(), this.getElement());
      this.infoTableRow.setModel(this.getModel());
    }
    this.infoBut.setFocus(false);
  }

  @EventHandler("editBut")
  private void editButClick(ClickEvent ce){
    this.showPopup();
    this.editBut.setFocus(false);
  }

  private void showPopup(){
    this.editPopUp.setModel(this.getModel());
    this.editPopUp.setVisible(true);
  }

  @EventHandler("removeBut")
  private void removeButClick(ClickEvent ce){
    boolean confirm = Window.confirm("Opravdu chcete smazat firmu " + this.name.getInnerText() + "?");
    if(confirm){
      this.closeInfoRow();
      this.callRemoveCompany();
    }
    this.removeBut.setFocus(false);
  }

  private void closeInfoRow(){
    if(this.getElement().getNextSiblingElement() != null && this.getElement().getNextSiblingElement().isOrHasChild(this.infoTableRow.getElement())){
      this.infoTableRow.getElement().removeFromParent();
    }
  }

  private void callRemoveCompany() throws NumberFormatException{
    this.companyCaller.call(new RemoteCallback<Boolean>(){
      @Override
      public void callback(Boolean response){

      }
    }).removeCompayById(this.getModel().getId());
  }


}
