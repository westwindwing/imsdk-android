package com.qunar.im.base.jobs;

import com.qunar.im.base.util.LogUtil;

public abstract class OnJob implements Runnable {
    public void onCancel() {
        LogUtil.d("TASK", "Canceled");
    }

    public void onAdded() {
        LogUtil.d("TASK", "Added");
    }
}
