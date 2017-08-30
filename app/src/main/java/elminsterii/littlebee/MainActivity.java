package elminsterii.littlebee;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
//
//        // Inflate your custom layout
//        final ViewGroup actionBarLayout = (ViewGroup) getLayoutInflater().inflate(
//                R.layout.top_bar_layout,
//                null);
//
//        // Set up your ActionBar
//        final ActionBar actionBar = getSupportActionBar();
//        actionBar.setDisplayShowHomeEnabled(false);
//        actionBar.setDisplayShowTitleEnabled(false);
//        actionBar.setDisplayShowCustomEnabled(true);
//        actionBar.setCustomView(actionBarLayout);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.top_bar_menu, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        final MenuItem itemSetting = menu.findItem(R.id.menu_setting);
        final MenuItem itemAdd = menu.findItem(R.id.menu_add);

        RelativeLayout rootView = (RelativeLayout) itemSetting.getActionView();

        ImageButton mBtnSetting = (ImageButton) rootView.findViewById(R.id.imageButtonSetting);
        ImageButton mBtnAdd = (ImageButton) rootView.findViewById(R.id.imageButtonAdd);

        mBtnSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onOptionsItemSelected(itemSetting);
            }
        });

        mBtnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onOptionsItemSelected(itemAdd);
            }
        });

        return super.onPrepareOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        switch (item.getItemId()) {
            case R.id.menu_setting:
                break;

            case R.id.menu_add:
                break;

            default:
                return false;
        }

        return true;
    }
}
