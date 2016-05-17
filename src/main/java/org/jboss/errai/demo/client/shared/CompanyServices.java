package org.jboss.errai.demo.client.shared;

import java.util.List;
import org.jboss.errai.bus.server.annotations.Remote;

/**
 *
 * @author ondra
 */

@Remote
public interface CompanyServices{

  public List<Company> listOfCompany();

}
