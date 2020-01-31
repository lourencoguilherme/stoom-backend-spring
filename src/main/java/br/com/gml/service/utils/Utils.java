package br.com.gml.service.utils;

import br.com.gml.service.utils.enums.Mask;
import br.com.gml.service.utils.enums.PropLocale;

import javax.swing.text.MaskFormatter;
import java.text.MessageFormat;
import java.text.ParseException;
import java.util.Locale;
import java.util.ResourceBundle;

public class Utils {

    public static String cepFormatString(String value) {
        return formatString(value, Mask.CEP.getValue());
    }

    public static String formatString(String value, String pattern) {
        MaskFormatter mf;
        try {
            mf = new MaskFormatter(pattern);
            mf.setValueContainsLiteralCharacters(false);
            return mf.valueToString(value);
        } catch (ParseException ex) {
            return value;
        }
    }

    public static String pegaKeyPropertiesPadrao(String key) {
        return pegaKeyProperties(PropLocale.getDefault(), key, (Object[]) null);
    }

    public static String pegaKeyPropertiesPadrao(PropLocale props, String key) {
        return pegaKeyProperties(props, key, (Object[]) null);
    }

    public static String pegaKeyPropertiesPadrao(String key, Object... items) {
        return pegaKeyProperties(PropLocale.getDefault(), key, items);
    }

    public static String pegaKeyProperties(PropLocale props, String key, Object... items) {
        try{

            Locale propsLocale = new Locale(props.getLanguage(),props.getPais());

            ResourceBundle bundle = ResourceBundle.getBundle("messages", propsLocale);

            String messages = key;

            if(items == null) {
                messages = bundle.getString(key);

            } else {
                for (Object i : items) {
                    if (i == null) {
                        i = "";
                    }
                }
                messages = MessageFormat.format(bundle.getString(key), items);
            }

            return messages;
        } catch(Exception ex) {

        }
        return key;
    }
}
