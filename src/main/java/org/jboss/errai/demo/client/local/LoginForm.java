package org.jboss.errai.demo.client.local;

import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.Node;
import com.google.gwt.dom.client.NodeList;
import com.google.gwt.dom.client.Style;
import org.jboss.errai.demo.client.local.pageStruct.TopBar;
import org.jboss.errai.demo.client.local.pageStruct.NavBar;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.TextBox;
import com.google.inject.Key;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import org.jboss.errai.bus.client.api.messaging.Message;
import org.jboss.errai.common.client.api.Caller;
import org.jboss.errai.common.client.api.ErrorCallback;
import org.jboss.errai.common.client.api.RemoteCallback;
import org.jboss.errai.ui.nav.client.local.Page;
import org.jboss.errai.ui.shared.api.annotations.DataField;
import org.jboss.errai.ui.shared.api.annotations.EventHandler;
import org.jboss.errai.ui.shared.api.annotations.Templated;
import org.jboss.errai.ui.nav.client.local.PageShowing;
import org.jboss.errai.ui.nav.client.local.TransitionTo;
import org.jboss.errai.demo.client.shared.userEntity.User;
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
  private HTML usernameDiv;

  @Inject
  @DataField
  private HTML passwordDiv;

  @Inject
  @DataField
  private HTML emptyWarn;

  @Inject
  @DataField
  private HTML loginWarn;

  @Inject
  private Caller<AuthenticationService> authCaller;

  @Inject
  private TopBar topBar;

  @Inject
  private TransitionTo<Dashboard> anchorDashboard;

  @Inject
  private NavBar navBar;

  @PageShowing
  private void pageShowing(){
    this.navBar.setVisible(false);
  }

  @PageHiding
  private void pageHiding(){
    this.navBar.setVisible(true);
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
    this.makeColredEmpty();
  }

  private void makeColredEmpty(){
    int emptyFields = 0;
    emptyFields += this.setErrorToEmptyField(this.username, this.usernameDiv);
    emptyFields += this.setErrorToEmptyField(this.password, this.passwordDiv);

    if(emptyFields != 0){
      this.emptyWarn.setVisible(true);
    }else{
      this.emptyWarn.setVisible(false);
    }
  }

  //TODO refaktor -> parent z textBox
  private int setErrorToEmptyField(TextBox textBox, HTML outDiv){
    String classAtribute = outDiv.getElement().getAttribute("class");
    int empty = 0;
    if(textBox.getText().isEmpty()){
      classAtribute += (classAtribute.contains("has-error")) ? "" : " has-error";
      empty = 1;
    }else{
      classAtribute = classAtribute.replace("has-error", "");
    }
    outDiv.getElement().setAttribute("class", classAtribute);
    return empty;
  }

  private boolean isFormFilled(){
    if(this.username.getText().isEmpty() || this.password.getText().isEmpty()){
      return false;
    }else{
      return true;
    }
  }
}
