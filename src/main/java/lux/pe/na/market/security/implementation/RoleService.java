package lux.pe.na.market.security.implementation;


import lux.pe.na.market.security.entity.Role;
import lux.pe.na.market.security.enums.RoleName;
import lux.pe.na.market.security.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class RoleService {

    private final RoleRepository repository;

    @Autowired
    public RoleService(RoleRepository repository) {
        this.repository = repository;
    }

    public Optional<Role> getByName(RoleName roleName) {
        return repository.findByRoleName(roleName);
    }

    public void save(Role role) {
        repository.save(role);
    }
}
