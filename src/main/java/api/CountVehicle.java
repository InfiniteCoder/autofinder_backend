package api;

public class CountVehicle{
    long lostPincode;
    long count;

    public CountVehicle(long lostPincode,long count){
        this.lostPincode = lostPincode;
        this.count = count;
    }

    public long getLostPincode(){
        return this.lostPincode;
    }

    public long getCount(){
        return this.count;
    }
}
