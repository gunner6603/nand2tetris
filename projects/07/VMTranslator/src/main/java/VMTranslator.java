import modules.CodeWriter;
import modules.Parser;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;

public class VMTranslator {

    public static void main(String[] args) throws IOException {
        File inputPath = new File(args[0]);

        if (inputPath.isDirectory()) { //not yet implemented block
            File outputFile = new File(inputPath.getPath() + ".asm");
            File dir = inputPath;
            File[] inputFiles = dir.listFiles(new FileFilter() {
                @Override
                public boolean accept(File f) {
                    return f.isFile() && f.getName().endsWith(".vm");
                }
            });

            CodeWriter codeWriter = new CodeWriter(outputFile);
            codeWriter.writeInit();

            for (File inputFile : inputFiles) {
                translateAndWrite(inputFile, codeWriter);
            }

            codeWriter.close();

        } else {
            File inputFile = inputPath;
            File outputFile = new File(inputPath.getPath().replaceAll("[.]vm$", ".asm"));
            CodeWriter codeWriter = new CodeWriter(outputFile);
            translateAndWrite(inputFile, codeWriter);
            codeWriter.close();
        }
    }

    private static void translateAndWrite(File inputFile, CodeWriter codeWriter) throws IOException {
        Parser parser = new Parser(inputFile);
        codeWriter.setFileName(inputFile.getName().replace(".vm", ""));

        while (parser.hasMoreCommands()) {
            parser.advance();
            switch (parser.commandType()) {
                case ARITHMETIC:
                    codeWriter.writeArithmetic(parser.arg1());
                    break;
                case PUSH:
                case POP:
                    codeWriter.writePushPop(parser.commandType(), parser.arg1(), parser.arg2());
                    break;
                case LABEL:
                    codeWriter.writeLabel(parser.arg1());
                    break;
                case GOTO:
                    codeWriter.writeGoto(parser.arg1());
                    break;
                case IF:
                    codeWriter.writeIf(parser.arg1());
                    break;
                case FUNCTION:
                    codeWriter.writeFunction(parser.arg1(), parser.arg2());
                    break;
                case RETURN:
                    codeWriter.writeReturn();
                    break;
                case CALL:
                    codeWriter.writeCall(parser.arg1(), parser.arg2());
                    break;
            }
        }
    }
}
