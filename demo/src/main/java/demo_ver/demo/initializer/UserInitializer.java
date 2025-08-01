package demo_ver.demo.initializer;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import demo_ver.demo.model.ManageUser;
import demo_ver.demo.service.ManageUserService;

@Component
public class UserInitializer implements CommandLineRunner {

    private final ManageUserService manageUserService;

    public UserInitializer(ManageUserService manageUserService) {
        this.manageUserService = manageUserService;
    }

    @Override
    public void run(String... args) {
        // Add default users
        addUserIfNotExists("Admin", "hacttadmin@graduate.utm.my", 1001);   // Admin
        addUserIfNotExists("Manager", "haviineesh@graduate.utm.my", 1003);         // Project Manager
        addUserIfNotExists("Tester", "haviineesh@gmail.com", 1002);         // Tester
        addUserIfNotExists("Developer", "hyperagilectt@gmail.com", 1004); // Developer
        addUserIfNotExists("tester2", "tester@graduate.utm.my", 1002);     // Tester
    }

    private void addUserIfNotExists(String username, String email, int roleID) {
        String password = "123456";

        boolean exists = manageUserService
                .getAllUsers()
                .stream()
                .anyMatch(u -> u.getUsername().equalsIgnoreCase(username));

        if (!exists) {
            ManageUser newUser = new ManageUser();
            newUser.setEmail(email);
            newUser.setUsername(username);
            newUser.setPassword(password); // It will be encoded inside addUser
            manageUserService.addUser(newUser, roleID);
            System.out.println("User " + username + " added.");
        } else {
            System.out.println("User " + username + " already exists.");
        }
    }
}
