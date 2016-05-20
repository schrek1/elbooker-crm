package org.jboss.errai.demo.client.local;

import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Style;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.i18n.client.LocaleInfo;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.VerticalPanel;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import org.jboss.errai.databinding.client.api.DataBinder;
import org.jboss.errai.databinding.client.api.PropertyChangeEvent;
import org.jboss.errai.databinding.client.api.PropertyChangeHandler;
import org.jboss.errai.demo.client.shared.Company;
import org.jboss.errai.ui.client.widget.HasModel;
import org.jboss.errai.ui.client.widget.ValueImage;
import org.jboss.errai.ui.shared.api.annotations.AutoBound;
import org.jboss.errai.ui.shared.api.annotations.Bound;
import org.jboss.errai.ui.shared.api.annotations.DataField;
import org.jboss.errai.ui.shared.api.annotations.EventHandler;
import org.jboss.errai.ui.shared.api.annotations.Templated;

@Templated("Dashboard.html#company")
public class CompanyItemWidget extends Composite implements HasModel<Company>{
  
  // nabinduje poskytnutou instanci modelu vsem polozkam anotovanym bound
  @Inject
  @AutoBound
  private DataBinder<Company> company;

  // You can also choose to instantiate your own widgets. Injection is not
  // required. In case of Element, direct injection is not supported.
  @Bound
  @DataField
  private final Element id = DOM.createTD();

  @Bound
  @DataField
  private final Element name = DOM.createTD();

  @Bound
  @DataField
  private final Element web = DOM.createTD();

  @DataField
  private final Button infoBut = new Button();

  @DataField
  private final Button editBut = new Button();

  @DataField
  private final Button removeBut = new Button();

  @Override
  public Company getModel(){
    return company.getModel();
  }

  @Override
  public void setModel(Company model){
    company.setModel(model);
  }

  @EventHandler("infoBut")
  private void infoButClick(ClickEvent ce){
    DialogBox dialogBox = new DialogBox(true,true);
    dialogBox.setGlassStyleName("PopupPanel-GlassStyle");
    dialogBox.setGlassEnabled(true);

    dialogBox.setAnimationEnabled(true);
    dialogBox.setText("Detail firmy");

    FlowPanel panel = new FlowPanel();
    panel.add(new Button("button"));
    panel.add(new Button("button"));
    panel.add(new Button("button"));
    panel.setSize("500px", "300px");
    dialogBox.add(panel);

    dialogBox.center();
    dialogBox.show();

    this.infoBut.setFocus(false);
  }

  @EventHandler("editBut")
  private void editButClick(ClickEvent ce){
    Window.alert("info");
    this.editBut.setFocus(false);
  }

  @EventHandler("removeBut")
  private void removeButClick(ClickEvent ce){
    Window.alert("remove");
    this.removeBut.setFocus(false);
  }

}
