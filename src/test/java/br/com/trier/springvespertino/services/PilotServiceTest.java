package br.com.trier.springvespertino.services;

import br.com.trier.springvespertino.models.Country;
import br.com.trier.springvespertino.models.Pilot;
import br.com.trier.springvespertino.models.Team;
import br.com.trier.springvespertino.repositories.PilotRepository;
import br.com.trier.springvespertino.services.impl.PilotServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
public class PilotServiceTest {
    @InjectMocks
    private PilotServiceImpl pilotService;
    @Mock
    private PilotRepository pilotRepository;

    @Test
    void testFindById() {
        Country country = new Country(1, "Country A");
        Team team = new Team(1, "Team A");
        Pilot pilot = new Pilot(1, "Pilot A", country, team);

        when(pilotRepository.findById(1)).thenReturn(Optional.of(pilot));
        Pilot foundPilot = pilotService.findById(1);
        assertEquals(pilot, foundPilot);
    }

    @Test
    void testInsert() {
        Country country = new Country(1, "Country A");
        Team team = new Team(1, "Team A");
        Pilot pilot = new Pilot(1, "Pilot A", country, team);

        when(pilotRepository.save(pilot)).thenReturn(pilot);
        Pilot insertedPilot = pilotService.insert(pilot);
        assertEquals(pilot, insertedPilot);

    }

    @Test
    void testListAll() {
        Country country = new Country(1, "Country A");
        Team team = new Team(1, "Team A");
        List<Pilot> pilots = new ArrayList<>();
        pilots.add(new Pilot(1, "Pilot A", country, team));
        pilots.add(new Pilot(2, "Pilot B", country, team));
        pilots.add(new Pilot(3, "Pilot C", country, team));

        when(pilotRepository.findAll()).thenReturn(pilots);
        List<Pilot> result = pilotService.listAll();
        assertEquals(3, result.size());
    }

    @Test
    void testUpdate() {
        Country country = new Country(1, "Country A");
        Team team = new Team(1, "Team A");
        Pilot pilot = new Pilot(1, "Pilot A", country, team);

        when(pilotRepository.findById(1)).thenReturn(Optional.of(pilot));
        when(pilotRepository.save(pilot)).thenReturn(pilot);
        var upRace = pilotService.update((pilot));
        assertEquals(pilot, upRace);
    }

    @Test
    void testFindByNameStartsWithIgnoreCase() {
        Country country = new Country(1, "Country A");
        Team team = new Team(1, "Team A");
        List<Pilot> pilots = new ArrayList<>();
        pilots.add(new Pilot(1, "Pilot A", country, team));
        pilots.add(new Pilot(2, "pilot a", country, team));

        when(pilotRepository.findByNameStartsWithIgnoreCase("Pilot")).thenReturn(pilots);
        List<Pilot> result = pilotService.findByNameStartsWithIgnoreCase("Pilot");
        assertEquals(2, result.size());

    }

    @Test
    void testFindByCountry() {
        Country country = new Country(1, "Country A");
        Team team = new Team(1, "Team A");

        List<Pilot> pilots = new ArrayList<>();
        pilots.add(new Pilot(1, "Pilot A", country, team));
        pilots.add(new Pilot(2, "Pilot B", country, team));

        when(pilotRepository.findByCountry(country)).thenReturn(pilots);
        List<Pilot> result = pilotService.findByCountry(country);
        assertEquals(2, result.size());

    }

    @Test
    void testFindByTeam() {
        Country country = new Country(1, "Country A");
        Team team = new Team(1, "Team A");
        List<Pilot> pilots = new ArrayList<>();
        pilots.add(new Pilot(1, "Pilot A",country, team));
        pilots.add(new Pilot(2, "Pilot B", country,team));

        when(pilotRepository.findByTeam(team)).thenReturn(pilots);
        List<Pilot> result = pilotService.findByTeam(team);
        assertEquals(2, result.size());

    }
}