package com.unity3d.player;

import androidx.exifinterface.media.ExifInterface;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
final class ReflectionHelper {
    protected static boolean LOG = false;
    protected static final boolean LOGV = false;
    private static Q[] a = new Q[4096];
    private static long b = 0;
    private static long c = 0;
    private static boolean d = false;

    /* JADX WARN: Removed duplicated region for block: B:29:0x0049  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x0078  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static float a(java.lang.Class r11, java.lang.Class[] r12, java.lang.Class[] r13) {
        /*
            int r0 = r13.length
            r1 = 1036831949(0x3dcccccd, float:0.1)
            if (r0 != 0) goto L7
            return r1
        L7:
            r0 = 0
            if (r12 != 0) goto Lc
            r2 = r0
            goto Ld
        Lc:
            int r2 = r12.length
        Ld:
            int r2 = r2 + 1
            int r3 = r13.length
            r4 = 0
            if (r2 == r3) goto L14
            return r4
        L14:
            r2 = 1056964608(0x3f000000, float:0.5)
            r3 = 1065353216(0x3f800000, float:1.0)
            if (r12 == 0) goto L4f
            int r5 = r12.length
            r6 = r0
            r7 = r3
        L1d:
            if (r0 >= r5) goto L50
            r8 = r12[r0]
            int r9 = r6 + 1
            r6 = r13[r6]
            boolean r10 = r8.equals(r6)
            if (r10 == 0) goto L2d
            r6 = r3
            goto L4a
        L2d:
            boolean r10 = r8.isPrimitive()
            if (r10 != 0) goto L49
            boolean r10 = r6.isPrimitive()
            if (r10 != 0) goto L49
            java.lang.Class r10 = r8.asSubclass(r6)     // Catch: java.lang.ClassCastException -> L41
            if (r10 == 0) goto L41
            r6 = r2
            goto L4a
        L41:
            java.lang.Class r6 = r6.asSubclass(r8)     // Catch: java.lang.ClassCastException -> L49
            if (r6 == 0) goto L49
            r6 = r1
            goto L4a
        L49:
            r6 = r4
        L4a:
            float r7 = r7 * r6
            int r0 = r0 + 1
            r6 = r9
            goto L1d
        L4f:
            r7 = r3
        L50:
            int r12 = r13.length
            int r12 = r12 + (-1)
            r12 = r13[r12]
            boolean r13 = r11.equals(r12)
            if (r13 == 0) goto L5d
            r1 = r3
            goto L79
        L5d:
            boolean r13 = r11.isPrimitive()
            if (r13 != 0) goto L78
            boolean r13 = r12.isPrimitive()
            if (r13 != 0) goto L78
            java.lang.Class r13 = r11.asSubclass(r12)     // Catch: java.lang.ClassCastException -> L71
            if (r13 == 0) goto L71
            r1 = r2
            goto L79
        L71:
            java.lang.Class r11 = r12.asSubclass(r11)     // Catch: java.lang.ClassCastException -> L78
            if (r11 == 0) goto L78
            goto L79
        L78:
            r1 = r4
        L79:
            float r7 = r7 * r1
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unity3d.player.ReflectionHelper.a(java.lang.Class, java.lang.Class[], java.lang.Class[]):float");
    }

    private static Class a(String str, int[] iArr) {
        while (iArr[0] < str.length()) {
            int i = iArr[0];
            iArr[0] = i + 1;
            char cCharAt = str.charAt(i);
            if (cCharAt != '(' && cCharAt != ')') {
                if (cCharAt == 'L') {
                    int iIndexOf = str.indexOf(59, iArr[0]);
                    if (iIndexOf == -1) {
                        return null;
                    }
                    String strSubstring = str.substring(iArr[0], iIndexOf);
                    iArr[0] = iIndexOf + 1;
                    try {
                        return Class.forName(strSubstring.replace('/', '.'));
                    } catch (ClassNotFoundException unused) {
                        return null;
                    }
                }
                if (cCharAt == 'Z') {
                    return Boolean.TYPE;
                }
                if (cCharAt == 'I') {
                    return Integer.TYPE;
                }
                if (cCharAt == 'F') {
                    return Float.TYPE;
                }
                if (cCharAt == 'V') {
                    return Void.TYPE;
                }
                if (cCharAt == 'B') {
                    return Byte.TYPE;
                }
                if (cCharAt == 'C') {
                    return Character.TYPE;
                }
                if (cCharAt == 'S') {
                    return Short.TYPE;
                }
                if (cCharAt == 'J') {
                    return Long.TYPE;
                }
                if (cCharAt == 'D') {
                    return Double.TYPE;
                }
                if (cCharAt == '[') {
                    return Array.newInstance((Class<?>) a(str, iArr), 0).getClass();
                }
                D.Log(5, "! parseType; " + cCharAt + " is not known!");
                return null;
            }
        }
        return null;
    }

    private static synchronized boolean a(Q q) {
        Q q2 = a[q.d & 4095];
        if (!q.equals(q2)) {
            return false;
        }
        q.e = q2.e;
        return true;
    }

    private static Class[] a(String str) {
        Class clsA;
        int i = 0;
        int[] iArr = {0};
        ArrayList arrayList = new ArrayList();
        while (iArr[0] < str.length() && (clsA = a(str, iArr)) != null) {
            arrayList.add(clsA);
        }
        Class[] clsArr = new Class[arrayList.size()];
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            clsArr[i] = (Class) it.next();
            i++;
        }
        return clsArr;
    }

    protected static synchronized boolean beginProxyCall(long j) {
        if (j != b) {
            return false;
        }
        c++;
        return true;
    }

    protected static Object createInvocationError(long j, boolean z) {
        return new S(j, z);
    }

    protected static synchronized void endProxyCall() {
        long j = c - 1;
        c = j;
        if (0 == j && d) {
            ReflectionHelper.class.notifyAll();
        }
    }

    protected static synchronized void endUnityLaunch() {
        try {
            b++;
            d = true;
            while (c > 0) {
                ReflectionHelper.class.wait();
            }
        } catch (InterruptedException unused) {
            D.Log(6, "Interrupted while waiting for all proxies to exit.");
        }
        d = false;
    }

    protected static Constructor getConstructorID(Class cls, String str) {
        Constructor<?> constructor;
        Q q = new Q(cls, "", str);
        if (a(q)) {
            constructor = (Constructor) q.e;
        } else {
            Class[] clsArrA = a(str);
            Constructor<?>[] constructors = cls.getConstructors();
            int length = constructors.length;
            Constructor<?> constructor2 = null;
            float f = 0.0f;
            int i = 0;
            while (true) {
                if (i >= length) {
                    break;
                }
                Constructor<?> constructor3 = constructors[i];
                float fA = a(Void.TYPE, constructor3.getParameterTypes(), clsArrA);
                if (fA > f) {
                    if (fA == 1.0f) {
                        constructor2 = constructor3;
                        break;
                    }
                    constructor2 = constructor3;
                    f = fA;
                }
                i++;
            }
            synchronized (ReflectionHelper.class) {
                q.e = constructor2;
                a[q.d & 4095] = q;
            }
            constructor = constructor2;
        }
        if (constructor != null) {
            return constructor;
        }
        throw new NoSuchMethodError("<init>" + str + " in class " + cls.getName());
    }

    protected static Field getFieldID(Class cls, String str, String str2, boolean z) {
        Field field;
        Class superclass = cls;
        Q q = new Q(superclass, str, str2);
        if (a(q)) {
            field = (Field) q.e;
        } else {
            Class[] clsArrA = a(str2);
            float f = 0.0f;
            Field field2 = null;
            while (superclass != null) {
                Field[] declaredFields = superclass.getDeclaredFields();
                int length = declaredFields.length;
                int i = 0;
                while (true) {
                    if (i >= length) {
                        break;
                    }
                    Field field3 = declaredFields[i];
                    if (z == Modifier.isStatic(field3.getModifiers()) && field3.getName().compareTo(str) == 0) {
                        float fA = a(field3.getType(), null, clsArrA);
                        if (fA > f) {
                            field2 = field3;
                            if (fA == 1.0f) {
                                f = fA;
                                break;
                            }
                            f = fA;
                        } else {
                            continue;
                        }
                    }
                    i++;
                }
                if (f == 1.0f || superclass.isPrimitive() || superclass.isInterface() || superclass.equals(Object.class) || superclass.equals(Void.TYPE)) {
                    break;
                }
                superclass = superclass.getSuperclass();
            }
            synchronized (ReflectionHelper.class) {
                q.e = field2;
                a[q.d & 4095] = q;
            }
            field = field2;
        }
        if (field == null) {
            throw new NoSuchFieldError(String.format("no %s field with name='%s' signature='%s' in class L%s;", z ? "static" : "non-static", str, str2, superclass.getName()));
        }
        return field;
    }

    protected static String getFieldSignature(Field field) {
        Class<?> type = field.getType();
        if (type.isPrimitive()) {
            String name = type.getName();
            return "boolean".equals(name) ? "Z" : "byte".equals(name) ? "B" : "char".equals(name) ? "C" : "double".equals(name) ? "D" : "float".equals(name) ? "F" : "int".equals(name) ? "I" : "long".equals(name) ? "J" : "short".equals(name) ? ExifInterface.LATITUDE_SOUTH : name;
        }
        if (type.isArray()) {
            return type.getName().replace('.', '/');
        }
        return "L" + type.getName().replace('.', '/') + ";";
    }

    protected static Method getMethodID(Class cls, String str, String str2, boolean z) {
        Method method;
        Q q = new Q(cls, str, str2);
        if (a(q)) {
            method = (Method) q.e;
        } else {
            Class[] clsArrA = a(str2);
            Method method2 = null;
            float f = 0.0f;
            while (cls != null) {
                Method[] declaredMethods = cls.getDeclaredMethods();
                int length = declaredMethods.length;
                int i = 0;
                while (true) {
                    if (i >= length) {
                        break;
                    }
                    Method method3 = declaredMethods[i];
                    if (z == Modifier.isStatic(method3.getModifiers()) && method3.getName().compareTo(str) == 0) {
                        float fA = a(method3.getReturnType(), method3.getParameterTypes(), clsArrA);
                        if (fA <= f) {
                            continue;
                        } else {
                            if (fA == 1.0f) {
                                method2 = method3;
                                f = fA;
                                break;
                            }
                            method2 = method3;
                            f = fA;
                        }
                    }
                    i++;
                }
                if (f == 1.0f || cls.isPrimitive() || cls.isInterface() || cls.equals(Object.class) || cls.equals(Void.TYPE)) {
                    break;
                }
                cls = cls.getSuperclass();
            }
            synchronized (ReflectionHelper.class) {
                q.e = method2;
                a[q.d & 4095] = q;
            }
            method = method2;
        }
        if (method == null) {
            throw new NoSuchMethodError(String.format("no %s method with name='%s' signature='%s' in class L%s;", z ? "static" : "non-static", str, str2, cls.getName()));
        }
        return method;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static native void nativeProxyFinalize(long j);

    /* JADX INFO: Access modifiers changed from: private */
    public static native Object nativeProxyInvoke(long j, String str, Object[] objArr);

    /* JADX INFO: Access modifiers changed from: private */
    public static native void nativeProxyJNIFreeGCHandle(long j);

    /* JADX INFO: Access modifiers changed from: private */
    public static native void nativeProxyLogJNIInvokeException(long j);

    protected static Object newProxyInstance(UnityPlayer unityPlayer, long j, Class cls) {
        return newProxyInstance(unityPlayer, j, new Class[]{cls});
    }

    protected static Object newProxyInstance(UnityPlayer unityPlayer, long j, Class[] clsArr) {
        return Proxy.newProxyInstance(ReflectionHelper.class.getClassLoader(), clsArr, new P(unityPlayer, j));
    }
}
