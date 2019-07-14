package com.assignment.parkinglot.parkinglotservice;

import com.assignment.parkinglot.objectmodels.Vehicle;

import java.util.List;

/**
 *
 */
public interface ParkingLotService {
  public void instantiateParkingLot (int capacity);

  public void park (Vehicle vehicle);

  public void leave (int slotNo);

  public int getAvailableSlots (int level);

  public List<String> getRegistrationNumberFromColor (String color);

  public List<Integer> getSlotNumbersFromColor (String color);

  public String getSlotNumberFromRegistrationNo (String registrationNumber);

  public void getLotStats();

  public void delete();
  }
