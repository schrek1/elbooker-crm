package org.jboss.errai.demo.client.local;

import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.Composite;
import javax.enterprise.context.Dependent;
import javax.faces.bean.ViewScoped;
import org.jboss.errai.ui.nav.client.local.Page;
import org.jboss.errai.ui.shared.api.annotations.DataField;
import org.jboss.errai.ui.shared.api.annotations.Templated;

@Templated("NavBar.html#navBar")
public class NavBar extends Composite{

  @DataField
  private Element navBar = DOM.createDiv();

  public Element getNavBar(){
    return this.navBar;
  }
}
