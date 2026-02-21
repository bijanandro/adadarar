package com.unity3d.player;

import androidx.core.util.Predicate$$ExternalSyntheticBackport0;
import java.text.SimpleDateFormat;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
abstract class ApplicationExitInfoBase {
    ApplicationExitInfoBase() {
    }

    public abstract int describeContents();

    public abstract int getDefiningUid();

    public abstract String getDescription();

    public abstract int getImportance();

    public abstract int getPackageUid();

    public abstract int getPid();

    public abstract String getProcessName();

    public abstract byte[] getProcessStateSummary();

    public abstract long getPss();

    public abstract int getRealUid();

    public abstract int getReason();

    public abstract long getRss();

    public abstract int getStatus();

    public abstract long getTimestamp();

    public abstract byte[] getTrace();

    public int hashCode() {
        return Objects.hashCode(getDescription()) + ((Objects.hashCode(getProcessName()) + ((Predicate$$ExternalSyntheticBackport0.m(getTimestamp()) + (((((((((((((((((getPid() * 31) + getRealUid()) * 31) + getPackageUid()) * 31) + getDefiningUid()) * 31) + getReason()) * 31) + getImportance()) * 31) + getStatus()) * 31) + ((int) getPss())) * 31) + ((int) getRss())) * 31)) * 31)) * 31);
    }

    public String toString() {
        String str;
        StringBuilder sb = new StringBuilder("ApplicationExitInfo [");
        sb.append(hashCode());
        sb.append("]");
        sb.append(System.lineSeparator());
        sb.append("{");
        long timestamp = getTimestamp();
        sb.append(System.lineSeparator());
        sb.append("timestamp : ");
        sb.append(timestamp);
        sb.append(System.lineSeparator());
        sb.append("date : ");
        sb.append(new SimpleDateFormat("yyyy MM dd HH:mm:ss").format(Long.valueOf(timestamp)));
        sb.append(System.lineSeparator());
        sb.append("pid : ");
        sb.append(getPid());
        sb.append(System.lineSeparator());
        sb.append("realUid : ");
        sb.append(getRealUid());
        sb.append(System.lineSeparator());
        sb.append("packageUid : ");
        sb.append(getPackageUid());
        sb.append(System.lineSeparator());
        sb.append("definingUid : ");
        sb.append(getDefiningUid());
        sb.append(System.lineSeparator());
        sb.append("process : ");
        sb.append(getProcessName());
        sb.append(System.lineSeparator());
        sb.append("reason : ");
        sb.append(getReason());
        sb.append(System.lineSeparator());
        sb.append("status : ");
        sb.append(getStatus());
        sb.append(System.lineSeparator());
        sb.append("importance : ");
        sb.append(getImportance());
        sb.append(System.lineSeparator());
        sb.append("pss : ");
        sb.append(getPss());
        sb.append(System.lineSeparator());
        sb.append("rss : ");
        sb.append(getRss());
        sb.append(System.lineSeparator());
        sb.append("description : ");
        sb.append(getDescription());
        byte[] processStateSummary = getProcessStateSummary();
        sb.append(System.lineSeparator());
        sb.append("state : ");
        String str2 = "empty";
        if (processStateSummary == null || processStateSummary.length == 0) {
            str = "empty";
        } else {
            str = Integer.toString(processStateSummary.length) + " bytes";
        }
        sb.append(str);
        byte[] trace = getTrace();
        sb.append(System.lineSeparator());
        sb.append("trace : ");
        if (trace != null) {
            str2 = Integer.toString(trace.length) + " bytes";
        }
        sb.append(str2);
        sb.append(System.lineSeparator());
        sb.append("}");
        return sb.toString();
    }
}
