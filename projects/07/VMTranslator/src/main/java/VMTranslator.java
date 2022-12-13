import modules.CodeWriter;
import modules.Parser;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;

public class VMTranslator {

    public static void main(String[] args) throws IOException {
        String inputPath = "../StackArithmetic/SimpleAdd/SimpleAdd.vm";


        if (new File(inputPath).isDirectory()) { //not yet implemented block
            String outputPath = inputPath + ".asm";
            File dir = new File(inputPath);
            File[] files = dir.listFiles(new FileFilter() {
                @Override
                public boolean accept(File f) {
                    return f.isFile() && f.getName().endsWith(".vm");
                }
            });
            for (File file : files) {
                System.out.println("file = " + file);
            }
        } else {
            String outputPath = inputPath.substring(0, inputPath.lastIndexOf(".")) + ".asm";
            System.out.println("outputPath = " + outputPath);
            Parser parser = new Parser(inputPath);
            CodeWriter codeWriter = new CodeWriter(outputPath);

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
