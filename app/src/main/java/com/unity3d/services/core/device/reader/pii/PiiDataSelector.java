package com.unity3d.services.core.device.reader.pii;

import com.unity3d.services.core.configuration.IExperiments;
import com.unity3d.services.core.device.reader.JsonStorageKeyNames;
import com.unity3d.services.core.misc.IJsonStorageReader;
import com.unity3d.services.core.misc.Utilities;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class PiiDataSelector {
    private final IExperiments _experiments;
    private final IJsonStorageReader _jsonStorageReader;
    private final PiiTrackingStatusReader _piiTrackingStatusReader;

    public PiiDataSelector(PiiTrackingStatusReader piiTrackingStatusReader, IJsonStorageReader iJsonStorageReader, IExperiments iExperiments) {
        this._piiTrackingStatusReader = piiTrackingStatusReader;
        this._jsonStorageReader = iJsonStorageReader;
        this._experiments = iExperiments;
    }

    /* JADX INFO: renamed from: com.unity3d.services.core.device.reader.pii.PiiDataSelector$2, reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$unity3d$services$core$device$reader$pii$PiiPrivacyMode;

        static {
            int[] iArr = new int[PiiPrivacyMode.values().length];
            $SwitchMap$com$unity3d$services$core$device$reader$pii$PiiPrivacyMode = iArr;
            try {
                iArr[PiiPrivacyMode.NONE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$unity3d$services$core$device$reader$pii$PiiPrivacyMode[PiiPrivacyMode.NULL.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$unity3d$services$core$device$reader$pii$PiiPrivacyMode[PiiPrivacyMode.MIXED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public PiiDecisionData whatToDoWithPII() {
        int i = AnonymousClass2.$SwitchMap$com$unity3d$services$core$device$reader$pii$PiiPrivacyMode[this._piiTrackingStatusReader.getPrivacyMode().ordinal()];
        if (i == 1 || i == 2) {
            return allowTrackingDecision();
        }
        if (i == 3) {
            return mixedModeDecision();
        }
        return notAllowedDecision();
    }

    private PiiDecisionData allowTrackingDecision() {
        return new PiiDecisionData(this._experiments.isUpdatePiiFields() ? DataSelectorResult.UPDATE : DataSelectorResult.INCLUDE, getPiiContentFromStorage());
    }

    private PiiDecisionData notAllowedDecision() {
        return new PiiDecisionData(DataSelectorResult.EXCLUDE);
    }

    private PiiDecisionData mixedModeDecision() {
        if (this._piiTrackingStatusReader.getUserNonBehavioralFlag()) {
            return new PiiDecisionData(DataSelectorResult.INCLUDE, getUserBehavioralAttribute());
        }
        PiiDecisionData piiDecisionDataAllowTrackingDecision = allowTrackingDecision();
        piiDecisionDataAllowTrackingDecision.appendData(getUserBehavioralAttribute());
        return piiDecisionDataAllowTrackingDecision;
    }

    private HashMap<String, Object> getUserBehavioralAttribute() {
        return new HashMap<String, Object>() { // from class: com.unity3d.services.core.device.reader.pii.PiiDataSelector.1
            {
                put(JsonStorageKeyNames.USER_NON_BEHAVIORAL_KEY, Boolean.valueOf(PiiDataSelector.this._piiTrackingStatusReader.getUserNonBehavioralFlag()));
            }
        };
    }

    private Map<String, Object> getPiiContentFromStorage() {
        HashMap map = new HashMap();
        IJsonStorageReader iJsonStorageReader = this._jsonStorageReader;
        if (iJsonStorageReader == null) {
            return map;
        }
        Object obj = iJsonStorageReader.get(JsonStorageKeyNames.UNIFIED_CONFIG_PII_KEY);
        return obj instanceof JSONObject ? Utilities.combineJsonIntoMap(map, (JSONObject) obj, "unifiedconfig.pii.") : map;
    }
}
