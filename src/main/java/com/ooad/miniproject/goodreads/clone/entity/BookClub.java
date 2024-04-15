package com.ooad.miniproject.goodreads.clone.entity;
import java.io.*;
//import javax.persistence.*;
import java.util.*;


public class BookClub implements Serializable{
    private Long id;
    public String name;
    /*@OneToMany(mappedBy = "bookClub", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    public Set<Question> questions = new HashSet<>();

    @ManyToMany(mappedBy = "bookClubs")
    public Set<User> members = new HashSet<>();
    */

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
/*
    public Set<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(Set<Question> questions) {
        this.questions = questions;
    }

    public void addQuestion(Question question) {
        questions.add(question);
        question.setBookClub(this);
    }

    public void removeQuestion(Question question) {
        questions.remove(question);
        question.setBookClub(null);
    }

    public Set<User> getMembers() {
        return members;
    }

    public void setMembers(Set<User> members) {
        this.members = members;
    }

    public void addMember(User user) {
        members.add(user);
        user.getBookClubs().add(this);
    }

    //public void removeMember(User user) {
    //    members.remove(user);
    //    user.getBookClubs().remove(this);
    //}

    public int getNumberOfMembers() {
        return members.size();
    }
    */
    
}
