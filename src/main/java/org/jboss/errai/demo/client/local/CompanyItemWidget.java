package org.jboss.errai.demo.client.local;

import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import org.jboss.errai.common.client.api.Caller;
import org.jboss.errai.common.client.api.RemoteCallback;
import org.jboss.errai.databinding.client.api.DataBinder;
import org.jboss.errai.demo.client.shared.companyEntity.Company;
import org.jboss.errai.demo.client.shared.userEntity.Role;
import org.jboss.errai.demo.client.shared.userEntity.User;
import org.jboss.errai.demo.client.shared.userEntity.UsersRole;
import org.jboss.errai.security.shared.api.annotation.RestrictedAccess;
import org.jboss.errai.security.shared.service.AuthenticationService;
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
  private Caller<AuthenticationService> authCaller;

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
    authCaller.call(new RemoteCallback<User>(){
      @Override
      public void callback(User user){
        Role companyRole = new Role(UsersRole.COMPANY);
        if(user.getRoles().contains(companyRole)){
          removeBut.getElement().removeFromParent();
          if(!company.getModel().haveAccess(user)){
            getElement().removeFromParent();
          }
        }
      }
    }).getUser();
  }

  @EventHandler("infoBut")
  private void infoButClick(ClickEvent ce
  ){
    boolean isOpen = this.getElement().getNextSiblingElement() != null && this.getElement().getNextSiblingElement().isOrHasChild(this.infoTableRow.getElement());
    this.infoTableRow.getElement().removeFromParent();
    if(!isOpen){
      HTMLPanel panel = (HTMLPanel)this.getParent();
      panel.getElement().insertAfter(this.infoTableRow.getElement(), this.getElement());
    }
    this.infoBut.setFocus(false);
  }

  @EventHandler("editBut")
  private void editButClick(ClickEvent ce
  ){
    Element innerDiv = DOM.getElementById("page-inner");
    this.editPopUp.setVisible(true);
    innerDiv.appendChild(this.editPopUp.getElement());
    this.editBut.setFocus(false);
  }

  @EventHandler("removeBut")
  private void removeButClick(ClickEvent ce
  ){
    boolean confirm;
    confirm = Window.confirm("Opravdu chcete smazat firmu " + this.name.getInnerText() + "?");
    if(confirm){
      if(this.getElement().getNextSiblingElement() != null && this.getElement().getNextSiblingElement().isOrHasChild(this.infoTableRow.getElement())){
        this.infoTableRow.getElement().removeFromParent();
      }
      this.removeFromParent();
    }
    this.removeBut.setFocus(false);
  }

}
