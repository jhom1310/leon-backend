package br.edu.ufersa.leon.leon.repositories;

import br.edu.ufersa.leon.leon.entity.Role;
import br.edu.ufersa.leon.leon.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
