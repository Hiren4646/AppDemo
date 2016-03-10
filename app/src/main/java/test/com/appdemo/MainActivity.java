package test.com.appdemo;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import test.com.utils.Utils;

public class MainActivity extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */


    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private static final int ANIM_DURATION_TOOLBAR = 300;
    Toolbar toolbar;
    TextView txtItem;
    ImageView imgToolbarLogo;
    String tabTitles[] = new String[] { "Item1", "Item2", "Item3" ,"Item4","Item5"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtItem=(TextView)findViewById(R.id.txtItem);
         toolbar = (Toolbar) findViewById(R.id.toolbar);
        imgToolbarLogo = (ImageView) findViewById(R.id.ivLogo);
        setSupportActionBar(toolbar);
        final LinearLayout linearBackground=(LinearLayout)findViewById(R.id.linearBackground);
       final Button btnOrange=(Button)findViewById(R.id.btnOrange);
        final Button btnBlue=(Button)findViewById(R.id.btnBlue);
        final  Button btnGreen=(Button)findViewById(R.id.btnGreen);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.


        // Get the ViewPager and set it's PagerAdapter so that it can display items
        ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        viewPager.setAdapter(new SampleFragmentPagerAdapter(getSupportFragmentManager(),
                MainActivity.this));

        // Give the TabLayout the ViewPager
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);


        btnOrange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linearBackground.setBackgroundColor(getResources().getColor(android.R.color.holo_orange_light));
            }
        });
        btnBlue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linearBackground.setBackgroundColor(getResources().getColor(android.R.color.holo_blue_light));
            }
        });
        btnGreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linearBackground.setBackgroundColor(getResources().getColor(android.R.color.holo_green_light));
            }
        });
        viewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {

                getTabTitle(tabTitles[position]);
            }
        });
       /* tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                getTabTitle(tab.getText().toString());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });*/
    }

    public  void getTabTitle(String title){
        txtItem.setText(title);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        int actionbarSize = Utils.dpToPx(56);
        toolbar.setTranslationY(-actionbarSize);
        imgToolbarLogo.setTranslationY(-actionbarSize);
       // getInboxMenuItem().getActionView().setTranslationY(-actionbarSize);

        toolbar.animate()
                .translationY(0)
                .setDuration(ANIM_DURATION_TOOLBAR)
                .setStartDelay(300);
        imgToolbarLogo.animate()
                .translationY(0)
                .setDuration(ANIM_DURATION_TOOLBAR)
                .setStartDelay(400);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_scenario2) {
        Intent i=new Intent(getApplicationContext(),SecondActivity.class);
            startActivity(i);


            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public class SampleFragmentPagerAdapter extends FragmentPagerAdapter {
        final int PAGE_COUNT = 5;

        private Context context;

        public SampleFragmentPagerAdapter(FragmentManager fm, Context context) {
            super(fm);
            this.context = context;
        }

        @Override
        public int getCount() {
            return PAGE_COUNT;
        }

        @Override
        public Fragment getItem(int position) {

                return TabFragment.newInstance(position + 1);


        }

        @Override
        public CharSequence getPageTitle(int position) {
            // Generate title based on item position
            getTabTitle(tabTitles[0]);
            return tabTitles[position];
        }
    }
}
