package model.roulette.wheel;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import model.roulette.number.BaseRouletteNumber;
import model.roulette.number.RouletteNumber;

public class BaseWheel implements Wheel {
    
    private final List<BaseRouletteNumber> rouletteNumbers;
    
    public BaseWheel() {
        this.rouletteNumbers = new ArrayList<>();
        this.rouletteNumbers.add(new BaseRouletteNumber(0, Color.GREEN));
        this.rouletteNumbers.add(new BaseRouletteNumber(1, Color.RED));
        this.rouletteNumbers.add(new BaseRouletteNumber(2, Color.BLACK));
        this.rouletteNumbers.add(new BaseRouletteNumber(3, Color.RED));
        this.rouletteNumbers.add(new BaseRouletteNumber(4, Color.BLACK));
        this.rouletteNumbers.add(new BaseRouletteNumber(5, Color.RED));
        this.rouletteNumbers.add(new BaseRouletteNumber(6, Color.BLACK));
        this.rouletteNumbers.add(new BaseRouletteNumber(7, Color.RED));
        this.rouletteNumbers.add(new BaseRouletteNumber(8, Color.BLACK));
        this.rouletteNumbers.add(new BaseRouletteNumber(9, Color.RED));
        this.rouletteNumbers.add(new BaseRouletteNumber(10, Color.BLACK));
        this.rouletteNumbers.add(new BaseRouletteNumber(11, Color.BLACK));
        this.rouletteNumbers.add(new BaseRouletteNumber(12, Color.RED));
        this.rouletteNumbers.add(new BaseRouletteNumber(13, Color.BLACK));
        this.rouletteNumbers.add(new BaseRouletteNumber(14, Color.RED));
        this.rouletteNumbers.add(new BaseRouletteNumber(15, Color.BLACK));
        this.rouletteNumbers.add(new BaseRouletteNumber(16, Color.RED));
        this.rouletteNumbers.add(new BaseRouletteNumber(17, Color.BLACK));
        this.rouletteNumbers.add(new BaseRouletteNumber(18, Color.RED));
        this.rouletteNumbers.add(new BaseRouletteNumber(19, Color.RED));
        this.rouletteNumbers.add(new BaseRouletteNumber(20, Color.BLACK));
        this.rouletteNumbers.add(new BaseRouletteNumber(21, Color.RED));
        this.rouletteNumbers.add(new BaseRouletteNumber(22, Color.BLACK));
        this.rouletteNumbers.add(new BaseRouletteNumber(23, Color.RED));
        this.rouletteNumbers.add(new BaseRouletteNumber(24, Color.BLACK));
        this.rouletteNumbers.add(new BaseRouletteNumber(25, Color.RED));
        this.rouletteNumbers.add(new BaseRouletteNumber(26, Color.BLACK));
        this.rouletteNumbers.add(new BaseRouletteNumber(27, Color.RED));
        this.rouletteNumbers.add(new BaseRouletteNumber(28, Color.BLACK));
        this.rouletteNumbers.add(new BaseRouletteNumber(29, Color.BLACK));
        this.rouletteNumbers.add(new BaseRouletteNumber(30, Color.RED));
        this.rouletteNumbers.add(new BaseRouletteNumber(31, Color.BLACK));
        this.rouletteNumbers.add(new BaseRouletteNumber(32, Color.RED));
        this.rouletteNumbers.add(new BaseRouletteNumber(33, Color.BLACK));
        this.rouletteNumbers.add(new BaseRouletteNumber(34, Color.RED));
        this.rouletteNumbers.add(new BaseRouletteNumber(35, Color.BLACK));
        this.rouletteNumbers.add(new BaseRouletteNumber(36, Color.RED));
    }

    @Override
    public List<RouletteNumber> getList() {
        return Collections.unmodifiableList(this.rouletteNumbers);
    }

}
