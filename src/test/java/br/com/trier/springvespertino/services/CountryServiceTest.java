package br.com.trier.springvespertino.services;

import br.com.trier.springvespertino.models.Country;
import br.com.trier.springvespertino.repositories.CountryRepository;
import br.com.trier.springvespertino.services.impl.CountryServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
public class CountryServiceTest {
    @InjectMocks
    private CountryServiceImpl countryService;

    @Mock
    private CountryRepository countryRepository;

    @Test
    void testSalvar() {
        Country country = new Country(1, "Country A");

        when(countryRepository.save(country)).thenReturn(country);
        Country savedCountry = countryService.salvar(country);
        assertEquals(country, savedCountry);

    }

    @Test
    void testUpdate() {
        Country country = new Country(1, "Country A");

        when(countryRepository.save(country)).thenReturn(country);
        Country updatedCountry = countryService.update(country);
        assertEquals(country, updatedCountry);
    }

    @Test
    void testListAll() {
        List<Country> countries = new ArrayList<>();
        countries.add(new Country(1, "Country A"));
        countries.add(new Country(2, "Country B"));
        countries.add(new Country(3, "Country C"));

        when(countryRepository.findAll()).thenReturn(countries);
        List<Country> result = countryService.listAll();
        assertEquals(3, result.size());

    }

    @Test
    void testFindByIdExistingId() {
        Country country = new Country(1, "Country A");
        when(countryRepository.findById(1)).thenReturn(Optional.of(country));
        Country foundCountry = countryService.findById(1);
        assertEquals(country, foundCountry);
    }


    @Test
    void testFindByNomeEqualsIgnoreCaseExistingName() {
        List<Country> countries = new ArrayList<>();
        countries.add(new Country(1, "Country A"));

        when(countryService.findByNomeEqualsIgnoreCase("Country A")).thenReturn(countries);
        List<Country> result = countryService.findByNomeEqualsIgnoreCase("Country A");
        assertEquals(1, result.size());
        assertEquals(countries, result);
    }

    @Test
    void testFindByNomeEqualsIgnoreCaseNonExistingName() {
        when(countryService.findByNomeEqualsIgnoreCase("Country A")).thenReturn(new ArrayList<>());
        List<Country> result = countryService.findByNomeEqualsIgnoreCase("Country A");
        assertEquals(0, result.size());
    }
}
