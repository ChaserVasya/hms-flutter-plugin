/*
 * Copyright 2020-2024. Huawei Technologies Co., Ltd. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License")
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.huawei.hms.flutter.push.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.huawei.hms.flutter.push.backgroundmessaging.BackgroundMessagingService;

public class BackgroundMessageBroadcastReceiver extends BroadcastReceiver {
    private static final String TAG = BackgroundMessageBroadcastReceiver.class.getSimpleName();
 
    
    public static final String BACKGROUND_REMOTE_MESSAGE = "com.huawei.hms.flutter.push.receiver.BACKGROUND_REMOTE_MESSAGE";
    

    @Override
    public void onReceive(Context context, final Intent intent) {
        Log.i(TAG, "BackgroundMessageBroadcastReceiver onReceive called with action: " + intent.getAction());
        
        if (intent != null) {
            final String action = intent.getAction();
            if (BACKGROUND_REMOTE_MESSAGE.equals(action)) {
                Log.i(TAG, "Processing BACKGROUND_REMOTE_MESSAGE");
                
                Bundle result = intent.getExtras();
                if (result != null) {
                    Log.i(TAG, "Extras found, enqueueing work to BackgroundMessagingService");
                    BackgroundMessagingService.enqueueWork(context, intent);
                } else {
                    Log.w(TAG, "No extras found in BACKGROUND_REMOTE_MESSAGE intent");
                }
            } else {
                Log.w(TAG, "Unknown action: " + action);
            }
        } else {
            Log.w(TAG, "Received null intent");
        }
    }
}