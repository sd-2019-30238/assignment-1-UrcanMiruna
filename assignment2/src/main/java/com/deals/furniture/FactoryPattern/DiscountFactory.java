package com.deals.furniture.FactoryPattern;

public class DiscountFactory {
    public Discount getDiscount(String type){
        if(type==null){
            return null;
        }
        if(type.equalsIgnoreCase("kitchen")){
            return new TypeDiscount();
        }
        return null;
    }
}