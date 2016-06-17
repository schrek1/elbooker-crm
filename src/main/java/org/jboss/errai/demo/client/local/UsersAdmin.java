package org.jboss.errai.demo.client.local;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.text.shared.Renderer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.ValueListBox;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import org.jboss.errai.common.client.api.Caller;
import org.jboss.errai.common.client.api.RemoteCallback;
import org.jboss.errai.databinding.client.api.DataBinder;
import org.jboss.errai.demo.client.shared.companyEntity.Company;
import org.jboss.errai.demo.client.shared.services.UserServices;
import org.jboss.errai.demo.client.shared.userEntity.User;
import org.jboss.errai.demo.client.shared.userEntity.UsersRole;
import org.jboss.errai.security.shared.api.Role;
import org.jboss.errai.security.shared.api.annotation.RestrictedAccess;
import org.jboss.errai.security.shared.service.AuthenticationService;
import org.jboss.errai.ui.client.widget.HasModel;
import org.jboss.errai.ui.nav.client.local.Page;
import org.jboss.errai.ui.nav.client.local.PageShowing;
import org.jboss.errai.ui.nav.client.local.PageShown;
import org.jboss.errai.ui.shared.api.annotations.AutoBound;
import org.jboss.errai.ui.shared.api.annotations.Bound;
import org.jboss.errai.ui.shared.api.annotations.DataField;
import org.jboss.errai.ui.shared.api.annotations.EventHandler;
import org.jboss.errai.ui.shared.api.annotations.Model;
import org.jboss.errai.ui.shared.api.annotations.Templated;

@Page
@Templated
@RestrictedAccess(roles = "ADMIN")
public class UsersAdmin extends Composite implements HasModel<User>{

  @Inject
  @AutoBound
  private DataBinder<User> userBinder;

  @Inject
  @DataField
  private TextBox username;

  @Inject
  @DataField
  private TextBox password;

  @Inject
  @DataField
  private TextBox checkPass;

  private boolean modelUpdate;

  @DataField
  private ValueListBox roles = new ValueListBox(new Renderer<UsersRole>(){
    @Override
    public String render(UsersRole role){
      return role.getNameOfRole();
    }

    @Override
    public void render(UsersRole role, Appendable appendable) throws IOException{
      appendable.append(render(role));
    }
  });

  @DataField
  private ValueListBox listBox = new ValueListBox(new Renderer<User>(){
    @Override
    public String render(User user){
      return user.getIdentifier();
    }

    @Override
    public void render(User user, Appendable appendable) throws IOException{
      appendable.append(render(user));
    }
  });

  @Inject
  @DataField
  private Button addButton;

  @Inject
  @DataField
  private AddUserPopup addPop;

  @Inject
  @DataField
  private Button removeButton;

  @Inject
  @DataField
  private Button editButton;

  @Inject
  private Caller<UserServices> userCaller;

  @Inject
  private Caller<AuthenticationService> authCaller;

  @EventHandler("addButton")
  private void addButtonClick(ClickEvent ce){
    this.addPop.setVisible(true);
    this.addButton.setFocus(false);
  }

  @PostConstruct
  private void init(){
    this.addTextBoxChangeHandlers();
    this.fillListBox();
    this.fillRoles();
    this.addHandlerToListBox();
  }

  private void addHandlerToListBox(){
    listBox.addValueChangeHandler(new ValueChangeHandler(){
      @Override
      public void onValueChange(ValueChangeEvent event){
        updateForm();
      }
    });
  }

  private void updateForm(){
    User user = (User)listBox.getValue();
    this.setModel(user);
    this.setElementsValue();
    this.removeAllWarnOnFields();
    this.removePassFields();
    this.modelUpdate = false;
  }

  private void setElementsValue(){
    User model = this.userBinder.getModel();

    this.username.setText(model.getIdentifier());

    this.roles.setValue(parseEnumFromModel());
  }

  private Object parseEnumFromModel(){
    Object roleArray[] = this.userBinder.getModel().getRoles().toArray();
    if(roleArray.length != 0){
      Role role = (Role)roleArray[0];
      UsersRole enumRole = UsersRole.valueOf(role.getName());
      return enumRole;
    }else{
      return null;
    }
  }

  private void fillListBox(){
    this.userCaller.call(new RemoteCallback<List<User>>(){
      @Override
      public void callback(List<User> users){
        removeCurrentUserFromList(users);
      }
    }).getListOfUsersNP();
  }

  private void removeCurrentUserFromList(final List<User> users){
    this.authCaller.call(new RemoteCallback<User>(){
      @Override
      public void callback(User logged){
        users.remove(logged);
        attachListToBox(users);
      }
    }).getUser();
  }

  private void attachListToBox(List<User> users){
    this.listBox.setValue(users.get(0));
    this.updateForm();
    this.listBox.setAcceptableValues(users);
  }

  @Override
  public User getModel(){
    return this.userBinder.getModel();
  }

  @Override
  public void setModel(User model){
    this.userBinder.setModel(model);
  }

  private void fillRoles(){
    List<UsersRole> roles = Arrays.asList(UsersRole.values());
    this.roles.setValue(roles.get(0));
    this.roles.setAcceptableValues(roles);
  }

  private void addTextBoxChangeHandlers(){
    this.username.addChangeHandler(new ChangeHandler(){
      @Override
      public void onChange(ChangeEvent event){
        User user = (User)listBox.getValue();
        if(!username.getText().equals(user.getIdentifier())){
          username.getElement().getParentElement().addClassName("has-warning");
        }else{
          username.getElement().getParentElement().removeClassName("has-warning");
        }
      }
    });

    this.checkPass.addChangeHandler(new ChangeHandler(){
      @Override
      public void onChange(ChangeEvent event){
        if(!checkPass.getText().isEmpty()){
          checkPass.getElement().getParentElement().addClassName("has-warning");
        }else{
          checkPass.getElement().getParentElement().removeClassName("has-warning");
        }
      }
    });
    this.password.addChangeHandler(new ChangeHandler(){
      @Override
      public void onChange(ChangeEvent event){
        if(!password.getText().isEmpty()){
          password.getElement().getParentElement().addClassName("has-warning");
        }else{
          password.getElement().getParentElement().removeClassName("has-warning");
        }
      }
    });
    this.roles.addValueChangeHandler(new ValueChangeHandler<UsersRole>(){
      @Override
      public void onValueChange(ValueChangeEvent<UsersRole> event){
        UsersRole newVal = event.getValue();
        UsersRole modelVal = (UsersRole)parseEnumFromModel();

        if(newVal != modelVal){
          roles.getElement().getParentElement().addClassName("has-warning");
        }else{
          roles.getElement().getParentElement().removeClassName("has-warning");
        }
      }
    });
  }

  private void removeAllWarnOnFields(){
    username.getElement().getParentElement().removeClassName("has-warning");
    checkPass.getElement().getParentElement().removeClassName("has-warning");
    password.getElement().getParentElement().removeClassName("has-warning");
    roles.getElement().getParentElement().removeClassName("has-warning");
  }

  private void removePassFields(){
    this.password.setText("");
    this.checkPass.setText("");
  }

}
