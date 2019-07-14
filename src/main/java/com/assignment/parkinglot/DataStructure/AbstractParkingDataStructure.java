package com.assignment.parkinglot.DataStructure;

import com.assignment.parkinglot.objectmodels.Vehicle;

import java.util.List;

public interface AbstractParkingDataStructure<T extends Vehicle> {

  public int parkCar(T vehicle);

  public boolean leave(int slot);

  public List<List<String>> getLotStats();

  public List<String> getRegNumberForColor(String color);

  public List<Integer> getSlotNumbersFromColor(String colour);

  public int getSlotNoFromRegistrationNo(String registrationNo);

  public int getNumFreeSlots();


}
