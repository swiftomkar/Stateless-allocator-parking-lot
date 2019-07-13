package com.assignment.parkinglot.Processor;

import com.assignment.parkinglot.interaction.Constants;
import com.assignment.parkinglot.parkinglotservice.ParkingLotService;

public class RequestProcessor implements AbstractRequestsProcessor {
  private ParkingLotService parkingLotService;

  public void processRequest(String request){
    String [] params = request.split(" ");
    String command = params[0];
    switch (command){
      case Constants.CREATE_PARKING_LOT:


    }
  }
}
