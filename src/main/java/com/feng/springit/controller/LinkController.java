package com.feng.springit.controller;

import com.feng.springit.domain.Link;
import com.feng.springit.repository.LinkRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/links")
public class LinkController {

    private LinkRepository linkRepository;

    //this constructor has a bean - link repo, spring is going to autowire the linkrepo class into here
    public LinkController(LinkRepository linkRepository) {
        this.linkRepository = linkRepository;
    }

    //list
    @GetMapping("/")
    public List<Link> list() {
        return linkRepository.findAll();
    }

    //CRUD
    @PostMapping("/create")
    public Link create(@ModelAttribute Link link) {
        return linkRepository.save(link);
    }

    @GetMapping("/{id}")
    public Optional<Link> read(@PathVariable Long id) {
        return linkRepository.findById(id);
    }

    @PutMapping("/{id}")
    public Link update(@PathVariable Long id, @ModelAttribute Link link) {
        //get the id
        return linkRepository.save(link);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
         linkRepository.deleteById(id);
    }

}
