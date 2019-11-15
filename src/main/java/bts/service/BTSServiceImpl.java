package bts.service;

import bts.model.Issue;
import bts.model.IssuePriority;
import bts.model.Project;
import bts.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BTSServiceImpl implements BTSService {

    private static final String PROJECTS_FILE_NAME = "projects.txt";
    private static final String USERS_FILE_NAME = "users.txt";
    private static final String ISSUES_FILE_NAME = "issues.txt";


    private static final String CREATE_PROJECTS_QUERY = "CREATE TABLE projects (id IDENTITY, name VARCHAR(32), description VARCHAR(32), PRIMARY KEY (id))";
    private static final String CREATE_USERS_QUERY = "CREATE TABLE users (id IDENTITY, name VARCHAR(32), PRIMARY KEY (id))";
    private static final String CREATE_ISSUES_QUERY = "CREATE TABLE issues (id IDENTITY, title VARCHAR(32), description VARCHAR(32), priority VARCHAR(32), project VARCHAR(32), author VARCHAR(32), PRIMARY KEY (id), FOREIGN KEY (project) REFERENCES projects(name), FOREIGN KEY (author) REFERENCES users(name))";
    private static final String INSERT_PROJECTS_QUERY = "INSERT INTO projects (name, description) VALUES('%s', '%s')";
    private static final String INSERT_USERS_QUERY = "INSERT INTO users (name) VALUES('%s')";
    private static final String INSERT_ISSUES_QUERY = "INSERT INTO issues (title, description, priority, project, author) VALUES('%s', '%s', '%s', '%s', '%s')";
    private static final String SELECT_PROJECTS_QUERY = "SELECT * FROM projects";
    private static final String SELECT_USERS_QUERY = "SELECT * FROM users";
    private static final String SELECT_ISSUES_QUERY = "SELECT * FROM issues";
    private static final String SELECT_FILTER_ISSUES_QUERY = "SELECT * FROM issues WHERE project = '%s' AND author = '%s'";


    private static final Loader loader = new LoaderImpl();
    private static final Writer writer = new WriterImpl();

    public BTSServiceImpl() {
        List <Project> projects = loader.load(PROJECTS_FILE_NAME);
        List<User> users = loader.load(USERS_FILE_NAME);
        List<Issue> issues = loader.load(ISSUES_FILE_NAME);

        try (Connection connection = DriverManager.getConnection("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1")) {
            try (Statement statement = connection.createStatement()) {
                statement.execute(CREATE_PROJECTS_QUERY);
                statement.execute(CREATE_USERS_QUERY);
                statement.execute(CREATE_ISSUES_QUERY);

                for (Project project : projects) {
                    statement.execute(String.format(
                            INSERT_PROJECTS_QUERY, project.getName(), project.getDescription()));
                }
                for (User user : users) {
                    statement.execute(String.format(
                            INSERT_USERS_QUERY, user.getName()));
                }
                for (Issue issue : issues) {
                   statement.execute(String.format(
                            INSERT_ISSUES_QUERY, issue.getTitle(), issue.getDescription(), issue.getPriority().name(),
                                                        issue.getProject().getName(), issue.getAuthor().getName()));
                }
            }
        } catch (SQLException e) {
            System.out.println("Database connection failure: "
                    + e.getMessage());
        }
    }

    @Override
    public List<User> getAllUsers() {

        List<User> users = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1")) {
            try (PreparedStatement query = connection.prepareStatement(SELECT_USERS_QUERY)) {
                ResultSet rs = query.executeQuery();
                while (rs.next()) {
                    users.add(new User(rs.getInt("id"),rs.getNString("name")));
                }
                rs.close();
            }
        } catch (SQLException e) {
            System.out.println("Database connection failure: "
                    + e.getMessage());
        }

        return users;
    }

    @Override
    public List<Project> getAllProjects() {

        List<Project> projects = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1")) {
            try (PreparedStatement query = connection.prepareStatement(SELECT_PROJECTS_QUERY)) {
                ResultSet rs = query.executeQuery();
                while (rs.next()) {
                    projects.add(new Project(rs.getInt("id"),rs.getNString("name"), rs.getNString("description")));
                }
                rs.close();
            }
        } catch (SQLException e) {
            System.out.println("Database connection failure: "
                    + e.getMessage());
        }

        return projects;
    }

    @Override
    public List<Issue> getAllIssues() {
        List<Issue> issues = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1")) {
            try (PreparedStatement query = connection.prepareStatement(SELECT_ISSUES_QUERY)) {
                ResultSet rs = query.executeQuery();
                while (rs.next()) {
                    issues.add(new Issue(rs.getInt("id"), rs.getNString("title"),
                            rs.getNString("description"), IssuePriority.valueOf(rs.getNString("priority")),
                            new Project(rs.getNString("project"), null), new User(rs.getNString("author"))));
                }
                rs.close();
            }
        } catch (SQLException e) {
            System.out.println("Database connection failure: "
                    + e.getMessage());
        }

        return issues;
    }

    @Override
    public void addUser(User user) {
        List<User> users = getAllUsers();
        users.add(user);
        writer.write(users, USERS_FILE_NAME);

        try (Connection connection = DriverManager.getConnection("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1")) {
            try (Statement statement = connection.createStatement()) {
                statement.execute(String.format(
                        INSERT_USERS_QUERY, user.getName()));
            }
        } catch (SQLException e) {
            System.out.println("Database connection failure: "
                    + e.getMessage());
        }
    }

    @Override
    public void addProject(Project project) {
        List<Project> projects = getAllProjects();
        projects.add(project);
        writer.write(projects, PROJECTS_FILE_NAME);

        try (Connection connection = DriverManager.getConnection("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1")) {
            try (Statement statement = connection.createStatement()) {
                statement.execute(String.format(
                        INSERT_PROJECTS_QUERY, project.getName(), project.getDescription()));
            }
        } catch (SQLException e) {
            System.out.println("Database connection failure: "
                    + e.getMessage());
        }
    }

    @Override
    public void addIssue(Issue issue) {
        List<Issue> issues = getAllIssues();
        issues.add(issue);
        writer.write(issues, ISSUES_FILE_NAME);

        try (Connection connection = DriverManager.getConnection("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1")) {
            try (Statement statement = connection.createStatement()) {
                statement.execute(String.format(
                        INSERT_ISSUES_QUERY, issue.getTitle(), issue.getDescription(), issue.getPriority().name(),
                        issue.getProject().getName(), issue.getAuthor().getName()));
            }
        } catch (SQLException e) {
            System.out.println("Database connection failure: "
                    + e.getMessage());
        }
    }

    @Override
    public List<Issue> findIssuesByProjectAndUser(String projectName, String userName) {
        List<Issue> result = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1")) {
            try (PreparedStatement query = connection.prepareStatement(
                    String.format(SELECT_FILTER_ISSUES_QUERY, projectName, userName))) {
                ResultSet rs = query.executeQuery();
                while (rs.next()) {
                    result.add(new Issue(rs.getInt("id"), rs.getNString("title"),
                            rs.getNString("description"), IssuePriority.valueOf(rs.getNString("priority")),
                            new Project(rs.getNString("project"), null), new User(rs.getNString("author"))));
                }
                rs.close();
            }
        } catch (SQLException e) {
            System.out.println("Database connection failure: "
                    + e.getMessage());
        }

        return result;
    }
}
