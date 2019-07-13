package com.assignment.parkinglot.Processor;

import com.assignment.parkinglot.parkinglotservice.ParkingLotService;

public interface AbstractRequestsProcessor {
  //public void setService(ParkingLotService parkingLotService);

  public void processRequest(String action);

}