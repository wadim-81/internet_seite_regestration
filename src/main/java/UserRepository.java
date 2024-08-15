import java.util.HashMap;
import java.util.Map;

public class UserRepository implements UserRepositoryInterface {
    // (Хранилище данных)

    private Map<String, String> users = new HashMap<>();

    public void save(User user) {
        // Сохранение пользователя
        // Убедитесь, что пользователь не существует перед сохранением
        if (exists(user.getUsername())) {
            System.out.println("Пользователь уже существует: " + user.getUsername());
            return; // Не сохраняем, если пользователь уже есть
        }
        // Сохранение пользователя
        users.put(user.getUsername(), user.getPassword());
    }

    @Override
    public boolean exists(String username) {
        // Проверка существования пользователя в хранилище
        return users.containsKey(username);
    }

    @Override
    public boolean validate(String username, String password) {

        // Проверка  сохраненного пароля
        // Здесь используется метод get у Map, чтобы извлечь сохраненный пароль для указанного имени пользователя (username).
        //  Если пользователь с таким именем не существует, storedPassword будет равно null.
        String storedPassword = users.get(username);


        //это условие сравнивает введенный пользователем пароль с сохраненным паролем. Если они совпадают,
        //  это означает, что пользователь ввел правильный пароль.
        boolean isValid = storedPassword != null && password.equals(storedPassword);

        // Логирование результата проверки Это для отладки и мониторинга,
        // так как позволяет видеть, какие пользователи проходят проверку, а какие нет.
        System.out.println("Проверка пользователя: " + username + ", результат: " + isValid);
        return isValid;

    }
   // этот метод ищет пользователя по имени в карте users и возвращает объект User если пользователь найден,
   // или null если пользователь не найден
    @Override
    public User findByUsername(String username) {
        String password = users.get(username);
        if (password!= null) {
            return new User(username, password);
        } else {
            return null;
    }}}