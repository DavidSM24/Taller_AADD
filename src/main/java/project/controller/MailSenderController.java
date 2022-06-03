package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.models.Agency;
import project.models.Email;
import project.services.MailSenderService;

import javax.validation.Valid;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/mailSender")
public class MailSenderController {

    @Autowired
    MailSenderService service;

    @PostMapping()
    public ResponseEntity<Boolean> sendMail(@Valid @RequestBody Email mail) {

        boolean result;
        try {
            result = service.sendMail(mail);

            if(result) return new ResponseEntity<Boolean>(result,new HttpHeaders(), HttpStatus.OK);
            else return new ResponseEntity<Boolean>(HttpStatus.FORBIDDEN);
        }
        catch(Exception e) {
            return new ResponseEntity<Boolean>(HttpStatus.FORBIDDEN);
        }

    }
}
