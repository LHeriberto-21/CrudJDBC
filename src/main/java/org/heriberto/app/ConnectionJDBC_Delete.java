package org.heriberto.app;

import org.heriberto.app.models.User;
import org.heriberto.app.repository.implementations.UserRepository;
import org.heriberto.app.repository.interfaces.IUserRepository;

import java.util.Scanner;

public class ConnectionJDBC_Delete {
    private static final Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {

        IUserRepository<User> userRepository = new UserRepository();

        System.out.println("============= Obteniendo a todos los usuarios =============");
        userRepository.allUser().forEach(u -> System.out.println(" -> " + u));

        System.out.println("============= Obteniendo al usuario por el ID =============");
        System.out.println("Escribe el numero para buscar al usuario: ");
        Long getUserById = sc.nextLong();
        System.out.println(userRepository.byId(getUserById));

        System.out.println("============= Crear o guardar al usuario =============");
        System.out.println("Escribe el numero para eliminar al usuario: ");
        Long removeUser = sc.nextLong();
        userRepository.remove(removeUser);

        System.out.println("============= Todos los usuarios =============");
        userRepository.allUser().forEach(System.out::println);

    }
}