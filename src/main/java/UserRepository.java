import java.util.HashMap;
import java.util.Map;

public class UserRepository implements UserRepositoryInterface {
    // (Хранилище данных)

    private Map<String, String> users = new HashMap<>();

    public void save(User user) {
        // Сохранение пользователя
        users.put(user.getUsername(), user.getPassword());
    }

    @Override
    public boolean exists(String username) {
        // Проверка существования пользователя
        return users.containsKey(username);
    }

    @Override
    public boolean validate(String username, String password) {
        // Проверка соответствия пароля
        String storedPassword = users.get(username);
        return storedPassword != null && password.equals(storedPassword);


    }}