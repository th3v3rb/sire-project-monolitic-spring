package com.dantesoft.siremono.internal.database.seeders;

import com.dantesoft.siremono.internal.events.DomainEventPublisher;
import com.dantesoft.siremono.modules.auth.authentication.events.UserRegisteredEvent;
import com.dantesoft.siremono.modules.auth.authentication.store.entity.AccountEntity;
import com.dantesoft.siremono.modules.auth.authentication.store.entity.PermissionEntity;
import com.dantesoft.siremono.modules.auth.authentication.store.entity.RoleEntity;
import com.dantesoft.siremono.modules.auth.authentication.store.services.AccountService;
import com.dantesoft.siremono.modules.auth.authentication.store.services.PermissionService;
import com.dantesoft.siremono.modules.auth.authentication.store.services.RoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Set;

@Component
@RequiredArgsConstructor
@Slf4j
public class FirstUserSeeder implements DatabaseSeeder {
  private final RoleService roleService;
  private final PermissionService permissionService;
  private final AccountService accountService;
  private final PasswordEncoder encoder;
  private final DomainEventPublisher eventPublisher;

  @Override
  public void seed() {
    try {
      log.info("Starting database seed...");
      if (!rolesExist()) {
        seedRolesAndPermissions();
      } else {
        log.info("Roles already exist.");
      }

      if (!accountsExist()) {
        seedFirstAccount();
      } else {
        log.info("Users already exist.");
      }
    } catch (Exception e) {
      log.error("Seeding error", e);
    }
  }

  private boolean rolesExist() {
    return !roleService.findAll().isEmpty();
  }

  private boolean accountsExist() {
    return !accountService.findAll().isEmpty();
  }

  private void seedRolesAndPermissions() {
    var guestRole = roleService.save(new RoleEntity("GUEST"));
    var adminRole = roleService.save(new RoleEntity("ADMIN"));

    var viewBrandsPermission = new PermissionEntity("view:brands");
    viewBrandsPermission = permissionService.save(viewBrandsPermission);

    guestRole.setPermissions(Set.of(viewBrandsPermission));
    roleService.save(guestRole);
    roleService.save(adminRole);

    log.info("Seeded roles and permissions.");
  }

  private void seedFirstAccount() {
    var guestRole = roleService.findByName("GUEST");

    var user = new AccountEntity();
    user.setUsername("Hugo Penayo");
    user.setEmail("1205hugopenayo@gmail.com");
    user.setPassword(encoder.encode("Hugo*zxc12*05"));
    user.setCreatedAt(LocalDateTime.now());
    user.setEmailVerifiedAt(LocalDateTime.now());
    user.setEnabled(true);
    user.setRoles(Set.of(guestRole));

    user = accountService.save(user);
    eventPublisher.publish(new UserRegisteredEvent(user));
    log.info("First user created.");
  }
}
