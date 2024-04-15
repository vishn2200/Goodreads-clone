package com.ooad.miniproject.goodreads.clone.entity;


public class Book {
    private Long id;
    public String author;
    public String genre;
    public String name;
    public double rating;
    public String summary;
    public String image_path;

    public Book(){

    }

    public Book(String name, String author, String genre, double rating, String summary, String image_path) {
        this.name=name;
        this.author=author;
        this.genre=genre;
        this.rating=rating;
        this.summary = summary;
        this.image_path=image_path;
    }


    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id=id;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name=name;
    }

    public String getAuthor(){
        return author;
    }

    public void setAuthor(String author){
        this.author=author;
    }

    public String getGenre(){
        return genre;
    }

    public void setGenre(String genre){
        this.genre=genre;
    }

    public double getRating(){
        return rating;
    }

    public void setRating(double rating){
        this.rating=rating;
    }

    public String getSummary() {
        return summary;
      }
    
      public void setSummary(String summary) {
        // Consider adding validation here to ensure summary length is within the limit (e.g., 50 words)
        //if (summary.length() > 50) {
        //  throw new IllegalArgumentException("Summary length cannot exceed 50 words");
        //}
        this.summary = summary;
      }

      public String getImagePath(){
        return image_path;
      }

      public void setImagePath(String image_path){
        this.image_path=image_path;
      }
}
