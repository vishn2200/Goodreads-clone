package com.ooad.miniproject.goodreads.clone.entity;

public class ReadingListBook {
    private Long id;
    public Long readingListId;
    public Long bookId;
    public String title;
    public ReadingStatus status;
    public Double rating;

    public ReadingListBook(){

    }

    public ReadingListBook(Long readingListId, Long bookId, String title, ReadingStatus status, Double rating){
        this.readingListId=readingListId;
        this.bookId=bookId;
        this.title=title; 
        this.status=status;
        this.rating=rating;
    }

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id=id;
    }

    public Long getReadingListId(){
        return readingListId;
    }

    public void setReadingListId(Long ReadingListId){
        this.readingListId=readingListId;
    }

    public Long getBookId(){
        return bookId;
    }

    public void setBookId(Long bookId){
        this.bookId=bookId;
    }

    public String getTitle(){
        return title;
    }

    public void setTitle(String title){
        this.title=title;
    }

    public ReadingStatus getStatus(){
        return status;
    }
    
    public void setStatus(ReadingStatus status){
        this.status=status;
    }

    public double getRating(){
        return rating;
    }

    public void setRating(Double rating){
        this.rating=rating;
    }
}
