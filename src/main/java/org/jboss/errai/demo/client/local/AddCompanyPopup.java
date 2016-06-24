package org.jboss.errai.demo.client.local;

import com.google.gwt.dom.client.Style;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import org.jboss.errai.common.client.api.RemoteCallback;
import org.jboss.errai.ui.shared.api.annotations.DataField;
import org.jboss.errai.ui.shared.api.annotations.EventHandler;
import org.jboss.errai.ui.shared.api.annotations.Templated;

@Templated("#addPopUp")
@ApplicationScoped
public class AddCompanyPopup extends Composite{

  @Inject
  @DataField
  private Button addPopCloseBut;

  @Inject
  @DataField
  private Button addPopXBut;

  @Inject
  @DataField
  private Button saveBut;

  @EventHandler("addPopXBut")
  private void editXButClick(ClickEvent ce){
    this.closeModal();
  }

  @EventHandler("addPopCloseBut")
  private void editCloseButClick(ClickEvent ce){
    this.closeModal();
  }

  @EventHandler("saveBut")
  private void submitButClick(ClickEvent ce){
    this.closeModal();
  }

  private void closeModal(){
    this.setVisible(false);
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
