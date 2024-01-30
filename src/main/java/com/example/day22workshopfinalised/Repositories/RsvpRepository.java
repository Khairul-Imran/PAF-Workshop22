package com.example.day22workshopfinalised.Repositories;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import com.example.day22workshopfinalised.Models.Rsvp;

@Repository
public class RsvpRepository {
    
    @Autowired
    private JdbcTemplate template;

    // Get all Rsvps
    public List<Rsvp> findAllRsvps() {
        List<Rsvp> listOfRsvps = new ArrayList<>();
        SqlRowSet sqlRowSet = template.queryForRowSet(Queries.SQL_GET_ALL_RSVPS);

        while (sqlRowSet.next()) {
            Rsvp rsvp = Rsvp.populate(sqlRowSet);

            listOfRsvps.add(rsvp);
        }

        return (Collections.unmodifiableList(listOfRsvps));
    }

    // Get Rsvp by name
    public Optional<Rsvp> findRsvpByName(String name) {
        System.out.println("Repository: Finding rsvp from " + name);
        String queryName = "%" + name + "%"; // Don't forget this.
        SqlRowSet sqlRowSet = template.queryForRowSet(Queries.SQL_GET_RSVP_BY_NAME, queryName);

        if (sqlRowSet.next()) {
            return Optional.of(Rsvp.populate(sqlRowSet));
        }

        throw new NoSuchElementException("Rsvp with Name: " + name + " not found.");
    }

    // Get Rsvp by email -> For updating
    // ***** Could also have checked if it existed using COUNT *****
    public Optional<Rsvp> findRsvpByEmail(String email) {
        System.out.println("Repository: This is the email of the RSVP to be updated: " + email);
        SqlRowSet sqlRowSet = template.queryForRowSet(Queries.SQL_GET_RSVP_BY_EMAIL, email);

        if (sqlRowSet.next()) {
            return Optional.of(Rsvp.populate(sqlRowSet));
        }

        throw new NoSuchElementException("Rsvp with Email: " + email + " not found.");
    }

    // Add Rsvp
    public boolean insertRsvp(Rsvp rsvp) {
        System.out.println("Repository: This is the rsvp in repo: " + rsvp.toString());
        int insertAttempt = 0;
        insertAttempt = template.update(Queries.SQL_ADD_RSVP, 
            rsvp.getName(), 
            rsvp.getEmail(), 
            rsvp.getPhoneNumber(), 
            rsvp.getConfirmationDate(), 
            rsvp.getComments());

        if (insertAttempt > 0) {
            System.out.println("Insert of rsvp successful.");
            return true;
        }

        return false;
    }

    // Update Rsvp
    public boolean updateRsvp(Rsvp rsvp, String email) {
        System.out.println("Repository: This is the UPDATED rsvp received: " + rsvp.toString());
        int updateAttempt = 0;
        updateAttempt = template.update(Queries.SQL_UPDATE_RSVP_BY_EMAIL, 
            rsvp.getName(), 
            rsvp.getPhoneNumber(),
            rsvp.getConfirmationDate(),
            rsvp.getComments(), 
            email);

        if (updateAttempt > 0) {
            return true;
        }

        return false;
    }

    // Get total number of Rsvps
    public Integer findRsvpCount() {
        return template.queryForObject(Queries.SQL_COUNT_RSVPS, Integer.class);
    }

}
