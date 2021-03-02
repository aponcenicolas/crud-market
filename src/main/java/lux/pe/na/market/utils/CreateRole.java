package lux.pe.na.market.utils;

import lux.pe.na.market.security.implementation.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CreateRole implements CommandLineRunner {

    @Autowired
    RoleService roleService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        /*Role admin = new Role(RoleName.ROLE_ADMIN);
        Role user = new Role(RoleName.ROLE_ADMIN);
        roleService.save(admin);
        roleService.save(user);*/

        System.out.println("ADMIN: " + passwordEncoder.encode("admin"));
        System.out.println("USER: " + passwordEncoder.encode("user"));
    }
}
