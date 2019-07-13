package com.assignment.parkinglot.DataStructure;

import com.assignment.parkinglot.LotManager.AbstractLotManager;
import com.assignment.parkinglot.LotManager.LotManager;
import com.assignment.parkinglot.interaction.Constants;
import com.assignment.parkinglot.objectmodels.Vehicle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParkingDataStructure <T extends Vehicle> implements AbstractParkingDataStructure<T>{
  private int capacity;
  private int numFreeSlots;
  private Map<Integer, T> slotVehicleMapping;
  private AbstractLotManager lotManager;

  private static ParkingDataStructure parkingDataStructure = null;

  public static <T extends Vehicle> ParkingDataStructure<T> getInstance(int capacity) {
    if (parkingDataStructure == null) {
      parkingDataStructure = new ParkingDataStructure(capacity);
    }
    return parkingDataStructure;
  }

  private ParkingDataStructure(int capacity){
    this.capacity=capacity;
    this.numFreeSlots=capacity;
    this.lotManager = new LotManager();
    slotVehicleMapping = new HashMap<>();
    for (int i=1;i<=this.capacity;i++){
      slotVehicleMapping.put(i,null);
      lotManager.addSlot(i);
    }
  }

  public int parkCar(T vehicle){
    if (numFreeSlots==0){
      return Constants.NOT_AVAILABLE;
    }
    if (slotVehicleMapping.containsValue(vehicle)){
      return Constants.VEHICLE_ALREADY_EXIST;
    }
    int allotedSlot=lotManager.getSlot();
    slotVehicleMapping.put(allotedSlot,vehicle);
    numFreeSlots-=1;
    lotManager.removeSlot(allotedSlot);
    return allotedSlot;
  }

  public boolean leave(int slot){
    if(slotVehicleMapping.get(slot)==null){
      return false;
    }
    numFreeSlots+=1;
    lotManager.addSlot(slot);
    slotVehicleMapping.put(slot,null);
    return true;
  }
  public List<String> getRegNumberForColor(String color){
    List<String> regNumberForColor = new ArrayList<String>();
    for (int i=1;i<=capacity;i++){
      Vehicle vehicle=slotVehicleMapping.get(i);
      if (vehicle!=null && color.equals(vehicle.getColor())){
        regNumberForColor.add(vehicle.getRegistration_number());
      }
    }
    return regNumberForColor;
  }

  public List<Integer> getSlotNumbersFromColor(String color){
    List<Integer> slotNoFromColor = new ArrayList<Integer>();
    for (int i=1;i<=capacity;i++){
      Vehicle vehicle = slotVehicleMapping.get(i);
      if (vehicle!=null && color.equals(vehicle.getColor())){
        slotNoFromColor.add(i);
      }
    }
    return slotNoFromColor;
  }

  public int getSlotNoFromRegistrationNo (String regNo){
    int slotNoFromReg;
    for (int i=0;i<=capacity;i++){
      Vehicle vehicle=slotVehicleMapping.get(i);
      if (vehicle!=null && regNo.equals(vehicle.getRegistration_number())){
        slotNoFromReg=i;
        return slotNoFromReg;
      }
    }
    return Constants.NOT_AVAILABLE;
  }

  public int getNumFreeSlots()
  {

    return numFreeSlots;
  }

  public List<String> getLotStats(){
    List<String> lotStats = new ArrayList<String>();
    for(int i=1; i<=capacity;i++){
      Vehicle vehicle = slotVehicleMapping.get(i);
      if (vehicle!=null){
        lotStats.add("slot No:" + i + "RegNo:" + vehicle.getRegistration_number() + "color:" + vehicle.getColor());
      }
    }
    return lotStats;
  }

}

