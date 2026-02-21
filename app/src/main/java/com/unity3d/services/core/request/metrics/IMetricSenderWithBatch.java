package com.unity3d.services.core.request.metrics;

import com.unity3d.services.core.properties.InitializationStatusReader;

/* JADX INFO: loaded from: classes.dex */
public abstract class IMetricSenderWithBatch extends MetricSenderBase {
    abstract void sendQueueIfNeeded();

    abstract void updateOriginal(ISDKMetrics iSDKMetrics);

    @Override // com.unity3d.services.core.request.metrics.MetricSenderBase, com.unity3d.services.core.request.metrics.ISDKMetrics
    public /* bridge */ /* synthetic */ void sendMetricWithInitState(Metric metric) {
        super.sendMetricWithInitState(metric);
    }

    public IMetricSenderWithBatch(InitializationStatusReader initializationStatusReader) {
        super(initializationStatusReader);
    }
}
