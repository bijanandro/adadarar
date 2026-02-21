package com.unity3d.scar.adapter.v1920.signals;

import android.content.Context;
import com.google.android.gms.ads.AdFormat;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.query.QueryInfo;
import com.unity3d.scar.adapter.common.DispatchGroup;
import com.unity3d.scar.adapter.common.signals.ISignalCollectionListener;
import com.unity3d.scar.adapter.common.signals.ISignalsReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class SignalsReader implements ISignalsReader {
    private static SignalsStorage _signalsStorage;

    public SignalsReader(SignalsStorage signalsStorage) {
        _signalsStorage = signalsStorage;
    }

    @Override // com.unity3d.scar.adapter.common.signals.ISignalsReader
    public void getSCARSignals(Context context, String[] strArr, String[] strArr2, ISignalCollectionListener iSignalCollectionListener) {
        DispatchGroup dispatchGroup = new DispatchGroup();
        for (String str : strArr) {
            dispatchGroup.enter();
            getSCARSignal(context, str, AdFormat.INTERSTITIAL, dispatchGroup);
        }
        for (String str2 : strArr2) {
            dispatchGroup.enter();
            getSCARSignal(context, str2, AdFormat.REWARDED, dispatchGroup);
        }
        dispatchGroup.notify(new GMAScarDispatchCompleted(iSignalCollectionListener));
    }

    private void getSCARSignal(Context context, String str, AdFormat adFormat, DispatchGroup dispatchGroup) {
        AdRequest adRequestBuild = new AdRequest.Builder().build();
        QueryInfoMetadata queryInfoMetadata = new QueryInfoMetadata(str);
        QueryInfoCallback queryInfoCallback = new QueryInfoCallback(queryInfoMetadata, dispatchGroup);
        _signalsStorage.put(str, queryInfoMetadata);
        QueryInfo.generate(context, adFormat, adRequestBuild, queryInfoCallback);
    }

    private class GMAScarDispatchCompleted implements Runnable {
        private ISignalCollectionListener _signalListener;

        public GMAScarDispatchCompleted(ISignalCollectionListener iSignalCollectionListener) {
            this._signalListener = iSignalCollectionListener;
        }

        @Override // java.lang.Runnable
        public void run() {
            HashMap map = new HashMap();
            Iterator<Map.Entry<String, QueryInfoMetadata>> it = SignalsReader._signalsStorage.getPlacementQueryInfoMap().entrySet().iterator();
            String error = null;
            while (it.hasNext()) {
                QueryInfoMetadata value = it.next().getValue();
                map.put(value.getPlacementId(), value.getQueryStr());
                if (value.getError() != null) {
                    error = value.getError();
                }
            }
            if (map.size() > 0) {
                this._signalListener.onSignalsCollected(new JSONObject(map).toString());
            } else if (error == null) {
                this._signalListener.onSignalsCollected("");
            } else {
                this._signalListener.onSignalsCollectionFailed(error);
            }
        }
    }
}
