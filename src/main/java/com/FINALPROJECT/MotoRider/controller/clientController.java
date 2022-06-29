package com.FINALPROJECT.MotoRider.controller;


import com.FINALPROJECT.MotoRider.dto.ClientDTO;
import com.FINALPROJECT.MotoRider.models.Client;
import com.FINALPROJECT.MotoRider.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.io.UnsupportedEncodingException;
import java.util.Set;

import static com.FINALPROJECT.MotoRider.Utils.Util.*;

@RestController
@RequestMapping("/api")
public class clientController {

    @Autowired
    ClientService clientService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JavaMailSender mailSender;

    @GetMapping("/clients")
    Set<ClientDTO> getAll (){
        return clientService.getAllClientsDTO();
    }

    @GetMapping("/clients/{id}")
    ClientDTO getClient(@PathVariable long id){
        return clientService.getClientDTO(id);
    }

    @GetMapping("/current/clients")
    ClientDTO getCurrentDTO(Authentication authentication){
        return clientService.getCurrentDTO(authentication);
    }


    @PostMapping("/clients")
    public ResponseEntity<Object> register(
            @RequestParam String firstName, @RequestParam String lastName,
            @RequestParam String email, @RequestParam String password, HttpServletRequest request) throws MessagingException, UnsupportedEncodingException {

        if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || password.isEmpty())
            return new ResponseEntity<>("Missing data", HttpStatus.FORBIDDEN);


        if (clientService.getClientCredential(email) != null )
            return new ResponseEntity<>("Email already in use", HttpStatus.FORBIDDEN);


        if(password.length() < 5)
            return new ResponseEntity<>("Short password", HttpStatus.FORBIDDEN);



        Client client = new Client(firstName, lastName, email, passwordEncoder.encode(password));

        String randomCode = GenerateToken(64);
        client.setToken(randomCode);
        client.setEnabled(false);


        clientService.saveClient(client);

        sendVerificationEmail(client);

        return new ResponseEntity<>("User created, email verification is required",HttpStatus.CREATED);
    }

    @GetMapping("/verifyToken/{token}")
    public ResponseEntity<?> verifyToken(@PathVariable String token) throws MessagingException, UnsupportedEncodingException {
        Client client = clientService.getClientToken(token);
        if(client == null)
            return new ResponseEntity<>("Token incorrect", HttpStatus.FORBIDDEN);
        return new ResponseEntity<>(client, HttpStatus.OK);
    }


    @PostMapping("/clients/current/sendToken")
    public ResponseEntity<Object> sendTokenTemp(Authentication authentication) throws MessagingException, UnsupportedEncodingException {

        Client client = clientService.getCurrent(authentication);

        if(client == null)
            return new ResponseEntity<>("You not have a authorization", HttpStatus.FORBIDDEN);

        String toAddress = client.getEmail();
        String fromAddress = "motorideer@gmail.com";
        String senderName = "MotoRiderTeam";
        String subject = "Identity Verification Code";
        String content = "<h2 style=\"color:black;\">Hi [[name]]!</h2>"
                + "<p style=\"color:black;\"> Do not share this code with anyone! </p>"
                +"<img src=\"https://i.imgur.com/DjW6seD.png\" alt=\"ImgRegister\" width=\"450\" height=\"302\"/> <br>"
                + "<h3 style=\"color:#ff5e14;\">[[code]]</h3>"
                + "<div style=\"display:flex;gap: 0.4rem;\"> <p style=\"color:black;\"> Thank you, </p> <p style=\"color:rgb(232, 91, 26);font-weight: bold;\"> BankrdoX teams. </p> </div> "
                ;

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom(fromAddress, senderName);
        helper.setTo(toAddress);
        helper.setSubject(subject);

        content = content.replace("[[name]]", client.getFirstName());

        String code = GenerateCode(0,9);
        content = content.replace("[[code]]", code);

        helper.setText(content, true);
        mailSender.send(message);
        return new ResponseEntity<>(code, HttpStatus.OK);
    }

    @Transactional
    @PostMapping("/activateAccount/{token}")
    public ResponseEntity<Object> activateAccount(HttpServletRequest request, @PathVariable String token){

        Client client = clientService.getClientToken(token);

        if(client == null){
            return new ResponseEntity<>("Token invalid", HttpStatus.FORBIDDEN);}

        client.setEnabled(true);
        client.deleteToken();
        DeleteToken(token);
        clientService.saveClient(client);
        return new ResponseEntity<>( HttpStatus.ACCEPTED);
    }



    private void sendVerificationEmail(Client client)
            throws MessagingException, UnsupportedEncodingException {
        String toAddress = client.getEmail();
        String fromAddress = "motorideer@gmail.com";
        String senderName = "MotoRiderTeam";
        String subject = "Please verify your registration";
        String content = "<h2 style=\"color:black;\">Hi [[name]]!</h2>"
                + "<p style=\"color:black;\"> Please click the link below to verify your registration: </p>"
                +"<img src=\"https://i.ibb.co/pRktKZH/LOGO-LETRAS-PROJECTO-FINAL-negro.png\" alt=\"ImgRegister\" width=\"400\" height=\"250\"/> <br>"
                + "<h3><a href=\"[[URL]]\" target=\"_self\" style=\"color:#ff5e14;\">VERIFY YOUR ACCOUNT</a></h3>"
                + "<div style=\"display:flex;gap: 0.4rem;\"> <p style=\"color:black;\"> Thank you, </p> <p style=\"color:rgb(232, 91, 26);font-weight: bold;\"> MotoRiderTeam. </p> </div> "
                ;

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom(fromAddress, senderName);
        helper.setTo(toAddress);
        helper.setSubject(subject);

        content = content.replace("[[name]]", client.getFirstName());

        String verifyURL = "http://localhost:8080/web/activate-client.html?token=" + client.getToken();

        content = content.replace("[[URL]]", verifyURL);

        helper.setText(content, true);
        mailSender.send(message);
    }

}
