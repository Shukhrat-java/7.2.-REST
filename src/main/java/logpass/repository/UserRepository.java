package logpass.repository;

import logpass.model.Authorities;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class UserRepository {
    
    // заменяем хардкод
    private final Map<String, UserCredentials> userDatabase;
    
    public UserRepository() {
        this.userDatabase = initializeUserDatabase();
    }
    
    public List<Authorities> getUserAuthorities(String user, String password) {
        // Проверяем существование пользователя
        if (userDatabase.containsKey(user)) {
            UserCredentials credentials = userDatabase.get(user);
            if (credentials.getPassword().equals(password)) {
                return credentials.getAuthorities();
            }
        }
        return Collections.emptyList(); // Пустой список для неверных данных
    }
    
    // базы данных пользователей
    private Map<String, UserCredentials> initializeUserDatabase() {
        Map<String, UserCredentials> database = new HashMap<>();
        
        // права
        database.put("admin", new UserCredentials("admin123", 
            Arrays.asList(Authorities.READ, Authorities.WRITE, Authorities.DELETE)));
        
        database.put("user", new UserCredentials("user123",
            Arrays.asList(Authorities.READ, Authorities.WRITE)));
            
        database.put("guest", new UserCredentials("guest123",
            Arrays.asList(Authorities.READ)));
        
        // +пользователи
        database.put("manager", new UserCredentials("manager123",
            Arrays.asList(Authorities.READ, Authorities.WRITE)));
            
        database.put("viewer", new UserCredentials("viewer123",
            Arrays.asList(Authorities.READ)));
        
        return Collections.unmodifiableMap(database); // блок от изменений
    }
    
    // Вспомогательный класс для хранения данных
    private static class UserCredentials {
        private final String password;
        private final List<Authorities> authorities;
        
        public UserCredentials(String password, List<Authorities> authorities) {
            this.password = password;
            this.authorities = Collections.unmodifiableList(new ArrayList<>(authorities));
        }
        
        public String getPassword() {
            return password;
        }
        
        public List<Authorities> getAuthorities() {
            return authorities;
        }
    }
}
