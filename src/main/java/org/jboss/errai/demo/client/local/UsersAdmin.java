package org.jboss.errai.demo.client.local;

import com.google.gwt.core.client.GWT;
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
import org.jboss.errai.ui.client.widget.HasModel;
import org.jboss.errai.ui.nav.client.local.Page;
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

  @DataField
  private ValueListBox roles = new ValueListBox(new Renderer<String>(){
    @Override
    public String render(String role){
      return role;
    }

    @Override
    public void render(String role, Appendable appendable) throws IOException{
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
  private Caller<UserServices> userCaller;

  @EventHandler("addButton")
  private void addButtonClick(ClickEvent ce){
    this.addPop.setVisible(true);
    this.addButton.setFocus(false);
  }

  @PostConstruct
  private void init(){
    this.fillListBox();
    this.fillRoles();
    this.addHandlerToListBox();
  }

  private void addHandlerToListBox(){
    listBox.addValueChangeHandler(new ValueChangeHandler(){
      @Override
      public void onValueChange(ValueChangeEvent event){
        User user = (User)listBox.getValue();
        setModel(user);
        setElementsValue();
      }
    });
  }

  private void setElementsValue(){
    User model = this.userBinder.getModel();

    this.username.setText(model.getIdentifier());

    Object roles[] = model.getRoles().toArray();
    if(roles.length != 0){
      parseTextFromRole(roles[0]);
    }
  }

  private void parseTextFromRole(Object object){
    Role role = (Role)object;
    UsersRole enumRole = UsersRole.valueOf(role.getName());
    this.roles.setValue(enumRole.getNameOfRole());
  }

  private void fillListBox(){
    this.userCaller.call(new RemoteCallback<List<User>>(){
      @Override
      public void callback(List<User> users){
        listBox.setAcceptableValues(users);
      }
    }).getListOfUsersNP();
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
    List<String> roles = UsersRole.getAllRoles();
    this.roles.setValue(roles.get(0));
    this.roles.setAcceptableValues(roles);
  }

}
