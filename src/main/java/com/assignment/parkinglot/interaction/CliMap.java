package com.assignment.parkinglot.interaction;

import java.util.HashMap;
import java.util.Map;

public class CliMap {
  private static  Map<String,Integer> CommandToParamNo = new HashMap<String, Integer>();
  static {
    CommandToParamNo.put(Constants.CREATE_PARKING_LOT, 1);
    CommandToParamNo.put(Constants.PARK, 2);
    CommandToParamNo.put(Constants.LEAVE, 1);
    CommandToParamNo.put(Constants.STATUS, 0);
    CommandToParamNo.put(Constants.REG_NUMBER_FOR_CARS_WITH_COLOR, 1);
    CommandToParamNo.put(Constants.SLOTS_NUMBER_FOR_CARS_WITH_COLOR, 1);
    CommandToParamNo.put(Constants.SLOTS_NUMBER_FOR_REG_NUMBER, 1);
  }

  public static Map<String,Integer> getMap() {
    return CommandToParamNo;
  }
}
