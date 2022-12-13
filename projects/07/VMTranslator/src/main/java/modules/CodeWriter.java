package modules;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CodeWriter {

    private BufferedWriter bw;
    private int labelCount = 0;
    private String fileName;

    public CodeWriter(File outputFile) throws IOException {
        bw = new BufferedWriter(new FileWriter(outputFile));
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

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

        if (command.equals("neg")) {
            assemblyCodes = new String[]{
                    "@SP",
                    "M=M-1",
                    "A=M",
                    "M=-M",
                    "@SP",
                    "M=M+1"
            };
        }

        if (command.equals("eq")) {
            String ifTrue = getNextLabel();
            String end = getNextLabel();
            assemblyCodes = new String[]{
                    "@SP",
                    "M=M-1",
                    "A=M",
                    "D=M", //D=y
                    "@SP",
                    "M=M-1",
                    "A=M",
                    "D=M-D", //D=x-y
                    address(ifTrue),
                    "D;JEQ", //if x==y, goto ifTrue
                    "@SP",
                    "A=M",
                    "M=0", //push false(0)
                    address(end),
                    "0;JMP",
                    defineLabel(ifTrue),
                    "@SP",
                    "A=M",
                    "M=-1", //push true(-1)
                    defineLabel(end),
                    "@SP",
                    "M=M+1"
            };
        }

        if (command.equals("gt")) {
            String ifTrue = getNextLabel();
            String end = getNextLabel();
            assemblyCodes = new String[]{
                    "@SP",
                    "M=M-1",
                    "A=M",
                    "D=M", //D=y
                    "@SP",
                    "M=M-1",
                    "A=M",
                    "D=M-D", //D=x-y
                    address(ifTrue),
                    "D;JGT", //if x>y, goto ifTrue
                    "@SP",
                    "A=M",
                    "M=0", //push false(0)
                    address(end),
                    "0;JMP",
                    defineLabel(ifTrue),
                    "@SP",
                    "A=M",
                    "M=-1", //push true(-1)
                    defineLabel(end),
                    "@SP",
                    "M=M+1"
            };
        }

        if (command.equals("lt")) {
            String ifTrue = getNextLabel();
            String end = getNextLabel();
            assemblyCodes = new String[]{
                    "@SP",
                    "M=M-1",
                    "A=M",
                    "D=M", //D=y
                    "@SP",
                    "M=M-1",
                    "A=M",
                    "D=M-D", //D=x-y
                    address(ifTrue),
                    "D;JLT", //if x<y, goto ifTrue
                    "@SP",
                    "A=M",
                    "M=0", //push false(0)
                    address(end),
                    "0;JMP",
                    defineLabel(ifTrue),
                    "@SP",
                    "A=M",
                    "M=-1", //push true(-1)
                    defineLabel(end),
                    "@SP",
                    "M=M+1"
            };
        }

        if (command.equals("and")) {
            assemblyCodes = new String[]{
                    "@SP",
                    "M=M-1",
                    "A=M",
                    "D=M", //D=y
                    "@SP",
                    "M=M-1",
                    "A=M",
                    "M=M&D", //x=x&y
                    "@SP",
                    "M=M+1"
            };
        }

        if (command.equals("or")) {
            assemblyCodes = new String[]{
                    "@SP",
                    "M=M-1",
                    "A=M",
                    "D=M", //D=y
                    "@SP",
                    "M=M-1",
                    "A=M",
                    "M=M|D", //x=x|y
                    "@SP",
                    "M=M+1"
            };
        }

        if (command.equals("not")) {
            assemblyCodes = new String[]{
                    "@SP",
                    "M=M-1",
                    "A=M",
                    "M=!M",
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
                        "M=D", //push to the top of the stack
                        "@SP",
                        "M=M+1"
                };
            }
        }

        if (segment.equals("local") || segment.equals("argument") || segment.equals("this") || segment.equals("that")) {
            String segmentStartPointer = null;
            if (segment.equals("local")) { //initialize segmentation pointer
                segmentStartPointer = "LCL";
            } else if (segment.equals("argument")) {
                segmentStartPointer = "ARG";
            } else if (segment.equals("this")) {
                segmentStartPointer = "THIS";
            } else if (segment.equals("that")) {
                segmentStartPointer = "THAT";
            }

            if (command == CommandType.PUSH) {
                assemblyCodes = new String[]{
                        address(segmentStartPointer),
                        "D=M",
                        "@" + index,
                        "A=D+A",
                        "D=M", //D=value to be pushed
                        "@SP",
                        "A=M",
                        "M=D",
                        "@SP",
                        "M=M+1"
                };
            } else if (command == CommandType.POP) {
                assemblyCodes = new String[]{
                        address(segmentStartPointer),
                        "D=M",
                        "@" + index,
                        "D=D+A",
                        "@R13",
                        "M=D", //R13=address to save on
                        "@SP",
                        "M=M-1",
                        "A=M",
                        "D=M", //D=popped value
                        "@R13",
                        "A=M",
                        "M=D"
                };
            }
        }

        if (segment.equals("pointer") || segment.equals("temp")) {
            int segmentStart = -1;
            if (segment.equals("pointer")) {
                segmentStart = 3;
            } else if (segment.equals("temp")) {
                segmentStart = 5;
            }

            int targetAddress = segmentStart + index;

            if (command == CommandType.PUSH) {
                assemblyCodes = new String[] {
                        "@" + targetAddress,
                        "D=M",
                        "@SP",
                        "A=M",
                        "M=D",
                        "@SP",
                        "M=M+1"
                };
            } else if (command == CommandType.POP) {
                assemblyCodes = new String[] {
                        "@SP",
                        "M=M-1",
                        "A=M",
                        "D=M",
                        "@" + targetAddress,
                        "M=D"
                };
            }
        }

        if (segment.equals("static")) {
            String targetLabel = fileName + "." + index;

            if (command == CommandType.PUSH) {
                assemblyCodes = new String[] {
                        address(targetLabel),
                        "D=M",
                        "@SP",
                        "A=M",
                        "M=D",
                        "@SP",
                        "M=M+1"
                };
            } else if (command == CommandType.POP) {
                assemblyCodes = new String[] {
                        "@SP",
                        "M=M-1",
                        "A=M",
                        "D=M",
                        address(targetLabel),
                        "M=D"
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

    private String getNextLabel() {
        String label =  "LABEL$" + labelCount;
        labelCount++;
        return label;
    }

    private String address(String label) {
        return "@" + label;
    }

    private String defineLabel(String label) {
        return "(" + label + ")";
    }
}
