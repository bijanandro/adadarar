package androidx.room.util;

import android.os.Build;
import androidx.constraintlayout.widget.ConstraintLayout;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: FileUtil.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0018\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0007¨\u0006\u0006"}, d2 = {"copy", "", "input", "Ljava/nio/channels/ReadableByteChannel;", "output", "Ljava/nio/channels/FileChannel;", "room-runtime_release"}, k = 2, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
public final class FileUtil {
    public static final void copy(ReadableByteChannel input, FileChannel output) throws IOException {
        Intrinsics.checkNotNullParameter(input, "input");
        Intrinsics.checkNotNullParameter(output, "output");
        try {
            if (Build.VERSION.SDK_INT > 23) {
                output.transferFrom(input, 0L, Long.MAX_VALUE);
            } else {
                InputStream inputStreamNewInputStream = Channels.newInputStream(input);
                OutputStream outputStreamNewOutputStream = Channels.newOutputStream(output);
                byte[] bArr = new byte[4096];
                while (true) {
                    int i = inputStreamNewInputStream.read(bArr);
                    if (i <= 0) {
                        break;
                    } else {
                        outputStreamNewOutputStream.write(bArr, 0, i);
                    }
                }
            }
            output.force(false);
        } finally {
            input.close();
            output.close();
        }
    }
}
