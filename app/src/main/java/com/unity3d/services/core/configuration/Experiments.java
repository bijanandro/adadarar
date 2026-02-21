package com.unity3d.services.core.configuration;

import com.android.billingclient.api.BillingClient;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class Experiments extends ExperimentsBase {
    private static final Set<String> NEXT_SESSION_FLAGS = new HashSet(Arrays.asList("tsi", "tsi_upii", "tsi_p", "tsi_nt", "tsi_prr", "tsi_prw"));
    private final JSONObject _experimentData;

    public Experiments() {
        this(null);
    }

    public Experiments(JSONObject jSONObject) {
        if (jSONObject == null) {
            this._experimentData = new JSONObject();
        } else {
            this._experimentData = jSONObject;
        }
    }

    @Override // com.unity3d.services.core.configuration.IExperiments
    public boolean isTwoStageInitializationEnabled() {
        return this._experimentData.optBoolean("tsi", true);
    }

    @Override // com.unity3d.services.core.configuration.IExperiments
    public boolean isForwardExperimentsToWebViewEnabled() {
        return this._experimentData.optBoolean(BillingClient.FeatureType.PRODUCT_DETAILS, false);
    }

    @Override // com.unity3d.services.core.configuration.IExperiments
    public boolean isNativeTokenEnabled() {
        return this._experimentData.optBoolean("tsi_nt", true);
    }

    @Override // com.unity3d.services.core.configuration.IExperiments
    public boolean isUpdatePiiFields() {
        return this._experimentData.optBoolean("tsi_upii", false);
    }

    @Override // com.unity3d.services.core.configuration.IExperiments
    public boolean isPrivacyRequestEnabled() {
        return this._experimentData.optBoolean("tsi_prr", true);
    }

    @Override // com.unity3d.services.core.configuration.IExperiments
    public boolean shouldNativeTokenAwaitPrivacy() {
        return this._experimentData.optBoolean("tsi_prw", false);
    }

    @Override // com.unity3d.services.core.configuration.IExperiments
    public boolean isNativeWebViewCacheEnabled() {
        return this._experimentData.optBoolean("nwc", false);
    }

    @Override // com.unity3d.services.core.configuration.IExperiments
    public boolean isWebAssetAdCaching() {
        return this._experimentData.optBoolean("wac", false);
    }

    @Override // com.unity3d.services.core.configuration.IExperiments
    public boolean isWebGestureNotRequired() {
        return this._experimentData.optBoolean("wgr", false);
    }

    @Override // com.unity3d.services.core.configuration.IExperiments
    public boolean isNewLifecycleTimer() {
        return this._experimentData.optBoolean("nlt", false);
    }

    @Override // com.unity3d.services.core.configuration.IExperiments
    public JSONObject getExperimentsAsJson() {
        return this._experimentData;
    }

    @Override // com.unity3d.services.core.configuration.IExperiments
    public Map<String, String> getExperimentTags() {
        HashMap map = new HashMap();
        Iterator<String> itKeys = this._experimentData.keys();
        while (itKeys.hasNext()) {
            String next = itKeys.next();
            map.put(next, String.valueOf(this._experimentData.opt(next)));
        }
        return map;
    }

    @Override // com.unity3d.services.core.configuration.IExperiments
    public JSONObject getNextSessionExperiments() {
        if (this._experimentData == null) {
            return null;
        }
        HashMap map = new HashMap();
        Iterator<String> itKeys = this._experimentData.keys();
        while (itKeys.hasNext()) {
            String next = itKeys.next();
            if (NEXT_SESSION_FLAGS.contains(next)) {
                map.put(next, String.valueOf(this._experimentData.optBoolean(next)));
            }
        }
        return new JSONObject(map);
    }

    @Override // com.unity3d.services.core.configuration.IExperiments
    public JSONObject getCurrentSessionExperiments() {
        if (this._experimentData == null) {
            return null;
        }
        HashMap map = new HashMap();
        Iterator<String> itKeys = this._experimentData.keys();
        while (itKeys.hasNext()) {
            String next = itKeys.next();
            if (!NEXT_SESSION_FLAGS.contains(next)) {
                map.put(next, String.valueOf(this._experimentData.optBoolean(next)));
            }
        }
        return new JSONObject(map);
    }
}
