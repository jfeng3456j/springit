package com.feng.springit.service;


import com.feng.springit.domain.Link;
import com.feng.springit.repository.LinkRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LinkService {
    private final LinkRepository linkRepository;

    public LinkService(LinkRepository linkRepository) {
        this.linkRepository = linkRepository;
    }

    public List<Link> findAllLink () {
        return linkRepository.findAll();
    }

    public Optional<Link> findLinkById (Long id) {
        return linkRepository.findById(id);
    }

    public Link saveLink (Link link) {
        return linkRepository.save(link);
    }
}
