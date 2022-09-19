package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.List;

public class Main {
    private static final UserService userService = new UserServiceImpl();
    public static void main(String[] args) {

        userService.createUsersTable();
        userService.saveUser("Vasya", "Pupkin", (byte) 38);
        userService.saveUser("Petr", "Petrov", (byte) 32);
        userService.saveUser("Victor", "Smirnov", (byte) 60);
        userService.saveUser("Maxim", "Sidorov", (byte) 55);
        userService.removeUserById(2L);
        List<User> userList = userService.getAllUsers();
        for (User us : userList) {
            System.out.println(us.getName() + " " + us.getLastName() + " " + us.getAge());
        }
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
