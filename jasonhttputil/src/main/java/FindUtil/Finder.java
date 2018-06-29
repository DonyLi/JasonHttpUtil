package FindUtil;

import android.app.Activity;
import android.view.View;

public class Finder {
    private Activity activity;
    private View view;

    public Finder(Activity activity) {
        this.activity = activity;
    }

    public Finder(View view) {
        this.view = view;
    }

    public View findViewById(int id) {
        View view;
        if (this.activity != null) {
            view = this.activity.findViewById(id);
        } else {
            view = this.view.findViewById(id);
        }
        return view;
    }

}
