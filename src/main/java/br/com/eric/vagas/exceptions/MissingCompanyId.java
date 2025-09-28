package br.com.eric.vagas.exceptions;

public class MissingCompanyId extends RuntimeException {
  public MissingCompanyId() {
    super("Missing company_id");
  }
}
