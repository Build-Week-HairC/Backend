package com.lambdaschool.medcabinet;

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

@Transactional
@Component
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
        ResStrain s1 = new ResStrain("test1", "hybrid", 4.0, "$100 OG is a 50/50 hybrid strain that packs a strong punch. The name supposedly refers to both its strength and high price when it first started showing up in Hollywood.");
        ResStrain s2 = new ResStrain("test2", "hybrid", 4.7, "The ‘98 Aloha White Widow is an especially potent cut of White Widow that has grown in renown alongside Hawaiian legends like Maui Wowie and Kona Gold.");
        ResStrain s3 = new ResStrain("test3", "sativa", 4.4, "1024 is a sativa-dominant hybrid bred in Spain by Medical Seeds Co. The breeders claim to guard the secret genetics due to security reasons, but regardless of its genetic heritage, 1024 is a THC power");

        s1.getEffects().add("uplifted");
        s1.getEffects().add("euphoric");
        s1.getEffects().add("giggly");
        s1.getFlavors().add("tobacco");
        s1.getFlavors().add("earthy");

        s2.getEffects().add("happy");
        s2.getEffects().add("relaxed");
        s2.getFlavors().add("sweet");
        s2.getFlavors().add("berry");
        s2.getFlavors().add("flowery");

        s3.getEffects().add("aroused");
        s3.getEffects().add("sleepy");
        s3.getEffects().add("hungry");
        s3.getFlavors().add("earthy");
        s3.getFlavors().add("citrus");
        s3.getFlavors().add("pungent");

        Role r1 = new Role("admin");
        Role r2 = new Role("user");

        roleService.save(r1);
        roleService.save(r2);

        // admin, user
        ArrayList<UserRoles> admins = new ArrayList<>();
        admins.add(new UserRoles(new User(), r1));
        admins.add(new UserRoles(new User(), r2));
        User u1 = new User("admin", "password", "admin@lambdaschool.local", admins);
        u1.getUseremails()
          .add(new Useremail(u1, "admin@email.local"));
        u1.getUseremails()
          .add(new Useremail(u1, "admin@mymail.local"));
        u1 = userService.save(u1);

        // user
        ArrayList<UserRoles> datas = new ArrayList<>();
        datas.add(new UserRoles(new User(), r2));
        User u2 = new User("testmon", "password", "cinnamon@lambdaschool.local", datas);
        u2.getUseremails()
          .add(new Useremail(u2, "cinnamon@mymail.local"));
        u2.getUseremails()
          .add(new Useremail(u2, "hops@mymail.local"));
        u2.getUseremails()
          .add(new Useremail(u2, "bunny@email.local"));
        u2 = userService.save(u2);

        // user
        ArrayList<UserRoles> users = new ArrayList<>();
        users.add(new UserRoles(new User(), r1));
        User u3 = new User("testbarn", "password", "testbarn@school.lambda", users);
        u3.getUseremails()
          .add(new Useremail(u3, "barnbarn@email.local"));
        u3 = userService.save(u3);

        users = new ArrayList<>();
        users.add(new UserRoles(new User(), r2));
        User u4 = new User("testcat", "password", "testcat@school.lambda", users);
        u4 = userService.save(u4);

        users = new ArrayList<>();
        users.add(new UserRoles(new User(), r2));
        User u5 = new User("testdog", "password", "testdog@school.lambda", users);
        u5 = userService.save(u5);

        strainService.addToUser(u1.getUsername(), s1);
        strainService.addToUser(u1.getUsername(), s2);

        strainService.addToUser(u2.getUsername(), s2);
        strainService.addToUser(u2.getUsername(), s3);

        strainService.addToUser(u3.getUsername(), s1);
        strainService.addToUser(u3.getUsername(), s2);
        strainService.addToUser(u3.getUsername(), s3);

        System.out.println("\n*** Seed Data ***");
        System.out.println(u1);
        System.out.println(u2);
        System.out.println(u3);
        System.out.println(u4);
        System.out.println(u5);
        System.out.println(s1);
        System.out.println(s2);
        System.out.println(s3);
        System.out.println(strainService.findByUsername("admin"));
        System.out.println("*** Seed Data ***\n");
    }
}