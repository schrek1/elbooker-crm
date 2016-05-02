package org.jboss.errai.demo.client.local;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
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
  private Caller<LoginVerifyService> loginVerify;

  @EventHandler("login")
  private void onLoginClick(ClickEvent ce){
    GWT.log("Event vyvolan");
    this.login.setFocus(false);
    if(this.isFormFill()){
      GWT.log("volani login verify");
      this.loginVerify.call(new RemoteCallback<LoginResponse>(){
        @Override
        public void callback(LoginResponse response){
          Window.alert(response.toString());
        }
      }).authentication(loginRequest);
    }else{
      GWT.log("nevyplneny vsechny udaje");
    }
  }

  private boolean isFormFill(){
    if(this.username.getText().equals("") || this.password.getText().equals("")){
      return false;
    }else{
      return true;
    }
  }

}
