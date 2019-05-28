package FactoryPattern;

public class DiscountFactory {
    public Discount getDiscount(String type){
        if(type==null){
            return null;
        }
        if(type.equalsIgnoreCase("kitchen")){
            return new TypeDiscount();
        }else{
            if(type.equalsIgnoreCase("bedroom")){
                return  new BedroomDiscount();

            }else{
                if(type.equalsIgnoreCase("office")){
                    return new OfficeDiscount();
                }
            }
        }
        return null;
    }
}
