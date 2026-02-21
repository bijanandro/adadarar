package kotlin.time;

import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.time.Duration;

/* JADX INFO: compiled from: longSaturatedMath.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\f\u001a*\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0001H\u0002ø\u0001\u0000¢\u0006\u0004\b\u0006\u0010\u0007\u001a\"\u0010\b\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0004H\u0000ø\u0001\u0000¢\u0006\u0004\b\t\u0010\n\u001a\"\u0010\u000b\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0004H\u0002ø\u0001\u0000¢\u0006\u0004\b\f\u0010\n\u001a \u0010\r\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u00012\u0006\u0010\u000f\u001a\u00020\u0001H\u0000ø\u0001\u0000¢\u0006\u0002\u0010\n\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0010"}, d2 = {"checkInfiniteSumDefined", "", "longNs", "duration", "Lkotlin/time/Duration;", "durationNs", "checkInfiniteSumDefined-PjuGub4", "(JJJ)J", "saturatingAdd", "saturatingAdd-pTJri5U", "(JJ)J", "saturatingAddInHalves", "saturatingAddInHalves-pTJri5U", "saturatingDiff", "valueNs", "originNs", "kotlin-stdlib"}, k = 2, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
public final class LongSaturatedMathKt {
    /* JADX INFO: renamed from: saturatingAdd-pTJri5U, reason: not valid java name */
    public static final long m1899saturatingAddpTJri5U(long j, long j2) {
        long jM1791getInWholeNanosecondsimpl = Duration.m1791getInWholeNanosecondsimpl(j2);
        if (((j - 1) | 1) == Long.MAX_VALUE) {
            return m1898checkInfiniteSumDefinedPjuGub4(j, j2, jM1791getInWholeNanosecondsimpl);
        }
        if ((1 | (jM1791getInWholeNanosecondsimpl - 1)) == Long.MAX_VALUE) {
            return m1900saturatingAddInHalvespTJri5U(j, j2);
        }
        long j3 = j + jM1791getInWholeNanosecondsimpl;
        return ((j ^ j3) & (jM1791getInWholeNanosecondsimpl ^ j3)) < 0 ? j < 0 ? Long.MIN_VALUE : Long.MAX_VALUE : j3;
    }

    /* JADX INFO: renamed from: checkInfiniteSumDefined-PjuGub4, reason: not valid java name */
    private static final long m1898checkInfiniteSumDefinedPjuGub4(long j, long j2, long j3) {
        if (!Duration.m1803isInfiniteimpl(j2) || (j ^ j3) >= 0) {
            return j;
        }
        throw new IllegalArgumentException("Summing infinities of different signs");
    }

    /* JADX INFO: renamed from: saturatingAddInHalves-pTJri5U, reason: not valid java name */
    private static final long m1900saturatingAddInHalvespTJri5U(long j, long j2) {
        long jM1774divUwyO8pc = Duration.m1774divUwyO8pc(j2, 2);
        if (((Duration.m1791getInWholeNanosecondsimpl(jM1774divUwyO8pc) - 1) | 1) == Long.MAX_VALUE) {
            return (long) (j + Duration.m1814toDoubleimpl(j2, DurationUnit.NANOSECONDS));
        }
        return m1899saturatingAddpTJri5U(m1899saturatingAddpTJri5U(j, jM1774divUwyO8pc), jM1774divUwyO8pc);
    }

    public static final long saturatingDiff(long j, long j2) {
        if ((1 | (j2 - 1)) == Long.MAX_VALUE) {
            return Duration.m1823unaryMinusUwyO8pc(DurationKt.toDuration(j2, DurationUnit.DAYS));
        }
        long j3 = j - j2;
        if (((j3 ^ j) & (~(j3 ^ j2))) < 0) {
            long j4 = DurationKt.NANOS_IN_MILLIS;
            long j5 = (j / j4) - (j2 / j4);
            long j6 = (j % j4) - (j2 % j4);
            Duration.Companion companion = Duration.INSTANCE;
            long duration = DurationKt.toDuration(j5, DurationUnit.MILLISECONDS);
            Duration.Companion companion2 = Duration.INSTANCE;
            return Duration.m1807plusLRDsOJo(duration, DurationKt.toDuration(j6, DurationUnit.NANOSECONDS));
        }
        Duration.Companion companion3 = Duration.INSTANCE;
        return DurationKt.toDuration(j3, DurationUnit.NANOSECONDS);
    }
}
