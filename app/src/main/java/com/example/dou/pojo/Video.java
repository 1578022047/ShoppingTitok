package com.example.dou.pojo;


import java.io.Serializable;

public class Video implements Serializable {

  private Integer videoId;
  private String userId;
  private Integer likeNum;
  private Integer remarkNum;
  private String info;

  private double goodsPrice;
  private String goodsInfo;
  private String goodsName;

  public String getInfo() {
    return info;
  }

  public Video(Integer videoId, String userId, Integer likeNum, Integer remarkNum, String info, double goodsPrice, String goodsInfo, String goodsName, String imageUrl, String videoUrl) {
    this.videoId = videoId;
    this.userId = userId;
    this.likeNum = likeNum;
    this.remarkNum = remarkNum;
    this.info = info;
    this.goodsPrice = goodsPrice;
    this.goodsInfo = goodsInfo;
    this.goodsName = goodsName;
    this.imageUrl = imageUrl;
    this.videoUrl = videoUrl;
  }

//  public Video(final Integer videoId, final String userId, final Integer likeNum, final Integer remarkNum, final String info, final String imageUrl , final String videoUrl) {
//    this.videoId = videoId;
//    this.userId = userId;
//    this.likeNum = likeNum;
//    this.remarkNum = remarkNum;
//    this.info = info;
//    this.imageUrl = imageUrl;
//    this.videoUrl = videoUrl;
//  }

  public double getGoodsPrice() {
    return goodsPrice;
  }

  public void setGoodsPrice(double goodsPrice) {
    this.goodsPrice = goodsPrice;
  }

  public String getGoodsInfo() {
    return goodsInfo;
  }

  public void setGoodsInfo(String goodsInfo) {
    this.goodsInfo = goodsInfo;
  }

  public String getGoodsName() {
    return goodsName;
  }

  public void setGoodsName(String goodsName) {
    this.goodsName = goodsName;
  }

  public void setInfo(final String info) {
    this.info = info;
  }

  private String imageUrl;
  private String videoUrl;

  public String getImageUrl() {
    return imageUrl;
  }

  public void setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
  }

  public String getVideoUrl() {
    return videoUrl;
  }

  public void setVideoUrl(String videoUrl) {
    this.videoUrl = videoUrl;
  }

  @Override
  public String toString() {
    return "Video{" +
            "videoId=" + videoId +
            ", userId='" + userId + '\'' +
            ", likeNum=" + likeNum +
            ", remarkNum=" + remarkNum +
            ", info='" + info + '\'' +
            ", imageUrl='" + imageUrl + '\'' +
            ", videoUrl='" + videoUrl + '\'' +
            '}';
  }

  public Integer getVideoId() {
    return videoId;
  }

  public void setVideoId(Integer videoId) {
    this.videoId = videoId;
  }


  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }


  public Integer getLikeNum() {
    return likeNum;
  }

  public void setLikeNum(Integer likeNum) {
    this.likeNum = likeNum;
  }


  public Integer getRemarkNum() {
    return remarkNum;
  }

  public void setRemarkNum(Integer remarkNum) {
    this.remarkNum = remarkNum;
  }


}
