package utez.edu.mx.transferSave.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import utez.edu.mx.*;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // Buscar por email
    Optional<User> findByEmail(String email);

    // Buscar por clave pública de Stellar
    Optional<User> findByStellarPublicKey(String stellarPublicKey);

    // Verificar si existe un email
    boolean existsByEmail(String email);

    // Verificar si existe una clave pública de Stellar
    boolean existsByStellarPublicKey(String stellarPublicKey);

    // Buscar usuarios por rol
    List<User> findByRole(User.UserRole role);

    // Buscar usuarios activos
    List<User> findByActive(Boolean active);

    // Buscar usuarios verificados
    List<User> findByVerified(Boolean verified);

    // Buscar usuarios activos por rol
    List<User> findByRoleAndActive(User.UserRole role, Boolean active);

    // Buscar por nombre (búsqueda parcial)
    @Query("SELECT u FROM User u WHERE LOWER(u.fullName) LIKE LOWER(CONCAT('%', :searchTerm, '%')) AND u.active = true")
    List<User> searchByName(@Param("searchTerm") String searchTerm);

    // Buscar usuarios con filtros múltiples
    @Query("SELECT u FROM User u WHERE " +
            "(:role IS NULL OR u.role = :role) AND " +
            "(:verified IS NULL OR u.verified = :verified) AND " +
            "u.active = true")
    List<User> findUsersWithFilters(
            @Param("role") User.UserRole role,
            @Param("verified") Boolean verified
    );

    // Contar usuarios por rol
    Long countByRole(User.UserRole role);

    // Contar usuarios verificados
    Long countByVerified(Boolean verified);
}