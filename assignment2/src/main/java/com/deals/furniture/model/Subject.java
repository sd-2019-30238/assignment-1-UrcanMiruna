package com.deals.furniture.model;

import java.util.ArrayList;
import java.util.List;

public interface Subject {
   public void register(Observer observer);
   public void unregister(Observer observer);
   public void notifyObserbers();
   public Object getUpdate(Observer observer);
}
