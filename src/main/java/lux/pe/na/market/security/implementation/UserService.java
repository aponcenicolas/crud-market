package lux.pe.na.market.security.implementation;

import lux.pe.na.market.security.entity.User;
import lux.pe.na.market.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class UserService {

    private final UserRepository repository;

    @Autowired
    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public Optional<User> getByUserName(String userName) {
        return repository.findByUserName(userName);
    }

    public boolean existsByUserName(String userName) {
        return repository.existsByUserName(userName);
    }

    public boolean existsByMail(String mail) {
        return repository.existsByMail(mail);
    }

    public void save(User user) {
        repository.save(user);
    }
}
