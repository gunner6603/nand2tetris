package modules;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Parser {

    private List<String> cmdLines;
    private Iterator<String> cmdIterator;
    private String currentCmd;

    public Parser(String fileName) throws IOException {
        cmdLines = new LinkedList<>();
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        String line = null;
        while ((line = br.readLine()) != null) {
            line = line.replaceAll("\\s+", "");
            int commentIdx = -1;
            if ((commentIdx = line.indexOf("//")) != -1) {
                line = line.substring(0, commentIdx);
            }
            if (!line.isEmpty()) {
                cmdLines.add(line);
            }
        }
        br.close();
        cmdIterator = cmdLines.iterator();
    }

    public boolean hasMoreCommands() {
        return cmdIterator.hasNext();
    }

    public void advance() {
        currentCmd = cmdIterator.next();
    }

    public CommandType commandType() {
        if (currentCmd.startsWith("@")) {
            return CommandType.A_COMMAND;
        }
        if (currentCmd.startsWith("(")) {
            return CommandType.L_COMMAND;
        }
        return CommandType.C_COMMAND;
    }

    public String symbol() {
        if (commandType() == CommandType.A_COMMAND) {
            return currentCmd.substring(1);
        }
        return currentCmd.substring(1, currentCmd.length() - 1);
    }

    public String dest() {
        if (currentCmd.contains("=")) {
            return currentCmd.split("=")[0];
        }
        return null;
    }

    public String comp() {
        String compCode = currentCmd;
        if (compCode.contains("=")) {
            compCode = compCode.split("=")[1];
        }
        if (compCode.contains(";")) {
            compCode = compCode.split(";")[0];
        }
        return compCode;
    }

    public String jump() {
        if (currentCmd.contains(";")) {
            return currentCmd.split(";")[1];
        }
        return null;
    }

    public static void main(String[] args) throws IOException {
        Parser parser = new Parser("Add.asm");
        while (parser.hasMoreCommands()) {
            parser.advance();
            CommandType commandType = parser.commandType();
            System.out.println("commandType = " + commandType);
            if (commandType != CommandType.C_COMMAND) {
                String symbol = parser.symbol();
                System.out.println("symbol = " + symbol);
            } else {
                String dest = parser.dest();
                System.out.println("dest = " + dest);
                String comp = parser.comp();
                System.out.println("comp = " + comp);
                String jump = parser.jump();
                System.out.println("jump = " + jump);
            }
            System.out.println("------------------------");
        }
    }
}
