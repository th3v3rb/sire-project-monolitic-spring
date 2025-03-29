package com.dantesoft.siremono.modules.auth.store.seeders;

import java.time.LocalDateTime;
import java.util.Set;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.dantesoft.siremono.internal.SeedCommand;
import com.dantesoft.siremono.modules.auth.store.entity.Permission;
import com.dantesoft.siremono.modules.auth.store.entity.Role;
import com.dantesoft.siremono.modules.auth.store.entity.User;
import com.dantesoft.siremono.modules.auth.store.services.AuthService;
import com.dantesoft.siremono.modules.auth.store.services.PermissionService;
import com.dantesoft.siremono.modules.auth.store.services.RoleService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@RequiredArgsConstructor
public class RoleSeeder implements SeedCommand {
  private final RoleService roleService;
  private final PermissionService permissionService;
  private final AuthService userService;
  private final JdbcTemplate databaseTemplate;
  private final PasswordEncoder encoder;
  private Role guestRole;
  private Role adminRole;

  @Override
  public void seed() {
    populateRolesTable();
    populateFirstUser();
  }

  private void populateRolesTable() {
    try {
      String roleExistsQuery = "SELECT EXISTS (SELECT 1 FROM roles)";
      boolean rolesExist = Boolean.TRUE.equals(
          databaseTemplate.queryForObject(roleExistsQuery, Boolean.class));

      if (!rolesExist) {
        guestRole = new Role();
        guestRole.setName("GUEST");
        guestRole = roleService.save(guestRole);

        adminRole = new Role();
        adminRole.setName("ADMIN");
        adminRole = roleService.save(adminRole);
        log.info("Roles table has been seeded.");

        var viewBrandsPermission = new Permission();
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
      boolean usersExist = Boolean.TRUE.equals(
          databaseTemplate.queryForObject(userExistsQuery, Boolean.class));

      if (!usersExist && guestRole != null) {
        User user = new User();
        user.setUsername("Hugo Penayo");
        user.setEmail("1205hugopenayo@gmail.com");
        user.setPassword(encoder.encode("hugo2001"));
        user.setCreatedAt(LocalDateTime.now());
        user.setEmailVerifiedAt(LocalDateTime.now());
        user.setEnabled(true);
        user.setRoles(Set.of(guestRole));

        userService.save(user);
        log.info("First user has been created.");
      } else {
        log.info("Users already exist, skipping seed.");
      }
    } catch (Exception e) {
      log.error("Failed to seed first user and permissions", e);
    }
  }
}
