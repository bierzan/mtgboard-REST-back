package com.brzn.app.card;

import java.io.Serializable;

public class Ruling implements Serializable {

  private String date;
  private String text;

  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }}
