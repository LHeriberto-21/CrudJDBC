package org.heriberto.app;

import org.heriberto.app.models.User;
import org.heriberto.app.repository.implementations.UserRepository;
import org.heriberto.app.repository.interfaces.IUserRepository;

import java.util.Scanner;

public class ConnectionJDBC_Read {
    private static final Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {

        IUserRepository<User> userRepository = new UserRepository();

        System.out.println("============= Obteniendo a todos los usuarios =============");
        userRepository.allUser().forEach(System.out::println);

        System.out.println("============= Obteniendo al usuario por el ID =============");
        System.out.println("Escribe el numero para buscar al usuario: ");
        Long getUserById = sc.nextLong();
        System.out.println("Este es el usuario: "+ userRepository.byId(getUserById));

    }
}