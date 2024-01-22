package com.example.controllers;

import com.example.dtos.SearchResult;
import com.example.entities.Contact;
import com.example.entities.User;
import com.example.services.ContactUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class ContactUserController {

    private final ContactUserService contactUserService;

    @Autowired
    public ContactUserController(ContactUserService contactUserService) {
        this.contactUserService = contactUserService;
    }

    @PostMapping("/mark-as-spam/{phoneNumber}")
    public ResponseEntity<String> markNumberAsSpam(@PathVariable String phoneNumber) {
        contactUserService.markNumberAsSpam(phoneNumber);
        return ResponseEntity.ok("Number marked as spam");
    }

    @GetMapping("/search-by-name/{name}")
    public ResponseEntity<List<Contact>> searchContactsByName(@PathVariable String name) {
        List<Contact> contacts = contactUserService.searchContactsByName(name);
        return ResponseEntity.ok(contacts);
    }

    @GetMapping("/search-by-phone/{phoneNumber}")
    public ResponseEntity<List<Contact>> searchContactsByPhoneNumber(@PathVariable String phoneNumber) {
        List<Contact> contacts = contactUserService.searchContactsByPhoneNumber(phoneNumber);
        return ResponseEntity.ok(contacts);
    }

    @GetMapping("/depth-search/{phoneNumber}/{name}/{phoneOfSearcher}")	//along with the number searched - upon clicking of one of the search results
    																	//name and phone number of searcher also passed along[will be maintained in FE session storage]
    																	//as the requirement was that e-mail is to be displayed only if searcher is a contact of searched
    public ResponseEntity<SearchResult> depthSearch(
            @PathVariable String phoneNumber,
            @PathVariable String name,
            @PathVariable String phoneOfSearcher) {
        SearchResult result = contactUserService.depthSearch(phoneNumber, name, phoneOfSearcher);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/get-by-phone/{phoneNumber}")
    public ResponseEntity<User> getByPhoneNumber(@PathVariable String phoneNumber) {
        User user = contactUserService.getByPhoneNumber(phoneNumber);
        return ResponseEntity.ok(user);
    }
}
