package com.unity3d.services.core.configuration;

import com.android.billingclient.api.BillingClient;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class ExperimentObjects extends ExperimentsBase {
    private final Map<String, ExperimentObject> _experimentObjects = new HashMap();
    private final JSONObject _experimentObjetsData;

    public ExperimentObjects(JSONObject jSONObject) {
        if (jSONObject != null) {
            this._experimentObjetsData = jSONObject;
            Iterator<String> itKeys = jSONObject.keys();
            while (itKeys.hasNext()) {
                String next = itKeys.next();
                this._experimentObjects.put(next, new ExperimentObject(jSONObject.optJSONObject(next)));
            }
            return;
        }
        this._experimentObjetsData = new JSONObject();
    }

    public ExperimentObject getExperimentObject(String str) {
        return this._experimentObjects.get(str);
    }

    @Override // com.unity3d.services.core.configuration.IExperiments
    public boolean isTwoStageInitializationEnabled() {
        return getExperimentValue("tsi", true);
    }

    @Override // com.unity3d.services.core.configuration.IExperiments
    public boolean isForwardExperimentsToWebViewEnabled() {
        return getExperimentValueOrDefault(BillingClient.FeatureType.PRODUCT_DETAILS);
    }

    @Override // com.unity3d.services.core.configuration.IExperiments
    public boolean isNativeTokenEnabled() {
        return getExperimentValue("tsi_nt", true);
    }

    @Override // com.unity3d.services.core.configuration.IExperiments
    public boolean isUpdatePiiFields() {
        return getExperimentValueOrDefault("tsi_upii");
    }

    @Override // com.unity3d.services.core.configuration.IExperiments
    public boolean isPrivacyRequestEnabled() {
        return getExperimentValue("tsi_prr", true);
    }

    @Override // com.unity3d.services.core.configuration.IExperiments
    public boolean shouldNativeTokenAwaitPrivacy() {
        return getExperimentValueOrDefault("tsi_prw");
    }

    @Override // com.unity3d.services.core.configuration.IExperiments
    public boolean isNativeWebViewCacheEnabled() {
        return getExperimentValueOrDefault("nwc");
    }

    @Override // com.unity3d.services.core.configuration.IExperiments
    public boolean isWebAssetAdCaching() {
        return getExperimentValueOrDefault("wac");
    }

    @Override // com.unity3d.services.core.configuration.IExperiments
    public boolean isWebGestureNotRequired() {
        return getExperimentValueOrDefault("wgr");
    }

    @Override // com.unity3d.services.core.configuration.IExperiments
    public boolean isNewLifecycleTimer() {
        return getExperimentValueOrDefault("nlt");
    }

    private boolean getExperimentValue(String str, boolean z) {
        ExperimentObject experimentObject = getExperimentObject(str);
        return experimentObject != null ? experimentObject.getBooleanValue() : z;
    }

    private boolean getExperimentValueOrDefault(String str) {
        return getExperimentValue(str, false);
    }

    @Override // com.unity3d.services.core.configuration.IExperiments
    public JSONObject getExperimentsAsJson() {
        return this._experimentObjetsData;
    }

    @Override // com.unity3d.services.core.configuration.IExperiments
    public Map<String, String> getExperimentTags() {
        HashMap map = new HashMap();
        for (Map.Entry<String, ExperimentObject> entry : this._experimentObjects.entrySet()) {
            map.put(entry.getKey(), String.valueOf(entry.getValue().getBooleanValue()));
        }
        return map;
    }

    @Override // com.unity3d.services.core.configuration.IExperiments
    public JSONObject getCurrentSessionExperiments() {
        return getExperimentWithAppliedRule(ExperimentAppliedRule.IMMEDIATE);
    }

    @Override // com.unity3d.services.core.configuration.IExperiments
    public JSONObject getNextSessionExperiments() {
        return getExperimentWithAppliedRule(ExperimentAppliedRule.NEXT);
    }

    private JSONObject getExperimentWithAppliedRule(ExperimentAppliedRule experimentAppliedRule) {
        HashMap map = new HashMap();
        for (Map.Entry<String, ExperimentObject> entry : this._experimentObjects.entrySet()) {
            if (entry.getValue().getAppliedRule() == experimentAppliedRule) {
                map.put(entry.getKey(), String.valueOf(entry.getValue().getBooleanValue()));
            }
        }
        return new JSONObject(map);
    }
}
