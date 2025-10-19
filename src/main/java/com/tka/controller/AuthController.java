package com.tka.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tka.Models.User;
import com.tka.Repository.UserRepository;
import com.tka.Services.CustomUserDetailsService;
import com.tka.Services.UserService;
import com.tka.config.JwtProvider;
import com.tka.request.LoginRequest;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @PostMapping("/signup")
    public AuthResponse createUser(@RequestBody User user) throws Exception {

        User isExist = userRepository.findByEmail(user.getEmail());

        if (isExist != null) {
            throw new Exception("*** User already exists with this email ***");
        }

        // Create and save user
        User newUser = new User();
        newUser.setEmail(user.getEmail());
        newUser.setFirstName(user.getFirstName());
        newUser.setLastName(user.getLastName());
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));

        User savedUser = userRepository.save(newUser);

        // Create Authentication object
        Authentication authentication =
                new UsernamePasswordAuthenticationToken(savedUser.getEmail(), savedUser.getPassword());

        // Generate JWT
        String token = JwtProvider.generateToken(authentication);
        
        AuthResponse res=new AuthResponse(token,"Register Successfully");

        return res;  // ✅ return JWT instead of user object
    }
    
    @PostMapping("/signin")
    public AuthResponse signin(@RequestBody LoginRequest loginRequest) {
    	
    	Authentication authentication=authenticate(loginRequest.getEmail(),loginRequest.getPassword());
    	
        // Generate JWT
        String token = JwtProvider.generateToken(authentication);
        
        AuthResponse res=new AuthResponse(token,"Login Successfully");

    	return res;
    	
    }

	private Authentication authenticate(String email, String password) {
		UserDetails userDetails=customUserDetailsService.loadUserByUsername(email);
		
		if(userDetails == null) {
			throw new BadCredentialsException("Invalid username");
		}
		if(!passwordEncoder.matches(password, userDetails.getPassword())) {
			throw new BadCredentialsException("Password not match");
		}
		return new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
	}
}













