package org.jboss.errai.demo.client.shared;

import org.jboss.errai.common.client.api.annotations.Portable;

/**
 *
 * @author ondra
 */


@Portable
public class LoginResponse{

  public enum Response{
    ALL_OK, PASS_BAD, USER_BAD
  }

  private Response status;
  private String message;

  public LoginResponse(){
  }

  public LoginResponse(Response status){
    this.status = status;
  }

  public LoginResponse(Response status, String message){
    this.status = status;
    this.message = message;
  }

  public Response getStatus(){
    return status;
  }

  public String getMessage(){
    return message;
  }

  public void setStatus(Response status){
    this.status = status;
  }

  public void setMessage(String message){
    this.message = message;
  }

  @Override
  public String toString(){
    return "LoginResponse{" + "status=" + status + ", message=" + message + '}';
  }
}
