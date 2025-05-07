package com.dantesoft.siremono.internal.database.seeders;

import java.time.LocalDateTime;
import java.util.Set;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import com.dantesoft.siremono.internal.events.DomainEventPublisher;
import com.dantesoft.siremono.modules.auth.store.AccountService;
import com.dantesoft.siremono.modules.auth.events.UserRegisteredEvent;
import com.dantesoft.siremono.modules.auth.store.PermissionService;
import com.dantesoft.siremono.modules.auth.store.RoleService;
import com.dantesoft.siremono.modules.auth.store.entity.PermissionEntity;
import com.dantesoft.siremono.modules.auth.store.entity.RoleEntity;
import com.dantesoft.siremono.modules.auth.store.entity.AccountEntity;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@RequiredArgsConstructor
public class FirstUserSeeder implements ApplicationListener<ApplicationReadyEvent> {
  private final RoleService roleService;
  private final PermissionService permissionService;
  private final AccountService userService;
  private final JdbcTemplate databaseTemplate;
  private final PasswordEncoder encoder;
  private final DomainEventPublisher dep;
  private RoleEntity guestRole;
  private RoleEntity adminRole;

  @Override
  public void onApplicationEvent(ApplicationReadyEvent event) {
    populateRolesTable();
    populateFirstUser();
  }

  private void populateRolesTable() {
    try {
      String roleExistsQuery = "SELECT EXISTS (SELECT 1 FROM roles)";
      boolean question = databaseTemplate.queryForObject(roleExistsQuery, Boolean.class);
      boolean rolesExist = Boolean.TRUE.equals(question);

      if (!rolesExist) {
        guestRole = new RoleEntity();
        guestRole.setName("GUEST");
        guestRole = roleService.save(guestRole);

        adminRole = new RoleEntity();
        adminRole.setName("ADMIN");
        adminRole = roleService.save(adminRole);
        log.info("Roles table has been seeded.");

        var viewBrandsPermission = new PermissionEntity();
        viewBrandsPermission.setName("view:brands");
        viewBrandsPermission = permissionService.save(viewBrandsPermission);

        guestRole.setPermissions(Set.of(viewBrandsPermission));
        roleService.save(guestRole);

        log.info("Permissions have been seeded.");
      } else {
        log.info("Database already has roles, skipping seed.");
      }
    } catch (Exception e) {
      log.error("Failed to seed ROLE table", e);
    }
  }

  private void populateFirstUser() {
    try {
      String userExistsQuery = "SELECT EXISTS (SELECT 1 FROM users)";
      boolean usersExist = Boolean.TRUE.equals(databaseTemplate.queryForObject(userExistsQuery, Boolean.class));

      // if(false) {
      if (!usersExist && guestRole != null) {
        var user = new AccountEntity();
        user.setUsername("Hugo Penayo");
        user.setEmail("1205hugopenayo@gmail.com");
        user.setPassword(encoder.encode("Hugo*zxc12*05"));
        user.setCreatedAt(LocalDateTime.now());
        user.setEmailVerifiedAt(LocalDateTime.now());
        user.setEnabled(true);
        user.setRoles(Set.of(guestRole));

        user = userService.save(user);
        log.info("First user has been created.");

        dep.publish(new UserRegisteredEvent(user));

      } else {
        log.info("Users already exist, skipping seed.");
      }
    } catch (Exception e) {
      log.error("Failed to seed first user and permissions", e);
    }
  }

}
