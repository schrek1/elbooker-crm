package org.jboss.errai.demo.client.local;

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Style;
import com.google.gwt.dom.client.Style.Display;
import com.google.gwt.event.dom.client.ClickEvent;
import static com.google.gwt.thirdparty.streamhtmlparser.HtmlParser.Mode.HTML;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import org.jboss.errai.common.client.api.Caller;
import org.jboss.errai.common.client.api.RemoteCallback;
import org.jboss.errai.demo.client.local.pageStruct.NavBar;
import org.jboss.errai.demo.client.local.pageStruct.PagesEnum;
import org.jboss.errai.demo.client.shared.companyEntity.Company;
import org.jboss.errai.ui.client.widget.ListWidget;
import org.jboss.errai.ui.client.widget.Table;
import org.jboss.errai.ui.nav.client.local.DefaultPage;
import org.jboss.errai.ui.nav.client.local.Page;
import org.jboss.errai.ui.nav.client.local.PageShown;
import org.jboss.errai.ui.shared.api.annotations.DataField;
import org.jboss.errai.ui.shared.api.annotations.Templated;
import org.jboss.errai.demo.client.shared.services.CompanyServices;
import org.jboss.errai.demo.client.shared.userEntity.Role;
import org.jboss.errai.demo.client.shared.userEntity.User;
import org.jboss.errai.demo.client.shared.userEntity.UsersRole;
import org.jboss.errai.security.shared.api.annotation.RestrictedAccess;
import org.jboss.errai.security.shared.service.AuthenticationService;
import org.jboss.errai.ui.nav.client.local.PageHiding;
import org.jboss.errai.ui.nav.client.local.PageShowing;
import org.jboss.errai.ui.shared.api.annotations.EventHandler;

/**
 *
 * @author ondra
 */
@Page(role = DefaultPage.class)
@Templated
@RestrictedAccess
public class Dashboard extends Composite{

  @Inject
  private Caller<CompanyServices> companyCaller;

  @DataField
  @Inject
  private EditCompanyPopup editPopUp;

  @Inject
  private Caller<AuthenticationService> authCaller;

  @Inject
  @DataField
  @Table(root = "tbody")
  private ListWidget<Company, CompanyItemWidget> companyTable;

  @PageShowing
  private void sync(){
    companyCaller.call(new RemoteCallback<List<Company>>(){
      @Override
      public void callback(List<Company> response){
        companyTable.setItems(response);
        removeNotAccessible();
      }
    }).listOfCompany();
    this.editPopUp.setVisible(false);
  }

  @PageHiding
  private void hide(){
    this.editPopUp.removeFromParent();
  }

  public void removeNotAccessible(){
    authCaller.call(new RemoteCallback<User>(){
      @Override
      public void callback(User user){
        Role companyRole = new Role(UsersRole.COMPANY);
        if(user.getRoles().contains(companyRole)){
          int i = companyTable.getWidgetCount();
          while(i-- > 0){
            CompanyItemWidget item = companyTable.getWidget(i);
            item.removeRemoveBut();
            if(!item.getModel().haveAccess(user)){
              item.removeFromParent();
            }
          }
        }
      }
    }).getUser();
  }

}
