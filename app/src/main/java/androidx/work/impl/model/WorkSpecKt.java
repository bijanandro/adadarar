package androidx.work.impl.model;

import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: WorkSpec.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0002¨\u0006\u0003"}, d2 = {"generationalId", "Landroidx/work/impl/model/WorkGenerationalId;", "Landroidx/work/impl/model/WorkSpec;", "work-runtime_release"}, k = 2, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
public final class WorkSpecKt {
    public static final WorkGenerationalId generationalId(WorkSpec workSpec) {
        Intrinsics.checkNotNullParameter(workSpec, "<this>");
        return new WorkGenerationalId(workSpec.id, workSpec.getGeneration());
    }
}
