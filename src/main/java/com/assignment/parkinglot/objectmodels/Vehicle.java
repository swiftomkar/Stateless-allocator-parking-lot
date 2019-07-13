package com.assignment.parkinglot.objectmodels;

public abstract class Vehicle {
  private String registration_number = null;
  private String color = null;

  public Vehicle(String registration_number, String color){
    this.registration_number = registration_number;
    this.color = color.toLowerCase();
  }
  public String getRegistration_number() {
    return this.registration_number;
  }
  public String getColor() {
    return this.color;
  }

  public void setColor(String color) {
    this.color = color;
  }

  public void setRegistration_number(String registration_number) {
    this.registration_number = registration_number;
  }
  public String toString() {
    return "Registration Number:"+registration_number+" Color:"+color;
  }

}
