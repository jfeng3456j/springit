package com.feng.springit.controller;

import com.feng.springit.domain.Link;
import com.feng.springit.domain.Vote;
import com.feng.springit.repository.LinkRepository;
import com.feng.springit.repository.VoteRepository;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class VoteController {

    //controller component returns a representation of a view (html page) in template
    //restcontroller component returns a API response, no view

    private VoteRepository voteRepository;
    private LinkRepository linkRepository;

    public VoteController(VoteRepository voteRepository, LinkRepository linkRepository) {
        this.voteRepository = voteRepository;
        this.linkRepository = linkRepository;
    }

    //localhost:8080/vote/link/id/direction/-1/votecount/5
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @GetMapping("/vote/link/{linkID}/direction/{direction}/votecount/{voteCount}")
    public int vote(@PathVariable Long linkID, @PathVariable short direction, @PathVariable int voteCount) {
        Optional<Link> optionLink = linkRepository.findById(linkID);
        if (optionLink.isPresent()) {
            Link link = optionLink.get();
            Vote vote = new Vote(direction, link);
            voteRepository.save(vote);

            int updatedVoteCount = voteCount + direction;
            link.setVoteCount(updatedVoteCount);
            linkRepository.save(link);

            return updatedVoteCount;
        }
        return voteCount;
    }
}
