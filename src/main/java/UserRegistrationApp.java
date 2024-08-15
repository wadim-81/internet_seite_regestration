import java.util.Scanner;

public class UserRegistrationApp {
    private UserServiceInterface userService;

    public UserRegistrationApp(UserServiceInterface userService) {
        this.userService = userService;

    }
    public void start(Scanner scanner) {
        try {
            System.out.println("1. Регистрация\n2. Авторизация");

            int choice = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Введите имя пользователя: ");
            String username = scanner.nextLine();
            System.out.print("Введите пароль: ");
            String password = scanner.nextLine();

            if (choice == 1) {
                // Регистрация
                userService.register(username, password);
            } else if (choice == 2) {
                // Авторизация
                userService.login(username, password);
            }
        } catch (Exception e) {
            System.out.println("Произошла ошибка: " + e.getMessage());

        }
    }}