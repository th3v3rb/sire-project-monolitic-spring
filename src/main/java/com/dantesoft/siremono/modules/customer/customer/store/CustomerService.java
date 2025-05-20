package com.dantesoft.siremono.modules.customer.customer.store;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {
  private final CustomerRepository customerRepository;

  public CustomerEntity save(CustomerEntity customerEntity) {
    return customerRepository.save(customerEntity);
  }

  public Page<CustomerEntity> findAllByNameOrSocialReason(Pageable pageable, String nameOrSocialReason) {
    return customerRepository.searchByNameOrSocialReason(pageable, nameOrSocialReason);
  }
}
