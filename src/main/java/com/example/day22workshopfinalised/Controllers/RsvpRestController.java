package com.example.day22workshopfinalised.Controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.day22workshopfinalised.Models.Rsvp;
import com.example.day22workshopfinalised.Services.JsonService;
import com.example.day22workshopfinalised.Services.RsvpService;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;

@RestController
@RequestMapping(path = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class RsvpRestController {

    @Autowired
    private RsvpService rsvpService;

    @Autowired
    private JsonService jsonService;
    
    // Get all Rsvps
    @GetMapping(path = "/rsvps")
    public ResponseEntity<String> getAllRsvps() {
        List<Rsvp> listOfRsvps = rsvpService.findAllRsvps();
        JsonArray jsonRsvpArray = jsonService.rsvpListToJson(listOfRsvps);
        String jsonRsvpArrayString = jsonRsvpArray.toString();

        if (jsonRsvpArray.isEmpty()) {
            return ResponseEntity.status(404).body(
                Json.createObjectBuilder().add("Message: ", "Cannot find rsvps.").build().toString()
            );
        }

        return ResponseEntity.ok(jsonRsvpArrayString);
    }

    // Get Rsvp by name
    @GetMapping(path = "/rsvp/{name}")
    public ResponseEntity<String> getRsvpByName(@PathVariable String name) {
        System.out.println("Controller: Finding rsvp from " + name);
        Optional<Rsvp> rsvp = rsvpService.findRsvpByName(name);
        System.out.println("Rsvp from " + rsvp.get().getName() + " successfully received from repo.");
        System.out.println(rsvp.get().getConfirmationDate());
        // Issue with converting to json.
        JsonObject jsonRsvp = jsonService.rsvpToJson(rsvp);
        String jsonRsvpString = jsonRsvp.toString();

        if (jsonRsvp.isEmpty()) {
            return ResponseEntity.status(404).body(
                Json.createObjectBuilder().add("Message: ", "Cannot find " + name + ".").build().toString()
            );
        }

        return ResponseEntity.ok(jsonRsvpString);
    }

    // Add Rsvp
    @PostMapping(path = "/rsvp", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> addRsvp(@RequestBody Rsvp rsvp) {
        System.out.println("Controller: This is the rsvp received: " + rsvp.toString());
        Boolean saved = false;
        saved = rsvpService.insertRsvp(rsvp);

        if (saved) {
            return ResponseEntity.status(201).body(saved);
        }

        return new ResponseEntity<Boolean>(saved, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // Update Rsvp
    @PutMapping(path = "/rsvp/{email}")
    public ResponseEntity<Boolean> updateRsvp(@RequestBody Rsvp rsvp, @PathVariable("email") String email) {
        System.out.println("Controller: This is the UPDATED rsvp received: " + rsvp.toString());
        System.out.println("Controller: This is the email of the RSVP to be updated: " + email);
        Boolean updated = false;
        Optional<Rsvp> rsvpToUpdate = rsvpService.findRsvpByEmail(email);

        if (rsvpToUpdate != null && rsvp.getEmail().equals(email)) {
            updated = rsvpService.updateRsvp(rsvp, email);
        }

        if (updated) {
            return ResponseEntity.status(201).body(updated);
        }

        return ResponseEntity.ofNullable(updated);
    }

    // Get total number of Rsvps
    @GetMapping(path = "rsvps/count")
    public ResponseEntity<String> countRsvps() {
        int count = rsvpService.findRsvpCount();

        if (count >= 0) {
            return ResponseEntity.status(201).body("Total RSVPs: " + count);
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to retrieve count.");
    }
}
