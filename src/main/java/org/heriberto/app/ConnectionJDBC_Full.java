package org.heriberto.app;

import org.heriberto.app.options.Options;

import javax.swing.*;
import java.util.*;

public class ConnectionJDBC_Full {
    private static final Map<String, Object> options = new HashMap<>();
    public static void main(String[] args) {
        int indexOption = 0;

        options.put("Create", 1);
        options.put("Read", 2);
        options.put("Update", 3);
        options.put("Delete", 4);
        options.put("Exit", 5);
        while (indexOption != 5){
            Object[] arrayOptions = options.keySet().toArray();
            Object opt = JOptionPane.showInputDialog(null,
                    "Seleccione una operación", "Mantenedor usuario",
                    JOptionPane.INFORMATION_MESSAGE, null,
                    arrayOptions, arrayOptions[0]);
            if (opt == null) {
                JOptionPane.showMessageDialog(null, "Debe insertar una operación valida");
            } else {
                Long id = 0L;
                indexOption = (Integer) options.get(opt.toString());
                switch (indexOption) {
                    case 1 -> Options.createUser(id);
                    case 2 -> Options.allUsers();
                    case 3 -> Options.saveUser(id);
                    case 4 -> Options.delete(id);
                    case 5 -> JOptionPane.showMessageDialog(null, "Bye");
                }
            }
        }
    }
}
