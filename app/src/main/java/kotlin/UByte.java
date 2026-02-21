package kotlin;

import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.jvm.JvmInline;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.UIntRange;

/* JADX INFO: compiled from: UByte.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0010\u000f\n\u0000\n\u0002\u0010\u0005\n\u0002\b\t\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\u0010\u0000\n\u0002\b!\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\n\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u000e\b\u0087@\u0018\u0000 t2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001tB\u0014\b\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003ø\u0001\u0000¢\u0006\u0004\b\u0004\u0010\u0005J\u001b\u0010\b\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\u0087\fø\u0001\u0000¢\u0006\u0004\b\n\u0010\u000bJ\u001b\u0010\f\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\u0000H\u0097\nø\u0001\u0000¢\u0006\u0004\b\u000e\u0010\u000fJ\u001b\u0010\f\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\u0010H\u0087\nø\u0001\u0000¢\u0006\u0004\b\u0011\u0010\u0012J\u001b\u0010\f\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\u0013H\u0087\nø\u0001\u0000¢\u0006\u0004\b\u0014\u0010\u0015J\u001b\u0010\f\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\u0016H\u0087\nø\u0001\u0000¢\u0006\u0004\b\u0017\u0010\u0018J\u0016\u0010\u0019\u001a\u00020\u0000H\u0087\nø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b\u001a\u0010\u0005J\u001b\u0010\u001b\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0000H\u0087\nø\u0001\u0000¢\u0006\u0004\b\u001c\u0010\u000fJ\u001b\u0010\u001b\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0010H\u0087\nø\u0001\u0000¢\u0006\u0004\b\u001d\u0010\u0012J\u001b\u0010\u001b\u001a\u00020\u00132\u0006\u0010\t\u001a\u00020\u0013H\u0087\nø\u0001\u0000¢\u0006\u0004\b\u001e\u0010\u001fJ\u001b\u0010\u001b\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0016H\u0087\nø\u0001\u0000¢\u0006\u0004\b \u0010\u0018J\u001a\u0010!\u001a\u00020\"2\b\u0010\t\u001a\u0004\u0018\u00010#HÖ\u0003¢\u0006\u0004\b$\u0010%J\u001b\u0010&\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0000H\u0087\bø\u0001\u0000¢\u0006\u0004\b'\u0010\u000fJ\u001b\u0010&\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0010H\u0087\bø\u0001\u0000¢\u0006\u0004\b(\u0010\u0012J\u001b\u0010&\u001a\u00020\u00132\u0006\u0010\t\u001a\u00020\u0013H\u0087\bø\u0001\u0000¢\u0006\u0004\b)\u0010\u001fJ\u001b\u0010&\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0016H\u0087\bø\u0001\u0000¢\u0006\u0004\b*\u0010\u0018J\u0010\u0010+\u001a\u00020\rHÖ\u0001¢\u0006\u0004\b,\u0010-J\u0016\u0010.\u001a\u00020\u0000H\u0087\nø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b/\u0010\u0005J\u0016\u00100\u001a\u00020\u0000H\u0087\bø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b1\u0010\u0005J\u001b\u00102\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0000H\u0087\nø\u0001\u0000¢\u0006\u0004\b3\u0010\u000fJ\u001b\u00102\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0010H\u0087\nø\u0001\u0000¢\u0006\u0004\b4\u0010\u0012J\u001b\u00102\u001a\u00020\u00132\u0006\u0010\t\u001a\u00020\u0013H\u0087\nø\u0001\u0000¢\u0006\u0004\b5\u0010\u001fJ\u001b\u00102\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0016H\u0087\nø\u0001\u0000¢\u0006\u0004\b6\u0010\u0018J\u001b\u00107\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\u0087\bø\u0001\u0000¢\u0006\u0004\b8\u0010\u000bJ\u001b\u00107\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0010H\u0087\bø\u0001\u0000¢\u0006\u0004\b9\u0010\u0012J\u001b\u00107\u001a\u00020\u00132\u0006\u0010\t\u001a\u00020\u0013H\u0087\bø\u0001\u0000¢\u0006\u0004\b:\u0010\u001fJ\u001b\u00107\u001a\u00020\u00162\u0006\u0010\t\u001a\u00020\u0016H\u0087\bø\u0001\u0000¢\u0006\u0004\b;\u0010<J\u001b\u0010=\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\u0087\fø\u0001\u0000¢\u0006\u0004\b>\u0010\u000bJ\u001b\u0010?\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0000H\u0087\nø\u0001\u0000¢\u0006\u0004\b@\u0010\u000fJ\u001b\u0010?\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0010H\u0087\nø\u0001\u0000¢\u0006\u0004\bA\u0010\u0012J\u001b\u0010?\u001a\u00020\u00132\u0006\u0010\t\u001a\u00020\u0013H\u0087\nø\u0001\u0000¢\u0006\u0004\bB\u0010\u001fJ\u001b\u0010?\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0016H\u0087\nø\u0001\u0000¢\u0006\u0004\bC\u0010\u0018J\u001b\u0010D\u001a\u00020E2\u0006\u0010\t\u001a\u00020\u0000H\u0087\nø\u0001\u0000¢\u0006\u0004\bF\u0010GJ\u001b\u0010H\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0000H\u0087\nø\u0001\u0000¢\u0006\u0004\bI\u0010\u000fJ\u001b\u0010H\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0010H\u0087\nø\u0001\u0000¢\u0006\u0004\bJ\u0010\u0012J\u001b\u0010H\u001a\u00020\u00132\u0006\u0010\t\u001a\u00020\u0013H\u0087\nø\u0001\u0000¢\u0006\u0004\bK\u0010\u001fJ\u001b\u0010H\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0016H\u0087\nø\u0001\u0000¢\u0006\u0004\bL\u0010\u0018J\u001b\u0010M\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0000H\u0087\nø\u0001\u0000¢\u0006\u0004\bN\u0010\u000fJ\u001b\u0010M\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0010H\u0087\nø\u0001\u0000¢\u0006\u0004\bO\u0010\u0012J\u001b\u0010M\u001a\u00020\u00132\u0006\u0010\t\u001a\u00020\u0013H\u0087\nø\u0001\u0000¢\u0006\u0004\bP\u0010\u001fJ\u001b\u0010M\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0016H\u0087\nø\u0001\u0000¢\u0006\u0004\bQ\u0010\u0018J\u0010\u0010R\u001a\u00020\u0003H\u0087\b¢\u0006\u0004\bS\u0010\u0005J\u0010\u0010T\u001a\u00020UH\u0087\b¢\u0006\u0004\bV\u0010WJ\u0010\u0010X\u001a\u00020YH\u0087\b¢\u0006\u0004\bZ\u0010[J\u0010\u0010\\\u001a\u00020\rH\u0087\b¢\u0006\u0004\b]\u0010-J\u0010\u0010^\u001a\u00020_H\u0087\b¢\u0006\u0004\b`\u0010aJ\u0010\u0010b\u001a\u00020cH\u0087\b¢\u0006\u0004\bd\u0010eJ\u000f\u0010f\u001a\u00020gH\u0016¢\u0006\u0004\bh\u0010iJ\u0016\u0010j\u001a\u00020\u0000H\u0087\bø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\bk\u0010\u0005J\u0016\u0010l\u001a\u00020\u0010H\u0087\bø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\bm\u0010-J\u0016\u0010n\u001a\u00020\u0013H\u0087\bø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\bo\u0010aJ\u0016\u0010p\u001a\u00020\u0016H\u0087\bø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\bq\u0010eJ\u001b\u0010r\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\u0087\fø\u0001\u0000¢\u0006\u0004\bs\u0010\u000bR\u0016\u0010\u0002\u001a\u00020\u00038\u0000X\u0081\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u0006\u0010\u0007\u0088\u0001\u0002\u0092\u0001\u00020\u0003ø\u0001\u0000\u0082\u0002\b\n\u0002\b\u0019\n\u0002\b!¨\u0006u"}, d2 = {"Lkotlin/UByte;", "", "data", "", "constructor-impl", "(B)B", "getData$annotations", "()V", "and", "other", "and-7apg3OU", "(BB)B", "compareTo", "", "compareTo-7apg3OU", "(BB)I", "Lkotlin/UInt;", "compareTo-WZ4Q5Ns", "(BI)I", "Lkotlin/ULong;", "compareTo-VKZWuLQ", "(BJ)I", "Lkotlin/UShort;", "compareTo-xj2QHRw", "(BS)I", "dec", "dec-w2LRezQ", "div", "div-7apg3OU", "div-WZ4Q5Ns", "div-VKZWuLQ", "(BJ)J", "div-xj2QHRw", "equals", "", "", "equals-impl", "(BLjava/lang/Object;)Z", "floorDiv", "floorDiv-7apg3OU", "floorDiv-WZ4Q5Ns", "floorDiv-VKZWuLQ", "floorDiv-xj2QHRw", "hashCode", "hashCode-impl", "(B)I", "inc", "inc-w2LRezQ", "inv", "inv-w2LRezQ", "minus", "minus-7apg3OU", "minus-WZ4Q5Ns", "minus-VKZWuLQ", "minus-xj2QHRw", "mod", "mod-7apg3OU", "mod-WZ4Q5Ns", "mod-VKZWuLQ", "mod-xj2QHRw", "(BS)S", "or", "or-7apg3OU", "plus", "plus-7apg3OU", "plus-WZ4Q5Ns", "plus-VKZWuLQ", "plus-xj2QHRw", "rangeTo", "Lkotlin/ranges/UIntRange;", "rangeTo-7apg3OU", "(BB)Lkotlin/ranges/UIntRange;", "rem", "rem-7apg3OU", "rem-WZ4Q5Ns", "rem-VKZWuLQ", "rem-xj2QHRw", "times", "times-7apg3OU", "times-WZ4Q5Ns", "times-VKZWuLQ", "times-xj2QHRw", "toByte", "toByte-impl", "toDouble", "", "toDouble-impl", "(B)D", "toFloat", "", "toFloat-impl", "(B)F", "toInt", "toInt-impl", "toLong", "", "toLong-impl", "(B)J", "toShort", "", "toShort-impl", "(B)S", "toString", "", "toString-impl", "(B)Ljava/lang/String;", "toUByte", "toUByte-w2LRezQ", "toUInt", "toUInt-pVg5ArA", "toULong", "toULong-s-VKNKU", "toUShort", "toUShort-Mh2AYeg", "xor", "xor-7apg3OU", "Companion", "kotlin-stdlib"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
@JvmInline
public final class UByte implements Comparable<UByte> {
    public static final byte MAX_VALUE = -1;
    public static final byte MIN_VALUE = 0;
    public static final int SIZE_BITS = 8;
    public static final int SIZE_BYTES = 1;
    private final byte data;

    /* JADX INFO: renamed from: box-impl, reason: not valid java name */
    public static final /* synthetic */ UByte m461boximpl(byte b) {
        return new UByte(b);
    }

    /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
    public static byte m467constructorimpl(byte b) {
        return b;
    }

    /* JADX INFO: renamed from: equals-impl, reason: not valid java name */
    public static boolean m473equalsimpl(byte b, Object obj) {
        return (obj instanceof UByte) && b == ((UByte) obj).getData();
    }

    /* JADX INFO: renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m474equalsimpl0(byte b, byte b2) {
        return b == b2;
    }

    public static /* synthetic */ void getData$annotations() {
    }

    /* JADX INFO: renamed from: hashCode-impl, reason: not valid java name */
    public static int m479hashCodeimpl(byte b) {
        return b;
    }

    /* JADX INFO: renamed from: toByte-impl, reason: not valid java name */
    private static final byte m504toByteimpl(byte b) {
        return b;
    }

    /* JADX INFO: renamed from: toDouble-impl, reason: not valid java name */
    private static final double m505toDoubleimpl(byte b) {
        return b & MAX_VALUE;
    }

    /* JADX INFO: renamed from: toFloat-impl, reason: not valid java name */
    private static final float m506toFloatimpl(byte b) {
        return b & MAX_VALUE;
    }

    /* JADX INFO: renamed from: toInt-impl, reason: not valid java name */
    private static final int m507toIntimpl(byte b) {
        return b & MAX_VALUE;
    }

    /* JADX INFO: renamed from: toLong-impl, reason: not valid java name */
    private static final long m508toLongimpl(byte b) {
        return ((long) b) & 255;
    }

    /* JADX INFO: renamed from: toShort-impl, reason: not valid java name */
    private static final short m509toShortimpl(byte b) {
        return (short) (b & 255);
    }

    /* JADX INFO: renamed from: toUByte-w2LRezQ, reason: not valid java name */
    private static final byte m511toUBytew2LRezQ(byte b) {
        return b;
    }

    public boolean equals(Object obj) {
        return m473equalsimpl(this.data, obj);
    }

    public int hashCode() {
        return m479hashCodeimpl(this.data);
    }

    /* JADX INFO: renamed from: unbox-impl, reason: not valid java name and from getter */
    public final /* synthetic */ byte getData() {
        return this.data;
    }

    @Override // java.lang.Comparable
    public /* bridge */ /* synthetic */ int compareTo(UByte uByte) {
        return Intrinsics.compare(getData() & MAX_VALUE, uByte.getData() & MAX_VALUE);
    }

    private /* synthetic */ UByte(byte b) {
        this.data = b;
    }

    /* JADX INFO: renamed from: compareTo-7apg3OU, reason: not valid java name */
    private int m462compareTo7apg3OU(byte b) {
        return Intrinsics.compare(getData() & MAX_VALUE, b & MAX_VALUE);
    }

    /* JADX INFO: renamed from: compareTo-7apg3OU, reason: not valid java name */
    private static int m463compareTo7apg3OU(byte b, byte b2) {
        return Intrinsics.compare(b & MAX_VALUE, b2 & MAX_VALUE);
    }

    /* JADX INFO: renamed from: compareTo-xj2QHRw, reason: not valid java name */
    private static final int m466compareToxj2QHRw(byte b, short s) {
        return Intrinsics.compare(b & MAX_VALUE, s & UShort.MAX_VALUE);
    }

    /* JADX INFO: renamed from: compareTo-WZ4Q5Ns, reason: not valid java name */
    private static final int m465compareToWZ4Q5Ns(byte b, int i) {
        return UnsignedKt.uintCompare(UInt.m543constructorimpl(b & MAX_VALUE), i);
    }

    /* JADX INFO: renamed from: compareTo-VKZWuLQ, reason: not valid java name */
    private static final int m464compareToVKZWuLQ(byte b, long j) {
        return UnsignedKt.ulongCompare(ULong.m621constructorimpl(((long) b) & 255), j);
    }

    /* JADX INFO: renamed from: plus-7apg3OU, reason: not valid java name */
    private static final int m491plus7apg3OU(byte b, byte b2) {
        return UInt.m543constructorimpl(UInt.m543constructorimpl(b & MAX_VALUE) + UInt.m543constructorimpl(b2 & MAX_VALUE));
    }

    /* JADX INFO: renamed from: plus-xj2QHRw, reason: not valid java name */
    private static final int m494plusxj2QHRw(byte b, short s) {
        return UInt.m543constructorimpl(UInt.m543constructorimpl(b & MAX_VALUE) + UInt.m543constructorimpl(s & UShort.MAX_VALUE));
    }

    /* JADX INFO: renamed from: plus-WZ4Q5Ns, reason: not valid java name */
    private static final int m493plusWZ4Q5Ns(byte b, int i) {
        return UInt.m543constructorimpl(UInt.m543constructorimpl(b & MAX_VALUE) + i);
    }

    /* JADX INFO: renamed from: plus-VKZWuLQ, reason: not valid java name */
    private static final long m492plusVKZWuLQ(byte b, long j) {
        return ULong.m621constructorimpl(ULong.m621constructorimpl(((long) b) & 255) + j);
    }

    /* JADX INFO: renamed from: minus-7apg3OU, reason: not valid java name */
    private static final int m482minus7apg3OU(byte b, byte b2) {
        return UInt.m543constructorimpl(UInt.m543constructorimpl(b & MAX_VALUE) - UInt.m543constructorimpl(b2 & MAX_VALUE));
    }

    /* JADX INFO: renamed from: minus-xj2QHRw, reason: not valid java name */
    private static final int m485minusxj2QHRw(byte b, short s) {
        return UInt.m543constructorimpl(UInt.m543constructorimpl(b & MAX_VALUE) - UInt.m543constructorimpl(s & UShort.MAX_VALUE));
    }

    /* JADX INFO: renamed from: minus-WZ4Q5Ns, reason: not valid java name */
    private static final int m484minusWZ4Q5Ns(byte b, int i) {
        return UInt.m543constructorimpl(UInt.m543constructorimpl(b & MAX_VALUE) - i);
    }

    /* JADX INFO: renamed from: minus-VKZWuLQ, reason: not valid java name */
    private static final long m483minusVKZWuLQ(byte b, long j) {
        return ULong.m621constructorimpl(ULong.m621constructorimpl(((long) b) & 255) - j);
    }

    /* JADX INFO: renamed from: times-7apg3OU, reason: not valid java name */
    private static final int m500times7apg3OU(byte b, byte b2) {
        return UInt.m543constructorimpl(UInt.m543constructorimpl(b & MAX_VALUE) * UInt.m543constructorimpl(b2 & MAX_VALUE));
    }

    /* JADX INFO: renamed from: times-xj2QHRw, reason: not valid java name */
    private static final int m503timesxj2QHRw(byte b, short s) {
        return UInt.m543constructorimpl(UInt.m543constructorimpl(b & MAX_VALUE) * UInt.m543constructorimpl(s & UShort.MAX_VALUE));
    }

    /* JADX INFO: renamed from: times-WZ4Q5Ns, reason: not valid java name */
    private static final int m502timesWZ4Q5Ns(byte b, int i) {
        return UInt.m543constructorimpl(UInt.m543constructorimpl(b & MAX_VALUE) * i);
    }

    /* JADX INFO: renamed from: times-VKZWuLQ, reason: not valid java name */
    private static final long m501timesVKZWuLQ(byte b, long j) {
        return ULong.m621constructorimpl(ULong.m621constructorimpl(((long) b) & 255) * j);
    }

    /* JADX INFO: renamed from: div-7apg3OU, reason: not valid java name */
    private static final int m469div7apg3OU(byte b, byte b2) {
        return UnsignedKt.m796uintDivideJ1ME1BU(UInt.m543constructorimpl(b & MAX_VALUE), UInt.m543constructorimpl(b2 & MAX_VALUE));
    }

    /* JADX INFO: renamed from: div-xj2QHRw, reason: not valid java name */
    private static final int m472divxj2QHRw(byte b, short s) {
        return UnsignedKt.m796uintDivideJ1ME1BU(UInt.m543constructorimpl(b & MAX_VALUE), UInt.m543constructorimpl(s & UShort.MAX_VALUE));
    }

    /* JADX INFO: renamed from: div-WZ4Q5Ns, reason: not valid java name */
    private static final int m471divWZ4Q5Ns(byte b, int i) {
        return UnsignedKt.m796uintDivideJ1ME1BU(UInt.m543constructorimpl(b & MAX_VALUE), i);
    }

    /* JADX INFO: renamed from: div-VKZWuLQ, reason: not valid java name */
    private static final long m470divVKZWuLQ(byte b, long j) {
        return UnsignedKt.m798ulongDivideeb3DHEI(ULong.m621constructorimpl(((long) b) & 255), j);
    }

    /* JADX INFO: renamed from: rem-7apg3OU, reason: not valid java name */
    private static final int m496rem7apg3OU(byte b, byte b2) {
        return UnsignedKt.m797uintRemainderJ1ME1BU(UInt.m543constructorimpl(b & MAX_VALUE), UInt.m543constructorimpl(b2 & MAX_VALUE));
    }

    /* JADX INFO: renamed from: rem-xj2QHRw, reason: not valid java name */
    private static final int m499remxj2QHRw(byte b, short s) {
        return UnsignedKt.m797uintRemainderJ1ME1BU(UInt.m543constructorimpl(b & MAX_VALUE), UInt.m543constructorimpl(s & UShort.MAX_VALUE));
    }

    /* JADX INFO: renamed from: rem-WZ4Q5Ns, reason: not valid java name */
    private static final int m498remWZ4Q5Ns(byte b, int i) {
        return UnsignedKt.m797uintRemainderJ1ME1BU(UInt.m543constructorimpl(b & MAX_VALUE), i);
    }

    /* JADX INFO: renamed from: rem-VKZWuLQ, reason: not valid java name */
    private static final long m497remVKZWuLQ(byte b, long j) {
        return UnsignedKt.m799ulongRemaindereb3DHEI(ULong.m621constructorimpl(((long) b) & 255), j);
    }

    /* JADX INFO: renamed from: floorDiv-7apg3OU, reason: not valid java name */
    private static final int m475floorDiv7apg3OU(byte b, byte b2) {
        return UnsignedKt.m796uintDivideJ1ME1BU(UInt.m543constructorimpl(b & MAX_VALUE), UInt.m543constructorimpl(b2 & MAX_VALUE));
    }

    /* JADX INFO: renamed from: floorDiv-xj2QHRw, reason: not valid java name */
    private static final int m478floorDivxj2QHRw(byte b, short s) {
        return UnsignedKt.m796uintDivideJ1ME1BU(UInt.m543constructorimpl(b & MAX_VALUE), UInt.m543constructorimpl(s & UShort.MAX_VALUE));
    }

    /* JADX INFO: renamed from: floorDiv-WZ4Q5Ns, reason: not valid java name */
    private static final int m477floorDivWZ4Q5Ns(byte b, int i) {
        return UnsignedKt.m796uintDivideJ1ME1BU(UInt.m543constructorimpl(b & MAX_VALUE), i);
    }

    /* JADX INFO: renamed from: floorDiv-VKZWuLQ, reason: not valid java name */
    private static final long m476floorDivVKZWuLQ(byte b, long j) {
        return UnsignedKt.m798ulongDivideeb3DHEI(ULong.m621constructorimpl(((long) b) & 255), j);
    }

    /* JADX INFO: renamed from: mod-7apg3OU, reason: not valid java name */
    private static final byte m486mod7apg3OU(byte b, byte b2) {
        return m467constructorimpl((byte) UnsignedKt.m797uintRemainderJ1ME1BU(UInt.m543constructorimpl(b & MAX_VALUE), UInt.m543constructorimpl(b2 & MAX_VALUE)));
    }

    /* JADX INFO: renamed from: mod-xj2QHRw, reason: not valid java name */
    private static final short m489modxj2QHRw(byte b, short s) {
        return UShort.m727constructorimpl((short) UnsignedKt.m797uintRemainderJ1ME1BU(UInt.m543constructorimpl(b & MAX_VALUE), UInt.m543constructorimpl(s & UShort.MAX_VALUE)));
    }

    /* JADX INFO: renamed from: mod-WZ4Q5Ns, reason: not valid java name */
    private static final int m488modWZ4Q5Ns(byte b, int i) {
        return UnsignedKt.m797uintRemainderJ1ME1BU(UInt.m543constructorimpl(b & MAX_VALUE), i);
    }

    /* JADX INFO: renamed from: mod-VKZWuLQ, reason: not valid java name */
    private static final long m487modVKZWuLQ(byte b, long j) {
        return UnsignedKt.m799ulongRemaindereb3DHEI(ULong.m621constructorimpl(((long) b) & 255), j);
    }

    /* JADX INFO: renamed from: inc-w2LRezQ, reason: not valid java name */
    private static final byte m480incw2LRezQ(byte b) {
        return m467constructorimpl((byte) (b + 1));
    }

    /* JADX INFO: renamed from: dec-w2LRezQ, reason: not valid java name */
    private static final byte m468decw2LRezQ(byte b) {
        return m467constructorimpl((byte) (b - 1));
    }

    /* JADX INFO: renamed from: rangeTo-7apg3OU, reason: not valid java name */
    private static final UIntRange m495rangeTo7apg3OU(byte b, byte b2) {
        return new UIntRange(UInt.m543constructorimpl(b & MAX_VALUE), UInt.m543constructorimpl(b2 & MAX_VALUE), null);
    }

    /* JADX INFO: renamed from: and-7apg3OU, reason: not valid java name */
    private static final byte m460and7apg3OU(byte b, byte b2) {
        return m467constructorimpl((byte) (b & b2));
    }

    /* JADX INFO: renamed from: or-7apg3OU, reason: not valid java name */
    private static final byte m490or7apg3OU(byte b, byte b2) {
        return m467constructorimpl((byte) (b | b2));
    }

    /* JADX INFO: renamed from: xor-7apg3OU, reason: not valid java name */
    private static final byte m515xor7apg3OU(byte b, byte b2) {
        return m467constructorimpl((byte) (b ^ b2));
    }

    /* JADX INFO: renamed from: inv-w2LRezQ, reason: not valid java name */
    private static final byte m481invw2LRezQ(byte b) {
        return m467constructorimpl((byte) (~b));
    }

    /* JADX INFO: renamed from: toUShort-Mh2AYeg, reason: not valid java name */
    private static final short m514toUShortMh2AYeg(byte b) {
        return UShort.m727constructorimpl((short) (b & 255));
    }

    /* JADX INFO: renamed from: toUInt-pVg5ArA, reason: not valid java name */
    private static final int m512toUIntpVg5ArA(byte b) {
        return UInt.m543constructorimpl(b & MAX_VALUE);
    }

    /* JADX INFO: renamed from: toULong-s-VKNKU, reason: not valid java name */
    private static final long m513toULongsVKNKU(byte b) {
        return ULong.m621constructorimpl(((long) b) & 255);
    }

    /* JADX INFO: renamed from: toString-impl, reason: not valid java name */
    public static String m510toStringimpl(byte b) {
        return String.valueOf(b & MAX_VALUE);
    }

    public String toString() {
        return m510toStringimpl(this.data);
    }
}
