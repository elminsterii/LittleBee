package elminsterii.littlebee.adapters;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.widget.ArrayAdapter;

/**
 * Created by ElminsterII on 2017/8/29.
 */

public class DrawerListAdapter extends ArrayAdapter {

    public DrawerListAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull Object[] objects) {
        super(context, resource, objects);
    }


}
