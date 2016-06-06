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
import com.google.inject.Inject;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;
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
import org.jboss.errai.security.shared.api.annotation.RestrictedAccess;
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

  @Inject
  @DataField
  @Table(root = "tbody")
  private ListWidget<Company, CompanyItemWidget> companyTable;

  @DataField
  private Element editPopUp = DOM.createDiv();

  @DataField
  private Element infoTr = DOM.createTR();

  @Inject
  @DataField
  private Button editPopCloseBut;

  @Inject
  @DataField
  private Button editPopXBut;

  @PostConstruct
  private void postConstruct(){
    this.hideWindowDiv();
  }

  @PageShown
  private void sync(){
    companyCaller.call(new RemoteCallback<List<Company>>(){
      @Override
      public void callback(List<Company> response){
        companyTable.setItems(response);
      }
    }).listOfCompany();

  }

  @EventHandler("editPopXBut")
  private void editXButClick(ClickEvent ce){
    this.editPopUp.getStyle().setDisplay(Display.NONE);
  }

  @EventHandler("editPopCloseBut")
  private void editCloseButClick(ClickEvent ce){
    this.editPopUp.getStyle().setDisplay(Display.NONE);
  }

  private void hideWindowDiv(){
    this.editPopUp.getStyle().setDisplay(Display.NONE);
    this.infoTr.getStyle().setDisplay(Display.NONE);
  }

}
