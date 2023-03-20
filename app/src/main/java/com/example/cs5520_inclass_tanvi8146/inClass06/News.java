package com.example.cs5520_inclass_tanvi8146.inClass06;

import com.example.cs5520_inclass_tanvi8146.inClass06.Article;

import java.io.Serializable;
import java.util.List;

public class News implements Serializable {

    private String status;
    private int totalResults;
    private List<Article> articles;

    public News() {

    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    @Override
    public String toString() {
        return "News{" +
                "status='" + status + '\'' +
                ", totalResult=" + totalResults +
                ", news=" + articles +
                '}';
    }
}
