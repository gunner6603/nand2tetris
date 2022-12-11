package modules;

import java.util.HashMap;
import java.util.Map;

public class SymbolTable {

    private Map<String, Integer> table;

    public SymbolTable() {
        this.table = new HashMap<>();
        //predefined symbols
        for (int i = 0; i < 16; i++) {
            table.put("R" + i, i);
        }

        table.put("SP", 0);
        table.put("LCL", 1);
        table.put("ARG", 2);
        table.put("THIS", 3);
        table.put("THAT", 4);
        table.put("SCREEN", Integer.parseInt("4000", 16));
        table.put("KBD", Integer.parseInt("6000", 16));
    }

    public void addEntry(String symbol, int address) {
        table.put(symbol, address);
    }

    public boolean contains(String symbol) {
        return table.containsKey(symbol);
    }

    public int getAddress(String symbol) {
        return table.get(symbol);
    }
}
