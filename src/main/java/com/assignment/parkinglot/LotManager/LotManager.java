package com.assignment.parkinglot.LotManager;

import java.util.TreeSet;

public class LotManager implements AbstractLotManager {
  private TreeSet<Integer> avaliableSlots;

  public LotManager()
  {
    avaliableSlots = new TreeSet<Integer>();
  }

  public void addSlot(int i)
  {

    avaliableSlots.add(i);
  }

  public int getSlot()
  {

    return avaliableSlots.first();
  }

  public void removeSlot(int availableSlot)
  {

    avaliableSlots.remove(availableSlot);
  }
}
