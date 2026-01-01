package org.heriberto.app.options;

import org.heriberto.app.models.User;
import org.heriberto.app.repository.implementations.UserRepository;
import org.heriberto.app.repository.interfaces.IUserRepository;

import javax.swing.*;
import java.util.List;

public class Options {

    private static final IUserRepository<User> userRepository = new UserRepository();

    public static void createUser(Long indexOption) {
        String username, password, email;
        // create
        if (indexOption == 1) {
            username = JOptionPane.showInputDialog(null, "Ingrese el username para un nuevo usuario: ");
            email = JOptionPane.showInputDialog(null, "Ingrese algún correo electrónico: ");
            password = JOptionPane.showInputDialog(null, "Ingrese una contraseña: ");

            User user = new User();
            user.setUsername(username);
            user.setEmail(email);
            user.setPassword(password);

            userRepository.createUser(user);
            JOptionPane.showMessageDialog(null, "El usuario se creó con éxito!...");
        }
    }

    public static void allUsers() {
        List<User> userList = userRepository.allUser();
        StringBuilder sb = new StringBuilder("Usuarios en la base de datos: \n");
        for (User user : userList) {
            sb.append(">> ").append(user).append("\n");
        }
        JOptionPane.showMessageDialog(null, sb.toString(), "Lista de usuarios: ", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void saveUser(Long id) {
        id = Long.valueOf(JOptionPane.showInputDialog(null, "Ingrese el id del usuario para actualizar: "));
        String username, email, password;
        User user = userRepository.byId(id);

        if (user != null) {
            username = JOptionPane.showInputDialog(null, "Ingrese el username: ", user.getUsername());
            email = JOptionPane.showInputDialog(null, "Ingrese un correo electrónico: ", user.getEmail());
            password = JOptionPane.showInputDialog(null, "Ingrese una contraseña: ", user.getPassword());
            user.setUsername(username);
            user.setEmail(email);
            user.setPassword(password);

            userRepository.createUser(user);

            JOptionPane.showMessageDialog(null, "Usuario actualizado correctamente...!!!");
        } else {
            JOptionPane.showMessageDialog(null, "El id que colocó no existe en la base de datos...!");
        }
    }

    public  static void delete(Long id) {
        id = Long.valueOf(JOptionPane.showInputDialog(null, "Id para eliminar al usuario: "));
        userRepository.remove(id);
        JOptionPane.showMessageDialog(null, "Usuario eliminado con éxito...!");

    }
}
