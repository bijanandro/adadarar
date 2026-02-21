package com.unity3d.services.core.configuration;

import com.unity3d.services.core.request.WebRequest;
import com.unity3d.services.core.request.metrics.SDKMetrics;
import com.unity3d.services.core.request.metrics.TSIMetric;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class ConfigurationLoader implements IConfigurationLoader {
    private final ConfigurationRequestFactory _configurationRequestFactory;
    private final Configuration _localConfiguration;

    public ConfigurationLoader(ConfigurationRequestFactory configurationRequestFactory) {
        this._localConfiguration = configurationRequestFactory.getConfiguration();
        this._configurationRequestFactory = configurationRequestFactory;
    }

    @Override // com.unity3d.services.core.configuration.IConfigurationLoader
    public void loadConfiguration(IConfigurationLoaderListener iConfigurationLoaderListener) throws Exception {
        try {
            WebRequest webRequest = this._configurationRequestFactory.getWebRequest();
            InitializeEventsMetricSender.getInstance().didConfigRequestStart();
            String strMakeRequest = webRequest.makeRequest();
            if (webRequest.getResponseCode() / 100 != 2) {
                iConfigurationLoaderListener.onError("Non 2xx HTTP status received from ads configuration request.");
                return;
            }
            try {
                this._localConfiguration.handleConfigurationData(new JSONObject(strMakeRequest), true);
                sendConfigMetrics(this._localConfiguration.getUnifiedAuctionToken(), this._localConfiguration.getStateId());
                iConfigurationLoaderListener.onSuccess(this._localConfiguration);
            } catch (Exception unused) {
                iConfigurationLoaderListener.onError("Could not create web request");
            }
        } catch (Exception e) {
            iConfigurationLoaderListener.onError("Could not create web request: " + e);
        }
    }

    @Override // com.unity3d.services.core.configuration.IConfigurationLoader
    public Configuration getLocalConfiguration() {
        return this._localConfiguration;
    }

    private void sendConfigMetrics(String str, String str2) {
        if (this._localConfiguration.getExperiments() == null || !this._localConfiguration.getExperiments().isTwoStageInitializationEnabled()) {
            return;
        }
        if (str == null || str.isEmpty()) {
            SDKMetrics.getInstance().sendMetric(TSIMetric.newMissingToken());
        }
        if (str2 == null || str2.isEmpty()) {
            SDKMetrics.getInstance().sendMetric(TSIMetric.newMissingStateId());
        }
    }
}
