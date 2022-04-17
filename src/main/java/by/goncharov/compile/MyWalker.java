package by.goncharov.compile;

import by.goncharov.compile.antlr.AntlrTestBaseListener;
import by.goncharov.compile.antlr.AntlrTestParser;
import org.antlr.v4.runtime.*;

import javax.swing.*;
import java.awt.*;
import java.util.Collections;
import java.util.List;

public class MyWalker extends BaseErrorListener {
    private static boolean errors = false;

    @Override
    public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e) {
        List<String> stack = ((Parser) recognizer).getRuleInvocationStack();
        Collections.reverse(stack);
        StringBuilder buf = new StringBuilder();
        buf.append("rule stack: ").append(stack).append(" ");
        buf.append("line ").append(line).append(":").append(charPositionInLine).append(" at ").append(offendingSymbol).append(": ").append(msg);
        JDialog dialog = new JDialog();
        Container contentPane = dialog.getContentPane();
        contentPane.add(new JLabel(buf.toString()));
        contentPane.setBackground(Color.white);
        dialog.setTitle("Syntax error");
        dialog.pack();
        dialog.setLocationRelativeTo(null);
        dialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        dialog.setVisible(true);
        errors = true;
    }

    public static void setErrors(boolean error) {
        errors = error;
    }

    public static boolean isErrors() {
        return errors;
    }
}