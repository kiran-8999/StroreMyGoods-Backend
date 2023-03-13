package com.example.storemygoods.controller;

import com.example.storemygoods.model.Bill;
import com.example.storemygoods.model.User;
import com.example.storemygoods.service.IBillService;
import com.example.storemygoods.service.IUserService;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api/v1")
public class BillController {
    @Autowired
    private IBillService billService;
    @Autowired
    private IUserService userService;

    @GetMapping("/bills")
    private ResponseEntity<?> allUsers() {
        HashMap<String, String> res = new HashMap<>();
        try {
            return new ResponseEntity<>(billService.all(), HttpStatus.OK);
        } catch (Exception e) {
            res.put("msg", e.getMessage());
            return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/bills/{id}")
    private ResponseEntity<?> getById(@PathVariable Long id) {
        HashMap<String, String> res = new HashMap<>();
        try {
            return new ResponseEntity<>(billService.getById(id), HttpStatus.OK);
        } catch (Exception e) {
            res.put("msg", e.getMessage());
            return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/bill/{userEmail}")
    private ResponseEntity<?> getByUserEmail(@PathVariable String userEmail) {
        HashMap<String, String> res = new HashMap<>();
        try {
            Bill b = billService.getByUserEmail(userEmail);
            return new ResponseEntity<>(billService.all().stream().filter(i -> i.getId().equals(b.getUserEmail())), HttpStatus.OK);
//            return new ResponseEntity<>(billService.getByUserEmail(userEmail),HttpStatus.OK);
        } catch (Exception e) {
            res.put("msg", e.getMessage());
            return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/addBills/{email}/{id}")
    private ResponseEntity<?> addBills(@PathVariable String email, @PathVariable Long id) {
        HashMap<String, String> res = new HashMap<>();
        try {
            User u = userService.getByEmail(email);
            Bill b= billService.getById(id);
            u.getBills().add(b);
            return  new ResponseEntity<>(userService.update(u),HttpStatus.OK);
        }catch (Exception e){
            res.put("msg", e.getMessage());
            return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/bills")
    private ResponseEntity<?> addUser(@RequestBody Bill user) {
        HashMap<String, String> res = new HashMap<>();
        try {
            return new ResponseEntity<>(billService.add(user), HttpStatus.OK);
        } catch (Exception e) {
            res.put("msg", e.getMessage());
            return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/bills/{id}")
    private ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody Bill bill) {
        HashMap<String, String> res = new HashMap<>();
        try {
//            Bill u = billService.getById(id);
            return new ResponseEntity<>(billService.update(bill), HttpStatus.OK);
        } catch (Exception e) {
            res.put("msg", e.getMessage());
            return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/bills/{id}")
    private ResponseEntity<?> deleteUser(@PathVariable Long id) {
        HashMap<String, String> res = new HashMap<>();
        try {
            billService.delete(id);
            res.put("msg", "Bill deleted successfully");
            return new ResponseEntity<>(res, HttpStatus.OK);
        } catch (Exception e) {
            res.put("msg", e.getMessage());
            return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
