package org.heriberto.app;

import org.heriberto.app.data.ContextDB;
import org.heriberto.app.models.User;
import org.heriberto.app.repository.implementations.UserRepository;
import org.heriberto.app.repository.interfaces.IUserRepository;

import javax.swing.*;
import java.sql.*;
import java.util.*;

public class ConnectionJDBC_Full {
    public static void main(String[] args) {

        try(Connection connection = ContextDB.getConnection()){
            IUserRepository<User> userRepository = new UserRepository();
            int indexOption = 0;

            do {
                Map<String, Integer> options = new HashMap<>();
                options.put("Create", 1);
                options.put("Read", 2);
                options.put("Update", 3);
                options.put("Delete", 4);
                options.put("Exit", 5);

                Object[] arrayOptions = options.keySet().toArray();
                Object opt = JOptionPane.showInputDialog(null,
                        "Seleccione una operación", "Mantenedor usuario",
                        JOptionPane.INFORMATION_MESSAGE, null,
                        arrayOptions, arrayOptions[0]);

                if( opt== null){
                    JOptionPane.showMessageDialog(null, "Debe insertar una operación valida");
                } else {
                    indexOption = options.get(opt.toString());

                    Long id;
                    String username, password, email;

                    switch (indexOption){
                        // create
                        case 1 -> {
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
                        // read
                        case 2 -> userRepository.allUser().forEach(user -> System.out.println(" -> " + user));
                        // update
                        case 3 -> {
                            id = Long.valueOf(JOptionPane.showInputDialog(null, "Ingrese el id del usuario para actualizar: "));

                            User user = userRepository.byId(id);
                            if(user != null){
                                username = JOptionPane.showInputDialog(null, "Ingrese el username: " , user.getUsername());
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
                        case  4 -> {
                            id = Long.valueOf(JOptionPane.showInputDialog(null, "Ingrese el id para eliminar al usuario: "));
                            userRepository.remove(id);
                            JOptionPane.showMessageDialog(null, "Usuario eliminado con éxito...!!!");
                        }

                    }

                }
            }  while (indexOption != 5);
        } catch (SQLException sql){
            throw new RuntimeException(sql.getMessage());
        }
    }

}
