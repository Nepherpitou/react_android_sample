package co.tula.nativewidget;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import com.facebook.react.ReactInstanceManager;
import com.facebook.react.ReactRootView;
import com.facebook.react.bridge.NativeModuleCallExceptionHandler;
import com.facebook.react.common.LifecycleState;
import com.facebook.react.shell.MainReactPackage;

public class MainActivity extends AppCompatActivity {

    private static final int OVERLAY_PERMISSION_REQ_CODE = 1000;

    @NonNull
    private ReactRootView        rootView;
    private ReactInstanceManager instanceManager;
    private NativeModuleCallExceptionHandler exHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rootView = (ReactRootView) findViewById(R.id.react);

        exHandler = new NativeModuleCallExceptionHandler() {
            @Override
            public void handleException(Exception e) {
                e.printStackTrace();
            }
        };

        instanceManager = ReactInstanceManager.builder()
                                              .setApplication(getApplication())
                                              .setBundleAssetName("index.android.bundle")
                                              .setJSMainModuleName("NativeWidget")
                                              .addPackage(new MainReactPackage())
                                              .addPackage(new SomeReactPackage())
                                              .setNativeModuleCallExceptionHandler(exHandler)
                                              .setUseDeveloperSupport(true)
                                              .setInitialLifecycleState(LifecycleState.RESUMED)
                                              .build();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!Settings.canDrawOverlays(this)) {
                Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                                           Uri.parse("package:" + getPackageName()));
                startActivityForResult(intent, OVERLAY_PERMISSION_REQ_CODE);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == OVERLAY_PERMISSION_REQ_CODE) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (!Settings.canDrawOverlays(this)) {
                    // SYSTEM_ALERT_WINDOW permission not granted...
                }
            }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        instanceManager.onHostPause(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        instanceManager.onHostResume(this, null);
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        rootView.startReactApplication(instanceManager, "NativeWidget", null);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        instanceManager.onHostDestroy(this);
    }
}
