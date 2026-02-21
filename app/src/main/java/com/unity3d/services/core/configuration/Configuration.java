package com.unity3d.services.core.configuration;

import android.text.TextUtils;
import androidx.vectordrawable.graphics.drawable.PathInterpolatorCompat;
import com.google.android.gms.common.internal.ImagesContract;
import com.unity3d.ads.metadata.MediationMetaData;
import com.unity3d.services.core.device.reader.DeviceInfoReaderBuilder;
import com.unity3d.services.core.device.reader.GameSessionIdReader;
import com.unity3d.services.core.misc.Utilities;
import com.unity3d.services.core.properties.SdkProperties;
import com.unity3d.services.core.request.WebRequest;
import java.io.File;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class Configuration {
    private String _configJsonString;
    private String _configUrl;
    private ConfigurationRequestFactory _configurationRequestFactory;
    private int _connectedEventThresholdInMs;
    private boolean _delayWebViewUpdate;
    private ExperimentsReader _experimentReader;
    private int _loadTimeout;
    private int _maxRetries;
    private int _maximumConnectedEvents;
    private double _metricSampleRate;
    private String _metricsUrl;
    private String[] _moduleConfigurationList;
    private Map<String, IModuleConfiguration> _moduleConfigurations;
    private long _networkErrorTimeout;
    private int _privacyRequestWaitTimeout;
    private int _resetWebAppTimeout;
    private long _retryDelay;
    private double _retryScalingFactor;
    private String _sdkVersion;
    private int _showTimeout;
    private String _src;
    private String _stateId;
    private int _tokenTimeout;
    private String _unifiedAuctionToken;
    private Class[] _webAppApiClassList;
    private long _webViewAppCreateTimeout;
    private int _webViewBridgeTimeout;
    private String _webViewData;
    private String _webViewHash;
    private String _webViewUrl;
    private String _webViewVersion;

    public Configuration() {
        this._moduleConfigurationList = new String[]{"com.unity3d.services.core.configuration.CoreModuleConfiguration", "com.unity3d.services.ads.configuration.AdsModuleConfiguration", "com.unity3d.services.analytics.core.configuration.AnalyticsModuleConfiguration", "com.unity3d.services.banners.configuration.BannersModuleConfiguration", "com.unity3d.services.store.core.configuration.StoreModuleConfiguration"};
        this._experimentReader = new ExperimentsReader();
        setOptionalFields(new JSONObject(), false);
    }

    public Configuration(String str) {
        this(str, new Experiments());
    }

    public Configuration(JSONObject jSONObject) throws JSONException, MalformedURLException {
        this._moduleConfigurationList = new String[]{"com.unity3d.services.core.configuration.CoreModuleConfiguration", "com.unity3d.services.ads.configuration.AdsModuleConfiguration", "com.unity3d.services.analytics.core.configuration.AnalyticsModuleConfiguration", "com.unity3d.services.banners.configuration.BannersModuleConfiguration", "com.unity3d.services.store.core.configuration.StoreModuleConfiguration"};
        this._experimentReader = new ExperimentsReader();
        handleConfigurationData(jSONObject, false);
    }

    public Configuration(String str, ExperimentsReader experimentsReader) {
        this(str, experimentsReader.getCurrentlyActiveExperiments());
        this._experimentReader = experimentsReader;
    }

    public Configuration(String str, IExperiments iExperiments) {
        this();
        this._configUrl = str;
        this._configurationRequestFactory = new ConfigurationRequestFactory(this, new DeviceInfoReaderBuilder(new ConfigurationReader(), PrivacyConfigStorage.getInstance(), GameSessionIdReader.getInstance()));
        this._experimentReader.updateLocalExperiments(iExperiments);
    }

    public String getConfigUrl() {
        return this._configUrl;
    }

    public Class[] getWebAppApiClassList() {
        if (this._webAppApiClassList == null) {
            createWebAppApiClassList();
        }
        return this._webAppApiClassList;
    }

    public String[] getModuleConfigurationList() {
        return this._moduleConfigurationList;
    }

    public String getWebViewUrl() {
        return this._webViewUrl;
    }

    public void setWebViewUrl(String str) {
        this._webViewUrl = str;
    }

    public String getWebViewHash() {
        return this._webViewHash;
    }

    public void setWebViewHash(String str) {
        this._webViewHash = str;
    }

    public String getWebViewVersion() {
        return this._webViewVersion;
    }

    public String getWebViewData() {
        return this._webViewData;
    }

    public void setWebViewData(String str) {
        this._webViewData = str;
    }

    public String getSdkVersion() {
        return this._sdkVersion;
    }

    public boolean getDelayWebViewUpdate() {
        return this._delayWebViewUpdate;
    }

    public int getResetWebappTimeout() {
        return this._resetWebAppTimeout;
    }

    public int getMaxRetries() {
        return this._maxRetries;
    }

    public long getRetryDelay() {
        return this._retryDelay;
    }

    public double getRetryScalingFactor() {
        return this._retryScalingFactor;
    }

    public int getConnectedEventThreshold() {
        return this._connectedEventThresholdInMs;
    }

    public int getMaximumConnectedEvents() {
        return this._maximumConnectedEvents;
    }

    public long getNetworkErrorTimeout() {
        return this._networkErrorTimeout;
    }

    public int getShowTimeout() {
        return this._showTimeout;
    }

    public int getLoadTimeout() {
        return this._loadTimeout;
    }

    public int getWebViewBridgeTimeout() {
        return this._webViewBridgeTimeout;
    }

    public String getMetricsUrl() {
        return this._metricsUrl;
    }

    public double getMetricSampleRate() {
        return this._metricSampleRate;
    }

    public long getWebViewAppCreateTimeout() {
        return this._webViewAppCreateTimeout;
    }

    public String getStateId() {
        String str = this._stateId;
        return str != null ? str : "";
    }

    public String getUnifiedAuctionToken() {
        return this._unifiedAuctionToken;
    }

    public IExperiments getExperiments() {
        return this._experimentReader.getCurrentlyActiveExperiments();
    }

    public ExperimentsReader getExperimentsReader() {
        return this._experimentReader;
    }

    public int getTokenTimeout() {
        return this._tokenTimeout;
    }

    public int getPrivacyRequestWaitTimeout() {
        return this._privacyRequestWaitTimeout;
    }

    public String getSrc() {
        String str = this._src;
        return str != null ? str : "";
    }

    public IModuleConfiguration getModuleConfiguration(String str) {
        Map<String, IModuleConfiguration> map = this._moduleConfigurations;
        if (map != null && map.containsKey(str)) {
            return this._moduleConfigurations.get(str);
        }
        try {
            IModuleConfiguration iModuleConfiguration = (IModuleConfiguration) Class.forName(str).newInstance();
            if (iModuleConfiguration != null) {
                if (this._moduleConfigurations == null) {
                    HashMap map2 = new HashMap();
                    this._moduleConfigurations = map2;
                    map2.put(str, iModuleConfiguration);
                }
                return iModuleConfiguration;
            }
        } catch (Exception unused) {
        }
        return null;
    }

    public String getJSONString() {
        return this._configJsonString;
    }

    protected void makeRequest() throws Exception {
        if (this._configUrl == null) {
            throw new MalformedURLException("Base URL is null");
        }
        WebRequest webRequest = this._configurationRequestFactory.getWebRequest();
        InitializeEventsMetricSender.getInstance().didConfigRequestStart();
        handleConfigurationData(new JSONObject(webRequest.makeRequest()), true);
        saveToDisk();
    }

    protected void handleConfigurationData(JSONObject jSONObject, boolean z) throws JSONException, MalformedURLException {
        String string = !jSONObject.isNull(ImagesContract.URL) ? jSONObject.getString(ImagesContract.URL) : null;
        if (TextUtils.isEmpty(string)) {
            throw new MalformedURLException("WebView URL is null or empty");
        }
        this._webViewUrl = string;
        try {
            this._webViewHash = !jSONObject.isNull("hash") ? jSONObject.getString("hash") : null;
        } catch (JSONException unused) {
            this._webViewHash = null;
        }
        this._unifiedAuctionToken = !jSONObject.isNull("tkn") ? jSONObject.optString("tkn") : null;
        this._stateId = jSONObject.isNull("sid") ? null : jSONObject.optString("sid");
        setOptionalFields(jSONObject, z);
        this._configJsonString = getFilteredConfigJson(jSONObject).toString();
    }

    private void setOptionalFields(JSONObject jSONObject, boolean z) {
        IExperiments experiments;
        this._webViewVersion = jSONObject.optString(MediationMetaData.KEY_VERSION, null);
        this._delayWebViewUpdate = jSONObject.optBoolean("dwu", false);
        this._resetWebAppTimeout = jSONObject.optInt("rwt", 10000);
        this._maxRetries = jSONObject.optInt("mr", 6);
        this._retryDelay = jSONObject.optLong("rd", 5000L);
        this._retryScalingFactor = jSONObject.optDouble("rcf", 2.0d);
        this._connectedEventThresholdInMs = jSONObject.optInt("cet", 10000);
        this._maximumConnectedEvents = jSONObject.optInt("mce", 500);
        this._networkErrorTimeout = jSONObject.optLong("net", 60000L);
        this._sdkVersion = jSONObject.optString("sdkv", "");
        this._showTimeout = jSONObject.optInt("sto", 10000);
        this._loadTimeout = jSONObject.optInt("lto", 30000);
        this._webViewBridgeTimeout = jSONObject.optInt("wto", 5000);
        this._metricsUrl = jSONObject.optString("murl", "");
        this._metricSampleRate = jSONObject.optDouble("msr", 100.0d);
        this._webViewAppCreateTimeout = jSONObject.optLong("wct", 60000L);
        this._tokenTimeout = jSONObject.optInt("tto", 5000);
        this._privacyRequestWaitTimeout = jSONObject.optInt("prwto", PathInterpolatorCompat.MAX_NUM_POINTS);
        this._src = jSONObject.optString("src", null);
        if (jSONObject.has("expo")) {
            experiments = new ExperimentObjects(jSONObject.optJSONObject("expo"));
        } else {
            experiments = new Experiments(jSONObject.optJSONObject("exp"));
        }
        if (z) {
            this._experimentReader.updateRemoteExperiments(experiments);
        } else {
            this._experimentReader.updateLocalExperiments(experiments);
        }
    }

    private void createWebAppApiClassList() {
        ArrayList arrayList = new ArrayList();
        for (String str : getModuleConfigurationList()) {
            IModuleConfiguration moduleConfiguration = getModuleConfiguration(str);
            if (moduleConfiguration != null && moduleConfiguration.getWebAppApiClassList() != null) {
                arrayList.addAll(Arrays.asList(moduleConfiguration.getWebAppApiClassList()));
            }
        }
        this._webAppApiClassList = (Class[]) arrayList.toArray(new Class[arrayList.size()]);
    }

    public void saveToDisk() {
        Utilities.writeFile(new File(SdkProperties.getLocalConfigurationFilepath()), getJSONString());
    }

    private JSONObject getFilteredConfigJson(JSONObject jSONObject) throws JSONException {
        JSONObject jSONObject2 = new JSONObject();
        Iterator<String> itKeys = jSONObject.keys();
        while (itKeys.hasNext()) {
            String next = itKeys.next();
            Object objOpt = jSONObject.opt(next);
            if (!next.equals("tkn") && !next.equals("sid")) {
                jSONObject2.put(next, objOpt);
            }
        }
        return jSONObject2;
    }
}
