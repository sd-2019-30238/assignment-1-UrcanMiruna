package com.a3.bfd.model;

import java.util.ArrayList;
import java.util.List;
import com.a3.bfd.model.Observer;

public interface Subject {
    public void register(Observer observer);
    public void unregister(Observer observer);
    public void notifyObserbers();
}
