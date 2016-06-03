package org.jboss.errai.demo.client.local;

import com.google.gwt.user.client.ui.Composite;
import java.util.List;
import javax.inject.Inject;
import org.jboss.errai.common.client.api.RemoteCallback;
import org.jboss.errai.demo.client.local.pageStruct.NavBar;
import org.jboss.errai.demo.client.local.pageStruct.PagesEnum;
import org.jboss.errai.demo.client.shared.companyEntity.Company;
import org.jboss.errai.security.shared.api.annotation.RestrictedAccess;
import org.jboss.errai.ui.nav.client.local.DefaultPage;
import org.jboss.errai.ui.nav.client.local.Page;
import org.jboss.errai.ui.nav.client.local.PageShown;
import org.jboss.errai.ui.shared.api.annotations.Templated;

@Page
@Templated
@RestrictedAccess
public class EditAccount extends Composite{

}
