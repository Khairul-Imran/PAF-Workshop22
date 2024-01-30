package com.example.day22workshopfinalised.Repositories;

public class Queries {

        public static final String SQL_GET_ALL_RSVPS = """
                        select * from rsvp
                        """;

        public static final String SQL_GET_RSVP_BY_NAME = """
                        select * from rsvp
                        where name like ?
                        """;

        public static final String SQL_GET_RSVP_BY_EMAIL = """
                        select * from rsvp
                        where email = ?
                        """;

        public static final String SQL_ADD_RSVP = """
                        insert into rsvp (name, email, phone, confirmation_date, comments)
                        values(?, ?, ?, ?, ?)
                        """;

        public static final String SQL_UPDATE_RSVP_BY_EMAIL = """
                        update rsvp set
                        name = ?,
                        phone = ?,
                        confirmation_date = ?,
                        comments = ?
                        where email = ?
                        """;

        public static final String SQL_COUNT_RSVPS = """
                        select count(*) from rsvp
                        """;

}
