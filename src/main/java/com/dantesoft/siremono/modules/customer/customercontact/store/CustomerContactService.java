package com.dantesoft.siremono.modules.customer.customercontact.store;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerContactService {
  private final CustomerContactRepository customerContactRepository;

  public CustomerContactEntity save(CustomerContactEntity customerContactEntity) {
    return customerContactRepository.save(customerContactEntity);
  }
}
