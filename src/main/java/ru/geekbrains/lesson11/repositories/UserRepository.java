package ru.geekbrains.lesson11.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.geekbrains.lesson11.entities.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
