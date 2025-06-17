/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Custom;

import javax.swing.JOptionPane;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

/**
 *
 * @author OZ
 */
public class NoteLength extends DocumentFilter {
    private int maxLength;
    
    public NoteLength(int maxLength) {
        this.maxLength = maxLength;
    }

    @Override
    public void insertString(DocumentFilter.FilterBypass fb, int offset, String string, AttributeSet attr) 
            throws BadLocationException {
        if ((fb.getDocument().getLength() + string.length()) <= maxLength) {
            super.insertString(fb, offset, string, attr);
        } else {
            JOptionPane.showMessageDialog(null,
                "Maksimal " + maxLength + " karakter tercapai",
                "Peringatan",
                JOptionPane.WARNING_MESSAGE);
        }
    }

    @Override
    public void replace(DocumentFilter.FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
            throws BadLocationException {
        if ((fb.getDocument().getLength() + text.length() - length) <= maxLength) {
            super.replace(fb, offset, length, text, attrs);
        } else {
            JOptionPane.showMessageDialog(null,
                "Maksimal " + maxLength + " karakter tercapai",
                "Peringatan",
                JOptionPane.WARNING_MESSAGE);
        }
    }
}
