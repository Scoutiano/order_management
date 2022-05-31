package com.example.ordermanagement.controller;

import com.example.ordermanagement.dto.JWTAuthResponse;
import com.example.ordermanagement.dto.LoginDto;
import com.example.ordermanagement.dto.SignUpDto;
import com.example.ordermanagement.model.Customer;
import com.example.ordermanagement.model.Role;
import com.example.ordermanagement.repository.CustomerRepository;
import com.example.ordermanagement.repository.RoleRepository;
import com.example.ordermanagement.security.JwtTokenProvider;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;

@Api(value = "Auth controller exposes siginin and signup REST APIs")
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenProvider tokenProvider;

    /**
     * Sign in to API using credentials provided in LoginDto object
     *
     * @param loginDto contains sign in credentials
     * @return response indicating whether the request was authenticated or not
     */
    @ApiOperation(value = "REST API to Register or Signup user to Blog app")
    @PostMapping("/signin")
    public ResponseEntity<JWTAuthResponse> authenticateUser(@RequestBody LoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getEmail(), loginDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        // get token form tokenProvider
        String token = tokenProvider.generateToken(authentication);

        return ResponseEntity.ok(new JWTAuthResponse(token));
    }

    /**
     * Sign up to API using credentials provided in SignUpDto
     *
     * @param signUpDto contains sign up credentials
     * @return
     * @throws ParseException
     */
    @ApiOperation(value = "REST API to Signin or Login user to Blog app")
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody SignUpDto signUpDto) throws ParseException {

        // add check for username exists in a DB
        if (customerRepository.existsByEmail(signUpDto.getEmail())) {
            return new ResponseEntity<>("Email is already in use!", HttpStatus.BAD_REQUEST);
        }

        // create user object
        Customer customer = new Customer();
        customer.setFirstName(signUpDto.getFirstName());
        customer.setLastName(signUpDto.getLastName());
        customer.setEmail(signUpDto.getEmail());
        customer.setPassword(passwordEncoder.encode(signUpDto.getPassword()));
        customer.setBornAt(new SimpleDateFormat("yyyy-MM-dd").parse(signUpDto.getBornAt()));
        customerRepository.save(customer);

        return new ResponseEntity<>("User registered successfully", HttpStatus.OK);

    }
}
