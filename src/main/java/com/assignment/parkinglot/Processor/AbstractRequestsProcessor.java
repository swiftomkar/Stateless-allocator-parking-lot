package com.assignment.parkinglot.Processor;

import com.assignment.parkinglot.interaction.CliMap;
import com.assignment.parkinglot.parkinglotservice.ParkingLotService;

public interface AbstractRequestsProcessor {
  public void setService(ParkingLotService parkingLotService);

  public void processRequest(String action);

  public default boolean isCommandValid(String inputString)
  {
    // Split the input string to validate command and input value
    boolean valid = true;
    try
    {
      String[] inputs = inputString.split(" ");
      int params = CliMap.getMap().get(inputs[0]);
      switch (inputs.length)
      {
        case 1:
          if (params != 0) // e.g status -> inputs = 1
            valid = false;
          break;
        case 2:
          if (params != 1) // create_parking_lot 6 -> inputs = 2
            valid = false;
          break;
        case 3:
          if (params != 2) // park KA-01-P-333 White -> inputs = 3
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