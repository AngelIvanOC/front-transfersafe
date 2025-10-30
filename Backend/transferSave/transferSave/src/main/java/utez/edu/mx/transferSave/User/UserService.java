package utez.edu.mx.transferSave.User;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utez.edu.mx.*;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    // Obtener todos los usuarios
    @Transactional(readOnly = true)
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Obtener usuarios activos
    @Transactional(readOnly = true)
    public List<User> getActiveUsers() {
        return userRepository.findByActive(true);
    }

    // Obtener usuario por ID
    @Transactional(readOnly = true)
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    // Obtener usuario por email
    @Transactional(readOnly = true)
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    // Obtener usuario por clave pública de Stellar
    @Transactional(readOnly = true)
    public Optional<User> getUserByStellarKey(String stellarPublicKey) {
        return userRepository.findByStellarPublicKey(stellarPublicKey);
    }

    // Crear usuario (registro)
    @Transactional
    public User createUser(User user) {
        // Verificar si el email ya existe
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("El email ya está registrado");
        }

        // Verificar si la clave de Stellar ya existe (si se proporciona)
        if (user.getStellarPublicKey() != null &&
                !user.getStellarPublicKey().isEmpty() &&
                userRepository.existsByStellarPublicKey(user.getStellarPublicKey())) {
            throw new RuntimeException("La clave pública de Stellar ya está registrada");
        }

        // TODO: Aquí deberías encriptar la contraseña con BCrypt
        // user.setPassword(passwordEncoder.encode(user.getPassword()));

        user.setActive(true);
        user.setVerified(false);
        user.setRole(User.UserRole.USER);

        return userRepository.save(user);
    }

    // Actualizar usuario
    @Transactional
    public User updateUser(Long id, User userDetails) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con id: " + id));

        // Actualizar campos
        user.setFullName(userDetails.getFullName());
        user.setPhoneNumber(userDetails.getPhoneNumber());

        // Solo actualizar email si es diferente y no existe
        if (!user.getEmail().equals(userDetails.getEmail())) {
            if (userRepository.existsByEmail(userDetails.getEmail())) {
                throw new RuntimeException("El email ya está en uso");
            }
            user.setEmail(userDetails.getEmail());
        }

        // Solo actualizar clave Stellar si es diferente y no existe
        if (userDetails.getStellarPublicKey() != null &&
                !userDetails.getStellarPublicKey().equals(user.getStellarPublicKey())) {
            if (userRepository.existsByStellarPublicKey(userDetails.getStellarPublicKey())) {
                throw new RuntimeException("La clave pública de Stellar ya está en uso");
            }
            user.setStellarPublicKey(userDetails.getStellarPublicKey());
        }

        return userRepository.save(user);
    }

    // Cambiar contraseña
    @Transactional
    public User changePassword(Long id, String newPassword) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con id: " + id));

        // TODO: Encriptar contraseña
        // user.setPassword(passwordEncoder.encode(newPassword));
        user.setPassword(newPassword);

        return userRepository.save(user);
    }

    // Eliminar usuario (soft delete)
    @Transactional
    public void deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con id: " + id));

        user.setActive(false);
        userRepository.save(user);
    }

    // Eliminar usuario permanentemente
    @Transactional
    public void deleteUserPermanently(Long id) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("Usuario no encontrado con id: " + id);
        }
        userRepository.deleteById(id);
    }

    // Verificar usuario
    @Transactional
    public User verifyUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con id: " + id));

        user.setVerified(true);
        return userRepository.save(user);
    }

    // Cambiar rol de usuario
    @Transactional
    public User changeUserRole(Long id, User.UserRole newRole) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con id: " + id));

        user.setRole(newRole);
        return userRepository.save(user);
    }

    // Buscar usuarios por rol
    @Transactional(readOnly = true)
    public List<User> getUsersByRole(User.UserRole role) {
        return userRepository.findByRoleAndActive(role, true);
    }

    // Buscar usuarios verificados
    @Transactional(readOnly = true)
    public List<User> getVerifiedUsers() {
        return userRepository.findByVerified(true);
    }

    // Buscar usuarios por nombre
    @Transactional(readOnly = true)
    public List<User> searchUsers(String searchTerm) {
        return userRepository.searchByName(searchTerm);
    }

    // Buscar con filtros múltiples
    @Transactional(readOnly = true)
    public List<User> searchWithFilters(User.UserRole role, Boolean verified) {
        return userRepository.findUsersWithFilters(role, verified);
    }

    // Vincular clave de Stellar
    @Transactional
    public User linkStellarAccount(Long id, String stellarPublicKey) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con id: " + id));

        if (userRepository.existsByStellarPublicKey(stellarPublicKey)) {
            throw new RuntimeException("Esta clave pública de Stellar ya está vinculada a otra cuenta");
        }

        user.setStellarPublicKey(stellarPublicKey);
        return userRepository.save(user);
    }

    // Validar credenciales (para login básico)
    @Transactional(readOnly = true)
    public Optional<User> validateCredentials(String email, String password) {
        Optional<User> user = userRepository.findByEmail(email);

        // TODO: Aquí deberías validar con BCrypt
        // if (user.isPresent() && passwordEncoder.matches(password, user.get().getPassword())) {
        if (user.isPresent() && user.get().getPassword().equals(password)) {
            if (!user.get().getActive()) {
                throw new RuntimeException("La cuenta está desactivada");
            }
            return user;
        }

        return Optional.empty();
    }
}