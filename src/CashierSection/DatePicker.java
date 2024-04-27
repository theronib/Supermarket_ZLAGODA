package CashierSection;

import javax.swing.*;
import java.text.ParseException;
import java.util.Calendar;

public class DatePicker extends JFormattedTextField.AbstractFormatter {
    private String datePattern = "M/dd/yyyy";
    private java.text.SimpleDateFormat dateFormatter = new java.text.SimpleDateFormat(datePattern);

    @Override
    public Object stringToValue(String text) throws ParseException {
        return dateFormatter.parseObject(text);
    }

    @Override
    public String valueToString(Object value) throws ParseException {
        if (value != null) {
            Calendar cal = (Calendar) value;
            return dateFormatter.format(cal.getTime());
        }
        return "";
    }
}

