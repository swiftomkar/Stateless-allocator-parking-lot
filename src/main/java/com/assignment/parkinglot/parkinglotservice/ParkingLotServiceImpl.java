package com.assignment.parkinglot.parkinglotservice;

import com.assignment.parkinglot.DataStructure.AbstractParkingDataStructure;
import com.assignment.parkinglot.DataStructure.ParkingDataStructure;
import com.assignment.parkinglot.interaction.Constants;
import com.assignment.parkinglot.objectmodels.Vehicle;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ParkingLotServiceImpl implements ParkingLotService {

  private AbstractParkingDataStructure<Vehicle> data = null;
  private final static Logger logger = Logger.getLogger("logger");

  public void instantiateParkingLot (int capacity) {
    if (data!=null){
      System.out.println("Parking lot already exists");
      return;
    }
    this.data= ParkingDataStructure.getInstance(capacity);
    System.out.println("Created a parking lot with " + capacity +" slots" );
  }
  public void park (Vehicle vehicle){
    if (isLotValid()) {
      try {
        int slot = data.parkCar(vehicle);
        if (slot == Constants.NOT_AVAILABLE) {
          System.out.println("Sorry, parking lot is full");
        } else if (slot == Constants.VEHICLE_ALREADY_EXIST) {
          System.out.println("this car is already parked! don't park a parked car!");
        } else {
          System.out.println("Allocated slot number: "+ slot);
        }
      } catch (Exception e) {
        logger.log(Level.WARNING,e.toString());
      }
    }
    else{
      System.out.println("No valid Parking Lot");
    }
  }

  public void leave (int slotNo){
    if (isLotValid()){
      try{
        if (data.leave(slotNo)){
          System.out.println("Slot number " + slotNo + " is free");
        }
        else{
          System.out.println("No car was parked here");
        }
      }
      catch (Exception e){
        logger.log(Level.WARNING,e.toString());
      }
    }
    else{
      System.out.println("No valid Parking Lot, use create_parking_lot");
    }
  }

  public int getAvailableSlots(int level){
    if (isLotValid()) {
      try {
        int value = data.getNumFreeSlots();
        return value;
      } catch (Exception e) {
        logger.log(Level.WARNING,e.toString());
      }
    }
    return 0;
  }

  public void getLotStats(){
    List<List<String>> stats=data.getLotStats();
    System.out.println("Slot No.\tRegistration No\t\tColour");
    for (List<String> var: stats){
      System.out.println(var.get(0)+"\t\t\t"+var.get(1)+"\t\t\t\t"+var.get(2));
    }
  }

  public List<String> getRegistrationNumberFromColor (String color){
    List<String> RegNoList = new ArrayList<String>();
    if (isLotValid()){
       try{
         RegNoList = data.getRegNumberForColor(color.toLowerCase());
       }
       catch (Exception e){
         logger.log(Level.WARNING,e.toString());
       }
     }
    else{
      System.out.println("No valid Parking Lot, use create_parking_lot");
    }
     return RegNoList;
  }

  public List<Integer> getSlotNumbersFromColor (String color){
    List<Integer> slotsWithAColor = new ArrayList<Integer>();
    if (isLotValid()){
      try{
        slotsWithAColor=data.getSlotNumbersFromColor(color.toLowerCase());
      }
      catch (Exception e){
        logger.log(Level.WARNING,e.toString());
      }
    }
    else{
      System.out.println("No valid Parking Lot, use create_parking_lot");
    }
    return slotsWithAColor;
  }

  public int getSlotNumberFromRegistrationNo (String registrationNumber){
    int value = -1;
    if(isLotValid()){
      try{
        value = data.getSlotNoFromRegistrationNo(registrationNumber);
      }
      catch (Exception e){
        logger.log(Level.WARNING,e.toString());
      }
    }
    else{
      System.out.println("No valid Parking Lot, use create_parking_lot");
    }
    return value;
  }

  private boolean isLotValid(){
    return data!=null;
  }

}
