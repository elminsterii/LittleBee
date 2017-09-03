package elminsterii.littlebee.tools;

/**
 * Created by ElminsterII on 2017/9/3.
 */

public class GlobalFunctions {

    public static boolean IsNullOrEmpty(String strData) {
        if (strData == null || strData.isEmpty())
            return true;
        return false;
    }
}
