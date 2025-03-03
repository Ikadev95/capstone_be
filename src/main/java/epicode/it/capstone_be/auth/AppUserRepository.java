package epicode.it.capstone_be.auth;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    Optional<AppUser> findByUsername(String username);
    boolean existsByUsername(String username);

    @Query(value = "SELECT us.*  FROM users us JOIN utenti u ON us.id = u.user_id WHERE u.email = :email ",nativeQuery = true)
    Optional<AppUser> findByEmail(@Param("email") String email);
}
