package org.jboss.errai.demo.client.local.pageStruct;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import org.jboss.errai.demo.client.local.CompanyAdmin;
import org.jboss.errai.demo.client.local.Dashboard;
import org.jboss.errai.demo.client.local.EditAccount;
import org.jboss.errai.demo.client.local.UsersAdmin;
import org.jboss.errai.security.shared.api.annotation.RestrictedAccess;
import org.jboss.errai.ui.nav.client.local.Navigation;
import org.jboss.errai.ui.nav.client.local.TransitionAnchor;
import org.jboss.errai.ui.shared.TemplateWidget;
import org.jboss.errai.ui.shared.api.annotations.DataField;
import org.jboss.errai.ui.shared.api.annotations.Templated;

@Templated("NavBar.html#navBar")
@ApplicationScoped
public class NavBar extends Composite{

  @Inject
  Navigation nav;

  @Inject
  @DataField
  @RestrictedAccess
  TransitionAnchor<Dashboard> linkDashboard;

  @Inject
  @DataField
  @RestrictedAccess(roles = "ADMIN")
  TransitionAnchor<UsersAdmin> linkUsers;

  @Inject
  @DataField
  @RestrictedAccess
  TransitionAnchor<CompanyAdmin> linkCompany;

  @Inject
  @DataField
  @RestrictedAccess
  TransitionAnchor<EditAccount> linkAccount;

  public NavBar(){
    //handler pro prirazovani/odebirani class menu itemum
    History.addValueChangeHandler(new ValueChangeHandler<String>(){
      @Override
      public void onValueChange(ValueChangeEvent<String> event){
        TemplateWidget tw = (TemplateWidget)NavBar.this.getWidget();
        if(NavBar.this.nav.getCurrentPage() != null){
          for(Widget widget : tw){
            if(widget instanceof TransitionAnchor){
              TransitionAnchor ta = (TransitionAnchor)widget;
              if(ta.toPageType().getSimpleName().equals(NavBar.this.nav.getCurrentPage().name())){
                ta.addStyleName("active-menu");
              }else{
                ta.removeStyleName("active-menu");
              }
            }
          }
        }
      }
    });
  }

}
