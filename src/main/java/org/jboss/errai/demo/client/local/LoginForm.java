package org.jboss.errai.demo.client.local;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Style;
import org.jboss.errai.demo.client.local.pageStruct.NavBar;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.TextBox;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import org.jboss.errai.bus.client.api.messaging.Message;
import org.jboss.errai.common.client.api.Caller;
import org.jboss.errai.common.client.api.ErrorCallback;
import org.jboss.errai.common.client.api.RemoteCallback;
import org.jboss.errai.demo.client.local.pageStruct.TopBar;
import org.jboss.errai.ui.nav.client.local.Page;
import org.jboss.errai.ui.shared.api.annotations.DataField;
import org.jboss.errai.ui.shared.api.annotations.EventHandler;
import org.jboss.errai.ui.shared.api.annotations.Templated;
import org.jboss.errai.ui.nav.client.local.PageShowing;
import org.jboss.errai.ui.nav.client.local.TransitionTo;
import org.jboss.errai.demo.client.shared.userEntity.User;
import org.jboss.errai.ioc.client.api.AfterInitialization;
import org.jboss.errai.security.shared.exception.AuthenticationException;
import org.jboss.errai.security.shared.service.AuthenticationService;
import org.jboss.errai.ui.nav.client.local.PageHiding;
import org.jboss.errai.ui.nav.client.local.api.LoginPage;

/**
 *
 * @author ondra
 */
@Page(role = LoginPage.class)
@Templated("LoginForm.html#container")
public class LoginForm extends Composite{

  @Inject
  @DataField
  private TextBox password;

  @Inject
  @DataField
  private TextBox username;

  @Inject
  @DataField
  private Button login;

  @Inject
  @DataField
  private HTML emptyWarn;

  @Inject
  @DataField
  private HTML loginWarn;

  @Inject
  private Caller<AuthenticationService> authCaller;

  @Inject
  private TransitionTo<Dashboard> anchorDashboard;

  @Inject
  private NavBar navBar;

  @Inject
  private TopBar topBar;

  @PostConstruct
  private void init(){
    this.navBar.setVisible(false);
    this.topBar.setVisible(false);
  }

  @PageShowing
  private void pageShowing(){
    this.authCaller.call(new RemoteCallback<Boolean>(){
      public void callback(Boolean isLogged){
        if(isLogged){
          LoginForm.this.anchorDashboard.go();
        }
      }
    }).isLoggedIn();
  }

  @PageHiding
  private void pageHiding(){
    this.navBar.setVisible(true);
    this.topBar.setVisibleLoginInfo(true);
  }

  @EventHandler("username")
  private void onLoginBoxHitEnter(KeyPressEvent kpe){
    if(kpe.getCharCode() == KeyCodes.KEY_ENTER){
      this.loginClickHandler(null);
    }
  }

  @EventHandler("password")
  private void onPassBoxHitEnter(KeyPressEvent kpe){
    if(kpe.getCharCode() == KeyCodes.KEY_ENTER){
      this.loginClickHandler(null);
    }
  }

  @EventHandler("login")
  private void loginClickHandler(ClickEvent ce){
    this.login.setFocus(false);

    if(this.isFormFilled()){
      this.callLogin();
    }
    this.updateFormStyle();
  }

  private void callLogin(){

    this.authCaller.call(new RemoteCallback<User>(){
      @Override
      public void callback(User response){
        loginWarn.setVisible(false);
        anchorDashboard.go();
      }
    }, new ErrorCallback<Message>(){
      @Override
      public boolean error(Message message, Throwable t){
        if(t instanceof AuthenticationException){
          loginWarn.setVisible(true);
        }
        return true;
      }
    }).login(this.username.getText(), this.password.getText());
  }

  private void updateFormStyle(){
    int emptyFields = 0;

    emptyFields += this.setColorToEmptyField(this.username);
    emptyFields += this.setColorToEmptyField(this.password);

    if(emptyFields > 0){
      this.emptyWarn.setVisible(true);
      this.loginWarn.setVisible(false);
    }else{
      this.emptyWarn.setVisible(false);
    }
  }

  private int setColorToEmptyField(TextBox box){
    Element parent = box.getElement().getParentElement();
    if(box.getText().isEmpty()){
      parent.addClassName("has-error");
      return 1;
    }else{
      parent.removeClassName("has-error");
      return 0;
    }
  }

  private boolean isFormFilled(){
    if(this.username.getText().isEmpty() || this.password.getText().isEmpty()){
      return false;
    }else{
      return true;
    }
  }
}
