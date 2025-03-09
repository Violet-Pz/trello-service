package com.tsukilaw.trello.controller;

import com.tsukilaw.trello.entity.Issue;
import com.tsukilaw.trello.service.IssueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/issues")
public class IssueController {
    private final IssueService issueService;
    @Autowired
    public IssueController(IssueService issueService) {
        this.issueService = issueService;
    }
    @PostMapping
    public ResponseEntity<Issue> createIssue(@RequestBody Issue issue) {
        return ResponseEntity.ok(issueService.createIssue(issue));
    }
}
