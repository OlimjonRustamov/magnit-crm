package uz.o_rustamov.magnitcrm.data_loader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import uz.o_rustamov.magnitcrm.entity.Role;
import uz.o_rustamov.magnitcrm.entity.User;
import uz.o_rustamov.magnitcrm.enums.Permission;
import uz.o_rustamov.magnitcrm.repository.RoleRepository;
import uz.o_rustamov.magnitcrm.repository.UserRepository;

import java.util.Arrays;
import java.util.Collections;

import static uz.o_rustamov.magnitcrm.Constants.*;
import static uz.o_rustamov.magnitcrm.enums.Permission.*;

@Component
public class MyDataLoader implements CommandLineRunner {

    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        insertDeveloper();

        insertManager();

        insertDriver();

        insertMarketStaff();
    }

    private void insertDeveloper() {
        //if developer role does not exist -> system will create it automatically
        if (!roleRepository.existsByName(ROLE_DEVELOPER)) {
            Role developer = new Role();
            developer.setName(ROLE_DEVELOPER);
            //developer have all rights
            developer.setPermissionList(Arrays.asList(Permission.values()));
            developer = roleRepository.save(developer);
            if (!userRepository.existsByUsername(olimjon_rustamov)) {
                User userDeveloper = new User();
                userDeveloper.setUsername(olimjon_rustamov);
                userDeveloper.setFullName("Olimjon Rustamov");
                userDeveloper.setPhoneNumber("+998900123477");
                userDeveloper.setPassword(passwordEncoder.encode(olimjon_rustamov));
                userDeveloper.setRole(developer);
                userDeveloper.setEnabled(true);
                userDeveloper = userRepository.save(userDeveloper);
            }
        }
    }

    private void insertManager() {
        //if manager role does not exist -> system will create it automatically
        if (!roleRepository.existsByName(ROLE_MANAGER)) {
            Role manager = new Role();
            manager.setName(ROLE_MANAGER);
            manager.setPermissionList(Arrays.asList(ADD_INPUT, VIEW_INPUTS, EDIT_INPUT,
                    ADD_SUPPLIER, VIEW_SUPPLIERS, ADD_PRODUCT, DELETE_PRODUCT, EDIT_PRODUCT, VIEW_PRODUCTS,
                    ADD_RECIPIENT, EDIT_RECIPIENT, VIEW_RECIPIENTS, VIEW_OUTPUTS, ADD_OUTPUT, EDIT_OUTPUT));
            manager = roleRepository.save(manager);
            if (!userRepository.existsByUsername(oqil_akramov)) {
                User userManager = new User();
                userManager.setUsername(oqil_akramov);
                userManager.setFullName("Oqil Akramov");
                userManager.setPassword(passwordEncoder.encode(oqil_akramov));
                userManager.setPhoneNumber("+998907184488");
                userManager.setRole(manager);
                userManager.setEnabled(true);
                userManager = userRepository.save(userManager);
            }
        }
    }

    private void insertDriver() {
        //if driver role does not exist -> system will create it automatically
        if (!roleRepository.existsByName(ROLE_DRIVER)) {
            Role driver = new Role();
            driver.setName(ROLE_DRIVER);
            //driver can only view own inputs and outputs
            driver.setPermissionList(Collections.singletonList(VIEW_MY_OUTPUTS));
            driver = roleRepository.save(driver);
            if (!userRepository.existsByUsername(asqar_akramov)) {
                User userDriver = new User();
                userDriver.setUsername(asqar_akramov);
                userDriver.setFullName("Asqar Akramov");
                userDriver.setPhoneNumber("+998907111485");
                userDriver.setPassword(passwordEncoder.encode(asqar_akramov));
                userDriver.setRole(driver);
                userDriver.setEnabled(true);
                userDriver = userRepository.save(userDriver);
            }
            if (!userRepository.existsByUsername(bahriddin_umarov)) {
                User userDriver = new User();
                userDriver.setUsername(bahriddin_umarov);
                userDriver.setFullName("Bahriddin Umarov");
                userDriver.setPhoneNumber("+998973095088");
                userDriver.setPassword(passwordEncoder.encode(bahriddin_umarov));
                userDriver.setRole(driver);
                userDriver.setEnabled(true);
                userDriver = userRepository.save(userDriver);
            }
        }
    }

    private void insertMarketStaff() {
        //if market staff role does not exist -> system will create it automatically
        if (!roleRepository.existsByName(ROLE_MARKET_STAFF)) {
            Role marketStaff = new Role();
            marketStaff.setName(ROLE_MARKET_STAFF);
            //market staff can only view own inputs and outputs
            marketStaff.setPermissionList(Arrays.asList(VIEW_INPUTS, VIEW_OUTPUTS, ADD_OUTPUT));
            marketStaff = roleRepository.save(marketStaff);
            if (!userRepository.existsByUsername(odil_akramov)) {
                User userMarketStaff = new User();
                userMarketStaff.setUsername(odil_akramov);
                userMarketStaff.setFullName("Odil Akramov");
                userMarketStaff.setPhoneNumber("+998907104568");
                userMarketStaff.setPassword(passwordEncoder.encode(odil_akramov));
                userMarketStaff.setRole(marketStaff);
                userMarketStaff.setEnabled(true);
                userMarketStaff = userRepository.save(userMarketStaff);
            }
        }
    }
}
