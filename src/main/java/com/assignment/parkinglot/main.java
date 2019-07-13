package com.assignment.parkinglot;

import com.assignment.parkinglot.Processor.AbstractRequestsProcessor;
import com.assignment.parkinglot.Processor.RequestProcessor;
import com.assignment.parkinglot.interaction.CliMap;
import com.assignment.parkinglot.parkinglotservice.ParkingLotServiceImpl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;

public class main {
  public static void main(String [] args) {
    System.out.println("main run");
    AbstractRequestsProcessor processor = new RequestProcessor();
    processor.setService(new ParkingLotServiceImpl());
    BufferedReader bufferReader = null;
    String inputCommand = null;
    try {
      System.out.println("-----------------------------");
      System.out.println("-----------GO-JEK------------");
      System.out.println("---------Omkar Desai---------");
      System.out.println("-----------------------------");
      help();
      switch (args.length) {
        case 0: // Interactive: command-line input/output
        {
          System.out.println("Please Enter 'exit' to end Execution");
          System.out.println("Input:");
          while (true)
          {
            try
            {
              bufferReader = new BufferedReader(new InputStreamReader(System.in));
              inputCommand = bufferReader.readLine().trim();
              if (inputCommand.equalsIgnoreCase("exit"))
              {
                break;
              }
              else
              {
                if (isCommandValid(inputCommand))
                {
                  try
                  {
                    processor.processRequest(inputCommand.trim());
                  }
                  catch (Exception e)
                  {
                    System.out.println(e.getMessage());
                  }
                }
                else
                {
                  help();
                }
              }
            }
            catch (Exception e)
            {
            }
          }
          break;
        }
        case 1:// File input/output
        {
          File inputFile = new File(args[0]);
          try
          {
            bufferReader = new BufferedReader(new FileReader(inputFile));
            int lineNo = 1;
            while ((inputCommand = bufferReader.readLine()) != null)
            {
              inputCommand = inputCommand.trim();
              if (isCommandValid(inputCommand))
              {
                try
                {
                  processor.processRequest(inputCommand);
                }
                catch (Exception e)
                {
                  System.out.println(e.getMessage());
                }
              }
              else
                System.out.println("Incorrect Command Found at line: " + lineNo + " ,Input: " + inputCommand);
              lineNo++;
            }
          }
          catch (Exception e)
          {
          }
          break;
        }
        default:
          System.out.println("Invalid input");

      }
    }
    catch (Exception e){

    }
  }

  private static void help(){
    System.out.println("Available Commands:");
    System.out.println("1) create_parking_lot <size> -> instantiate a parking lot");
    System.out.println("2) park <car registration no> <color> -> park a car");
    System.out.println("3) leave <slot no> -> remove car from lot");
    System.out.println("4) status -> get lot status");
    System.out.println("5) registration_numbers_for_cars_with_color <car_color> -> get reg no for cars of a particular color ");
    System.out.println("6) slot_numbers_for_cars_with_color <car_color> -> get slot nos for cars with a particular color");
    System.out.println("7) slot_number_for_registration_number <registration no> -> get slot no for a car using its reg no");
  }

  private static boolean isCommandValid(String inputString){
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
