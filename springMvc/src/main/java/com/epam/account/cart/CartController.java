package com.epam.account.cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class CartController {

    @Autowired
    GoodRepository goodRepository;

    @GetMapping(value = "/cart")
    public String cart(Model model) {
        model.addAttribute("goods", goodRepository.findAll());
        return "cart";
    }

    @GetMapping(value = "/cart/delete/{id}")
    public String cart(Model model, @PathVariable("id") Long id) {
        goodRepository.delete(id);
        return "redirect:/cart";
    }

    @PostMapping(value = "/cart/add")
    public String add(Model model, @RequestParam("name") String name, @RequestParam("price") Integer price, @RequestParam("quantity") Integer quantity) {
        Good good = new Good();
        good.setName(name);
        good.setPrice(price);
        good.setQuantity(quantity);
        goodRepository.save(good);

        return "redirect:/cart";
    }

    @PostMapping(value = "/cart/edit/{id}")
    public String add(Model model, @PathVariable("id") Long id, @RequestParam("quantity") Integer quantity) {

        Good good = goodRepository.findOne(id);
        good.setQuantity(quantity);
        goodRepository.save(good);

        return "redirect:/cart";
    }


}
