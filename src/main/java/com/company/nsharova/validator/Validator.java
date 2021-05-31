package com.company.nsharova.validator;

import java.util.Map;

public interface Validator<T> {
  void validate(T dto, Map<String, String> errors);
}
