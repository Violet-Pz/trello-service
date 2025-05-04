package com.tsukilaw.trello.controller;

import com.tsukilaw.trello.entity.Issue;
import com.tsukilaw.trello.service.IssueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/issues")
public class IssueController {
    @Autowired
    private final IssueService issueService;
    @Autowired
    public IssueController(IssueService issueService) {
        this.issueService = issueService;
    }
    @PostMapping
    public ResponseEntity<Issue> createIssue(@RequestBody Issue issue) {
        return ResponseEntity.ok(issueService.createIssue(issue));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Issue> updateIssue(@PathVariable Long id, @RequestBody Issue updatedIssue) {
        Issue issue = issueService.updateIssue(id, updatedIssue);
        return ResponseEntity.ok(issue);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Issue> getIssueById(@PathVariable Long id) {
        Issue issue = issueService.getIssueById(id);
        if (issue != null) {
            return ResponseEntity.ok(issue); // 返回找到的 Issue
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND) // 如果没有找到，返回 404
                    .body(null);
        }
    }

    @GetMapping
    public ResponseEntity<List<Issue>> getAllIssues() {
        return ResponseEntity.ok(issueService.getIssues());
    }
}
