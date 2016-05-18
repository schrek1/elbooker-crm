package org.jboss.errai.demo.client.local;

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.inject.Inject;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;
import org.jboss.errai.common.client.api.Caller;
import org.jboss.errai.common.client.api.RemoteCallback;
import org.jboss.errai.demo.client.shared.Company;
import org.jboss.errai.ui.client.widget.ListWidget;
import org.jboss.errai.ui.client.widget.Table;
import org.jboss.errai.ui.nav.client.local.DefaultPage;
import org.jboss.errai.ui.nav.client.local.Page;
import org.jboss.errai.ui.nav.client.local.PageShown;
import org.jboss.errai.ui.shared.api.annotations.DataField;
import org.jboss.errai.ui.shared.api.annotations.Templated;
import org.jboss.errai.demo.client.shared.CompanyServices;

/**
 *
 * @author ondra
 */
@Page(role = DefaultPage.class)
@Templated
@Dependent
public class Dashboard extends Composite{

  @Inject
  private Caller<CompanyServices> companyCaller;

  @Inject
  private TopBar topBar;

  @Inject
  private NavBar navBar;

  @DataField
  private Element content = DOM.createDiv();

  @Inject
  @DataField
  @Table(root = "tbody")
  private ListWidget<Company, CompanyItemWidget> companyTable;

  @PostConstruct
  private void postConstruct(){
    this.setVisible(false);
    Document.get().getElementById("wrapper").appendChild(this.topBar.getTopBar());
    Document.get().getElementById("wrapper").appendChild(this.navBar.getNavBar());
    Document.get().getElementById("wrapper").appendChild(this.content);
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

}
