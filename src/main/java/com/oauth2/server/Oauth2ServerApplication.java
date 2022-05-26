package com.oauth2.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.oauth2.server.entity.User;
import com.oauth2.server.repository.UserRepository;

@SpringBootApplication(scanBasePackages = {"com.oauth2.server.config","com.oauth2.server.repository","com.oauth2.server.service"})
public class Oauth2ServerApplication implements CommandLineRunner{
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Override
	public void run(String... args) throws Exception {
		User user=new User();
		user.setEmail("nilesh.java@gmail.com");
		user.setEnabled(true);
		user.setFirstname("nilesh");
		user.setId(101);
		user.setLastname("das");
		user.setPassword(passwordEncoder.encode("nilesh@123"));
		user.setRole("USER");
		//userRepository.deleteAll();
		//userRepository.save(user);
		
		System.out.println("User saved==============");
		System.out.println("Retrieving Info===================");
		User userTest=userRepository.findUserByEmail("surjeetkm.java@gmail.com");
		System.out.println(userTest.getPassword());
	}
	public static void main(String[] args) {
		SpringApplication.run(Oauth2ServerApplication.class, args);
	}
	
}
