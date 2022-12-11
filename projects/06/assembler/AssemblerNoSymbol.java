import modules.Code;
import modules.CommandType;
import modules.Parser;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import static modules.Code.*;

public class AssemblerNoSymbol {

    public static void main(String[] args) throws IOException {
        String fileName = args[0];
        String assemblyFileName = fileName.split("[.]")[0] + ".hack";
        Parser parser = new Parser(fileName);
        BufferedWriter bw = new BufferedWriter(new FileWriter(assemblyFileName));

        while (parser.hasMoreCommands()) {
            parser.advance();
            CommandType commandType = parser.commandType();
            if (commandType == CommandType.A_COMMAND) {
                String binaryString = Integer.toBinaryString(Integer.parseInt(parser.symbol()));
                String convertedCode = "0".repeat(16 - binaryString.length()) + binaryString;
                bw.write(convertedCode + '\n');
            } else {
                String dest = parser.dest();
                String comp = parser.comp();
                String jump = parser.jump();
                String convertedCode = "111" + comp(comp) + dest(dest) + jump(jump);
                bw.write(convertedCode + '\n');
            }
        }
        bw.flush();
        bw.close();
    }
}
