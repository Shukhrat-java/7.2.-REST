package logpass.repository;

import logpass.model.Authorities;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class UserRepository {
    
    // Базы данных пользователей
    public List<Authorities> getUserAuthorities(String user, String password) {
        // Проверка пользователей
        if ("admin".equals(user) && "admin123".equals(password)) {
            return Arrays.asList(Authorities.READ, Authorities.WRITE, Authorities.DELETE);
        } else if ("user".equals(user) && "user123".equals(password)) {
            return Arrays.asList(Authorities.READ, Authorities.WRITE);
        } else if ("guest".equals(user) && "guest123".equals(password)) {
            return Arrays.asList(Authorities.READ);
        } else {
            return new ArrayList<>(); // Пустой список для неизвестных пользователей
        }
    }
}
