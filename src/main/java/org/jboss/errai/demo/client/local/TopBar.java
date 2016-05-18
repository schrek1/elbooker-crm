package org.jboss.errai.demo.client.local;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Node;
import com.google.gwt.user.client.DOM;
import javax.enterprise.context.Dependent;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import org.jboss.errai.ui.shared.api.annotations.DataField;
import org.jboss.errai.ui.shared.api.annotations.Templated;

@Templated("TopBar.html#topBar")
@ViewScoped
public class TopBar extends Composite{

  @DataField
  private Element topBar = DOM.createDiv();

  public Element getTopBar(){
    return this.topBar;
  }
}
