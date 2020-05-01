package com.vale.model;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class GitRepo {

    /*
    Modified sample data from: https://developer.github.com/v3/users/#get-the-authenticated-user
   [
  {
    "id": 1296269,
    "node_id": "MDEwOlJlcG9zaXRvcnkxMjk2MjY5",
    "name": "Hello-World",
    "full_name": "octocat/Hello-World",
    "owner": {
            ignore this
    },.......
    ]
    */

    private int id;
    private String node_id;
    private String name;
    private String full_name;
    private GitHubUser owner;

    public GitHubUser getOwner() {
        return owner;
    }

    public void setOwner(GitHubUser owner) {
        this.owner = owner;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNode_id() {
        return node_id;
    }

    public void setNode_id(String node_id) {
        this.node_id = node_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }
}
