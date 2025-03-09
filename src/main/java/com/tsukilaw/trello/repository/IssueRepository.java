package com.tsukilaw.trello.repository;

import com.tsukilaw.trello.entity.Issue;
import com.tsukilaw.trello.entity.IssueStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IssueRepository extends JpaRepository<Issue, Long> {
    List<Issue> findByStatus(IssueStatus status);
}
