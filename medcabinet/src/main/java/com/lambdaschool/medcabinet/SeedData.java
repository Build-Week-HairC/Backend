package com.lambdaschool.medcabinet;

import com.github.javafaker.Faker;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;
import com.lambdaschool.medcabinet.models.*;
import com.lambdaschool.medcabinet.services.RoleService;
import com.lambdaschool.medcabinet.services.StrainService;
import com.lambdaschool.medcabinet.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

// comment out these annotations in production if you don't want seed data to be loaded
//@Transactional
//@Component
public class SeedData implements CommandLineRunner
{
    @Autowired
    RoleService roleService;

    @Autowired
    UserService userService;

    @Autowired
    StrainService strainService;


    @Override
    public void run(String[] args) throws Exception
    {
        Role r1 = new Role("admin");
        Role r2 = new Role("user");
//        Role r3 = new Role("data");

        roleService.save(r1);
        roleService.save(r2);
//        roleService.save(r3);

        // admin, data, user
        // don't reuse these arrays - recreate every time
        ArrayList<UserRoles> admins = new ArrayList<>();
        admins.add(new UserRoles(new User(),
                                 r1));
        admins.add(new UserRoles(new User(),
                                 r2));
//        admins.add(new UserRoles(new User(),
//                                 r3));

        Strain s1 = new Strain("100-Og", "hybrid", 4.0, "$100 OG is a 50/50 hybrid strain that packs a strong punch. The name supposedly refers to both its strength and high price when it first started showing up in Hollywood.");
        Strain s2 = new Strain("98-White-Widow", "hybrid", 4.7, "The ‘98 Aloha White Widow is an especially potent cut of White Widow that has grown in renown alongside Hawaiian legends like Maui Wowie and Kona Gold.");
        Strain s3 = new Strain("1024", "sativa", 4.4, "1024 is a sativa-dominant hybrid bred in Spain by Medical Seeds Co. The breeders claim to guard the secret genetics due to security reasons, but regardless of its genetic heritage, 1024 is a THC power");

        List<Strain> u1Strains = new ArrayList<>();
        u1Strains.add(s2);
        u1Strains.add(s3);

        User u1 = new User("admin",
                           "password",
                           "admin@lambdaschool.local",
                           admins);
        u1.getUseremails()
          .add(new Useremail(u1,
                             "admin@email.local"));
        u1.getUseremails()
          .add(new Useremail(u1,
                             "admin@mymail.local"));

        u1.setStrains(u1Strains);

        System.out.println("\n\n" + u1 + "\n\n");

        userService.save(u1);

        strainService.save(s1);
        strainService.save(s2);
        strainService.save(s3);

        // data, user
//        ArrayList<UserRoles> datas = new ArrayList<>();
//        datas.add(new UserRoles(new User(),
//                                r3));
//        datas.add(new UserRoles(new User(),
//                                r2));
//        User u2 = new User("cinnamon",
//                           "1234567",
//                           "cinnamon@lambdaschool.local",
//                           datas);
//        u2.getUseremails()
//          .add(new Useremail(u2,
//                             "cinnamon@mymail.local"));
//        u2.getUseremails()
//          .add(new Useremail(u2,
//                             "hops@mymail.local"));
//        u2.getUseremails()
//          .add(new Useremail(u2,
//                             "bunny@email.local"));
//        userService.save(u2);

        // user
//        ArrayList<UserRoles> users = new ArrayList<>();
//        users.add(new UserRoles(new User(),
//                                r2));
//        User u3 = new User("barnbarn",
//                           "ILuvM4th!",
//                           "barnbarn@lambdaschool.local",
//                           users);
//        u3.getUseremails()
//          .add(new Useremail(u3,
//                             "barnbarn@email.local"));
//        userService.save(u3);
//
//        users = new ArrayList<>();
//        users.add(new UserRoles(new User(),
//                                r2));
//        User u4 = new User("puttat",
//                           "password",
//                           "puttat@school.lambda",
//                           users);
//        userService.save(u4);
//
//        users = new ArrayList<>();
//        users.add(new UserRoles(new User(),
//                                r2));
//        User u5 = new User("misskitty",
//                           "password",
//                           "misskitty@school.lambda",
//                           users);
//        userService.save(u5);

        // using JavaFaker create a bunch of regular users
        // https://www.baeldung.com/java-faker
        // https://www.baeldung.com/regular-expressions-java

//        FakeValuesService fakeValuesService = new FakeValuesService(new Locale("en-US"),
//                                                                    new RandomService());
//        Faker nameFaker = new Faker(new Locale("en-US"));
//
//        for (int i = 0; i < 100; i++)
//        {
//            new User();
//            User fakeUser;
//
//            users = new ArrayList<>();
//            users.add(new UserRoles(new User(),
//                                    r2));
//            fakeUser = new User(nameFaker.name()
//                                         .username(),
//                                "password",
//                                nameFaker.internet()
//                                         .emailAddress(),
//                                users);
//            fakeUser.getUseremails()
//                    .add(new Useremail(fakeUser,
//                                       fakeValuesService.bothify("????##@gmail.com")));
//            userService.save(fakeUser);
//        }
    }
}