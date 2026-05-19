package com.pawsitters.controller;

import com.pawsitters.model.AnimalType;
import com.pawsitters.model.Host;
import com.pawsitters.service.HostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;

@Controller
@RequestMapping("/hosts")
public class HostController {

    private final HostService service;

    public HostController(HostService service) {
        this.service = service;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("hosts", service.findAll());
        return "hosts/list";
    }

    @GetMapping("/new")
    public String newForm(Model model) {
        model.addAttribute("host", new Host());
        model.addAttribute("animalTypes", AnimalType.values());
        return "hosts/form";
    }

    @PostMapping
    public String create(@ModelAttribute Host host,
                         @RequestParam(required = false) List<AnimalType> animals,
                         Model model) {
        try {
            host.setAcceptedAnimals(animals == null ? new HashSet<>() : new HashSet<>(animals));
            service.create(host);
            return "redirect:/hosts";
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("host", host);
            model.addAttribute("animalTypes", AnimalType.values());
            return "hosts/form";
        }
    }

    @GetMapping("/{id}")
    public String detail(@PathVariable Long id, Model model) {
        Host host = service.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Gastgeber nicht gefunden"));
        model.addAttribute("host", host);
        return "hosts/detail";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        service.deleteById(id);
        return "redirect:/hosts";
    }
}
