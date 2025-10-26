package com.tsukilaw.trello.service;

import com.tsukilaw.trello.entity.Issue;
import com.tsukilaw.trello.entity.IssueStatus;
import com.tsukilaw.trello.repository.IssueRepository;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Service
public class IssueService {
    private final IssueRepository issueRepository;
    private final Logger LOGGER = LoggerFactory.getLogger(IssueService.class);
    @Autowired
    public IssueService(IssueRepository issueRepository) {
        this.issueRepository = issueRepository;
    }

    public Issue createIssue(Issue issue) {
        return issueRepository.save(issue);
    }

    public List<Issue> getIssues () {
        return issueRepository.findAll();
    }

    public Issue getIssueById (Long issueId) {
        Optional<Issue> issue = issueRepository.findById(issueId);
        return issue.orElse(null);
    }

    public List<Issue> getIssuesByStatus(IssueStatus status) {
        return issueRepository.findByStatus(status);
    }

    public Issue updateIssue(Long id, Issue updatedIssue) {
        Optional<Issue> existingIssue = issueRepository.findById(id);

        if (existingIssue.isEmpty()) {
            LOGGER.warn("Issue with id {} not found", id);
            throw new EntityNotFoundException("Issue with id " + id + " not found");
        }

        Issue issue = existingIssue.get();
        issue.setTitle(updatedIssue.getTitle());
        issue.setDescription(updatedIssue.getDescription());
        issue.setStatus(updatedIssue.getStatus());
        issue.setUpdatedAt(LocalDateTime.now());

        return issueRepository.save(issue);
    }

    public void deleteIssueById(Long issueId) {
        issueRepository.deleteById(issueId);
    }
}
