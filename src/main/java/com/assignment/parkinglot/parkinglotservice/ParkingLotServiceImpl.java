package com.assignment.parkinglot.parkinglotservice;

import com.assignment.parkinglot.DataStructure.AbstractParkingDataStructure;
import com.assignment.parkinglot.DataStructure.ParkingDataStructure;
import com.assignment.parkinglot.interaction.Constants;
import com.assignment.parkinglot.objectmodels.Vehicle;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class ParkingLotServiceImpl implements ParkingLotService {

  private AbstractParkingDataStructure<Vehicle> data = null;
  private final static Logger logger = Logger.getLogger("logger");

  public void instantiateParkingLot (int capacity) {
    if (data!=null){
      return;
    }
    this.data= ParkingDataStructure.getInstance(capacity);
    System.out.println("Parking lot with capacity "+capacity );
  }
  public void park (Vehicle vehicle){
    if (isLotValid()) {
      try {
        int value = data.parkCar(vehicle);
        if (value == Constants.NOT_AVAILABLE) {
          System.out.println("Parking full, sorry!");
        } else if (value == Constants.VEHICLE_ALREADY_EXIST) {
          System.out.println("this car is already parked! don't park a parked car!");
        } else {
          System.out.println("car" + vehicle.getRegistration_number() + " parked at slot " + value);
        }
      } catch (Exception e) {
        logger.info(e.toString());
      }
    }
  }

  public void leave (int slotNo){
    if (isLotValid()){
      try{
        if (data.leave(slotNo)){
          System.out.println("Slot" + slotNo + " is free");
        }
        else{
          System.out.println("No car was parked here");
        }
      }
      catch (Exception e){
        logger.info(e.toString());
      }
    }
  }

  public int getAvailableSlots(int level){
    if (isLotValid()) {
      try {
        int value = data.getNumFreeSlots();
        return value;
      } catch (Exception e) {
        logger.info(e.toString());
      }
    }
    return 0;
  }

  public List<String> getRegistrationNumberFromColor (String color){
    List<String> RegNoList = new ArrayList<String>();
    if (isLotValid()){
       try{
         RegNoList = data.getRegNumberForColor(color);
       }
       catch (Exception e){
       }
     }
     return RegNoList;
  }

  public List<Integer> getSlotNumbersFromColor (String color){
    List<Integer> slotsWithAColor = new ArrayList<Integer>();
    if (isLotValid()){
      try{
        slotsWithAColor=data.getSlotNumbersFromColor(color);
      }
      catch (Exception e){
      }
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

      }
    }
    return value;
  }

  private boolean isLotValid(){
    return data==null;
  }

}
