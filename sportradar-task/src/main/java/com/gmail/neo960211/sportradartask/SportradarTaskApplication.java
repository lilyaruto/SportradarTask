package com.gmail.neo960211.sportradartask;

import com.gmail.neo960211.sportradartask.model.Competitor;
import com.gmail.neo960211.sportradartask.model.Event;
import com.gmail.neo960211.sportradartask.model.Gender;
import com.gmail.neo960211.sportradartask.model.Venue;
import com.gmail.neo960211.sportradartask.repository.CompetitorRepository;
import com.gmail.neo960211.sportradartask.repository.EventRepository;
import com.gmail.neo960211.sportradartask.repository.VenueRepository;
import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
public class SportradarTaskApplication {

    public static void main(String[] args) {
        SpringApplication.run(SportradarTaskApplication.class, args);
    }

    @Bean
    public CommandLineRunner defaultDataLoader(VenueRepository venueRepository) {
        return args -> {
            venueRepository.save(new Venue("null", "null", 1, "null", "null", "null", "null", null));
        };
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

            List<Object> eventList = JsonPath.read(jsonContent, "$.Events[*]");
            JSONArray homeProb =  JsonPath.read(jsonContent, "$..probability_home_team_winner");
            JSONArray drawProb =  JsonPath.read(jsonContent, "$..probability_draw");
            JSONArray awayProb =  JsonPath.read(jsonContent, "$..probability_away_team_winner");
            for (int i = 0; i < eventList.size(); i++) {
                if (eventList.get(i) != null) {
                    String[] temp = eventList.get(i).toString().split(", ");
                    LocalDate date = LocalDate.of(
                            Integer.parseInt(temp[1].replace("start_date=", "").substring(0, 4)),
                            Integer.parseInt(temp[1].replace("start_date=", "").substring(5, 7)),
                            Integer.parseInt(temp[1].replace("start_date=", "").substring(8, 10)));
                    LocalDateTime datetime = date.atTime(
                            Integer.parseInt(temp[1].replace("start_date=", "").substring(11, 13)),
                            Integer.parseInt(temp[1].replace("start_date=", "").substring(14, 16)),
                            Integer.parseInt(temp[1].replace("start_date=", "").substring(17, 19)));
                    String[] compTemp = temp[6].replace("competitors=[", "").split("},");
                    String[] venueTemp = temp[7].replace("venue=", "").replace("{id=", "").split(",");
                    String[] tempHome = compTemp[0].split(",");
                    String[] tempAway = compTemp[1].split(",");
                    Venue venue;
                    if (venueTemp[0] == null) {
                        venue = venueRepository.findAllById("null");
                    } else {
                        venue = venueRepository.findAllById(venueTemp[0]);
                    }
                    String strHomeProb = homeProb.get(i).toString();
                    String strDrawProb = drawProb.get(i).toString();
                    String strAwayProb = awayProb.get(i).toString();
                    eventRepository.save(new Event(temp[0].replace("{sport_event_id=", ""),
                            datetime,
                            temp[2].replace("sport_name=", ""),
                            temp[3].replace("competition_name=", ""),
                            temp[4].replace("competition_id=", ""),
                            temp[5].replace("season_name=", ""),
                            Double.parseDouble(strHomeProb),
                            Double.parseDouble(strDrawProb),
                            Double.parseDouble(strAwayProb),
                            venue,
                            competitorRepository.findAllById(tempHome[0].replace("{\"id\":\"", "").replace("\"", "")),
                            competitorRepository.findAllById(tempAway[0].replace("{\"id\":\"", "").replace("\"", ""))));
                }
            }
        };
    }
}
