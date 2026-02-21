package com.unity3d.services.core.request.metrics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class MetricsContainer {
    private static final String METRICS_CONTAINER = "m";
    private static final String METRICS_CONTAINER_TAGS = "t";
    private static final String METRIC_CONTAINER_SAMPLE_RATE = "msr";
    private final MetricCommonTags _commonTags;
    private final String _metricSampleRate;
    private final List<Metric> _metrics;

    public MetricsContainer(String str, MetricCommonTags metricCommonTags, List<Metric> list) {
        this._metricSampleRate = str;
        this._commonTags = metricCommonTags;
        this._metrics = list;
    }

    public Map<String, Object> asMap() {
        HashMap map = new HashMap();
        ArrayList arrayList = new ArrayList();
        Iterator<Metric> it = this._metrics.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().asMap());
        }
        map.put(METRIC_CONTAINER_SAMPLE_RATE, this._metricSampleRate);
        map.put(METRICS_CONTAINER, arrayList);
        map.put(METRICS_CONTAINER_TAGS, this._commonTags.asMap());
        return map;
    }
}
