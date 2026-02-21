package com.google.android.gms.dynamic;

import android.R;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.dynamic.LifecycleDelegate;
import com.google.errorprone.annotations.ResultIgnorabilityUnspecified;
import java.util.LinkedList;

/* JADX INFO: compiled from: com.google.android.gms:play-services-base@@18.3.0 */
/* JADX INFO: loaded from: classes.dex */
public abstract class DeferredLifecycleHelper<T extends LifecycleDelegate> {
    private LifecycleDelegate zaa;
    private Bundle zab;
    private LinkedList zac;
    private final OnDelegateCreatedListener zad = new zaa(this);

    public static void showGooglePlayUnavailableMessage(FrameLayout frameLayout) {
        GoogleApiAvailability googleApiAvailability = GoogleApiAvailability.getInstance();
        Context context = frameLayout.getContext();
        int iIsGooglePlayServicesAvailable = googleApiAvailability.isGooglePlayServicesAvailable(context);
        String strZac = com.google.android.gms.common.internal.zac.zac(context, iIsGooglePlayServicesAvailable);
        String strZab = com.google.android.gms.common.internal.zac.zab(context, iIsGooglePlayServicesAvailable);
        LinearLayout linearLayout = new LinearLayout(frameLayout.getContext());
        linearLayout.setOrientation(1);
        linearLayout.setLayoutParams(new FrameLayout.LayoutParams(-2, -2));
        frameLayout.addView(linearLayout);
        TextView textView = new TextView(frameLayout.getContext());
        textView.setLayoutParams(new FrameLayout.LayoutParams(-2, -2));
        textView.setText(strZac);
        linearLayout.addView(textView);
        Intent errorResolutionIntent = googleApiAvailability.getErrorResolutionIntent(context, iIsGooglePlayServicesAvailable, null);
        if (errorResolutionIntent != null) {
            Button button = new Button(context);
            button.setId(R.id.button1);
            button.setLayoutParams(new FrameLayout.LayoutParams(-2, -2));
            button.setText(strZab);
            linearLayout.addView(button);
            button.setOnClickListener(new zae(context, errorResolutionIntent));
        }
    }

    /* JADX WARN: Failed to check method usage
    java.lang.NullPointerException: Cannot invoke "jadx.core.dex.nodes.MethodNode.getTopParentClass()" because "m" is null
    	at jadx.core.codegen.ClassGen.lambda$skipMethod$0(ClassGen.java:366)
    	at java.base/java.util.stream.ReferencePipeline$2$1.accept(Unknown Source)
    	at java.base/java.util.ArrayList$ArrayListSpliterator.forEachRemaining(Unknown Source)
    	at java.base/java.util.stream.AbstractPipeline.copyInto(Unknown Source)
    	at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(Unknown Source)
    	at java.base/java.util.stream.ReduceOps$ReduceOp.evaluateSequential(Unknown Source)
    	at java.base/java.util.stream.AbstractPipeline.evaluate(Unknown Source)
    	at java.base/java.util.stream.ReferencePipeline.collect(Unknown Source)
    	at jadx.core.codegen.ClassGen.skipMethod(ClassGen.java:367)
    	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:329)
    	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$2(ClassGen.java:303)
    	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(Unknown Source)
    	at java.base/java.util.ArrayList.forEach(Unknown Source)
    	at java.base/java.util.stream.SortedOps$RefSortingSink.end(Unknown Source)
    	at java.base/java.util.stream.Sink$ChainedReference.end(Unknown Source)
    	at java.base/java.util.stream.ReferencePipeline$7$1FlatMap.end(Unknown Source)
    	at java.base/java.util.stream.AbstractPipeline.copyInto(Unknown Source)
    	at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(Unknown Source)
    	at java.base/java.util.stream.ForEachOps$ForEachOp.evaluateSequential(Unknown Source)
    	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(Unknown Source)
    	at java.base/java.util.stream.AbstractPipeline.evaluate(Unknown Source)
    	at java.base/java.util.stream.ReferencePipeline.forEach(Unknown Source)
    	at jadx.core.codegen.ClassGen.addInnerClsAndMethods(ClassGen.java:299)
    	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:288)
    	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:272)
    	at jadx.core.codegen.ClassGen.addClassCode(ClassGen.java:159)
    	at jadx.core.codegen.ClassGen.makeClass(ClassGen.java:103)
    	at jadx.core.codegen.CodeGen.wrapCodeGen(CodeGen.java:45)
    	at jadx.core.codegen.CodeGen.generateJavaCode(CodeGen.java:34)
    	at jadx.core.codegen.CodeGen.generate(CodeGen.java:22)
    	at jadx.core.ProcessClass.process(ProcessClass.java:88)
    	at jadx.core.ProcessClass.generateCode(ProcessClass.java:126)
    	at jadx.core.dex.nodes.ClassNode.generateClassCode(ClassNode.java:405)
    	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:393)
    	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:343)
     */
    static /* bridge */ /* synthetic */ LifecycleDelegate zaa(DeferredLifecycleHelper deferredLifecycleHelper) {
        return deferredLifecycleHelper.zaa;
    }

