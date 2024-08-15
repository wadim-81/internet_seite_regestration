import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        UserRepositoryInterface userRepository =new UserRepository();
        UserService userService = new UserService(userRepository); // Создаем экземпляр UserService
        UserRegistrationApp userRegistrationApp = new UserRegistrationApp(userService);


        Scanner scanner = new Scanner(System.in);
        userRegistrationApp.start(scanner);


        scanner.close();
    }
    }

