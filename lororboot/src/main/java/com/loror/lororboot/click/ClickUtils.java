package com.loror.lororboot.click;

import android.app.Activity;
import android.app.Dialog;
import android.support.v4.app.Fragment;
import android.view.View;

import com.loror.lororboot.annotation.Click;

import java.lang.reflect.Method;

public class ClickUtils {
    /**
     * 找到所有Click并注册监听
     */
    public static void findAndBindClick(final Object object) {
        Method[] methods = object.getClass().getMethods();
        for (int i = 0; i < methods.length; i++) {
            final Method method = methods[i];
            Click click = (Click) method.getAnnotation(Click.class);
            if (click != null) {
                View view = null;
                int id = click.id();
                if (object instanceof Activity) {
                    view = ((Activity) object).findViewById(id);
                } else if (object instanceof Fragment) {
                    view = ((Fragment) object).getView().findViewById(id);
                } else if (object instanceof Dialog) {
                    view = ((Dialog) object).findViewById(id);
                }
                if (view != null) {
                    method.setAccessible(true);
                    final long clickSpace = click.clickSpace();
                    view.setOnClickListener(new View.OnClickListener() {
                        long clickTime;

                        @Override
                        public void onClick(View v) {
                            if (System.currentTimeMillis() - clickTime > clickSpace) {
                                clickTime = System.currentTimeMillis();
                                try {
                                    method.invoke(object, v);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    });
                }
            }
        }
    }

    /**
     * 找到BindAbleAdapter中所有Click并注册监听
     */
    public static void findAndBindClickOfAdapter(final Object object, View parent) {
        Method[] methods = object.getClass().getMethods();
        for (int i = 0; i < methods.length; i++) {
            final Method method = methods[i];
            Click click = (Click) method.getAnnotation(Click.class);
            if (click != null) {
                int id = click.id();
                View view = parent.findViewById(id);
                if (view != null) {
                    method.setAccessible(true);
                    final long clickSpace = click.clickSpace();
                    view.setOnClickListener(new View.OnClickListener() {
                        long clickTime;

                        @Override
                        public void onClick(View v) {
                            if (System.currentTimeMillis() - clickTime > clickSpace) {
                                clickTime = System.currentTimeMillis();
                                try {
                                    method.invoke(object, v);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    });
                }
            }
        }
    }
}
