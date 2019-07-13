package com.assignment.parkinglot.Processor;

import com.assignment.parkinglot.interaction.CliMap;
import com.assignment.parkinglot.parkinglotservice.ParkingLotService;

public interface AbstractRequestsProcessor {
  public void setService(ParkingLotService parkingLotService);

  public void processRequest(String action);

  public default boolean isCommandValid(String inputString)
  {
    boolean valid = true;
    try
    {
      String[] inputs = inputString.split(" ");
      int params = CliMap.getMap().get(inputs[0]);
      switch (inputs.length)
      {
        case 1:
          if (params != 0)
            valid = false;
          break;
        case 2:
          if (params != 1)
            valid = false;
          break;
        case 3:
          if (params != 2)
            valid = false;
          break;
        default:
          valid = false;
      }
    }
    catch (Exception e)
    {
      valid = false;
    }
    return valid;
  }

}