package com.example.storemygoods.controller;

import com.example.storemygoods.model.Bill;
import com.example.storemygoods.model.User;
import com.example.storemygoods.service.IBillService;
import com.example.storemygoods.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api/v1")
public class UserController {
    @Autowired
    private IUserService userService;
    @Autowired
    private IBillService billService;

    @Value("BILL_DUE_DAYS")
    private static final int BILL_DUE_DAYS = 30;

    @GetMapping("/remind")
    private ResponseEntity<?> payRemind(String userName) {
        HashMap<String, String> res = new HashMap<>();
        try {
            List<Bill> userBills = billService.getByUserName(userName);
            List<Bill> dueBills = new ArrayList<>();
            LocalDate currentDate = LocalDate.now();
            for (Bill b : userBills) {
                int currentDay = currentDate.getDayOfYear();
                int billDay = b.getDate().getDayOfYear();
                if (currentDay - billDay >= BILL_DUE_DAYS) {
                    dueBills.add(b);
                }
            }
            return ResponseEntity.ok().body(dueBills);
        } catch (Exception e) {
            res.put("msg", e.getMessage());
            return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/reminder/{id}")
    private ResponseEntity<?> reminder(@PathVariable Long id){
        HashMap<String,String> res= new HashMap<>();
        try{
            Bill b=billService.getById(id);
            List<Bill> dueBills = new ArrayList<>();
            int currentDay=b.getDate().getDayOfYear();
            int billDay=b.getEndDate().getDayOfYear();
            if (billDay-currentDay >= BILL_DUE_DAYS){
                dueBills.add(b);
            }
            return ResponseEntity.ok().body(dueBills);
        }catch (Exception e){
            res.put("msg",e.getMessage());
            return new ResponseEntity<>(res,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping("/register")
    private ResponseEntity<?> register(@RequestBody User user) {
        HashMap<String, String> res = new HashMap<>();
        try {

            if (user.getEmail().equals("") && user.getFirstName().equals("") && user.getLastName().equals("") && user.getPassword().equals("") && user.getAddress().equals("")) {
                res.put("msg", "Please fill all the fields");
                return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
            }
            boolean checkUser = userService.getByEmail(user.getEmail()) != null;
            if (checkUser) {
                res.put("msg", "User already exists");
                return new ResponseEntity<>(res, HttpStatus.ALREADY_REPORTED);
            } else {
                userService.add(user);
                res.put("msg", "User registered successfully");
                return new ResponseEntity<>(res, HttpStatus.OK);
            }

        } catch (Exception e) {
            res.put("msg", e.getMessage());
            return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/login")
    private ResponseEntity<?> login(@RequestBody User user) {
        HashMap<String, String> res = new HashMap<>();
        try {
            if (user.getEmail().equals("") && user.getPassword().equals("")) {
                res.put("msg", "please fill all the fields");
                return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
            }
            if (user.getEmail().equals("admin@gmail.com") && user.getPassword().equals("admin")) {
                res.put("msg", "Admin login successfully");
                return new ResponseEntity<>(res, HttpStatus.OK);
            }
            User checkUser = userService.getByEmail(user.getEmail());
            if (checkUser== null){
                res.put("msg","User not found");
                return  new ResponseEntity<>(res,HttpStatus.BAD_REQUEST);
            }else{
                res.put("msg","user login successfully");
                return  new ResponseEntity<>(res,HttpStatus.OK);
            }

        } catch (Exception e) {
            res.put("msg", e.getMessage());
            return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/users")
    private ResponseEntity<?> allUsers() {
        HashMap<String, String> res = new HashMap<>();
        try {
            return new ResponseEntity<>(userService.all(), HttpStatus.OK);
        } catch (Exception e) {
            res.put("msg", e.getMessage());
            return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    @GetMapping("/users/{id}")
    private ResponseEntity<?> getById(@PathVariable Long id) {
        HashMap<String, String> res = new HashMap<>();
        try {
            return new ResponseEntity<>(userService.getById(id), HttpStatus.OK);
        } catch (Exception e) {
            res.put("msg", e.getMessage());
            return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/user/{email}")
    private  ResponseEntity<?> getByEmail(@PathVariable String email){
        HashMap<String ,String > res=new HashMap<>();
        try{
            return new ResponseEntity<>(userService.getByEmail(email),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/users")
    private ResponseEntity<?> addUser(@RequestBody User user) {
        HashMap<String, String> res = new HashMap<>();
        try {
            return new ResponseEntity<>(userService.add(user), HttpStatus.OK);
        } catch (Exception e) {
            res.put("msg", e.getMessage());
            return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/users/{id}")
    private ResponseEntity<?> updateUser(@RequestBody User user, @PathVariable Long id) {
        HashMap<String, String> res = new HashMap<>();
        try {

            return new ResponseEntity<>(userService.update(user), HttpStatus.OK);
        } catch (Exception e) {
            res.put("msg", e.getMessage());
            return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/users/{id}")
    private ResponseEntity<?> deleteUser(@PathVariable Long id) {
        HashMap<String, String> res = new HashMap<>();
        try {
            userService.delete(id);
            res.put("msg", "User deleted successfully");
            return new ResponseEntity<>(res, HttpStatus.OK);
        } catch (Exception e) {
            res.put("msg", e.getMessage());
            return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
