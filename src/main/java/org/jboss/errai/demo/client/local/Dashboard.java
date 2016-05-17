package org.jboss.errai.demo.client.local;

import com.google.common.collect.Lists;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.InlineHTML;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.inject.Inject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.management.ObjectName;
import javax.persistence.PostLoad;
import org.jboss.errai.common.client.api.Caller;
import org.jboss.errai.common.client.api.RemoteCallback;
import org.jboss.errai.demo.client.shared.Company;
import org.jboss.errai.demo.client.shared.UserAccount.Role;
import org.jboss.errai.ui.client.widget.ListWidget;
import org.jboss.errai.ui.client.widget.Table;
import org.jboss.errai.ui.nav.client.local.DefaultPage;
import org.jboss.errai.ui.nav.client.local.Page;
import org.jboss.errai.ui.nav.client.local.PageShown;
import org.jboss.errai.ui.shared.api.annotations.DataField;
import org.jboss.errai.ui.shared.api.annotations.EventHandler;
import org.jboss.errai.ui.shared.api.annotations.Templated;
import org.jboss.errai.demo.client.shared.UsersAccountServices;
import org.jboss.errai.demo.client.shared.CompanyServices;
import org.jboss.errai.demo.client.shared.UserAccount;

/**
 *
 * @author ondra
 */
@Page(role=DefaultPage.class)
@Templated("DashBoard.html#page-inner")
public class Dashboard extends Composite{

  @Inject
  private Caller<CompanyServices> companyCaller;

  @Inject
  @DataField
  @Table(root = "tbody")
  private ListWidget<Company, CompanyItemWidget> companyTable;


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
