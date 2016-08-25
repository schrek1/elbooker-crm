package org.jboss.errai.demo.client.local;

import com.google.gwt.dom.client.Style;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.text.shared.Renderer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.ValueListBox;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import org.jboss.errai.demo.client.shared.userEntity.User;
import org.jboss.errai.demo.client.shared.userEntity.UsersRoles;
import org.jboss.errai.ui.shared.api.annotations.DataField;
import org.jboss.errai.ui.shared.api.annotations.EventHandler;
import org.jboss.errai.ui.shared.api.annotations.Templated;

@Templated
public class AddUserPopup extends Composite{

  @Inject
  @DataField
  private TextBox username;

  @Inject
  @DataField
  private TextBox password;

  @Inject
  @DataField
  private TextBox checkPass;

  @DataField
  private ValueListBox roles = new ValueListBox(new Renderer<UsersRoles>(){
    @Override
    public String render(UsersRoles role){
      return role.getNameOfRole();
    }

    @Override
    public void render(UsersRoles role, Appendable appendable) throws IOException{
      appendable.append(render(role));
    }
  });

  @Inject
  @DataField
  private Button createButton;

  @Inject
  @DataField
  private Button closeButton;

  @EventHandler("closeButton")
  private void closeButtClick(ClickEvent ce){
    this.setVisible(false);
  }

  @EventHandler("createButton")
  private void createButtClick(ClickEvent ce){
    this.setVisible(false);
    UsersRoles role = (UsersRoles)this.roles.getValue();
    Window.alert(role.toString());
  }

  @PostConstruct
  private void init(){
    List<UsersRoles> roles = Arrays.asList(UsersRoles.values());
    this.roles.setValue(roles.get(1));
    this.roles.setAcceptableValues(roles);
  }

  @Override
  public void setVisible(boolean visible){
    if(visible){
      this.getElement().getStyle().setDisplay(Style.Display.BLOCK);
    }else{
      this.getElement().getStyle().setDisplay(Style.Display.NONE);
    }
  }

}
