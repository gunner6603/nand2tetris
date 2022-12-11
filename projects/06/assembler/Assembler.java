import modules.CommandType;
import modules.Parser;
import modules.SymbolTable;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import static modules.Code.*;

public class Assembler {

    public static void main(String[] args) throws IOException {
        String fileName = args[0];
        String assemblyFileName = fileName.split("[.]")[0] + ".hack";
        Parser parser = new Parser(fileName);
        SymbolTable symbolTable = new SymbolTable();
        BufferedWriter bw = new BufferedWriter(new FileWriter(assemblyFileName));

        int cmdCounter = 0;
        while (parser.hasMoreCommands()) {
            parser.advance();
            CommandType commandType = parser.commandType();
            if (commandType == CommandType.L_COMMAND) {
                symbolTable.addEntry(parser.symbol(), cmdCounter);
            } else {
                cmdCounter++;
            }
        }

        parser.reset();
        int memoryPointer = 16;
        while (parser.hasMoreCommands()) {
            parser.advance();
            String convertedCode = null;
            CommandType commandType = parser.commandType();
            if (commandType == CommandType.A_COMMAND) {
                String symbol = parser.symbol();
                int address;
                if (startsWithDigit(symbol)) {
                    address = Integer.parseInt(symbol);
                } else {
                    if (!symbolTable.contains(symbol)) {
                        symbolTable.addEntry(symbol, memoryPointer);
                        memoryPointer++;
                    }
                    address = symbolTable.getAddress(symbol);
                }
                String binaryString = Integer.toBinaryString(address);
                convertedCode = "0".repeat(16 - binaryString.length()) + binaryString;
                bw.write(convertedCode + '\n');
            } else if (commandType == CommandType.C_COMMAND) {
                String dest = parser.dest();
                String comp = parser.comp();
                String jump = parser.jump();
                convertedCode = "111" + comp(comp) + dest(dest) + jump(jump);
                bw.write(convertedCode + '\n');
            }
        }
        bw.flush();
        bw.close();
    }

    private static boolean startsWithDigit(String str) {
        return str.charAt(0) - '0' >= 0 && str.charAt(0) - '0' < 10;
    }
}
