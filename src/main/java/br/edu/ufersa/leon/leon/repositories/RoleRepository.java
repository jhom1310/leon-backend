package br.edu.ufersa.leon.leon.repositories;

import br.edu.ufersa.leon.leon.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
