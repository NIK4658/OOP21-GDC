package model.roulette.wheel;

public interface WheelFactory {
    
    Wheel createBaseWheel();
    
    Wheel createAmericanWheel();

}
