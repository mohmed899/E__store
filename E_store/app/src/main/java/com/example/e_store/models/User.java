package com.example.e_store.models;

public class User {

 String U_ID;
 String fullName ;
 String ImgUri;
 String uEmail;
 String birthday;
 String address;
 String uPassword;

 public User(String u_ID, String fullName, String imgUri, String uEmail, String birthday, String address, String uPassword) {
  U_ID = u_ID;
  this.fullName = fullName;
  ImgUri = imgUri;
  this.uEmail = uEmail;
  this.birthday = birthday;
  this.address = address;
  this.uPassword = uPassword;
 }

 public String getU_ID() {
  return U_ID;
 }

 public void setU_ID(String u_ID) {
  U_ID = u_ID;
 }

 public String getFullName() {
  return fullName;
 }

 public void setFullName(String fullName) {
  this.fullName = fullName;
 }

 public String getImgUri() {
  return ImgUri;
 }

 public void setImgUri(String imgUri) {
  ImgUri = imgUri;
 }

 public String getuEmail() {
  return uEmail;
 }

 public void setuEmail(String uEmail) {
  this.uEmail = uEmail;
 }

 public String getBirthday() {
  return birthday;
 }

 public void setBirthday(String birthday) {
  this.birthday = birthday;
 }

 public String getAddress() {
  return address;
 }

 public void setAddress(String address) {
  this.address = address;
 }

 public String getuPassword() {
  return uPassword;
 }

 public void setuPassword(String uPassword) {
  this.uPassword = uPassword;
 }
}
