package org.jboss.errai.demo.client.local;

import com.google.gwt.user.client.ui.Composite;
import org.jboss.errai.security.shared.api.annotation.RestrictedAccess;
import org.jboss.errai.ui.nav.client.local.DefaultPage;
import org.jboss.errai.ui.nav.client.local.Page;
import org.jboss.errai.ui.shared.api.annotations.Templated;

@Page
@Templated
@RestrictedAccess(roles = "ADMIN")
public class UsersAdmin extends Composite{

}
