package com.epam.cdp.springbootlab.good;

import com.epam.cdp.springbootlab.logging.Log;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Supplier;

@Log4j2
@RestController
@RequestMapping(path = "goods")
public class GoodResourceREST implements GoodResource {

    private final GoodRepository goodRepository;
    private final GoodResourceClient goodResourceClient;

    @Autowired
    GoodResourceREST(GoodRepository goodRepository,
                     GoodResourceClient goodResourceClient) {
        this.goodRepository = goodRepository;
        this.goodResourceClient = goodResourceClient;
    }

    @Log
    @GetMapping
    public Iterable<Good> get() {
        return goodRepository.findAll();
    }

    @Log
    @GetMapping("/{id}")
    public Optional<Good> getById(@PathVariable(value = "id") Long id) {
        return goodRepository.findById(id);
    }

    @Log
    @PostMapping
    public Good create(@RequestBody Good good) {
        return goodRepository.save(good);
    }

    @Log
    @PutMapping("/{id}")
    public Good update(@PathVariable(value = "id") Long id, @RequestBody Good good) throws Throwable {
        if(good.getId() != null && !Objects.equals(id, good.getId())) {
            throw new IllegalArgumentException("Bad argument");
        }
        good.setId(id);

        goodRepository.findById(id).orElseThrow((Supplier<Throwable>) () -> new RuntimeException("not found"));

        return goodRepository.save(good);
    }

    @Log
    @DeleteMapping("/{id}")
    public String delete(@PathVariable(value = "id") Long id) throws Throwable {
        goodRepository.findById(id).orElseThrow((Supplier<Throwable>) () -> new RuntimeException("not found"));
        goodRepository.deleteById(id);
        return "ok";
    }

    @GetMapping(path = "test")
    public Optional<Good> test() throws Throwable {
        return goodResourceClient.getById(5L);
    }
}
