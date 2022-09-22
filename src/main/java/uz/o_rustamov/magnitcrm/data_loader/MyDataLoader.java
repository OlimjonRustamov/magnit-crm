package uz.o_rustamov.magnitcrm.data_loader;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import uz.o_rustamov.magnitcrm.entity.Recipient;
import uz.o_rustamov.magnitcrm.entity.Role;
import uz.o_rustamov.magnitcrm.entity.Supplier;
import uz.o_rustamov.magnitcrm.entity.User;
import uz.o_rustamov.magnitcrm.enums.Permission;
import uz.o_rustamov.magnitcrm.repository.RecipientRepository;
import uz.o_rustamov.magnitcrm.repository.RoleRepository;
import uz.o_rustamov.magnitcrm.repository.SupplierRepository;
import uz.o_rustamov.magnitcrm.repository.UserRepository;

import java.util.Arrays;
import java.util.Collections;

import static uz.o_rustamov.magnitcrm.Constants.*;
import static uz.o_rustamov.magnitcrm.enums.Permission.*;

@Component
public class MyDataLoader implements CommandLineRunner {

    UserRepository userRepository;
    RecipientRepository recipientRepository;
    SupplierRepository supplierRepository;
    RoleRepository roleRepository;
    PasswordEncoder passwordEncoder;

    public MyDataLoader(UserRepository userRepository, RecipientRepository recipientRepository, SupplierRepository supplierRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.recipientRepository = recipientRepository;
        this.supplierRepository = supplierRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        insertDeveloper();

        insertManager();

        insertDriver();

        insertMarketStaff();

        insertSuppliers();
        insertRecipients();
    }

    private void insertRecipients() {
        if (!recipientRepository.existsByName("Bahrom aka Shofirkon")) {
            recipientRepository.save(new Recipient("Bahrom aka Shofirkon"));
        }
    }

    private void insertSuppliers() {
        if (!supplierRepository.existsByName("Royal Muz Service")) {
            supplierRepository.save(new Supplier("Royal Muz Service"));
        }
        if (!supplierRepository.existsByName("Mazira MChJ")) {
            supplierRepository.save(new Supplier("Mazira MChJ"));
        }
        if (!supplierRepository.existsByName("Singaur Dairy Classic")) {
            supplierRepository.save(new Supplier("Singaur Dairy Classic"));
        }
        if (!supplierRepository.existsByName("Ulug'bek aka(Buxoro)")) {
            supplierRepository.save(new Supplier("Ulug'bek aka(Buxoro)"));
        }
        if (!supplierRepository.existsByName("Slayke Samarqand")) {
            supplierRepository.save(new Supplier("Slayke Samarqand"));
        }
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
            if (!userRepository.existsByUsername(anvar_ziyodullayev)) {
                User userManager = new User();
                userManager.setUsername(anvar_ziyodullayev);
                userManager.setFullName("Anvar Ziyodullayev");
                userManager.setPassword(passwordEncoder.encode(anvar_ziyodullayev));
                userManager.setPhoneNumber("+998919736688");
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
                User userAsqarAka = new User();
                userAsqarAka.setUsername(asqar_akramov);
                userAsqarAka.setFullName("Asqar Akramov");
                userAsqarAka.setPhoneNumber("+998907111485");
                userAsqarAka.setPassword(passwordEncoder.encode(asqar_akramov));
                userAsqarAka.setRole(driver);
                userAsqarAka.setEnabled(true);
                userAsqarAka = userRepository.save(userAsqarAka);
                recipientRepository.save(new Recipient("Asqar aka", userAsqarAka));
            }
            if (!userRepository.existsByUsername(bahriddin_umarov)) {
                User userBarisTogo = new User();
                userBarisTogo.setUsername(bahriddin_umarov);
                userBarisTogo.setFullName("Bahriddin Umarov");
                userBarisTogo.setPhoneNumber("+998973095088");
                userBarisTogo.setPassword(passwordEncoder.encode(bahriddin_umarov));
                userBarisTogo.setRole(driver);
                userBarisTogo.setEnabled(true);
                userBarisTogo = userRepository.save(userBarisTogo);

                recipientRepository.save(new Recipient("Baris tog'o", userBarisTogo));
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
