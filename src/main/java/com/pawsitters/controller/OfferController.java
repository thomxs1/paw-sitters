package com.pawsitters.controller;

import com.pawsitters.model.Offer;
import com.pawsitters.service.CareRequestService;
import com.pawsitters.service.HostService;
import com.pawsitters.service.OfferService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@Controller
@RequestMapping("/offers")
public class OfferController {

    private final OfferService offerService;
    private final HostService hostService;
    private final CareRequestService requestService;

    public OfferController(OfferService offerService,
                           HostService hostService,
                           CareRequestService requestService) {
        this.offerService = offerService;
        this.hostService = hostService;
        this.requestService = requestService;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("offers", offerService.findAll());
        return "offers/list";
    }

    /**
     * Formular fuer den Gastgeber, um ein Angebot zu einer Anfrage zu senden.
     */
    @GetMapping("/new")
    public String newForm(@RequestParam Long requestId, Model model) {
        model.addAttribute("requestId", requestId);
        model.addAttribute("hosts", hostService.findAll());
        model.addAttribute("request", requestService.findById(requestId).orElse(null));
        return "offers/form";
    }

    @PostMapping
    public String create(@RequestParam Long hostId,
                         @RequestParam Long requestId,
                         @RequestParam BigDecimal totalPrice,
                         @RequestParam(required = false) String message,
                         Model model) {
        try {
            offerService.createOffer(hostId, requestId, totalPrice, message);
            return "redirect:/requests/" + requestId;
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("requestId", requestId);
            model.addAttribute("hosts", hostService.findAll());
            model.addAttribute("request", requestService.findById(requestId).orElse(null));
            return "offers/form";
        }
    }

    /**
     * Annahme eines Angebots durch den Tierhalter.
     * Alle anderen Angebote zur gleichen Anfrage werden automatisch abgelehnt.
     */
    @PostMapping("/{id}/accept")
    public String accept(@PathVariable Long id) {
        Offer offer = offerService.acceptOffer(id);
        return "redirect:/requests/" + offer.getRequest().getId();
    }

    /**
     * Manuelle Ablehnung eines Angebots durch den Tierhalter.
     */
    @PostMapping("/{id}/reject")
    public String reject(@PathVariable Long id) {
        Offer offer = offerService.rejectOffer(id);
        return "redirect:/requests/" + offer.getRequest().getId();
    }
}
