package br.com.trier.springvespertino.services;

import br.com.trier.springvespertino.models.Country;
import br.com.trier.springvespertino.models.Speedway;
import br.com.trier.springvespertino.repositories.SpeedwayRepository;
import br.com.trier.springvespertino.services.impl.SpeedwayServiceImpl;
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
public class SpeedwayServiceTest {
    @InjectMocks
    private SpeedwayServiceImpl speedwayService;

    @Mock
    private SpeedwayRepository speedwayRepository;

    @Test
    void testFindById() {
        Country country = new Country(1, "Country A");
        Speedway speedway = new Speedway(1, "Speedway A", 100, country);

        when(speedwayRepository.findById(1)).thenReturn(Optional.of(speedway));

        Speedway foundSpeedway = speedwayService.findById(1);

        assertEquals(1, foundSpeedway.getId());
    }

    @Test
    void testInsert() {
        Country country = new Country(1, "Country A");
        Speedway speedway = new Speedway(1, "Speedway A", 1000, country);
        when(speedwayService.insert(speedway)).thenReturn(speedway);
        Speedway insertedSpeedway = speedwayService.insert(speedway);
        assertEquals(speedway, insertedSpeedway);
    }

    @Test
    void testListAll() {
        Country country = new Country(1, "Country A");
        List<Speedway> speedways = new ArrayList<>();
        speedways.add(new Speedway(1, "Speedway A", 1000, country));
        speedways.add(new Speedway(2, "Speedway B", 1000, country));
        speedways.add(new Speedway(3, "Speedway C", 100, country));

        when(speedwayRepository.findAll()).thenReturn(speedways);
        List<Speedway> result = speedwayService.listAll();
        assertEquals(3, result.size());
    }

    @Test
    void testUpdate() {
        Country country = new Country(1, "Country A");
        Speedway speedway = new Speedway(1, "Speedway A", 300, country);

        when(speedwayRepository.findById(1)).thenReturn(Optional.of(speedway));
        when(speedwayRepository.save(speedway)).thenReturn(speedway);
        var upTeam = speedwayService.update((speedway));
        assertEquals(1, upTeam.getId());

    }

    @Test
    void testFindByNameStartsWithIgnoreCase() {
        Country country = new Country(1, "Country A");
        List<Speedway> speedways = new ArrayList<>();
        speedways.add(new Speedway(1, "Speedway A", 300, country));
        speedways.add(new Speedway(2, "speedway a",500, country));

        when(speedwayRepository.findByNameStartsWithIgnoreCase("Speedway")).thenReturn(speedways);
        List<Speedway> result = speedwayService.findByNameStartsWithIgnoreCase("Speedway");
        assertEquals(2, result.size());
    }

    @Test
    void testFindBySizeBetween() {
        Country country = new Country(1, "Country A");
        List<Speedway> speedways = new ArrayList<>();
        speedways.add(new Speedway(1, "Speedway A", 500, country));
        speedways.add(new Speedway(2, "Speedway B", 1000, country));

        when(speedwayRepository.findBySizeBetween(800, 1200)).thenReturn(speedways);
        List<Speedway> result = speedwayService.findBySizeBetween(800, 1200);
        assertEquals(2, result.size());
    }

    @Test
    void testFindByCountryOrderBySizeDesc() {
        Country country = new Country(1, "Country A");
        List<Speedway> speedways = new ArrayList<>();
        speedways.add(new Speedway(1, "Speedway A", 500, country));
        speedways.add(new Speedway(2, "Speedway B", 1000, country));
        speedways.add(new Speedway(3, "Speedway C", 800, country));

        when(speedwayRepository.findByCountryOrderBySizeDesc(country)).thenReturn(speedways);
        List<Speedway> result = speedwayService.findByCountryOrderBySizeDesc(country);
        assertEquals(3, result.size());
    }
}
