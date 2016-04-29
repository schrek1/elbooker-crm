package org.jboss.errai.demo.server;

import org.jboss.errai.demo.client.shared.UserAccount;

/**
 *
 * @author ondra
 */
public class VerifyResponse{
  public enum Status{
    ACCOUNT_FOUND, UNKNOW_ACCOUNT
  }

  private Status queryStatus;
  private UserAccount userAccount;

  public VerifyResponse(Status queryStatus, UserAccount userAccount){
    this.queryStatus = queryStatus;
    this.userAccount = userAccount;
  }

  public Status getQueryStatus(){
    return queryStatus;
  }

  public UserAccount getUserAccount(){
    return userAccount;
  }

  public void setQueryStatus(Status queryStatus){
    this.queryStatus = queryStatus;
  }

  public void setUserAccount(UserAccount userAccount){
    this.userAccount = userAccount;
  }
}
