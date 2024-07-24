package ru.practice5.beans.instance;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.practice5.service.InstanceEngine;
@RestController
public class InstanceRequest {
    private final InstanceEngine instEng;
    @Autowired
    public InstanceRequest(InstanceEngine instEng) {
        this.instEng = instEng;
    }
    @RequestMapping(value = "/corporate-settlement-instance/create", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    public ResponseEntity<Object> createInstance(@RequestBody @Valid InstanceCreate instanceCreate, BindingResult result) {
        InstanceOut instOut = new InstanceOut();
        if (result.hasErrors())
        {
            for( ObjectError objErr : result.getAllErrors()){   instOut.setMessage( objErr.getDefaultMessage()); return ResponseEntity.status(400).body( instOut); }
        }
        int i = instEng.setProduct( instanceCreate, instOut);
        if (i != 200) {   instOut.setMessage(instEng.getMessage()); return ResponseEntity.status(i).body(instOut);  }
        return ResponseEntity.ok(instOut);
    }
}