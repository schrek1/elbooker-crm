package org.jboss.errai.demo.client.local;

import com.google.gwt.dom.client.Style;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.inject.Inject;
import org.jboss.errai.security.shared.api.annotation.RestrictedAccess;
import org.jboss.errai.ui.shared.api.annotations.DataField;
import org.jboss.errai.ui.shared.api.annotations.EventHandler;
import org.jboss.errai.ui.shared.api.annotations.Templated;

@Templated
public class EditCompanyPopup extends Composite{

  @Inject
  @DataField
  private Button editPopCloseBut;

  @Inject
  @DataField
  private Button editPopXBut;

  

  @EventHandler("editPopXBut")
  private void editXButClick(ClickEvent ce){
    this.setVisible(false);
  }

  @EventHandler("editPopCloseBut")
  private void editCloseButClick(ClickEvent ce){
    this.setVisible(false);
  }
}
