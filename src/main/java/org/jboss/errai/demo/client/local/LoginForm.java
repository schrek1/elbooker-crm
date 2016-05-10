package org.jboss.errai.demo.client.local;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.TextBox;
import javax.inject.Inject;
import org.jboss.errai.common.client.api.Caller;
import org.jboss.errai.common.client.api.RemoteCallback;
import org.jboss.errai.demo.client.shared.UserAccount;
import org.jboss.errai.demo.client.shared.LoginResponse;
import org.jboss.errai.ioc.client.api.EntryPoint;
import org.jboss.errai.ui.nav.client.local.DefaultPage;
import org.jboss.errai.ui.nav.client.local.Page;
import org.jboss.errai.ui.shared.api.annotations.Bound;
import org.jboss.errai.ui.shared.api.annotations.DataField;
import org.jboss.errai.ui.shared.api.annotations.EventHandler;
import org.jboss.errai.ui.shared.api.annotations.Model;
import org.jboss.errai.ui.shared.api.annotations.Templated;
import org.jboss.errai.demo.client.shared.LoginVerifyService;
import org.jboss.errai.ui.nav.client.local.TransitionAnchor;
import org.jboss.errai.ui.nav.client.local.TransitionTo;

/**
 *
 * @author ondra
 */

@Page(role = DefaultPage.class)
@Templated("LoginForm.html#container")
public class LoginForm extends Composite{

  @Inject
  @Model
  private UserAccount loginRequest;

  @Inject
  @Bound
  @DataField
  private TextBox password;

  @Inject
  @Bound
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
  private Caller<LoginVerifyService> loginVerify;

  @Inject
  private TransitionTo<Dashboard> anchorDashboard;

  @EventHandler("login")
  private void onLoginClick(ClickEvent ce){
    GWT.log("Event vyvolan");
    this.login.setFocus(false);
    
    this.addStyleName("class");

    if(this.isFormFilled()){
      GWT.log("volani login verify");
      this.loginVerify.call(new RemoteCallback<LoginResponse>(){
        @Override
        public void callback(LoginResponse response){
          //Window.alert(response.toString());
          if(response.toString().contains("ALL_OK")){
            loginWarn.setVisible(false);
            anchorDashboard.go();
          }else{
            loginWarn.setVisible(true);
          }
        }
      }).authentication(loginRequest);
    }else{
      GWT.log("nevyplneny vsechny udaje");
    }
    this.makeColredEmpty();
  }

  private void makeColredEmpty(){
    int emptyFields = 0;
    emptyFields += this.setErrorToEmpty(this.username, this.usernameDiv);
    emptyFields += this.setErrorToEmpty(this.password, this.passwordDiv);

    if(emptyFields != 0){
      this.emptyWarn.setVisible(true);
    }else{
      this.emptyWarn.setVisible(false);
    }
  }

  private int setErrorToEmpty(TextBox textBox, HTML outDiv){
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
