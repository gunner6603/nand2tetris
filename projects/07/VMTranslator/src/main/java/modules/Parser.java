package modules;

import exception.NotSupportedException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Parser {

    private List<String> codeLines;
    private Iterator<String> codeIterator;
    private String currentCodeLine;

    public Parser(File inputFile) throws IOException {
        codeLines = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(inputFile));
        String line = null;

        while ((line = br.readLine()) != null) {
            line = line.strip().replaceAll("\\s+", " "); //remove redundant whitespace
            int commentIndex = line.indexOf("//");
            if (commentIndex != -1) {
                line = line.substring(0, commentIndex); //remove comment
            }
            if (!line.isEmpty()) {
                codeLines.add(line);
            }
        }

        codeIterator = codeLines.iterator();
    }

    public boolean hasMoreCommands() {
        return codeIterator.hasNext();
    }

    public void advance() {
        currentCodeLine = codeIterator.next();
    }

    public CommandType commandType() {
        if (currentCodeLine.startsWith("push")) {
            return CommandType.PUSH;
        }

        if (currentCodeLine.startsWith("pop")) {
            return CommandType.POP;
        }

        if (currentCodeLine.startsWith("label")) {
            return CommandType.LABEL;
        }

        if (currentCodeLine.startsWith("goto")) {
            return CommandType.GOTO;
        }

        if (currentCodeLine.startsWith("if")) {
            return CommandType.IF;
        }

        if (currentCodeLine.startsWith("function")) {
            return CommandType.FUNCTION;
        }

        if (currentCodeLine.startsWith("return")) {
            return CommandType.RETURN;
        }

        if (currentCodeLine.startsWith("call")) {
            return CommandType.CALL;
        }

        return CommandType.ARITHMETIC;
    }

    public String arg1() {
        if (commandType() == CommandType.ARITHMETIC) {
            return currentCodeLine;
        }
        if (commandType() == CommandType.RETURN) {
            throw new NotSupportedException();
        }
        String[] fields = currentCodeLine.split(" ");
        return fields[1];
    }

    public int arg2() {
        if (commandType() == CommandType.PUSH || commandType() == CommandType.POP || commandType() == CommandType.FUNCTION || commandType() == CommandType.CALL) {
            String[] fields = currentCodeLine.split(" ");
            return Integer.parseInt(fields[2]);
        }
        throw new NotSupportedException();
    }
}
