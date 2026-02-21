package com.unity3d.player;

import android.app.GameManager;
import android.app.GameState;
import android.content.res.loader.ResourcesLoader;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.params.OutputConfiguration;
import android.hardware.camera2.params.SessionConfiguration;
import android.view.Surface;
import android.view.autofill.AutofillManager;
import java.lang.invoke.MethodHandles;
import java.nio.file.FileSystemLoopException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.Executor;

/* JADX INFO: compiled from: D8$$SyntheticClass */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class C$$ExternalSyntheticApiModelOutline0 {
    public static /* bridge */ /* synthetic */ GameManager m(Object obj) {
        return (GameManager) obj;
    }

    public static /* synthetic */ GameState m(boolean z, int i) {
        return new GameState(z, i);
    }

    public static /* synthetic */ GameState m(boolean z, int i, int i2, int i3) {
        return new GameState(z, i, i2, i3);
    }

    /* JADX INFO: renamed from: m, reason: collision with other method in class */
    public static /* synthetic */ ResourcesLoader m299m() {
        return new ResourcesLoader();
    }

    public static /* synthetic */ OutputConfiguration m(Surface surface) {
        return new OutputConfiguration(surface);
    }

    public static /* synthetic */ SessionConfiguration m(int i, List list, Executor executor, CameraCaptureSession.StateCallback stateCallback) {
        return new SessionConfiguration(i, list, executor, stateCallback);
    }

    /* JADX INFO: renamed from: m, reason: collision with other method in class */
    public static /* bridge */ /* synthetic */ AutofillManager m301m(Object obj) {
        return (AutofillManager) obj;
    }

    /* JADX INFO: renamed from: m, reason: collision with other method in class */
    public static /* bridge */ /* synthetic */ Class m303m() {
        return AutofillManager.class;
    }

    /* JADX INFO: renamed from: m, reason: collision with other method in class */
    public static /* bridge */ /* synthetic */ MethodHandles.Lookup m306m(Object obj) {
        return (MethodHandles.Lookup) obj;
    }

    /* JADX INFO: renamed from: m, reason: collision with other method in class */
    public static /* synthetic */ FileSystemLoopException m308m(String str) {
        return new FileSystemLoopException(str);
    }

    /* JADX INFO: renamed from: m, reason: collision with other method in class */
    public static /* bridge */ /* synthetic */ FileVisitResult m310m(Object obj) {
        return (FileVisitResult) obj;
    }

    /* JADX INFO: renamed from: m, reason: collision with other method in class */
    public static /* bridge */ /* synthetic */ FileVisitor m311m(Object obj) {
        return (FileVisitor) obj;
    }

    /* JADX INFO: renamed from: m, reason: collision with other method in class */
    public static /* bridge */ /* synthetic */ Path m313m(Object obj) {
        return (Path) obj;
    }

    /* JADX INFO: renamed from: m, reason: collision with other method in class */
    public static /* synthetic */ void m315m() {
    }

    public static /* bridge */ /* synthetic */ Class m$1() {
        return MethodHandles.Lookup.class;
    }

    /* JADX INFO: renamed from: m$1, reason: collision with other method in class */
    public static /* synthetic */ void m323m$1() {
    }

    public static /* bridge */ /* synthetic */ Class m$2() {
        return GameManager.class;
    }

    /* JADX INFO: renamed from: m$2, reason: collision with other method in class */
    public static /* synthetic */ void m324m$2() {
    }
}
