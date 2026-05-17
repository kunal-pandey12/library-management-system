package library.management.books.PasswordGenerator;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordGenerator {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
        System.out.println(encoder.encode("amit25"));
        System.out.println(encoder.encode("rahul54"));
        System.out.println(encoder.encode("aman26"));
        System.out.println(encoder.encode("rohit123"));
        System.out.println(encoder.encode("manu123"));

    }
}
