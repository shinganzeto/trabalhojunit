package br.com.trier.springvespertino.services;

import br.com.trier.springvespertino.models.Team;
import br.com.trier.springvespertino.repositories.TeamRepository;
import br.com.trier.springvespertino.services.impl.TeamServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TeamServiceTest {
    @InjectMocks
    private TeamServiceImpl teamService;
    @Mock
    private TeamRepository teamRepository;
    @Test
    void testSalvar() {
        Team team = new Team(1, "Team A");
        when(teamRepository.save(any())).thenReturn(team);
        var user = teamService.salvar(team);
        assertEquals(1, team.getId());
    }

    @Test
    void testListAll() {
        List<Team> teams = new ArrayList<>();
        teams.add(new Team(1, "Team A"));
        teams.add(new Team(2, "Team B"));
        teams.add(new Team(3, "Team C"));

        when(teamRepository.findAll()).thenReturn(teams);
        List<Team> result = teamService.listAll();
        assertEquals(3, result.size());
    }

    @Test
    void testFindById() {
        Team team = new Team(1, "Team A");

        when(teamRepository.findById(1)).thenReturn(Optional.of(team));

        Team foundTeam = teamService.findById(1);

        assertEquals(team, foundTeam);
    }

    @Test
    void testUpdate() {
        Team team = new Team(1, "Team A");

        when(teamRepository.findById(1)).thenReturn(Optional.of(team));
        when(teamRepository.save(team)).thenReturn(team);
        var upTeam = teamService.update((team));
        assertEquals(1, upTeam.getId());

    }

    @Test
    void testFindByNameIgnoreCase() {
        List<Team> teams = new ArrayList<>();
        teams.add(new Team(1, "Team A"));
        teams.add(new Team(2, "team a"));

        when(teamRepository.findByNameIgnoreCase("team A")).thenReturn(teams);
        List<Team> result = teamService.findByNameIgnoreCase("team A");
        assertEquals(2, result.size());

    }

    @Test
    void testFindByNameContains() {
        List<Team> teams = new ArrayList<>();
        teams.add(new Team(1, "Team A"));
        teams.add(new Team(2, "A Team"));

        when(teamRepository.findByNameContains("A")).thenReturn(teams);

        List<Team> result = teamService.findByNameContains("A");

        assertEquals(2, result.size());
    }
}
