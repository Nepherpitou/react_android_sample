package co.tula.nativewidget;

import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;

public class ReactSomeViewManager extends SimpleViewManager<SomeView> {
    @Override
    public String getName() {
        return "RCSomeView";
    }

    @Override
    protected SomeView createViewInstance(ThemedReactContext reactContext) {
        return new SomeView(reactContext);
    }
}
