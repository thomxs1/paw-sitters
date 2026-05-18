package com.pawsitters.controller;

import com.pawsitters.model.PetOwner;
import com.pawsitters.service.PetOwnerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/owners")
public class PetOwnerController {

    private final PetOwnerService service;

    public PetOwnerController(PetOwnerService service) {
        this.service = service;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("owners", service.findAll());
        return "owners/list";
    }

    @GetMapping("/new")
    public String newForm(Model model) {
        model.addAttribute("owner", new PetOwner());
        return "owners/form";
    }

    @PostMapping
    public String create(@ModelAttribute PetOwner owner, Model model) {
        try {
            service.create(owner);
            return "redirect:/owners";
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("owner", owner);
            return "owners/form";
        }
    }

    @GetMapping("/{id}")
    public String detail(@PathVariable Long id, Model model) {
        PetOwner owner = service.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Tierhalter nicht gefunden"));
        model.addAttribute("owner", owner);
        return "owners/detail";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        service.deleteById(id);
        return "redirect:/owners";
    }
}
