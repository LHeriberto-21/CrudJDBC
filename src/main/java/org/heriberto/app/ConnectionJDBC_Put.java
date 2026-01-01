package org.heriberto.app;

import org.heriberto.app.models.User;
import org.heriberto.app.repository.implementations.UserRepository;
import org.heriberto.app.repository.interfaces.IUserRepository;

import java.util.Scanner;

public class ConnectionJDBC_Put {
    private static final Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {

        IUserRepository<User> userRepository = new UserRepository();
        User user = new User();

        System.out.println("============= Crear o guardar al usuario =============");
        System.out.println("Escriba un nombre de usuario: ");
        String username = sc.nextLine();
        user.setUsername(username);
        System.out.println("Escriba el correo para su usuario: ");
        String email = sc.nextLine();
        user.setEmail(email);
        System.out.println("Escriba la contraseña para su cuenta: ");
        String password = sc.nextLine();
        user.setPassword(password);
        userRepository.createUser(user);

        System.out.println("============= Obteniendo a todos los usuario =============");
        userRepository.allUser().forEach(System.out::println);

    }
}