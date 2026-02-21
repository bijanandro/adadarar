package com.google.android.material.color.utilities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class Score {
    private static final double CUTOFF_CHROMA = 15.0d;
    private static final double CUTOFF_EXCITED_PROPORTION = 0.01d;
    private static final double CUTOFF_TONE = 10.0d;
    private static final double TARGET_CHROMA = 48.0d;
    private static final double WEIGHT_CHROMA_ABOVE = 0.3d;
    private static final double WEIGHT_CHROMA_BELOW = 0.1d;
    private static final double WEIGHT_PROPORTION = 0.7d;

    private Score() {
    }

    public static List<Integer> score(Map<Integer, Integer> map) {
        Iterator<Map.Entry<Integer, Integer>> it = map.entrySet().iterator();
        double dIntValue = 0.0d;
        while (it.hasNext()) {
            dIntValue += (double) it.next().getValue().intValue();
        }
        HashMap map2 = new HashMap();
        double[] dArr = new double[361];
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            Integer key = entry.getKey();
            int iIntValue = key.intValue();
            double dIntValue2 = ((double) entry.getValue().intValue()) / dIntValue;
            Cam16 cam16FromInt = Cam16.fromInt(iIntValue);
            map2.put(key, cam16FromInt);
            int iRound = (int) Math.round(cam16FromInt.getHue());
            dArr[iRound] = dArr[iRound] + dIntValue2;
        }
        HashMap map3 = new HashMap();
        for (Map.Entry entry2 : map2.entrySet()) {
            Integer num = (Integer) entry2.getKey();
            num.intValue();
            int iRound2 = (int) Math.round(((Cam16) entry2.getValue()).getHue());
            double d = 0.0d;
            for (int i = iRound2 - 15; i < iRound2 + 15; i++) {
                d += dArr[MathUtils.sanitizeDegreesInt(i)];
            }
            map3.put(num, Double.valueOf(d));
        }
        HashMap map4 = new HashMap();
        for (Map.Entry entry3 : map2.entrySet()) {
            Integer num2 = (Integer) entry3.getKey();
            num2.intValue();
            Cam16 cam16 = (Cam16) entry3.getValue();
            map4.put(num2, Double.valueOf((((Double) map3.get(num2)).doubleValue() * 100.0d * WEIGHT_PROPORTION) + ((cam16.getChroma() - TARGET_CHROMA) * (cam16.getChroma() < TARGET_CHROMA ? WEIGHT_CHROMA_BELOW : WEIGHT_CHROMA_ABOVE))));
        }
        List<Integer> listFilter = filter(map3, map2);
        HashMap map5 = new HashMap();
        for (Integer num3 : listFilter) {
            num3.intValue();
            map5.put(num3, (Double) map4.get(num3));
        }
        ArrayList<Map.Entry> arrayList = new ArrayList(map5.entrySet());
        Collections.sort(arrayList, new ScoredComparator());
        ArrayList arrayList2 = new ArrayList();
        for (Map.Entry entry4 : arrayList) {
            Integer num4 = (Integer) entry4.getKey();
            num4.intValue();
            Cam16 cam162 = (Cam16) map2.get(num4);
            Iterator it2 = arrayList2.iterator();
            while (true) {
                if (!it2.hasNext()) {
                    arrayList2.add((Integer) entry4.getKey());
                    break;
                }
                if (MathUtils.differenceDegrees(cam162.getHue(), ((Cam16) map2.get((Integer) it2.next())).getHue()) < CUTOFF_CHROMA) {
                    break;
                }
            }
        }
        if (arrayList2.isEmpty()) {
            arrayList2.add(-12417548);
        }
        return arrayList2;
    }

    private static List<Integer> filter(Map<Integer, Double> map, Map<Integer, Cam16> map2) {
        ArrayList arrayList = new ArrayList();
        for (Map.Entry<Integer, Cam16> entry : map2.entrySet()) {
            Integer key = entry.getKey();
            int iIntValue = key.intValue();
            Cam16 value = entry.getValue();
            double dDoubleValue = map.get(key).doubleValue();
            if (value.getChroma() >= CUTOFF_CHROMA && ColorUtils.lstarFromArgb(iIntValue) >= CUTOFF_TONE && dDoubleValue >= CUTOFF_EXCITED_PROPORTION) {
                arrayList.add(key);
            }
        }
        return arrayList;
    }

    static class ScoredComparator implements Comparator<Map.Entry<Integer, Double>> {
        @Override // java.util.Comparator
        public int compare(Map.Entry<Integer, Double> entry, Map.Entry<Integer, Double> entry2) {
            return -entry.getValue().compareTo(entry2.getValue());
        }
    }
}
