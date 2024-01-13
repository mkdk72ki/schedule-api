package com.mkdk.schedule.config;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Encode {
  public static void main(String[] args) {
    String password = "taro1";
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    String hashedPassword = encoder.encode(password);
    System.out.println("Hashed Password: " + hashedPassword);
  }
}
