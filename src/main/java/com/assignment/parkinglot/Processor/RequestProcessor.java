package com.assignment.parkinglot.Processor;

import com.assignment.parkinglot.interaction.Constants;
import com.assignment.parkinglot.objectmodels.car;
import com.assignment.parkinglot.parkinglotservice.ParkingLotService;

import java.util.logging.Logger;

public class RequestProcessor implements AbstractRequestsProcessor {
  private final static Logger logger = Logger.getLogger("logger");
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
          logger.info(e.toString());
        }
        break;
      case Constants.PARK:
          parkingLotService.park(new car(params[1],params[2]));
          break;
      case Constants.LEAVE:
        try{
          parkingLotService.leave(Integer.parseInt(params[1]));
        }
        catch (Exception e){
          logger.info(e.toString());
        }
        break;
      case Constants.REG_NUMBER_FOR_CARS_WITH_COLOR:
        System.out.println(parkingLotService.getRegistrationNumberFromColor(params[1]));
        break;
      case Constants.SLOTS_NUMBER_FOR_CARS_WITH_COLOR:
        System.out.println(parkingLotService.getSlotNumbersFromColor(params[1]));
        break;
      case Constants.SLOTS_NUMBER_FOR_REG_NUMBER:
        System.out.println(parkingLotService.getSlotNumberFromRegistrationNo(params[1]));
        break;
        default:
          break;
    }
  }
}
