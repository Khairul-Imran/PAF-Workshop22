package com.example.day22workshopfinalised.Models;

import java.util.Date;

// import org.joda.time.DateTime;
// import org.joda.time.format.DateTimeFormat;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Rsvp {
    
    Integer id;
    String name;
    String email;
    Integer phoneNumber;
    Date confirmationDate;
    String comments;

    public static Rsvp populate(SqlRowSet sqlRowSet) {
        final Rsvp rsvp = new Rsvp();
        System.out.println("Starting populating process for rsvp.");

        rsvp.setId(sqlRowSet.getInt("id"));
        rsvp.setName(sqlRowSet.getString("name"));
        rsvp.setEmail(sqlRowSet.getString("email"));
        rsvp.setPhoneNumber(sqlRowSet.getInt("phone"));

        rsvp.setConfirmationDate(sqlRowSet.getDate("confirmation_date"));
        // Important
        // rsvp.setConfirmationDate(new DateTime(DateTimeFormat
        //     .forPattern("dd/MM/yyyy")
        //     .parseDateTime(sqlRowSet.getString("confirmation_date"))));
        rsvp.setComments(sqlRowSet.getString("comments"));

        System.out.println("Ending populating process for rsvp.");

        return rsvp;
    }
}
