package com.assignment.parkinglot;

import com.assignment.parkinglot.objectmodels.car;
import com.assignment.parkinglot.parkinglotservice.ParkingLotService;
import com.assignment.parkinglot.parkinglotservice.ParkingLotServiceImpl;
import org.junit.Assert;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;


public class mainTest {
  private final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

  @Test
  public void createNewLot() {
    System.setOut(new PrintStream(byteArrayOutputStream));
    ParkingLotService parkingLotService = new ParkingLotServiceImpl();
    parkingLotService.instantiateParkingLot(6);
    assertEquals("Created a parking lot with 6 slots\n", byteArrayOutputStream.toString());
    parkingLotService.delete();
  }

  @Test
  public void createLotWhenExisting() {
    System.setOut(new PrintStream(byteArrayOutputStream));
    ParkingLotService parkingLotService = new ParkingLotServiceImpl();
    parkingLotService.instantiateParkingLot(6);
    parkingLotService.instantiateParkingLot(6);
    assertEquals("Created a parking lot with 6 slots\nParking lot already exists\n",
        byteArrayOutputStream.toString());
    parkingLotService.delete();

  }

  @Test
  public void parkInNonexistantLot() {
    System.setOut(new PrintStream(byteArrayOutputStream));
    ParkingLotService parkingLotService = new ParkingLotServiceImpl();
    parkingLotService.park(new car("ab12", "white"));
    assertEquals("No valid Parking Lot\n", byteArrayOutputStream.toString());
    parkingLotService.delete();

  }

  @Test
  public void parkingLotFull(){
    System.setOut(new PrintStream(byteArrayOutputStream));
    ParkingLotService parkingLotService = new ParkingLotServiceImpl();
    parkingLotService.instantiateParkingLot(2);
    parkingLotService.park(new car("ab12","white"));
    parkingLotService.park(new car("ab13","black"));
    parkingLotService.park(new car("ab14","white"));
    assertEquals("Created a parking lot with 2 slots\n" +
        "Allocated slot number: 1\n" +
        "Allocated slot number: 2\n" +
        "Sorry, parking lot is full\n",byteArrayOutputStream.toString());
    parkingLotService.delete();


  }

  @Test
  public void parkedCarLeave(){
    System.setOut(new PrintStream(byteArrayOutputStream));
    ParkingLotService parkingLotService = new ParkingLotServiceImpl();
    parkingLotService.instantiateParkingLot(2);
    parkingLotService.park(new car("ab12","white"));
    parkingLotService.park(new car("ab13","black"));
    parkingLotService.leave(2);
    assertEquals("Created a parking lot with 2 slots\n" +
        "Allocated slot number: 1\n" +
        "Allocated slot number: 2\n" +
        "Slot number 2 is free\n",byteArrayOutputStream.toString());
    parkingLotService.delete();

  }

  @Test
  public void testGetSlotFromReg(){
    System.setOut(new PrintStream(byteArrayOutputStream));
    ParkingLotService parkingLotService = new ParkingLotServiceImpl();
    parkingLotService.instantiateParkingLot(6);
    parkingLotService.park(new car("ab12","white"));
    parkingLotService.park(new car("ab13","white"));
    String slot=parkingLotService.getSlotNumberFromRegistrationNo("ab12");
    assertEquals("Created a parking lot with 6 slots\n" +
        "Allocated slot number: 1\n" +
        "Allocated slot number: 2\n"
        ,byteArrayOutputStream.toString());
    assertEquals(slot,"1");
    parkingLotService.delete();

  }

  @Test
  public void testGetRegnoFromColor(){
    System.setOut(new PrintStream(byteArrayOutputStream));
    ParkingLotService parkingLotService = new ParkingLotServiceImpl();
    parkingLotService.instantiateParkingLot(6);
    parkingLotService.park(new car("ab12","white"));
    parkingLotService.park(new car("ab13","white"));
    List<String> regNos=parkingLotService.getRegistrationNumberFromColor("white");
    assertEquals("Created a parking lot with 6 slots\n" +
        "Allocated slot number: 1\n" +
        "Allocated slot number: 2\n" ,byteArrayOutputStream.toString());
    List<String> expected=new  ArrayList<>();
    expected.add("ab12");
    expected.add("ab13");
    assertTrue(regNos.equals(expected));
    parkingLotService.delete();


  }

  @Test
  public void testGetSlotnoFromColor(){
    System.setOut(new PrintStream(byteArrayOutputStream));
    ParkingLotService parkingLotService = new ParkingLotServiceImpl();
    parkingLotService.instantiateParkingLot(6);
    parkingLotService.park(new car("ab12","white"));
    parkingLotService.park(new car("ab13","white"));
    List<Integer> slots=parkingLotService.getSlotNumbersFromColor("white");
    assertEquals("Created a parking lot with 6 slots\n" +
        "Allocated slot number: 1\n" +
        "Allocated slot number: 2\n"
        ,byteArrayOutputStream.toString());
    List<Integer> expected= new ArrayList<>();
    expected.add(1);
    expected.add(2);
    assertTrue(slots.equals(expected));
    parkingLotService.delete();

  }

  @Test
  public void testStatus(){
    System.setOut(new PrintStream(byteArrayOutputStream));
    ParkingLotService parkingLotService = new ParkingLotServiceImpl();
    parkingLotService.instantiateParkingLot(6);
    parkingLotService.park(new car("ab12","white"));
    parkingLotService.park(new car("ab13","white"));
    parkingLotService.getLotStats();
    assertEquals("Created a parking lot with 6 slots\n" +
        "Allocated slot number: 1\n" +
        "Allocated slot number: 2\n" +
        "Slot No.\tRegistration No\t\tColour\n" +
        "1\t\t\tab12\t\t\t\twhite\n"+
        "2\t\t\tab13\t\t\t\twhite\n",byteArrayOutputStream.toString());
    parkingLotService.delete();

  }
}
