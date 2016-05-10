package org.jboss.errai.demo.client.local;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.inject.Inject;
import javax.persistence.PostLoad;
import org.jboss.errai.ui.nav.client.local.DefaultPage;
import org.jboss.errai.ui.nav.client.local.Page;
import org.jboss.errai.ui.shared.api.annotations.DataField;
import org.jboss.errai.ui.shared.api.annotations.Templated;

/**
 *
 * @author ondra
 */

@Page
@Templated("DashBoard.html#wrapper")
public class Dashboard extends Composite{

 @PostLoad
 private void postLoad(){
   Window.alert("vitej jone");

   
 }


}
