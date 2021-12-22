package com.project.rest.repositories;

import com.project.rest.entities.Usuario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface UserRepository extends CrudRepository<Usuario, Long> {

    @Query("SELECT u from Usuario u WHERE u.login = ?1")
    Usuario findUsuarioByLogin(String login);
}
