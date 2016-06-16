package org.jboss.errai.demo.client.local;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import javax.inject.Inject;
import org.jboss.errai.security.shared.api.annotation.RestrictedAccess;
import org.jboss.errai.ui.nav.client.local.Page;
import org.jboss.errai.ui.shared.api.annotations.DataField;
import org.jboss.errai.ui.shared.api.annotations.EventHandler;
import org.jboss.errai.ui.shared.api.annotations.Templated;

@Page
@Templated
@RestrictedAccess(roles = "ADMIN")
public class UsersAdmin extends Composite{

  @Inject
  @DataField
  private Button addButton;

  @Inject
  @DataField
  private AddUserPopup addPop;

  @EventHandler("addButton")
  private void addButtonClick(ClickEvent ce){
    this.addPop.setVisible(true);
  }

}
