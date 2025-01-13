package com.example.tp3.controller;

import com.example.tp3.repositories.AdresseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AddressController {
    @Autowired
    AdresseRepository addressRepository;
    @GetMapping("/adresses")
    public String showAddresses(Model model) {
        model.addAttribute("allAddresses", addressRepository.findAll());
        return "addresses";
    }

    @GetMapping("/adresse")
    public String showAddressForm() {
        return "adresse"; // Le nom du fichier HTML du formulaire d'adresse
    }
}
