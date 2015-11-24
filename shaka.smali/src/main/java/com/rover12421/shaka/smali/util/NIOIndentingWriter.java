package com.rover12421.shaka.smali.util;

import org.jf.util.IndentingWriter;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;

/**
 * Created by rover12421 on 11/24/15.
 * 简单的测试,同IndentingWriter效率相当
 * 单并没有遵循IndentingWriter的换行机制,有待确认windows平台是否有换行问题
 */
public class NIOIndentingWriter extends IndentingWriter {
    private FileChannel channel;
    private CharBuffer charBuffer = CharBuffer.allocate(8192);

    private boolean beginningOfLine = true;
    private static final char[] newLine = System.getProperty("line.separator").toCharArray();

    public NIOIndentingWriter(FileChannel channel) {
        super(null);
        this.channel = channel;
    }

    public NIOIndentingWriter(FileOutputStream fos) {
        this(fos.getChannel());
    }

    protected FileChannel getChannel() {
        return channel;
    }

    private void writeBuffer() throws IOException {
        if (charBuffer.position() > 0) {
            charBuffer.flip();
            ByteBuffer buffer = StandardCharsets.UTF_8.encode(charBuffer);
            channel.write(buffer);
            charBuffer.rewind();
        }
    }

    @Override
    protected void writeIndent() throws IOException {
        for (int i=0; i<indentLevel; i++) {
            write0(' ');
        }
    }

    private void write0(int chr) throws IOException {
        if (charBuffer.remaining() < 1) {
            writeBuffer();
        }
        charBuffer.put((char) chr);
    }

    @Override
    public void write(int chr) throws IOException {
        if (chr == '\n') {
            write0(newLine);
            beginningOfLine = true;
        } else {
            if (beginningOfLine) {
                writeIndent();
            }
            beginningOfLine = false;
            write0(chr);
        }
    }

    private void writeLine(char[] chars, int start, int len) throws IOException {
        if (beginningOfLine && len > 0) {
            writeIndent();
            beginningOfLine = false;
        }
        write0(chars, start, len);
    }


    private void writeLine(String str, int start, int len) throws IOException {
        if (beginningOfLine && len > 0) {
            writeIndent();
            beginningOfLine = false;
        }
        write0(str, start, len);
    }

    private void write0(char[] chars) throws IOException {
        write0(chars, 0, chars.length);
    }

    private void write0(char[] chars, int start, int len) throws IOException {
        int size = len;
        int off = start;
        int remaining = charBuffer.remaining();
        while (size > remaining) {
            charBuffer.put(chars, off, remaining);
            size -= remaining;
            off += remaining;
            writeBuffer();
            remaining = charBuffer.remaining();
        }
        charBuffer.put(chars, off, size);
    }

    @Override
    public void write(char[] chars) throws IOException {
        write(chars, 0, chars.length);
    }

    @Override
    public void write(char[] chars, int start, int len) throws IOException {
        final int end = start+len;
        int pos = start;
        while (pos < end) {
            if (chars[pos] == '\n') {
                writeLine(chars, start, pos-start);

                write0(newLine);
                beginningOfLine = true;
                pos++;
                start = pos;
            } else {
                pos++;
            }
        }
        writeLine(chars, start, pos-start);
    }

    private void write0(String s) throws IOException {
        write0(s, 0, s.length());
    }

    private void write0(String str, int start, int len) throws IOException {
        int end = start+len;
        int pos = start;
        while (pos < end) {
            write0(str.charAt(pos));
            pos++;
        }
    }

    @Override
    public void write(String s) throws IOException {
        write(s, 0, s.length());
    }

    @Override
    public void write(String str, int start, int len) throws IOException {
        final int end = start+len;
        int pos = start;
        while (pos < end) {
            pos = str.indexOf('\n', start);
            if (pos == -1 || pos >= end) {
                writeLine(str, start, end-start);
                return;
            } else {
                writeLine(str, start, pos-start);
                write0(newLine);
                beginningOfLine = true;
                start = pos+1;
            }
        }
    }

//    @Override
//    public Writer append(CharSequence charSequence) throws IOException {
//        return super.append(charSequence);
//    }
//
//    @Override
//    public Writer append(CharSequence charSequence, int start, int len) throws IOException {
//        return super.append(charSequence, start, len);
//    }
//
//    @Override
//    public Writer append(char c) throws IOException {
//        return super.append(c);
//    }

    @Override
    public void flush() throws IOException {
        writeBuffer();
    }

    @Override
    public void close() throws IOException {
        writeBuffer();
        channel.close();
    }

//    @Override
//    public void indent(int indentAmount) {
//        super.indent(indentAmount);
//    }
//
//    @Override
//    public void deindent(int indentAmount) {
//        super.deindent(indentAmount);
//    }

    public void printUnsignedLongAsHex(long value) throws IOException {
        int bufferIndex = 23;
        do {
            int digit = (int)(value & 15);
            if (digit < 10) {
                buffer[bufferIndex--] = (char)(digit + '0');
            } else {
                buffer[bufferIndex--] = (char)((digit - 10) + 'a');
            }

            value >>>= 4;
        } while (value != 0);

        bufferIndex++;

        writeLine(buffer, bufferIndex, 24-bufferIndex);
    }

    public void printSignedLongAsDec(long value) throws IOException {
        int bufferIndex = 23;

        if (value < 0) {
            value *= -1;
            write('-');
        }

        do {
            long digit = value % 10;
            buffer[bufferIndex--] = (char)(digit + '0');

            value = value / 10;
        } while (value != 0);

        bufferIndex++;

        writeLine(buffer, bufferIndex, 24-bufferIndex);
    }

    public void printSignedIntAsDec(int value) throws IOException {
        int bufferIndex = 15;

        if (value < 0) {
            value *= -1;
            write('-');
        }

        do {
            int digit = value % 10;
            buffer[bufferIndex--] = (char)(digit + '0');

            value = value / 10;
        } while (value != 0);

        bufferIndex++;

        writeLine(buffer, bufferIndex, 16-bufferIndex);
    }

//    @Override
//    public void printUnsignedIntAsDec(int value) throws IOException {
//        super.printUnsignedIntAsDec(value);
//    }

    public long getPosition() throws IOException {
        flush();
        return channel.position();
    }

    public void position(long newPosition) throws IOException {
        channel.position(newPosition);
        charBuffer.rewind();
    }
}
