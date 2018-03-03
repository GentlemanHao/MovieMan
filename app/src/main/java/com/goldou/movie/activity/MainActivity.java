package com.goldou.movie.activity;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;

import com.goldou.movie.R;
import com.goldou.movie.fragment.CinemaFragment;
import com.goldou.movie.fragment.HomeFragment;
import com.goldou.movie.fragment.MeFragment;
import com.goldou.movie.fragment.MovieFragment;
import com.goldou.movie.fragment.ShowFragment;
import com.goldou.movie.utils.SpUtil;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabReselectListener;
import com.roughike.bottombar.OnTabSelectListener;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;

public class MainActivity extends AppCompatActivity {

    private HomeFragment homeFragment;
    private MovieFragment movieFragment;
    private CinemaFragment cinemaFragment;
    private ShowFragment showFragment;
    private MeFragment meFragment;
    private Fragment[] fragments;
    private int currentTabId;
    private BottomBar bottomBar;
    public LocationClient mLocationClient = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null) {
            homeFragment = (HomeFragment) getSupportFragmentManager().findFragmentByTag("home");
            movieFragment = (MovieFragment) getSupportFragmentManager().findFragmentByTag("movie");
            cinemaFragment = (CinemaFragment) getSupportFragmentManager().findFragmentByTag("cinema");
            showFragment = (ShowFragment) getSupportFragmentManager().findFragmentByTag("show");
            meFragment = (MeFragment) getSupportFragmentManager().findFragmentByTag("me");

            getSupportFragmentManager().beginTransaction().hide(movieFragment).hide(cinemaFragment)
                    .hide(showFragment).hide(meFragment).show(homeFragment).commit();
        } else {
            homeFragment = new HomeFragment();
            movieFragment = new MovieFragment();
            cinemaFragment = new CinemaFragment();
            showFragment = new ShowFragment();
            meFragment = new MeFragment();

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, homeFragment, "home").add(R.id.fragment_container, movieFragment, "movie")
                    .add(R.id.fragment_container, cinemaFragment, "cinema").add(R.id.fragment_container, showFragment, "show")
                    .add(R.id.fragment_container, meFragment, "me").hide(movieFragment).hide(cinemaFragment).hide(showFragment)
                    .hide(meFragment).show(homeFragment).commit();
        }
        fragments = new Fragment[]{homeFragment, movieFragment, cinemaFragment, showFragment, meFragment};

        initView();
    }

    private void initView() {

        bottomBar = (BottomBar) findViewById(R.id.bottomBar);
        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                switch (tabId) {
                    case R.id.tab_home:
                        tabId = 0;
                        break;
                    case R.id.tab_movie:
                        tabId = 1;
                        break;
                    case R.id.tab_cinema:
                        tabId = 2;
                        break;
                    case R.id.tab_show:
                        tabId = 3;
                        break;
                    case R.id.tab_me:
                        tabId = 4;
                        break;
                }
                if (currentTabId != tabId) {
                    FragmentTransaction trx = getSupportFragmentManager().beginTransaction();
                    trx.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                    trx.hide(fragments[currentTabId]);
                    if (!fragments[tabId].isAdded()) {
                        trx.add(R.id.fragment_container, fragments[tabId]);
                    }
                    trx.show(fragments[tabId]).commit();
                }
                currentTabId = tabId;
            }
        });
        bottomBar.setOnTabReselectListener(new OnTabReselectListener() {
            @Override
            public void onTabReSelected(@IdRes int tabId) {
                if (tabId == R.id.tab_movie) {
                    movieFragment.refreshHotMovie();
                }
            }
        });
    }

    public void setBottomBarSelection(int tabId) {
        bottomBar.selectTabAtPosition(tabId);
    }

    public void getLocation() {
        mLocationClient = new LocationClient(getApplicationContext());
        //声明LocationClient类
        mLocationClient.registerLocationListener(new MyLocationListener());
        //注册监听函数
        initLocation();
        mLocationClient.start();
    }

    private void initLocation() {
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
        option.setCoorType("bd09ll");
        option.setScanSpan(3000);
        option.setIsNeedAddress(true);
        option.setOpenGps(true);
        option.setLocationNotify(true);
        option.setIsNeedLocationDescribe(true);
        option.setIsNeedLocationPoiList(true);
        option.setIgnoreKillProcess(false);
        option.SetIgnoreCacheException(false);
        option.setEnableSimulateGps(false);
        mLocationClient.setLocOption(option);
    }

    public class MyLocationListener implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation location) {
            StringBuffer sb = new StringBuffer(256);
            String strLocation = "";

            sb.append("\nerror code : ");
            sb.append(location.getLocType());    //获取类型类型

            sb.append("\nlatitude : ");
            sb.append(location.getLatitude());    //获取纬度信息
            sb.append("\nlontitude : ");
            sb.append(location.getLongitude());    //获取经度信息

            SpUtil.putString(MainActivity.this, "latitude", location.getLatitude() + "");
            SpUtil.putString(MainActivity.this, "longitude", location.getLongitude() + "");

            if (location.getLocType() == BDLocation.TypeNetWorkLocation) {
                // 网络定位结果
                sb.append("\naddr : ");
                sb.append(location.getAddrStr());    //获取地址信息
                strLocation = location.getAddrStr();

                sb.append("\noperationers : ");
                sb.append(location.getOperators());    //获取运营商信息

                sb.append("\ndescribe : ");
                sb.append("网络定位成功");

                mLocationClient.stop();
                Intent intent = new Intent();
                intent.setAction("com.goldou.location");
                intent.putExtra("location", strLocation);
                MainActivity.this.sendBroadcast(intent);
            }

            sb.append("\nlocationdescribe : ");
            sb.append(location.getLocationDescribe());    //位置语义化信息

            Log.i("BaiduLocationApiDem", sb.toString());
        }

        @Override
        public void onConnectHotSpotMessage(String s, int i) {

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(0);
    }
}
