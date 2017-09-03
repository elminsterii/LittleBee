package elminsterii.littlebee;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import elminsterii.littlebee.tools.GlobalFunctions;

public class MainActivity extends AppCompatActivity {

    private Toolbar myToolbar;
    private TextView mTextViewSearchText;

    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private String[] mPlanetTitles;
    private ActionBarDrawerToggle mDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initToolBar();
        initDrawerLayout();
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

    private void initToolBar() {
        myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
    }

    private void initDrawerLayout() {

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayoutDrawer);
        mDrawerList = (ListView) findViewById(R.id.listViewDrawer);
        mPlanetTitles = getResources().getStringArray(R.array.drawer_list);

        int iWidth = getResources().getDisplayMetrics().widthPixels / 2;
        int iHeight = getResources().getDisplayMetrics().heightPixels;
        DrawerLayout.LayoutParams params = (android.support.v4.widget.DrawerLayout.LayoutParams) mDrawerList.getLayoutParams();
        params.width = iWidth;
        params.height = iHeight;
        mDrawerList.setLayoutParams(params);

        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow,
                GravityCompat.START);

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                myToolbar, R.string.drawer_open,
                R.string.drawer_close) {

            public void onDrawerClosed(View view) {
                invalidateOptionsMenu();
            }

            public void onDrawerOpened(View drawerView) {
                invalidateOptionsMenu();
            }
        };

        mDrawerList.setAdapter(new ArrayAdapter<>(this,
                R.layout.drawer_list_item, mPlanetTitles));

        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                mDrawerList.setItemChecked(position, true);
                setTitle(mPlanetTitles[position]);
                mDrawerLayout.closeDrawer(mDrawerList);
            }
        });

        mDrawerLayout.addDrawerListener(mDrawerToggle);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.top_bar_menu, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        final MenuItem itemSearchMenu = menu.findItem(R.id.menu_search);

        RelativeLayout rootView = (RelativeLayout) itemSearchMenu.getActionView();
        ImageButton mBtnSearch = (ImageButton) rootView.findViewById(R.id.imageButtonSearch);

        mTextViewSearchText = (TextView) rootView.findViewById(R.id.editTextSearch);

        mBtnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onOptionsItemSelected(itemSearchMenu);
            }
        });

        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        switch (item.getItemId()) {
            case R.id.menu_search:
                String strSearchText = mTextViewSearchText.getText().toString();

                if (GlobalFunctions.IsNullOrEmpty(strSearchText))
                    showSearchTextKeyboard();
                else
                    hideSearchTextKeyboard();

                //TODO : Do search function.

                break;

            default:
                return false;
        }

        return super.onOptionsItemSelected(item);
    }

    private void showSearchTextKeyboard() {
        mTextViewSearchText.requestFocus();
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(mTextViewSearchText, InputMethodManager.SHOW_IMPLICIT);
    }

    private void hideSearchTextKeyboard() {
        mTextViewSearchText.clearFocus();
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(mTextViewSearchText.getWindowToken(), 0);
    }
}
