package org.heriberto.app.repository.interfaces;

import org.heriberto.app.models.User;

import java.util.List;

public interface IUserRepository<T> {
    List<T> allUser();
    T byId(Long id);
    void createUser(T t);
    void remove(Long id);

}
