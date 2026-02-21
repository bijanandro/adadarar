package com.unity3d.services.core.request.metrics;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class Metric {
    private static final String METRIC_NAME = "n";
    private static final String METRIC_TAGS = "t";
    private static final String METRIC_VALUE = "v";
    private final String name;
    private final Map<String, String> tags;
    private final Object value;

    public Metric(String str, Object obj, Map<String, String> map) {
        this.name = str;
        this.value = obj;
        this.tags = map;
    }

    Map<String, Object> asMap() {
        HashMap map = new HashMap();
        String str = this.name;
        if (str != null) {
            map.put(METRIC_NAME, str);
        }
        Object obj = this.value;
        if (obj != null) {
            map.put(METRIC_VALUE, obj);
        }
        Map<String, String> map2 = this.tags;
        if (map2 != null) {
            map.put(METRIC_TAGS, map2);
        }
        return map;
    }

    public String getName() {
        return this.name;
    }

    public Object getValue() {
        return this.value;
    }

    public Map<String, String> getTags() {
        return this.tags;
    }

    public String toString() {
        return "Metric{name='" + this.name + "', value='" + this.value + "', tags=" + this.tags + '}';
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Metric)) {
            return false;
        }
        Metric metric = (Metric) obj;
        return Objects.equals(this.name, metric.name) && Objects.equals(this.value, metric.value) && Objects.equals(this.tags, metric.tags);
    }

    public int hashCode() {
        return Objects.hash(this.name, this.value, this.tags);
    }
}
