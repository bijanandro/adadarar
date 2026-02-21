package com.unity3d.services.core.request.metrics;

import android.text.TextUtils;
import com.unity3d.services.core.configuration.Configuration;
import com.unity3d.services.core.log.DeviceLog;
import com.unity3d.services.core.properties.InitializationStatusReader;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes.dex */
public final class SDKMetrics {
    private static final String NULL_INSTANCE_METRICS_URL = "nullInstanceMetricsUrl";
    private static MetricSenderWithBatch _batchedSender;
    private static final AtomicBoolean _configurationIsSet = new AtomicBoolean(false);
    private static ISDKMetrics _instance;

    public static void setConfiguration(Configuration configuration) {
        if (configuration == null) {
            DeviceLog.debug("Metrics will not be sent from the device for this session due to misconfiguration");
            return;
        }
        if (isAllowedToSetConfiguration(configuration)) {
            ISDKMetrics iSDKMetrics = _instance;
            if (iSDKMetrics instanceof MetricSender) {
                ((MetricSender) iSDKMetrics).shutdown();
            }
            if (configuration.getMetricSampleRate() >= new Random().nextInt(99) + 1) {
                _instance = new MetricSender(configuration, new InitializationStatusReader());
            } else {
                DeviceLog.debug("Metrics will not be sent from the device for this session");
                _instance = new NullInstance(NULL_INSTANCE_METRICS_URL);
            }
            MetricSenderWithBatch metricSenderWithBatch = _batchedSender;
            if (metricSenderWithBatch == null) {
                _batchedSender = new MetricSenderWithBatch(_instance, new InitializationStatusReader());
            } else {
                metricSenderWithBatch.updateOriginal(_instance);
            }
            _batchedSender.sendQueueIfNeeded();
        }
    }

    public static synchronized ISDKMetrics getInstance() {
        if (_instance == null) {
            _instance = new NullInstance(null);
        }
        if (_batchedSender == null) {
            _batchedSender = new MetricSenderWithBatch(_instance, new InitializationStatusReader());
        }
        return _batchedSender;
    }

    private static boolean isAllowedToSetConfiguration(Configuration configuration) {
        return !TextUtils.isEmpty(configuration.getMetricsUrl()) && _configurationIsSet.compareAndSet(false, true);
    }

    private static final class NullInstance implements ISDKMetrics {
        private final String _metricEndpoint;

        @Override // com.unity3d.services.core.request.metrics.ISDKMetrics
        public boolean areMetricsEnabledForCurrentSession() {
            return false;
        }

        public NullInstance(String str) {
            this._metricEndpoint = str;
        }

        @Override // com.unity3d.services.core.request.metrics.ISDKMetrics
        public void sendEvent(String str) {
            DeviceLog.debug("Metric " + str + " was skipped from being sent");
        }

        @Override // com.unity3d.services.core.request.metrics.ISDKMetrics
        public void sendEvent(String str, String str2, Map<String, String> map) {
            sendEvent(str);
        }

        @Override // com.unity3d.services.core.request.metrics.ISDKMetrics
        public void sendEvent(String str, Map<String, String> map) {
            sendEvent(str);
        }

        @Override // com.unity3d.services.core.request.metrics.ISDKMetrics
        public void sendMetric(Metric metric) {
            DeviceLog.debug("Metric " + metric + " was skipped from being sent");
        }

        @Override // com.unity3d.services.core.request.metrics.ISDKMetrics
        public void sendMetrics(List<Metric> list) {
            DeviceLog.debug("Metrics: " + list + " was skipped from being sent");
        }

        @Override // com.unity3d.services.core.request.metrics.ISDKMetrics
        public void sendMetricWithInitState(Metric metric) {
            sendMetric(metric);
        }

        @Override // com.unity3d.services.core.request.metrics.ISDKMetrics
        public String getMetricEndPoint() {
            return this._metricEndpoint;
        }
    }
}
