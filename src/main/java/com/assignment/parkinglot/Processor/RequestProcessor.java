package com.assignment.parkinglot.Processor;

import com.assignment.parkinglot.interaction.Constants;
import com.assignment.parkinglot.parkinglotservice.ParkingLotService;

public class RequestProcessor implements AbstractRequestsProcessor {
  private ParkingLotService parkingLotService;
  public void setService(ParkingLotService parkingLotService){
    this.parkingLotService = parkingLotService;
  }
  public void processRequest(String request){
    String [] params = request.split(" ");
    String command = params[0];
    switch (command){
      case Constants.CREATE_PARKING_LOT:
        try{
          parkingLotService.instantiateParkingLot(Integer.parseInt(params[1]));
        }
        catch (Exception e){

        }
        break;
      case Constants.LEAVE:
        try{
          parkingLotService.leave(Integer.parseInt(params[1]));
        }
        catch (Exception e){

        }
        break;
      case Constants.REG_NUMBER_FOR_CARS_WITH_COLOR:
        parkingLotService.getRegistrationNumberFromColor(params[1]);
        break;
      case Constants.SLOTS_NUMBER_FOR_CARS_WITH_COLOR:
        parkingLotService.getSlotNumbersFromColor(params[1]);
        break;
      case Constants.SLOTS_NUMBER_FOR_REG_NUMBER:
        parkingLotService.getSlotNumberFromRegistrationNo(params[1]);
        break;
        default:
          break;
    }
  }
}
