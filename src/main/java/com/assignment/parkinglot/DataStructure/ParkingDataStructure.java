package com.assignment.parkinglot.DataStructure;

import com.assignment.parkinglot.interaction.Constants;
import com.assignment.parkinglot.objectmodels.Vehicle;

import java.util.Map;

public class ParkingDataStructure <T extends Vehicle> implements AbstractParkingDataStructure<T>{
  private int capacity;
  private int numFreeSlots;
  private Map<Integer, T> slotVehicleMapping;

  private static ParkingDataStructure parkingDataStructure = null;

  public static <T extends Vehicle> ParkingDataStructure<T> getInstance(int level, int capacity) {
    if (parkingDataStructure == null) {
      parkingDataStructure = new ParkingDataStructure(level, capacity);
    }
    return parkingDataStructure;
  }

  private ParkingDataStructure(int level, int capacity){
    this.capacity=capacity;
    this.numFreeSlots=capacity;
    for (int i=0;i<=this.capacity;i++){
      this.slotVehicleMapping.put(i,null);

    }
  }
  @Override
  public int parkCar(T vehicle){
    if (numFreeSlots==0){
      return Constants.NOT_AVAILABLE;
    }
    else{

    }
  }




}

