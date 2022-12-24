package com.gmail.neo960211.sportradartask.model;

import lombok.Data;

@Data
public class Competitor {
    private String id;
    private String name;
    private String country;
    private String country_code;
    private String abbreviation;
    private Gender gender;

}
