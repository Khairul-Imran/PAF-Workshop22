package com.example.day22workshopfinalised.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.day22workshopfinalised.Models.Rsvp;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;

@Service
public class JsonService {
    
    public JsonObject rsvpToJson(Optional<Rsvp> optRsvp) {
        Rsvp rsvp = optRsvp.get();

        JsonObject rsvpJson = Json.createObjectBuilder()
            .add("id", rsvp.getId())
            .add("name", rsvp.getName())
            .add("email", rsvp.getEmail())
            .add("phone_number", rsvp.getPhoneNumber())
            .add("confirmation_date", rsvp.getConfirmationDate() != null ? rsvp.getConfirmationDate().toString() : "")
            .add("comments", rsvp.getComments())
            .build();
        
        return rsvpJson;
    }

    public JsonArray rsvpListToJson(List<Rsvp> rsvps) {
        JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();

        for (Rsvp rsvp : rsvps) {
            JsonObject rsvpJson = Json.createObjectBuilder()
                .add("id", rsvp.getId())
                .add("name", rsvp.getName())
                .add("email", rsvp.getEmail())
                .add("phone_number", rsvp.getPhoneNumber())
                .add("confirmation_date", rsvp.getConfirmationDate() != null ? rsvp.getConfirmationDate().toString() : "")
                .add("comments", rsvp.getComments())
                .build();
            jsonArrayBuilder.add(rsvpJson);
        }
        JsonArray jsonRsvpArray = jsonArrayBuilder.build();

        return jsonRsvpArray;
    }

}
