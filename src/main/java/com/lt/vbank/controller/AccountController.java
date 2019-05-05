//package com.lt.vbank.controller;
//
//import com.lt.vbank.model.Account;
//import com.lt.vbank.repository.AccountRepository;
//
//import com.lt.vbank.repository.AccountRepositoryImpl;
//import com.lt.vbank.service.AccountService;
//import com.lt.vbank.service.AccountServiceImpl;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//public class AccountController {
//
//    //TODO  switch to AccountService
//    @Autowired
//    private AccountServiceImpl service;
//
//    @GetMapping(value = "/accounts")
//    public ResponseEntity<Object> getAccounts() {
//        return new ResponseEntity<>(service.getAccounts(), HttpStatus.OK);
//    };
//
//    @RequestMapping(value = "/accounts/{id}", method = RequestMethod.PUT)
//    public ResponseEntity<Object> updateAccount(@PathVariable("name") String name, @RequestBody Account account) {
//        //if(!accountRepo.containsKey(id))throw new AccountNotFoundException();
//        service.updateAccount(account,name);
//        return new ResponseEntity<>("Account is updated successfully", HttpStatus.OK);
//    }
//
//    @RequestMapping(value = "/accounts", method = RequestMethod.POST)
//    public ResponseEntity<Object> createAccount(@RequestBody Account account) {
//        accountService.createAccount(account);
//        return new ResponseEntity<>("Account is created successfully", HttpStatus.CREATED);
//    }
//
//    @RequestMapping(value = "/accounts/{id}", method = RequestMethod.DELETE)
//    public ResponseEntity<Object> deleteAccount(@PathVariable("id") int id) {
////      if(!accountRepo.containsKey(id))throw new AccountNotFoundException();
//        accountService.deleteAccount(id);
//        return new ResponseEntity<>("Account is deleted successfully", HttpStatus.OK);
//    }
//}
//
