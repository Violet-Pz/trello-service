INSERT INTO issue (id, title, description, status, created_at, updated_at)
VALUES
    (1, 'Fix login bug', 'Users cannot log in with valid credentials', 'TODO', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (2, 'Add search functionality', 'Implement search feature for tasks', 'IN_PROGRESS', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (3, 'Update UI design', 'Redesign the dashboard for better user experience', 'ANALYZING', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (4, 'Write unit tests', 'Add unit tests for the service layer', 'DONE', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);