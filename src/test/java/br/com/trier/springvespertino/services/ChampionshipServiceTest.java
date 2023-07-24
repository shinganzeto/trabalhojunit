package br.com.trier.springvespertino.services;

import br.com.trier.springvespertino.models.Championship;
import br.com.trier.springvespertino.repositories.ChampionshipRepository;
import br.com.trier.springvespertino.services.impl.ChampionshipServiceImpl;
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
public class ChampionshipServiceTest {
    @InjectMocks
    private ChampionshipServiceImpl championshipService;

    @Mock
    private ChampionshipRepository championshipRepository;

    @Test
    void testFindById() {
        Championship championship = new Championship(1, "Championship A", 2000);

        when(championshipRepository.findById(1)).thenReturn(Optional.of(championship));
        Championship foundChampionship = championshipService.findById(1);
        assertEquals(championship, foundChampionship);
    }

    @Test
    void testInsert() {
        Championship championship = new Championship(1, "Championship A", 2023);

        when(championshipRepository.save(championship)).thenReturn(championship);
        Championship insertedChampionship = championshipService.insert(championship);
        assertEquals(championship, insertedChampionship);
    }

    @Test
    void testListAll() {
        List<Championship> championships = new ArrayList<>();
        championships.add(new Championship(1, "Championship A", 2000));
        championships.add(new Championship(2, "Championship B", 2000));
        championships.add(new Championship(3, "Championship C", 2000));

        when(championshipRepository.findAll()).thenReturn(championships);
        List<Championship> result = championshipService.listAll();
        assertEquals(3, result.size());
    }

    @Test
    void testUpdate() {
        Championship championship = new Championship(1, "Championship A",2023);

        when(championshipRepository.save(championship)).thenReturn(championship);
        Championship updatedChampionship = championshipService.update(championship);
        assertEquals(championship, updatedChampionship);
    }

    @Test
    void testFindByYearBetween() {
        List<Championship> championships = new ArrayList<>();
        championships.add(new Championship(1, "Championship A", 2021));
        championships.add(new Championship(2, "Championship B", 2022));

        when(championshipRepository.findByYearBetween(2021, 2023)).thenReturn(championships);
        List<Championship> result = championshipService.findByYearBetween(2021, 2023);
        assertEquals(2, result.size());

    }

    @Test
    void testFindByYear() {
        List<Championship> championships = new ArrayList<>();
        championships.add(new Championship(1, "Championship A", 2021));
        championships.add(new Championship(2, "Championship B", 2021));

        when(championshipRepository.findByYear(2021)).thenReturn(championships);
        List<Championship> result = championshipService.findByYear(2021);
        assertEquals(2, result.size());
    }

    @Test
    void testFindByDescriptionContainsIgnoreCase() {
        List<Championship> championships = new ArrayList<>();
        championships.add(new Championship(1, "Championship A", 2000));
        championships.add(new Championship(2, "Championship ABC", 2000));

        when(championshipRepository.findByDescriptionContainsIgnoreCase("Championship")).thenReturn(championships);
        List<Championship> result = championshipService.findByDescriptionContainsIgnoreCase("Championship");
        assertEquals(2, result.size());
    }

    @Test
    void testFindByescriptionContainsIgnoreCaseAndAnoEquals() {
        List<Championship> championships = new ArrayList<>();
        championships.add(new Championship(1, "Championship A", 2021));
        championships.add(new Championship(2, "Championship ABC", 2021));

        when(championshipService.findByescriptionContainsIgnoreCaseAndAnoEquals("Championship", 2021)).thenReturn(championships);
        List<Championship> result = championshipService.findByescriptionContainsIgnoreCaseAndAnoEquals("Championship", 2021);
        assertEquals(2, result.size());
    }
}