    private final void zae(int i) {
        while (!this.zac.isEmpty() && ((zah) this.zac.getLast()).zaa() >= i) {
            this.zac.removeLast();
        }
    }

    private final void zaf(Bundle bundle, zah zahVar) {
        LifecycleDelegate lifecycleDelegate = this.zaa;
        if (lifecycleDelegate != null) {
            zahVar.zab(lifecycleDelegate);
            return;
        }
        if (this.zac == null) {
            this.zac = new LinkedList();
        }
        this.zac.add(zahVar);
        if (bundle != null) {
            Bundle bundle2 = this.zab;
            if (bundle2 == null) {
                this.zab = (Bundle) bundle.clone();
            } else {
                bundle2.putAll(bundle);
            }
        }
        createDelegate(this.zad);
    }

    protected abstract void createDelegate(OnDelegateCreatedListener<T> onDelegateCreatedListener);

    public T getDelegate() {
        return (T) this.zaa;
    }

    protected void handleGooglePlayUnavailable(FrameLayout frameLayout) {
        showGooglePlayUnavailableMessage(frameLayout);
    }

    public void onCreate(Bundle bundle) {
        zaf(bundle, new zac(this, bundle));
    }

    @ResultIgnorabilityUnspecified
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        FrameLayout frameLayout = new FrameLayout(layoutInflater.getContext());
        zaf(bundle, new zad(this, frameLayout, layoutInflater, viewGroup, bundle));
        if (this.zaa == null) {
            handleGooglePlayUnavailable(frameLayout);
        }
        return frameLayout;
    }

    public void onDestroy() {
        LifecycleDelegate lifecycleDelegate = this.zaa;
        if (lifecycleDelegate != null) {
            lifecycleDelegate.onDestroy();
        } else {
            zae(1);
        }
    }

    public void onDestroyView() {
        LifecycleDelegate lifecycleDelegate = this.zaa;
        if (lifecycleDelegate != null) {
            lifecycleDelegate.onDestroyView();
        } else {
            zae(2);
        }
    }

    public void onInflate(Activity activity, Bundle bundle, Bundle bundle2) {
        zaf(bundle2, new zab(this, activity, bundle, bundle2));
    }

    public void onLowMemory() {
        LifecycleDelegate lifecycleDelegate = this.zaa;
        if (lifecycleDelegate != null) {
            lifecycleDelegate.onLowMemory();
        }
    }

    public void onPause() {
        LifecycleDelegate lifecycleDelegate = this.zaa;
        if (lifecycleDelegate != null) {
            lifecycleDelegate.onPause();
        } else {
            zae(5);
        }
    }

    public void onResume() {
        zaf(null, new zag(this));
    }

    public void onSaveInstanceState(Bundle bundle) {
        LifecycleDelegate lifecycleDelegate = this.zaa;
        if (lifecycleDelegate != null) {
            lifecycleDelegate.onSaveInstanceState(bundle);
            return;
        }
        Bundle bundle2 = this.zab;
        if (bundle2 != null) {
            bundle.putAll(bundle2);
        }
    }

    public void onStart() {
        zaf(null, new zaf(this));
    }

    public void onStop() {
        LifecycleDelegate lifecycleDelegate = this.zaa;
        if (lifecycleDelegate != null) {
            lifecycleDelegate.onStop();
        } else {
            zae(4);
        }
    }
}
