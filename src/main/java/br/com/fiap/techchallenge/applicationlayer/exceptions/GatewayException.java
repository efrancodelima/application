package br.com.fiap.techchallenge.applicationlayer.exceptions;

/**
 * Exceção customizada para os gateways.
 */
public class GatewayException extends Exception {

  /**
   * Construtor.
   *
   * @param msg Mensagem de erro associada à exceção.
   */
  public GatewayException(String msg) {
    super(msg);
  }

}
