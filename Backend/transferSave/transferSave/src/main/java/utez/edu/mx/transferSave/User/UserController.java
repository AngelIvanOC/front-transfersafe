package utez.edu.mx.transferSave.User;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class UserController {

    private final UserService userService;

    // Obtener todos los usuarios
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    // Obtener usuarios activos
    @GetMapping("/active")
    public ResponseEntity<List<User>> getActiveUsers() {
        return ResponseEntity.ok(userService.getActiveUsers());
    }

    // Obtener usuario por ID
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        return userService.getUserById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Obtener usuario por email
    @GetMapping("/email/{email}")
    public ResponseEntity<User> getUserByEmail(@PathVariable String email) {
        return userService.getUserByEmail(email)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Obtener usuario por clave de Stellar
    @GetMapping("/stellar/{stellarKey}")
    public ResponseEntity<User> getUserByStellarKey(@PathVariable String stellarKey) {
        return userService.getUserByStellarKey(stellarKey)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Registrar usuario
    @PostMapping("/register")
    public ResponseEntity<?> registerUser( @RequestBody User user) {
        try {
            User createdUser = userService.createUser(user);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
        } catch (RuntimeException e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }

    // Login básico (validación de credenciales)
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> credentials) {
        try {
            String email = credentials.get("email");
            String password = credentials.get("password");

            return userService.validateCredentials(email, password)
                    .map(user -> {
                        Map<String, Object> response = new HashMap<>();
                        response.put("message", "Login exitoso");
                        response.put("user", user);
                        return ResponseEntity.ok((Object) response);
                    })
                    .orElseGet(() -> {
                        Map<String, String> error = new HashMap<>();
                        error.put("error", "Credenciales inválidas");
                        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body((Object) error);
                    });
        } catch (RuntimeException e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }


    // Actualizar usuario
    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(
            @PathVariable Long id,
            @RequestBody User userDetails) {
        try {
            User updatedUser = userService.updateUser(id, userDetails);
            return ResponseEntity.ok(updatedUser);
        } catch (RuntimeException e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }

    // Cambiar contraseña
    @PatchMapping("/{id}/password")
    public ResponseEntity<?> changePassword(
            @PathVariable Long id,
            @RequestBody Map<String, String> passwordData) {
        try {
            String newPassword = passwordData.get("newPassword");
            User updatedUser = userService.changePassword(id, newPassword);

            Map<String, String> response = new HashMap<>();
            response.put("message", "Contraseña actualizada exitosamente");
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }

    // Eliminar usuario (soft delete)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        try {
            userService.deleteUser(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Eliminar permanentemente
    @DeleteMapping("/{id}/permanent")
    public ResponseEntity<Void> deleteUserPermanently(@PathVariable Long id) {
        try {
            userService.deleteUserPermanently(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Verificar usuario
    @PatchMapping("/{id}/verify")
    public ResponseEntity<?> verifyUser(@PathVariable Long id) {
        try {
            User verifiedUser = userService.verifyUser(id);
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Usuario verificado exitosamente");
            response.put("user", verifiedUser);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }
    }

    // Cambiar rol de usuario
    @PatchMapping("/{id}/role")
    public ResponseEntity<?> changeUserRole(
            @PathVariable Long id,
            @RequestParam User.UserRole role) {
        try {
            User updatedUser = userService.changeUserRole(id, role);
            return ResponseEntity.ok(updatedUser);
        } catch (RuntimeException e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }

    // Buscar usuarios por rol
    @GetMapping("/role/{role}")
    public ResponseEntity<List<User>> getUsersByRole(@PathVariable User.UserRole role) {
        return ResponseEntity.ok(userService.getUsersByRole(role));
    }

    // Buscar usuarios verificados
    @GetMapping("/verified")
    public ResponseEntity<List<User>> getVerifiedUsers() {
        return ResponseEntity.ok(userService.getVerifiedUsers());
    }

    // Buscar usuarios por nombre
    @GetMapping("/search")
    public ResponseEntity<List<User>> searchUsers(@RequestParam String query) {
        return ResponseEntity.ok(userService.searchUsers(query));
    }

    // Búsqueda con filtros múltiples
    @GetMapping("/filter")
    public ResponseEntity<List<User>> searchWithFilters(
            @RequestParam(required = false) User.UserRole role,
            @RequestParam(required = false) Boolean verified) {
        return ResponseEntity.ok(userService.searchWithFilters(role, verified));
    }

    // Vincular cuenta de Stellar
    @PatchMapping("/{id}/stellar")
    public ResponseEntity<?> linkStellarAccount(
            @PathVariable Long id,
            @RequestBody Map<String, String> stellarData) {
        try {
            String stellarPublicKey = stellarData.get("stellarPublicKey");
            User updatedUser = userService.linkStellarAccount(id, stellarPublicKey);

            Map<String, Object> response = new HashMap<>();
            response.put("message", "Cuenta de Stellar vinculada exitosamente");
            response.put("user", updatedUser);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }
}
