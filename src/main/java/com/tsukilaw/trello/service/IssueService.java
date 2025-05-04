package com.tsukilaw.trello.service;

import com.tsukilaw.trello.entity.Issue;
import com.tsukilaw.trello.repository.IssueRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


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

    public List<Issue> getIssues () {
        return issueRepository.findAll();
    }

    public Issue getIssueById (Long issueId) {
        Optional<Issue> issue = issueRepository.findById(issueId);
        return issue.orElse(null);
    }

    public Issue updateIssue(Long id, Issue updatedIssue) {
        // 先查询数据库中是否存在该 Issue
        Optional<Issue> existingIssue = issueRepository.findById(id);

        if (existingIssue.isEmpty()) {
            throw new EntityNotFoundException("Issue with id " + id + " not found");
        }

        // 获取现有的 Issue 并更新字段
        Issue issue = existingIssue.get();
        issue.setTitle(updatedIssue.getTitle());
        issue.setDescription(updatedIssue.getDescription());
        issue.setStatus(updatedIssue.getStatus());
        issue.setUpdatedAt(LocalDateTime.now());

        // 保存更新后的 Issue
        return issueRepository.save(issue);
    }

    public void deleteIssueById(Long issueId) {
        issueRepository.deleteById(issueId);
    }
}
