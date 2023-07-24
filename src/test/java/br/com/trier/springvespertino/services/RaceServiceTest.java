package br.com.trier.springvespertino.services;

import br.com.trier.springvespertino.models.Championship;
import br.com.trier.springvespertino.models.Country;
import br.com.trier.springvespertino.models.Race;
import br.com.trier.springvespertino.models.Speedway;
import br.com.trier.springvespertino.repositories.RaceRepository;
import br.com.trier.springvespertino.services.impl.RaceServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RaceServiceTest {
    @InjectMocks
    private RaceServiceImpl raceService;

    @Mock
    private RaceRepository raceRepository;
    @Test
    void testFindById() {
        Country country = new Country(1, "Country A");
        Speedway speedway = new Speedway(1, "Speedway A", 100, country);
        Championship ch = new Championship(1, "Champ", 2000);
        Race race = new Race(1, ZonedDateTime.now(), speedway, ch);

        when(raceRepository.findById(1)).thenReturn(Optional.of(race));
        Race foundRace = raceService.findById(1);
        assertEquals(race, foundRace);
    }

    @Test
    void testInsert() {
        Country country = new Country(1, "Country A");
        Speedway speedway = new Speedway(1, "Speedway A", 1300, country);
        Championship ch = new Championship(1, "Champ", 2023);
        Race race = new Race(1, ZonedDateTime.now(), speedway, ch);

        when(raceRepository.save(race)).thenReturn(race);
        Race insertedRace = raceService.insert(race);
        assertEquals(race, insertedRace);

    }

    @Test
    void testListAll() {
        Country country = new Country(1, "Country A");
        Speedway speedway = new Speedway(1, "Speedway A",100, country);
        Championship ch = new Championship(1, "Champ", 2000);
        List<Race> races = new ArrayList<>();
        races.add(new Race(1, ZonedDateTime.now(), speedway, ch));
        races.add(new Race(2, ZonedDateTime.now(), speedway, ch));
        races.add(new Race(3, ZonedDateTime.now(), speedway, ch));

        when(raceRepository.findAll()).thenReturn(races);
        List<Race> result = raceService.listAll();
        assertEquals(3, result.size());

    }

    @Test
    void testUpdate() {
        Country country = new Country(1, "Country A");
        Speedway speedway = new Speedway(1, "Speedway A",1000, country);
        Championship ch = new Championship(1, "Champ", 2023);
        Race race = new Race(1, ZonedDateTime.now(), speedway, ch);

        when(raceRepository.findById(1)).thenReturn(Optional.of(race));
        when(raceRepository.save(race)).thenReturn(race);
        var upRace = raceService.update((race));
        assertEquals(race, upRace);

    }
/*
    @Test
    void testFindByDate() {
        Country country = new Country(1, "Country A");
        Speedway speedway = new Speedway(1, "Speedway A", 1000, country);
        Championship ch = new Championship(1, "Champ", 2000);
        List<Race> races = new ArrayList<>();
        races.add(new Race(1, ZonedDateTime.now(), speedway, ch));
        races.add(new Race(2, ZonedDateTime.now(), speedway, ch));

        when(raceRepository.findByDate(ZonedDateTime.now())).thenReturn(races);
        List<Race> result = raceService.findByDate(ZonedDateTime.now());
        assertEquals(2, result.size());

    }
*/
    @Test
    void testFindBySpeedway() {
        Country country = new Country(1, "Country A");
        Speedway speedway = new Speedway(1, "Speedway A",1000, country);
        Championship ch = new Championship(1, "Champ", 2000);

        List<Race> races = new ArrayList<>();
        races.add(new Race(1, ZonedDateTime.now(), speedway, ch));
        races.add(new Race(2, ZonedDateTime.now(), speedway, ch));

        when(raceRepository.findBySpeedway(speedway)).thenReturn(races);

        List<Race> result = raceService.findBySpeedway(speedway);

        assertEquals(2, result.size());
    }

    @Test
    void testFindByChampionship() {
        Country country = new Country(1, "Country A");
        Speedway speedway = new Speedway(1, "Speedway A",1000, country);
        Championship ch = new Championship(1, "Champ", 2000);
        List<Race> races = new ArrayList<>();
        races.add(new Race(1, ZonedDateTime.now(), speedway, ch));
        races.add(new Race(2, ZonedDateTime.now(), speedway, ch));

        when(raceRepository.findByChampionship(ch)).thenReturn(races);

        List<Race> result = raceService.findByChampionship(ch);

        assertEquals(2, result.size());
    }
}

