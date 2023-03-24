package org.example.entities;

public class UserAccount {
    private final Long id;
    private String name;
    private final String login;
    private String password;
    private String pictureURL;

    public UserAccount(Long id, String name, String login, String password, String pictureURL) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.password = password;
        this.pictureURL = pictureURL;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPictureURL() {
        return pictureURL;
    }

    public void setPictureURL(String pictureURL) {
        this.pictureURL = pictureURL;
    }

    @Override
    public String toString() {
        return "UserAccount{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", pictureURL='" + pictureURL + '\'' +
                '}';
    }

    public String toTest() {
        return "UserAccount{" +
                "name='" + name + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", pictureURL='" + pictureURL + '\'' +
                '}';
    }
}
