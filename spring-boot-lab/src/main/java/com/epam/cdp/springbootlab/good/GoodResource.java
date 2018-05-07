package com.epam.cdp.springbootlab.good;

import com.epam.cdp.springbootlab.logging.Log;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

public interface GoodResource {

    @Log
    @GetMapping
    public Iterable<Good> get();

    @Log
    @GetMapping("/{id}")
    public Optional<Good> getById(@PathVariable(value = "id") Long id);

    @Log
    @PostMapping
    public Good create(@RequestBody Good good);

    @Log
    @PutMapping("/{id}")
    public Good update(@PathVariable(value = "id") Long id, @RequestBody Good good) throws Throwable;

    @Log
    @DeleteMapping("/{id}")
    public String delete(@PathVariable(value = "id") Long id) throws Throwable;
}
