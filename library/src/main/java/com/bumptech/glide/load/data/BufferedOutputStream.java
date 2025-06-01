package com.bumptech.glide.load.data;

import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.bumptech.glide.load.engine.bitmap_recycle.ArrayPool;
import java.io.IOException;
import java.io.OutputStream;
import javax.annotation.Nullable;

/**
 * An {@link OutputStream} implementation that recycles and re-uses {@code byte[]}s using the
 * provided {@link ArrayPool}.
 */
public final class BufferedOutputStream extends OutputStream {
  @NonNull private final OutputStream out;
  @Nullable private byte[] buffer;
  private ArrayPool arrayPool;
  private int index;

  public BufferedOutputStream(@NonNull OutputStream out, @NonNull ArrayPool arrayPool) {
    this(out, arrayPool, ArrayPool.STANDARD_BUFFER_SIZE_BYTES);
  }

  @VisibleForTesting
  BufferedOutputStream(@NonNull OutputStream out, ArrayPool arrayPool, int bufferSize) {
    this.out = out;
    this.arrayPool = arrayPool;
    buffer = arrayPool.get(bufferSize, byte[].class);
  }

  @Override
    public void write(int b) throws IOException {
        if (buffer != null) {
            buffer[index++] = (byte) b;
            maybeFlushBuffer();
        } else {
            throw new IOException("Buffer is not initialized.");
        }
    }

  @Override
  public void write(@NonNull byte[] b) throws IOException {
    write(b, 0, b.length);
  }

  @Override
    public void write(@NonNull byte[] b, int initialOffset, int length) throws IOException {
      int writtenSoFar = 0;
      do {
        int remainingToWrite = length - writtenSoFar;
        int currentOffset = initialOffset + writtenSoFar;
        
        if (index == 0 && remainingToWrite >= buffer.length) {
          out.write(b, currentOffset, remainingToWrite);
          return;
        }
  
        if (buffer != null) { // Null check added here
          int remainingSpaceInBuffer = buffer.length - index;
          int totalBytesToWriteToBuffer = Math.min(remainingToWrite, remainingSpaceInBuffer);
          
          System.arraycopy(b, currentOffset, buffer, index, totalBytesToWriteToBuffer);
          
          index += totalBytesToWriteToBuffer;
          writtenSoFar += totalBytesToWriteToBuffer;
          
          maybeFlushBuffer();
        }
      } while (writtenSoFar < length);
    }

  @Override
  public void flush() throws IOException {
    flushBuffer();
    out.flush();
  }

  private void flushBuffer() throws IOException {
    if (index > 0) {
      out.write(buffer, 0, index);
      index = 0;
    }
  }

  private void maybeFlushBuffer() throws IOException {
        if (buffer != null && index == buffer.length) {
            flushBuffer();
        }
    }

  @Override
  public void close() throws IOException {
    try {
      flush();
    } finally {
      out.close();
    }
    release();
  }

  private void release() {
    if (buffer != null) {
      arrayPool.put(buffer);
      buffer = null;
    }
  }
}
