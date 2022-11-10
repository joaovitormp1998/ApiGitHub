package com.example.appgithub;

public class User {
    private String name , username, organization,bio,avatarUrl;
    private int followers,following,repositories;

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public User(String name, String username, String organization, String bio, int followers, int following, int repositories, String avatarUrl) {
        this.name = name;
        this.username = username;
        this.organization = organization;
        this.bio = bio;
        this.followers = followers;
        this.following = following;
        this.repositories = repositories;
        this.avatarUrl = avatarUrl;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getOrganization() {
        return organization;
    }

    public String getBio() {
        return bio;
    }

    public int getFollowers() {
        return followers;
    }

    public int getFollowing() {
        return following;
    }

    public int getRepositories() {
        return repositories;
    }
}
