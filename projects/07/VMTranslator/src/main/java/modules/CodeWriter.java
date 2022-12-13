package modules;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class CodeWriter {

    private BufferedWriter bw;

    public CodeWriter(String filePath) throws IOException {
        bw = new BufferedWriter(new FileWriter(filePath));
    }

    //public void setFileName() {}

    public void writeArithmetic(String command) throws IOException { //add, sub, neg, eq, gt, lt, and, or, not
        String[] assemblyCodes = null;

        if (command.equals("add")) {
            assemblyCodes = new String[]{
                    "@SP",
                    "M=M-1",
                    "A=M",
                    "D=M", //D=y
                    "@SP",
                    "M=M-1",
                    "A=M",
                    "M=M+D", //x=x+y
                    "@SP",
                    "M=M+1"
            };
        }

        if (command.equals("sub")) {
            assemblyCodes = new String[]{
                    "@SP",
                    "M=M-1",
                    "A=M",
                    "D=M", //D=y
                    "@SP",
                    "M=M-1",
                    "A=M",
                    "M=M-D", //x=x-y
                    "@SP",
                    "M=M+1"
            };
        }

        writeCodes(assemblyCodes);
    }

    public void writePushPop(CommandType command, String segment, int index) throws IOException { //[push/pop] [argument, local, static, constant, this, that, pointer, temp] [index]
        String[] assemblyCodes = null;

        if (segment.equals("constant")) {
            if (command == CommandType.PUSH) {
                assemblyCodes = new String[]{
                        "@" + index,
                        "D=A", //D=constant
                        "@SP",
                        "A=M",
                        "M=D", //constant at the top of the stack
                        "@SP",
                        "M=M+1"
                };
            }
        }

        writeCodes(assemblyCodes);
    }

    public void close() throws IOException {
        bw.flush();
        bw.close();
    }

    private void writeCodes(String[] codes) throws IOException {
        for (String code : codes) {
            bw.write(code + '\n');
        }
    }
}
