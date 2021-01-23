package ru.geekbrains.lesson11.repositories;

import ru.geekbrains.lesson11.entities.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {
}
