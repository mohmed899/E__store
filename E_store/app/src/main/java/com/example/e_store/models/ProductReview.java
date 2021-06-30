package com.example.e_store.models;

public class ProductReview {
     String reviewId ;
     String productId ;
     String creatorId ;
     String reviewContent ;
     String rating ;
     String reviewerName;

    public ProductReview(String reviewId, String productId, String creatorId, String reviewContent, String rating, String reviewerName) {
        this.reviewId = reviewId;
        this.productId = productId;
        this.creatorId = creatorId;
        this.reviewContent = reviewContent;
        this.rating = rating;
        this.reviewerName = reviewerName;
    }

    public String getReviewerName() {
        return reviewerName;
    }

    public void setReviewerName(String reviewerName) {
        this.reviewerName = reviewerName;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getReviewId() {
        return reviewId;
    }

    public void setReviewId(String reviewId) {
        this.reviewId = reviewId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    public String getReviewContent() {
        return reviewContent;
    }

    public void setReviewContent(String reviewContent) {
        this.reviewContent = reviewContent;
    }
}
