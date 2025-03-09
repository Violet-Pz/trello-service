package com.tsukilaw.trello.service;

import com.tsukilaw.trello.entity.Issue;
import com.tsukilaw.trello.repository.IssueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class IssueService {
    private final IssueRepository issueRepository;
    @Autowired
    public IssueService(IssueRepository issueRepository) {
        this.issueRepository = issueRepository;
    }

    public Issue createIssue(Issue issue) {
        return issueRepository.save(issue);
    }
}
