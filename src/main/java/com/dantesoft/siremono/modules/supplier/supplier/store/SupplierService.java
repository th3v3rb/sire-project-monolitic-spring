package com.dantesoft.siremono.modules.supplier.supplier.store;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SupplierService {
  private final SupplierRepository supplierRepository;


  public Page<SupplierEntity> list(Pageable pageable, String filter) {
    return supplierRepository.searchByNameOrSocialReason(pageable, filter);
  }

  public SupplierEntity save(SupplierEntity supplier) {
    return supplierRepository.save(supplier);
  }

  public List<SupplierEntity> saveAll(List<SupplierEntity> suppliers) {
    return supplierRepository.saveAll(suppliers);
  }

  public SupplierEntity findByIdOrFail(UUID id) {
    return supplierRepository
        .findById(id)
        .orElseThrow(() -> new EntityNotFoundException(id.toString()));
  }

  public List<SupplierEntity> findAllWhereIdIn(List<UUID> ids) {
    return supplierRepository.findAllById(ids);
  }
}
