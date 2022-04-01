package roulette.table;


import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class Table {
    
    private final List<Number> table;
    
    public Table() {
        this.table = new ArrayList<>();
        this.populateTable();
        
        
        
        
        
        
        
        
    }
    
    private void populateTable() {
        this.table.put(0, Color.GREEN);
        setColorNumber(1);
        setColorNumber(19);
    }
    
    private void setColorNumber(final int j) {
        boolean change = false;
        for (int i = j; i < j + 18; i++) {
            if ((j + 10) == i) {
                change = !change;
            }
            if (change) {
                this.table.put(i, Color.BLACK);
            }
            else {
                this.table.put(i, Color.RED);
            }
            change = !change;
        }
    }
    
    public Map<Integer, Color> getTable(){
        return table;
    }

}
