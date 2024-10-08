

import com.google.common.hash.Hashing;

import java.nio.charset.StandardCharsets;

public class UserService implements UserServiceInterface {

    // Business Layer (Логика приложения)

    private UserRepositoryInterface userRepository;

    public UserService(UserRepositoryInterface userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void register(String username, String password) {
        // Проверка существования пользователя
        if (userRepository.exists(username)) {
            throw new RuntimeException("Пользователь уже существует");
        }

        // Проверка требований к паролю
        if (!isValidPassword(password)) {
            throw new RuntimeException("Пароль не соответствует требованиям.\nПароль должен содержать (символ, цифру, заглавную букву).");
        }

        // Шифрование пароля
        String encryptedPassword = encryptPassword(password);
        userRepository.save(new User(username, encryptedPassword));
        System.out.println("Регистрация успешна.");
    }

    public void login(String username, String password) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            System.out.println("Неверное имя пользователя или пароль.");
            return;
        }
        String encryptedPassword = user.getPassword();
        if (encryptedPassword.equals(encryptPassword(password))) {
            System.out.println("Авторизация успешна.");
        } else {
            System.out.println("Неверное имя пользователя или пароль.");
        }
    }

    private boolean isValidPassword(String password) {
        // Проверка на наличие хотя бы одной цифры, одного специального символа и одной заглавной буквы
        return password.length() >= 6
                && password.matches(".*\\d.*") // Наличие цифры
                && password.matches(".*[!@#$%^&*].*") // Наличие специального символа
                && password.matches(".*[A-Z].*"); // Наличие заглавной буквы
    }

    private String encryptPassword(String password) {
        String sha256hex = Hashing.sha256()
                .hashString(password, StandardCharsets.UTF_8)
                .toString();
        return sha256hex;
    }}