package com.example.day22workshopfinalised.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.day22workshopfinalised.Models.Rsvp;
import com.example.day22workshopfinalised.Repositories.RsvpRepository;

@Service
public class RsvpService {
    
    @Autowired
    private RsvpRepository rsvpRepository;
    
    // Get all Rsvps
    public List<Rsvp> findAllRsvps() {
        return rsvpRepository.findAllRsvps();
    }

    // Get Rsvp by name
    public Optional<Rsvp> findRsvpByName(String name) {
        System.out.println("Service: Finding rsvp from " + name);
        return rsvpRepository.findRsvpByName(name);
    }
    
    // Get Rsvp by email -> for updating.
    public Optional<Rsvp> findRsvpByEmail(String email) {
        System.out.println("Service: This is the email of the RSVP to be updated: " + email);
        return rsvpRepository.findRsvpByEmail(email);
    }

    // Add Rsvp
    public boolean insertRsvp(Rsvp rsvp) {
        System.out.println("Service: This is the rsvp in service: " + rsvp.toString());
        return rsvpRepository.insertRsvp(rsvp);
    }

    // Update Rsvp
    public boolean updateRsvp(Rsvp rsvp, String email) {
        System.out.println("Service: This is the UPDATED rsvp received: " + rsvp.toString());
        return rsvpRepository.updateRsvp(rsvp, email);
    }

    // Get total number of Rsvps
    public Integer findRsvpCount() {
        return rsvpRepository.findRsvpCount();
    }
}
