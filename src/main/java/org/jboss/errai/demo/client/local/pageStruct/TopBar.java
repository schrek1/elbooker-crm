package org.jboss.errai.demo.client.local.pageStruct;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Node;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.SimplePanel;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import org.jboss.errai.common.client.api.Caller;
import org.jboss.errai.common.client.api.RemoteCallback;
import org.jboss.errai.demo.client.local.Dashboard;
import org.jboss.errai.demo.client.local.LoginForm;
import org.jboss.errai.demo.client.shared.userEntity.User;
import org.jboss.errai.ioc.client.api.AfterInitialization;
import org.jboss.errai.security.shared.service.AuthenticationService;
import org.jboss.errai.ui.nav.client.local.PageShowing;
import org.jboss.errai.ui.nav.client.local.PageShown;
import org.jboss.errai.ui.nav.client.local.TransitionTo;
import org.jboss.errai.ui.shared.api.annotations.DataField;
import org.jboss.errai.ui.shared.api.annotations.EventHandler;
import org.jboss.errai.ui.shared.api.annotations.Templated;

@Templated("TopBar.html#topBar")
@RequestScoped
public class TopBar extends Composite{

  @Inject
  @DataField
  private Button logoutButton;

  @Inject
  private Caller<AuthenticationService> authCaller;

  @Inject
  private TransitionTo<LoginForm> anchorToLoginForm;

//  @Inject
//  @DataField
//  private HTMLPanel loginInfo;

  @DataField
  private Element usernameSpan = DOM.createSpan();

  @DataField
  private Element groupSpan = DOM.createSpan();


  @AfterInitialization
  private void afterInit(){
    this.authCaller.call(new RemoteCallback<User>() {
      @Override
      public void callback(User logedUser){
        Window.alert(logedUser.toString());
        TopBar.this.usernameSpan.setInnerText(logedUser.getIdentifier());
        TopBar.this.groupSpan.setInnerText(logedUser.getGroups().toString());

      }
    }).getUser();

  }

  @EventHandler("logoutButton")
  private void onLogoutClick(ClickEvent ce){
    this.authCaller.call().logout();
    this.anchorToLoginForm.go();
  }

//  public void setVisibleLoginInfo(boolean visible){
//    this.loginInfo.setVisible(visible);
//  }

}
