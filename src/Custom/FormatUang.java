/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Custom;
import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;
import java.awt.event.FocusEvent;
/**
 *
 * @author OZ
 */
public class FormatUang extends JTextField {
    private final NumberFormat format;
    private boolean isFormatting = false;
    private final String prefix = "Rp. ";
    private final String suffix = ",00";
    private final int MAX_DIGITS = 8;

    public FormatUang() {
        Locale indonesia = new Locale("id", "ID");
        format = NumberFormat.getNumberInstance(indonesia);
        format.setMinimumFractionDigits(2);
        format.setMaximumFractionDigits(2);
        
        setText(prefix + "0" + suffix);
        setHorizontalAlignment(JTextField.RIGHT);
        
        ((AbstractDocument) getDocument()).setDocumentFilter(new DocumentFilter() {
            @Override
            public void insertString(FilterBypass fb, int offset, String text, AttributeSet attr) 
                throws BadLocationException {
                if (isFormatting) {
                    super.insertString(fb, offset, text, attr);
                    return;
                }
                
                // Only allow digits
                String digits = text.replaceAll("[^0-9]", "");
                if (digits.isEmpty()) return;
                
                String currentText = fb.getDocument().getText(0, fb.getDocument().getLength());
                String numericText = currentText.replaceAll("[^0-9]", "");
                
                // Check max length
                if (numericText.length() + digits.length() > MAX_DIGITS) {
                    return;
                }
                
                // Only allow insertion in the numeric part (after prefix)
                if (offset >= prefix.length() && offset <= (prefix.length() + numericText.length())) {
                    super.insertString(fb, offset, digits, attr);
                    formatText();
                }
            }

            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) 
                throws BadLocationException {
                if (isFormatting) {
                    super.replace(fb, offset, length, text, attrs);
                    return;
                }
                
                // Only allow digits
                String digits = text.replaceAll("[^0-9]", "");
                if (digits.isEmpty() && text.length() > 0) return;
                
                String currentText = fb.getDocument().getText(0, fb.getDocument().getLength());
                String numericText = currentText.replaceAll("[^0-9]", "");
                
                // Check max length
                if (numericText.length() - length + digits.length() > MAX_DIGITS) {
                    return;
                }
                
                // Only allow replacement in the numeric part (after prefix)
                if (offset >= prefix.length() && offset <= (prefix.length() + numericText.length())) {
                    super.replace(fb, offset, length, digits, attrs);
                    formatText();
                }
            }
            
            @Override
            public void remove(FilterBypass fb, int offset, int length) throws BadLocationException {
                if (isFormatting) {
                    super.remove(fb, offset, length);
                    return;
                }
                
                String currentText = fb.getDocument().getText(0, fb.getDocument().getLength());
                String numericText = currentText.replaceAll("[^0-9]", "");
                
                // Only allow removal in the numeric part (after prefix)
                if (offset >= prefix.length() && offset < (prefix.length() + numericText.length())) {
                    super.remove(fb, offset, length);
                    formatText();
                }
            }
        });
        
        // Focus listener
        addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                SwingUtilities.invokeLater(() -> {
                    String text = getText();
                    // Position cursor after prefix
                    setCaretPosition(prefix.length());
                    // Select only the numeric part
                    setSelectionStart(prefix.length());
                    setSelectionEnd(text.length() - suffix.length());
                });
            }
            
            public void focusLost(FocusEvent evt) {
                formatText();
            }
        });
    }
    
    private void formatText() {
    if (isFormatting) return;
    
    isFormatting = true;
    try {
        // Simpan posisi caret sebelum formatting
        int caretPos = getCaretPosition();
        int selectionStart = getSelectionStart();
        int selectionEnd = getSelectionEnd();
        
        String text = getText().replaceAll("[^0-9]", "");
        
        if (text.isEmpty()) {
            setText(prefix + "0" + suffix);
            setCaretPosition(prefix.length());
            return;
        }
        
        try {
            // Potong jika melebihi max digit
            if (text.length() > MAX_DIGITS) {
                text = text.substring(0, MAX_DIGITS);
            }
            
            double amount = Double.parseDouble(text) / 100;
            String formatted = prefix + format.format(amount);
            
            // Pastikan suffix benar
            if (!formatted.endsWith(suffix)) {
                formatted = formatted.replaceAll("(\\,\\d{0,2})$", suffix);
            }
            
            if (!formatted.equals(getText())) {
                setText(formatted);
                
                // Hitung posisi caret baru setelah formatting
                int newCaretPos = calculateNewCaretPosition(caretPos, text.length(), formatted);
                setCaretPosition(newCaretPos);
                
                // Sesuaikan selection jika ada text yang dipilih
                if (selectionStart != selectionEnd) {
                    int newSelStart = calculateNewCaretPosition(selectionStart, text.length(), formatted);
                    int newSelEnd = calculateNewCaretPosition(selectionEnd, text.length(), formatted);
                    select(newSelStart, newSelEnd);
                }
            }
        } catch (NumberFormatException e) {
            setText(prefix + "0" + suffix);
            setCaretPosition(prefix.length());
        }
    } finally {
        isFormatting = false;
    }
}

// Helper method untuk menghitung posisi caret baru
private int calculateNewCaretPosition(int oldPos, int oldNumericLength, String newText) {
    // Posisi relatif terhadap bagian numeric (setelah prefix)
    int relativePos = oldPos - prefix.length();
    
    // Jika caret ada di prefix, tetap di prefix
    if (relativePos < 0) {
        return prefix.length();
    }
    
    // Jika caret ada di suffix/akhir, pindah ke akhir numeric part
    String numericPart = newText.substring(prefix.length(), newText.length() - suffix.length());
    if (relativePos >= oldNumericLength) {
        return prefix.length() + numericPart.length();
    }
    
    // Hitung posisi caret baru berdasarkan karakter non-digit yang ditambahkan (titik pemisah ribuan)
    int newPos = prefix.length();
    int numericCount = 0;
    
    for (int i = prefix.length(); i < newText.length() - suffix.length(); i++) {
        if (numericCount >= relativePos) {
            break;
        }
        if (Character.isDigit(newText.charAt(i))) {
            numericCount++;
        }
        newPos++;
    }
    
    return Math.min(newPos, prefix.length() + numericPart.length());
}
    public void setNilai(double nilai) {
    String formatted = "Rp. " + format.format(nilai);
    if (!formatted.endsWith(",00")) {
        formatted = formatted.replaceAll("(\\,\\d{0,2})$", ",00");
    }
    setText(formatted);
}
    public void setNilai(int nilai) {
    setNilai((double) nilai);
}
    public double getNilai() {
        try {
            String text = getText().replace(prefix, "").replace(".", "").replace(",", ".");
            return Double.parseDouble(text);
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }

}
