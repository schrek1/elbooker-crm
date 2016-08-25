package org.jboss.errai.demo.client.local;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.ValueListBox;
import java.io.IOException;
import org.jboss.errai.demo.client.shared.userEntity.UsersRoles;
import org.jboss.errai.security.shared.api.annotation.RestrictedAccess;
import org.jboss.errai.ui.nav.client.local.Page;
import org.jboss.errai.ui.shared.api.annotations.DataField;
import org.jboss.errai.ui.shared.api.annotations.Templated;
import com.google.gwt.text.shared.Renderer;
import com.google.gwt.user.client.ui.Button;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import org.jboss.errai.common.client.api.Caller;
import org.jboss.errai.common.client.api.RemoteCallback;
import org.jboss.errai.demo.client.shared.companyEntity.Company;
import org.jboss.errai.demo.client.shared.services.CompanyServices;
import org.jboss.errai.demo.client.shared.services.UserServices;
import org.jboss.errai.demo.client.shared.userEntity.Role;
import org.jboss.errai.demo.client.shared.userEntity.User;
import org.jboss.errai.ui.shared.api.annotations.EventHandler;

@Page
@Templated
@RestrictedAccess(roles = "ADMIN")
public class CompanyAdmin extends Composite{

  @DataField
  private ValueListBox companyBox = new ValueListBox(new Renderer<Company>(){
    @Override
    public String render(Company company){
      return company.getName();
    }

    @Override
    public void render(Company company, Appendable appendable) throws IOException{
      appendable.append(render(company));
    }
  });

  @DataField
  private ValueListBox accessBox = new ValueListBox(new Renderer<User>(){
    @Override
    public String render(User user){

      return user != null ? user.getIdentifier() : "";
    }

    @Override
    public void render(User user, Appendable appendable) throws IOException{
      if(user != null){
        appendable.append(render(user));
      }
    }
  });

  @DataField
  private ValueListBox userBox = new ValueListBox(new Renderer<User>(){
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
  private Button removeButton;

  @Inject
  private Caller<CompanyServices> companyCaller;

  @Inject
  private Caller<UserServices> userCaller;

  private List<User> companyUsers = new ArrayList<User>();

  @EventHandler("addButton")
  private void onAddButtonClick(ClickEvent ce){
    if(this.userBox.isEnabled()){
      Company target = (Company)this.companyBox.getValue();
      User addUser = (User)this.userBox.getValue();

      this.accessBox.setValue(addUser);

      this.accessBox.setEnabled(true);

    }

  }

    @EventHandler("removeButton")
  private void onRemoveButtonClick(ClickEvent ce){
    if(this.accessBox.isEnabled()){
      Company target = (Company)this.companyBox.getValue();
      User removeUser = (User)this.accessBox.getValue();

      this.userBox.setValue(removeUser);

      this.userBox.setEnabled(true);

    }

  }

  @PostConstruct
  private void init(){
    this.setUserList();
    this.addHandlerToListBox();
    this.fillCompanyBox();
  }

  private void setUserList(){
    this.userCaller.call(new RemoteCallback<List<User>>(){
      @Override
      public void callback(List<User> response){
        companyUsers = response;
      }
    }).getListOfCompanyUsers();
  }

  private void addHandlerToListBox(){
    this.companyBox.addValueChangeHandler(new ValueChangeHandler<User>(){
      @Override
      public void onValueChange(ValueChangeEvent<User> event){
        Company selectCompany = (Company)companyBox.getValue();
        List<User> userWithAccess = selectCompany.getAuthorizedUsers();

        fillAccessBox(userWithAccess);
        fillUserBox(userWithAccess);
      }

    });
  }

  private void fillAccessBox(List<User> userWithAccess){
    if(!userWithAccess.isEmpty()){
      attachToUserBox(accessBox, userWithAccess);
      this.removeButton.getElement().removeClassName("disabled");
    }else{
      setUserBoxEmpty(accessBox);
      this.removeButton.getElement().addClassName("disabled");
    }
  }

  private void fillUserBox(List<User> userWithAccess){
    List<User> availableUsers = getAvailableUsers(userWithAccess);

    if(!availableUsers.isEmpty()){
      attachToUserBox(userBox, availableUsers);
      this.addButton.getElement().removeClassName("disabled");
    }else{
      setUserBoxEmpty(userBox);
      this.addButton.getElement().addClassName("disabled");
    }
  }

  private List<User> getAvailableUsers(List<User> userWithAccess){
    List<User> availableUsers = new ArrayList<User>();
    Collections.copy(availableUsers, companyUsers);
    availableUsers.removeAll(userWithAccess);
    return availableUsers;
  }

  private void attachToUserBox(ValueListBox listbox, List<User> list){
    listbox.setEnabled(true);
    listbox.setValue(list.get(0), true);
    listbox.setAcceptableValues(list);
  }

  private void setUserBoxEmpty(ValueListBox listbox){
    listbox.setEnabled(false);
    listbox.setValue(new User("prazdne", Arrays.asList(new Role(UsersRoles.COMPANY))));
    listbox.setAcceptableValues(new ArrayList<User>());
  }

  private void fillCompanyBox(){
    this.companyCaller.call(new RemoteCallback<List<Company>>(){
      @Override
      public void callback(List<Company> response){
        if(!response.isEmpty()){
          companyBox.setValue(response.get(0), true);
          companyBox.setAcceptableValues(response);
        }
      }
    }).getListOfCompanies();
  }
}
