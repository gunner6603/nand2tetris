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
        } else {
            File inputFile = inputPath;
            File outputFile = new File(inputPath.getPath().replaceAll("[.]vm$", ".asm"));
            Parser parser = new Parser(inputFile);
            CodeWriter codeWriter = new CodeWriter(outputFile);
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
                }
            }

            codeWriter.close();
        }
    }
}
