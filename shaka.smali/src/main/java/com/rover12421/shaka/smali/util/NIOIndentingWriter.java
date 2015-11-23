package com.rover12421.shaka.smali.util;

import org.jf.util.IndentingWriter;

import java.io.FileOutputStream;
import java.io.IOException;
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

    public NIOIndentingWriter(FileOutputStream fos) {
        super(null);
        channel = fos.getChannel();
    }

    private void writeBuffer() throws IOException {
        charBuffer.flip();
        ByteBuffer buffer = StandardCharsets.UTF_8.encode(charBuffer);
        channel.write(buffer);
        charBuffer.rewind();
    }

//    @Override
//    protected void writeIndent() throws IOException {
//        super.writeIndent();
//    }

    @Override
    public void write(int chr) throws IOException {
        if (charBuffer.remaining() < 1) {
            writeBuffer();
        }
        charBuffer.put((char) chr);
    }

    @Override
    public void write(char[] chars) throws IOException {
        write(chars, 0, chars.length);
    }

    @Override
    public void write(char[] chars, int start, int len) throws IOException {
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
    public void write(String s) throws IOException {
        write(s, 0, s.length());
    }

    @Override
    public void write(String str, int start, int len) throws IOException {
        int end = start+len;
        int pos = start;
        while (pos < end) {
            write(str.charAt(pos));
            pos++;
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
//
//    @Override
//    public void printUnsignedLongAsHex(long value) throws IOException {
//        super.printUnsignedLongAsHex(value);
//    }
//
//    @Override
//    public void printSignedLongAsDec(long value) throws IOException {
//        super.printSignedLongAsDec(value);
//    }
//
//    @Override
//    public void printSignedIntAsDec(int value) throws IOException {
//        super.printSignedIntAsDec(value);
//    }
//
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
