package com.pawsitters.controller;

import com.pawsitters.model.CareRequest;
import com.pawsitters.model.RequestStatus;
import com.pawsitters.service.CareRequestService;
import com.pawsitters.service.PetService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
@RequestMapping("/requests")
public class CareRequestController {

    private final CareRequestService requestService;
    private final PetService petService;

    public CareRequestController(CareRequestService requestService, PetService petService) {
        this.requestService = requestService;
        this.petService = petService;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("requests", requestService.findAll());
        return "requests/list";
    }

    @GetMapping("/new")
    public String newForm(Model model) {
        model.addAttribute("pets", petService.findAll());
        return "requests/form";
    }

    @PostMapping
    public String create(@RequestParam Long petId,
                         @RequestParam String startDate,
                         @RequestParam String endDate,
                         Model model) {
        try {
            requestService.create(petId, LocalDate.parse(startDate), LocalDate.parse(endDate));
            return "redirect:/requests";
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("pets", petService.findAll());
            return "requests/form";
        }
    }

    /**
     * Detailansicht einer Anfrage: zeigt vorhandene Angebote
     * und passende Gastgeber an.
     */
    @GetMapping("/{id}")
    public String detail(@PathVariable Long id, Model model) {
        CareRequest request = requestService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Anfrage nicht gefunden"));
        model.addAttribute("request", request);
        model.addAttribute("matchingHosts", requestService.findMatchingHostsForRequest(id));
        return "requests/detail";
    }

    /**
     * Aktualisiert den Status einer Anfrage (z. B. CANCELLED).
     */
    @PostMapping("/{id}/status")
    public String updateStatus(@PathVariable Long id, @RequestParam RequestStatus status) {
        requestService.updateStatus(id, status);
        return "redirect:/requests/" + id;
    }
}
