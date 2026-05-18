package com.pawsitters.service;

import com.pawsitters.model.AnimalType;
import com.pawsitters.model.Host;
import com.pawsitters.repository.HostRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Service fuer Gastgeber (Hosts).
 * Verwaltet Profile von Gastgebern und kapselt die Suche nach passenden Gastgebern.
 */
@Service
public class HostService {

    private final HostRepository hostRepository;

    public HostService(HostRepository hostRepository) {
        this.hostRepository = hostRepository;
    }

    /**
     * Erstellt einen neuen Gastgeber.
     */
    public Host create(Host host) {
        if (host.getEmail() == null || host.getEmail().isBlank()) {
            throw new IllegalArgumentException("E-Mail ist erforderlich");
        }
        if (hostRepository.findByEmail(host.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Ein Gastgeber mit dieser E-Mail existiert bereits");
        }
        if (host.getAvailableFrom() != null && host.getAvailableUntil() != null
                && host.getAvailableUntil().isBefore(host.getAvailableFrom())) {
            throw new IllegalArgumentException("Verfuegbarkeitsende darf nicht vor dem Anfang liegen");
        }
        return hostRepository.save(host);
    }

    public List<Host> findAll() {
        return hostRepository.findAll();
    }

    public Optional<Host> findById(Long id) {
        return hostRepository.findById(id);
    }

    /**
     * Findet alle Gastgeber, die eine bestimmte Tierart in einem
     * gegebenen Zeitraum aufnehmen koennen.
     */
    public List<Host> findMatchingHosts(AnimalType animalType, LocalDate from, LocalDate until) {
        return hostRepository.findAll().stream()
                .filter(h -> h.canAccommodate(animalType, from, until))
                .toList();
    }

    public void deleteById(Long id) {
        hostRepository.deleteById(id);
    }
}
