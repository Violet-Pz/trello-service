package com.tsukilaw.trello.service;

import com.tsukilaw.trello.entity.Issue;
import com.tsukilaw.trello.entity.IssueStatus;
import com.tsukilaw.trello.repository.IssueRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class IssueServiceTest {

    @Mock
    private IssueRepository issueRepository;

    //@InjectMocks 用于创建并初始化被测试的类（IssueService），并将标记了 @Mock 的依赖（如 issueRepository）注入到该类中
    @InjectMocks
    private IssueService issueService;

    @BeforeEach
    void setUp() {
        //用于初始化当前测试类中标记了 @Mock 和 @InjectMocks 的字段，会扫描测试类中的注解并完成依赖注入。
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateIssue() {
        Issue issue = new Issue();
        issue.setTitle("Test Issue");
        issue.setDescription("Test Description");
        issue.setStatus(IssueStatus.TODO);

        when(issueRepository.save(issue)).thenReturn(issue);

        Issue createdIssue = issueService.createIssue(issue);

        assertNotNull(createdIssue);
        assertEquals("Test Issue", createdIssue.getTitle());
        verify(issueRepository, times(1)).save(issue);
    }

    @Test
    void testGetIssues() {
        Issue issue1 = new Issue();
        Issue issue2 = new Issue();
        when(issueRepository.findAll()).thenReturn(List.of(issue1, issue2));

        List<Issue> issues = issueService.getIssues();

        assertEquals(2, issues.size());
        verify(issueRepository, times(1)).findAll();
    }

    @Test
    void testGetIssueById() {
        Issue issue = new Issue();
        issue.setId(1L);
        when(issueRepository.findById(1L)).thenReturn(Optional.of(issue));

        Issue foundIssue = issueService.getIssueById(1L);

        assertNotNull(foundIssue);
        assertEquals(1L, foundIssue.getId());
        verify(issueRepository, times(1)).findById(1L);
    }

    @Test
    void testGetIssueById_NotFound() {
        when(issueRepository.findById(1L)).thenReturn(Optional.empty());

        Issue foundIssue = issueService.getIssueById(1L);

        assertNull(foundIssue);
        verify(issueRepository, times(1)).findById(1L);
    }

    @Test
    void testGetIssuesByStatus() {
        Issue issue = new Issue();
        issue.setStatus(IssueStatus.TODO);
        when(issueRepository.findByStatus(IssueStatus.TODO)).thenReturn(List.of(issue));

        List<Issue> issues = issueService.getIssuesByStatus(IssueStatus.TODO);

        assertEquals(1, issues.size());
        assertEquals(IssueStatus.TODO, issues.get(0).getStatus());
        verify(issueRepository, times(1)).findByStatus(IssueStatus.TODO);
    }

    @Test
    void testUpdateIssue() {
        Issue existingIssue = new Issue();
        existingIssue.setId(1L);
        existingIssue.setTitle("Old Title");

        Issue updatedIssue = new Issue();
        updatedIssue.setTitle("New Title");

        when(issueRepository.findById(1L)).thenReturn(Optional.of(existingIssue));
        when(issueRepository.save(existingIssue)).thenReturn(existingIssue);

        Issue result = issueService.updateIssue(1L, updatedIssue);

        assertEquals("New Title", result.getTitle());
        verify(issueRepository, times(1)).findById(1L);
        verify(issueRepository, times(1)).save(existingIssue);
    }

    @Test
    void testUpdateIssue_NotFound() {
        when(issueRepository.findById(1L)).thenReturn(Optional.empty());

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> {
            issueService.updateIssue(1L, new Issue());
        });

        assertEquals("Issue with id 1 not found", exception.getMessage());
        verify(issueRepository, times(1)).findById(1L);
        verify(issueRepository, never()).save(any());
    }

    @Test
    void testDeleteIssueById() {
        doNothing().when(issueRepository).deleteById(1L);

        issueService.deleteIssueById(1L);

        verify(issueRepository, times(1)).deleteById(1L);
    }
}