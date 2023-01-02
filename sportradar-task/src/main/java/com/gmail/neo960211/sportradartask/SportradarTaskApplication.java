package com.gmail.neo960211.sportradartask;

import com.gmail.neo960211.sportradartask.model.Competitor;
import com.gmail.neo960211.sportradartask.model.Gender;
import com.gmail.neo960211.sportradartask.model.Venue;
import com.gmail.neo960211.sportradartask.repository.CompetitorRepository;
import com.gmail.neo960211.sportradartask.repository.EventRepository;
import com.gmail.neo960211.sportradartask.repository.VenueRepository;
import com.jayway.jsonpath.JsonPath;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class SportradarTaskApplication {

    public static void main(String[] args) {
        SpringApplication.run(SportradarTaskApplication.class, args);
    }
@Bean
    public CommandLineRunner dataLoader(CompetitorRepository competitorRepository, VenueRepository venueRepository, EventRepository eventRepository) {
        return args -> {
            BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/data/BE_data.json"));
            String line = null;
            StringBuilder builder = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }
            String jsonContent = builder.toString();
            List<Object> competitorList = JsonPath.read(jsonContent, "$..competitors[*]");
            for (Object element:
                    competitorList) {
                if (element != null) {
                    String[] temp = element.toString().split(", ");
                    Gender tempGender;
                    if (temp[6].replace("gender=", "").equals("male}")) {
                        tempGender = Gender.Male;
                    } else {
                        tempGender = Gender.Female;
                    }
                    competitorRepository.save(new Competitor(temp[0].replace("{id=", ""),
                            temp[1].replace("name=", ""),
                            temp[2].replace("country=", ""),
                            temp[3].replace("country_code=", ""),
                            temp[4].replace("abbreviation=", ""),
                            tempGender,
                            null,
                            null));
                }
            }

            List<Object> venuelist = JsonPath.read(jsonContent, "$..venue");
            for (Object element:
                    venuelist) {
                if (element != null) {
                    String[] temp = element.toString().split(", ");
                    if (temp.length == 7) {
                        venueRepository.save(new Venue(temp[0].replace("{id=", ""),
                            temp[1].replace("name=", ""),
                            Integer.parseInt(temp[2].replace("capacity=", "")),
                            temp[3].replace("city_name=", ""),
                            temp[4].replace("country_name=", ""),
                            temp[5].replace("map_coordinates=", ""),
                                temp[6].replace("country_code=", "").replace("}", ""),
                            null));
                    } else if (temp.length == 6) {
                        venueRepository.save(new Venue(temp[0].replace("{id=", ""),
                            temp[1].replace("name=", ""),
                            Integer.parseInt(temp[2].replace("capacity=", "")),
                            temp[3].replace("city_name=", ""),
                            temp[4].replace("country_name=", ""),
                            null,
                                temp[5].replace("country_code=", "").replace("}", ""),
                            null));
                    } else if (temp.length == 8) {
                        venueRepository.save(new Venue(temp[0].replace("{id=", ""),
                            temp[1].replace("name=", ""),
                            Integer.parseInt(temp[2].replace("capacity=", "")),
                            temp[3].replace("city_name=", ""),
                            temp[4].replace("country_name=", ""),
                                temp[5].replace("map_coordinates=", "") +","+ temp[6],
                                temp[7].replace("country_code=", "").replace("}", ""),
                            null));
                    }
                }
            }
        };
    }
}
