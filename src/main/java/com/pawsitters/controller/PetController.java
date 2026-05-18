package com.pawsitters.controller;

import com.pawsitters.model.AnimalType;
import com.pawsitters.model.Pet;
import com.pawsitters.service.PetOwnerService;
import com.pawsitters.service.PetService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/pets")
public class PetController {

    private final PetService petService;
    private final PetOwnerService ownerService;

    public PetController(PetService petService, PetOwnerService ownerService) {
        this.petService = petService;
        this.ownerService = ownerService;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("pets", petService.findAll());
        return "pets/list";
    }

    @GetMapping("/new")
    public String newForm(Model model) {
        model.addAttribute("pet", new Pet());
        model.addAttribute("owners", ownerService.findAll());
        model.addAttribute("animalTypes", AnimalType.values());
        return "pets/form";
    }

    @PostMapping
    public String create(@ModelAttribute Pet pet,
                         @RequestParam Long ownerId,
                         Model model) {
        try {
            petService.register(pet, ownerId);
            return "redirect:/pets";
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("pet", pet);
            model.addAttribute("owners", ownerService.findAll());
            model.addAttribute("animalTypes", AnimalType.values());
            return "pets/form";
        }
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        petService.deleteById(id);
        return "redirect:/pets";
    }
}
