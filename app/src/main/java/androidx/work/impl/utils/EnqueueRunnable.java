package androidx.work.impl.utils;

import android.text.TextUtils;
import androidx.work.Logger;
import androidx.work.Operation;
import androidx.work.impl.OperationImpl;
import androidx.work.impl.Schedulers;
import androidx.work.impl.WorkContinuationImpl;
import androidx.work.impl.WorkDatabase;
import androidx.work.impl.WorkManagerImpl;
import androidx.work.impl.background.systemalarm.RescheduleReceiver;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class EnqueueRunnable implements Runnable {
    private static final String TAG = Logger.tagWithPrefix("EnqueueRunnable");
    private final OperationImpl mOperation;
    private final WorkContinuationImpl mWorkContinuation;

    public EnqueueRunnable(WorkContinuationImpl workContinuation) {
        this(workContinuation, new OperationImpl());
    }

    public EnqueueRunnable(WorkContinuationImpl workContinuation, OperationImpl result) {
        this.mWorkContinuation = workContinuation;
        this.mOperation = result;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            if (this.mWorkContinuation.hasCycles()) {
                throw new IllegalStateException("WorkContinuation has cycles (" + this.mWorkContinuation + ")");
            }
            if (addToDatabase()) {
                PackageManagerHelper.setComponentEnabled(this.mWorkContinuation.getWorkManagerImpl().getApplicationContext(), RescheduleReceiver.class, true);
                scheduleWorkInBackground();
            }
            this.mOperation.markState(Operation.SUCCESS);
        } catch (Throwable th) {
            this.mOperation.markState(new Operation.State.FAILURE(th));
        }
    }

    public Operation getOperation() {
        return this.mOperation;
    }

    public boolean addToDatabase() {
        WorkDatabase workDatabase = this.mWorkContinuation.getWorkManagerImpl().getWorkDatabase();
        workDatabase.beginTransaction();
        try {
            boolean zProcessContinuation = processContinuation(this.mWorkContinuation);
            workDatabase.setTransactionSuccessful();
            return zProcessContinuation;
        } finally {
            workDatabase.endTransaction();
        }
    }

    public void scheduleWorkInBackground() {
        WorkManagerImpl workManagerImpl = this.mWorkContinuation.getWorkManagerImpl();
        Schedulers.schedule(workManagerImpl.getConfiguration(), workManagerImpl.getWorkDatabase(), workManagerImpl.getSchedulers());
    }

    private static boolean processContinuation(WorkContinuationImpl workContinuation) {
        List<WorkContinuationImpl> parents = workContinuation.getParents();
        boolean zProcessContinuation = false;
        if (parents != null) {
            for (WorkContinuationImpl workContinuationImpl : parents) {
                if (!workContinuationImpl.isEnqueued()) {
                    zProcessContinuation |= processContinuation(workContinuationImpl);
                } else {
                    Logger.get().warning(TAG, "Already enqueued work ids (" + TextUtils.join(", ", workContinuationImpl.getIds()) + ")");
                }
            }
        }
        return enqueueContinuation(workContinuation) | zProcessContinuation;
    }

    private static boolean enqueueContinuation(WorkContinuationImpl workContinuation) {
        boolean zEnqueueWorkWithPrerequisites = enqueueWorkWithPrerequisites(workContinuation.getWorkManagerImpl(), workContinuation.getWork(), (String[]) WorkContinuationImpl.prerequisitesFor(workContinuation).toArray(new String[0]), workContinuation.getName(), workContinuation.getExistingWorkPolicy());
        workContinuation.markEnqueued();
        return zEnqueueWorkWithPrerequisites;
    }

    /* JADX WARN: Removed duplicated region for block: B:82:0x0148 A[PHI: r0 r8 r11 r12 r13
  0x0148: PHI (r0v1 java.lang.String[]) = 
  (r0v0 java.lang.String[])
  (r0v0 java.lang.String[])
  (r0v0 java.lang.String[])
  (r0v12 java.lang.String[])
  (r0v12 java.lang.String[])
 binds: [B:28:0x0068, B:29:0x006a, B:31:0x0078, B:81:0x0147, B:80:0x0145] A[DONT_GENERATE, DONT_INLINE]
  0x0148: PHI (r8v2 boolean) = (r8v1 boolean), (r8v1 boolean), (r8v1 boolean), (r8v5 boolean), (r8v6 boolean) binds: [B:28:0x0068, B:29:0x006a, B:31:0x0078, B:81:0x0147, B:80:0x0145] A[DONT_GENERATE, DONT_INLINE]
  0x0148: PHI (r11v2 boolean) = (r11v1 boolean), (r11v1 boolean), (r11v1 boolean), (r11v4 boolean), (r11v4 boolean) binds: [B:28:0x0068, B:29:0x006a, B:31:0x0078, B:81:0x0147, B:80:0x0145] A[DONT_GENERATE, DONT_INLINE]
  0x0148: PHI (r12v2 boolean) = (r12v1 boolean), (r12v1 boolean), (r12v1 boolean), (r12v5 boolean), (r12v5 boolean) binds: [B:28:0x0068, B:29:0x006a, B:31:0x0078, B:81:0x0147, B:80:0x0145] A[DONT_GENERATE, DONT_INLINE]
  0x0148: PHI (r13v2 boolean) = (r13v1 boolean), (r13v1 boolean), (r13v1 boolean), (r13v5 boolean), (r13v5 boolean) binds: [B:28:0x0068, B:29:0x006a, B:31:0x0078, B:81:0x0147, B:80:0x0145] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static boolean enqueueWorkWithPrerequisites(androidx.work.impl.WorkManagerImpl r16, java.util.List<? extends androidx.work.WorkRequest> r17, java.lang.String[] r18, java.lang.String r19, androidx.work.ExistingWorkPolicy r20) {
        /*
            Method dump skipped, instruction units count: 475
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.work.impl.utils.EnqueueRunnable.enqueueWorkWithPrerequisites(androidx.work.impl.WorkManagerImpl, java.util.List, java.lang.String[], java.lang.String, androidx.work.ExistingWorkPolicy):boolean");
    }
}
