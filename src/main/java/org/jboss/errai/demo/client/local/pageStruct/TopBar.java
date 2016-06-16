package org.jboss.errai.demo.client.local.pageStruct;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Style;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.Button;
import java.util.Iterator;
import javax.enterprise.context.Dependent;
import javax.enterprise.event.Observes;
import javax.faces.bean.ApplicationScoped;
import javax.inject.Inject;
import org.jboss.errai.common.client.api.Caller;
import org.jboss.errai.demo.client.local.LoginForm;
import org.jboss.errai.security.shared.api.identity.User;
import org.jboss.errai.security.shared.event.LoggedInEvent;
import org.jboss.errai.security.shared.event.LoggedOutEvent;
import org.jboss.errai.security.shared.service.AuthenticationService;
import org.jboss.errai.ui.nav.client.local.TransitionTo;
import org.jboss.errai.ui.shared.api.annotations.DataField;
import org.jboss.errai.ui.shared.api.annotations.EventHandler;
import org.jboss.errai.ui.shared.api.annotations.Templated;

@Templated("TopBar.html#topBar")
@ApplicationScoped
public class TopBar extends Composite{

  @Inject
  @DataField
  private Button logoutButton;

  @Inject
  private Caller<AuthenticationService> authCaller;

  @Inject
  private TransitionTo<LoginForm> anchorToLoginForm;

  @DataField
  private Element loginInfo = DOM.createDiv();

  @DataField
  private Element usernameSpan = DOM.createSpan();

  @DataField
  private Element rolesSpan = DOM.createSpan();

  private void onLoggedIn(@Observes LoggedInEvent le){
    this.refreshLoginInfo(le.getUser());
    this.setVisibleLoginInfo(true);
  }

  private void onLoggedOut(@Observes LoggedOutEvent le){
    this.refreshLoginInfo(User.ANONYMOUS);
    this.setVisibleLoginInfo(false);
  }

  private void refreshLoginInfo(User user){
    TopBar.this.usernameSpan.setInnerText(user.getIdentifier());
    Iterator iter = user.getRoles().iterator();
    TopBar.this.rolesSpan.setInnerText(iter.next().toString());
  }

  @EventHandler("logoutButton")
  private void onLogoutClick(ClickEvent ce){
    this.authCaller.call().logout();
    this.anchorToLoginForm.go();
  }

  public void setVisibleLoginInfo(boolean visible){
    if(visible){
      this.loginInfo.getStyle().setDisplay(Style.Display.INLINE);
    }else{
      this.loginInfo.getStyle().setDisplay(Style.Display.NONE);
    }

  }

}
