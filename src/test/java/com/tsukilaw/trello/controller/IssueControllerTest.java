package com.tsukilaw.trello.controller;

import com.tsukilaw.trello.entity.Issue;
import com.tsukilaw.trello.service.IssueService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatusCode;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

public class IssueControllerTest {
    @Mock
    private IssueService issueService;

    @InjectMocks
    private IssueController issueController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateIssue() {
        Issue issue = Issue.builder().id(1L).title("Test Issue").description("This is a test issue").build();
        when(issueService.createIssue(issue)).thenReturn(issue);
        assertEquals(issue, issueController.createIssue(issue).getBody());
        assertEquals(HttpStatusCode.valueOf(200), issueController.createIssue(issue).getStatusCode());
    }

    @Test
    void testGetIssuesByCorrectStatus() {
        when(issueService.getIssuesByStatus(com.tsukilaw.trello.entity.IssueStatus.TODO)).thenReturn(java.util.List.of());
        assertEquals(java.util.List.of(), issueController.getIssuesByStatus("TODO").getBody());
        assertEquals(HttpStatusCode.valueOf(200), issueController.getIssuesByStatus("TODO").getStatusCode());
    }

    @Test
    void testGetIssuesByWrongStatus() {
        assertNull(issueController.getIssuesByStatus("INVALID_STATUS").getBody());
        assertEquals(HttpStatusCode.valueOf(400), issueController.getIssuesByStatus("INVALID_STATUS").getStatusCode());
    }

    @Test
    void testGetIssueByIdNotFound() {
        when(issueService.getIssueById(1L)).thenReturn(null);
        assertNull(issueController.getIssueById(1L).getBody());
        assertEquals(HttpStatusCode.valueOf(404), issueController.getIssueById(1L).getStatusCode());
    }

    @Test
    void testGetIssueByIdFound() {
        Issue issue = Issue.builder().id(1L).title("Test Issue").description("This is a test issue").build();
        when(issueService.getIssueById(1L)).thenReturn(issue);
        assertEquals(issue, issueController.getIssueById(1L).getBody());
        assertEquals(HttpStatusCode.valueOf(200), issueController.getIssueById(1L).getStatusCode());
    }

    @Test
    void testUpdateIssue() {
        Issue updatedIssue = Issue.builder().id(1L).title("Updated Issue").description("This is an updated issue").build();
        when(issueService.updateIssue(1L, updatedIssue)).thenReturn(updatedIssue);
        assertEquals(updatedIssue, issueController.updateIssue(1L, updatedIssue).getBody());
        assertEquals(HttpStatusCode.valueOf(200), issueController.updateIssue(1L, updatedIssue).getStatusCode());
    }

    @Test
    void testGetAllIssues() {
        when(issueService.getIssues()).thenReturn(java.util.List.of());
        assertEquals(java.util.List.of(), issueController.getAllIssues().getBody());
        assertEquals(HttpStatusCode.valueOf(200), issueController.getAllIssues().getStatusCode());
    }

}
