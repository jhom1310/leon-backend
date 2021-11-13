package br.edu.ufersa.leon.leon.repositories;

import br.edu.ufersa.leon.leon.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {

}
