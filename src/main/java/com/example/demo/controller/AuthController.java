package com.example.demo.controller;

import com.example.demo.config.MailSenderService;
import com.example.demo.entity.User;
import com.example.demo.entity.UserActivation;
import com.example.demo.entity.dto.UserCreateDto;
import com.example.demo.entity.dto.UserLoginDto;
import com.example.demo.repository.UserActivationRepository;
import com.example.demo.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import static com.example.demo.util.PasscodeGenerate.generateRandomString;

@RestController
@RequestMapping("/api/user")
public class AuthController {

    @Autowired
    private UserActivationRepository userActivationRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;
    private final MailSenderService mailSenderService;

    public AuthController(MailSenderService mailSenderService) {
        this.mailSenderService = mailSenderService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserCreateDto userCreateDto) {

        User user1 = userRepository.getByName(userCreateDto.getName());
        User user2 = userRepository.getByMail(userCreateDto.getMail());
        if (user1!=null) {
            return new ResponseEntity<>("User is already exist!", HttpStatus.BAD_REQUEST);
        }else if(user2!=null){
            return new ResponseEntity<>("Mail is already exist!", HttpStatus.BAD_REQUEST);
        }else {
            String passcode = generateRandomString(4);
            mailSenderService.sendMail(userCreateDto.getMail(),"Hi,"+userCreateDto.getMail()+", "+"don't share this password!", passcode);

            User user = new User(userCreateDto.getName(),userCreateDto.getMail(),passwordEncoder.encode(userCreateDto.getPassword()),false);
            userRepository.save(user);
            UserActivation user3 = new UserActivation(userCreateDto.getName(),passcode);
            userActivationRepository.add(user3);
            return new ResponseEntity<>("User is register successful!", HttpStatus.OK);
        }

    }
    @PostMapping("/login")
    public ResponseEntity<String> authenticateUser(@RequestBody UserLoginDto loginDto) {
        User user = new User();
        if(userRepository.getByName(loginDto.getName()).getIsActive()){
           user = userRepository.getByName(loginDto.getName());
        }
        if(passwordEncoder.matches(loginDto.getPassword(), user.getPassword())) {
                return new ResponseEntity<>("User login successfully!...", HttpStatus.OK);
        } else{
                return new ResponseEntity<>("Password is wrong!...", HttpStatus.BAD_REQUEST);
         }

    }

    @PostMapping("/verify")
    public ResponseEntity<String> activateUser(
            @RequestParam String name,
            @RequestParam String passcode) {

        UserActivation user = userActivationRepository.getByName(name);
        if (user == null) {
            return ResponseEntity.badRequest().body("User not found");
        }

        if (!passcode.equals(user.getPasscode())) {
            return ResponseEntity.badRequest().body("Passcodes do not match");
        } else {
            // Update is_active to true
            userRepository.activateByUsername(name);
            return ResponseEntity.ok("User activated successfully");
        }
    }




}
