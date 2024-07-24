package ru.practice5.beans.acc;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.validation.BindingResult;
import ru.practice5.service.AccEngine;

@RestController
public class AccRequest {
    private AccEngine accEng;
    @Autowired
    public AccRequest(AccEngine accEng) {
        this.accEng = accEng;
    }
    @RequestMapping(value = "/corporate-settlement-account/create", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    public ResponseEntity<Object> createAccount(@RequestBody @Valid AccCreate accountCreate, BindingResult result)
    {
        AccOut accOut = new AccOut();
        if (result.hasErrors()) {
            for( ObjectError objErr : result.getAllErrors()) { accOut.setMessage( objErr.getDefaultMessage()); return ResponseEntity.status(400).body( accOut);}
        }
        int i = accEng.setAccount( accountCreate, accOut);
        if (i != 200) {accOut.setMessage(accEng.getMessage()); return ResponseEntity.status(i).body(accOut);}
        return ResponseEntity.ok(accOut);
    }

}
