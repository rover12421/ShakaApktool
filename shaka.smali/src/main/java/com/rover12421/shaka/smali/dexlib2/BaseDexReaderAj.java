package com.rover12421.shaka.smali.dexlib2;

import com.rover12421.shaka.lib.LogHelper;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.jf.dexlib2.dexbacked.BaseDexReader;
import org.jf.util.ExceptionWithContext;

/**
 * Created by rover12421 on 11/9/15.
 */
@Aspect
public class BaseDexReaderAj {
    private int getBaseOffset(BaseDexReader baseDexReader) {
        return baseDexReader.dexBuf.getBaseOffset0();
    }

    private byte[] getBuf(BaseDexReader baseDexReader) {
        return baseDexReader.dexBuf.getBuf0();
    }

    @Around("execution(* org.jf.dexlib2.dexbacked.BaseDexReader.readUleb128(..))" +
            "&& args(allowLarge)")
    public int readUleb128(ProceedingJoinPoint joinPoint, boolean allowLarge) {
        BaseDexReader thiz = (BaseDexReader) joinPoint.getThis();
        int baseOffset = getBaseOffset(thiz);

        int end = baseOffset + thiz.getOffset();
        int currentByteValue;
        int result;
        byte[] buf = getBuf(thiz);

        result = buf[end++] & 0xff;
        if (result > 0x7f) {
            currentByteValue = buf[end++] & 0xff;
            result = (result & 0x7f) | ((currentByteValue & 0x7f) << 7);
            if (currentByteValue > 0x7f) {
                currentByteValue = buf[end++] & 0xff;
                result |= (currentByteValue & 0x7f) << 14;
                if (currentByteValue > 0x7f) {
                    currentByteValue = buf[end++] & 0xff;
                    result |= (currentByteValue & 0x7f) << 21;
                    if (currentByteValue > 0x7f) {
                        currentByteValue = buf[end++];
                        result |= currentByteValue << 28;
                    }
                }
            }
        }

        thiz.setOffset(end - baseOffset);
        return result;
    }

    @Around("execution(* org.jf.dexlib2.dexbacked.BaseDexReader.readBigUleb128())")
    public int readBigUleb128(ProceedingJoinPoint joinPoint) {
        BaseDexReader thiz = (BaseDexReader) joinPoint.getThis();
        int baseOffset = getBaseOffset(thiz);

        int end = baseOffset + thiz.getOffset();
        int currentByteValue;
        int result;
        byte[] buf = getBuf(thiz);

        result = buf[end++] & 0xff;
        if (result > 0x7f) {
            currentByteValue = buf[end++] & 0xff;
            result = (result & 0x7f) | ((currentByteValue & 0x7f) << 7);
            if (currentByteValue > 0x7f) {
                currentByteValue = buf[end++] & 0xff;
                result |= (currentByteValue & 0x7f) << 14;
                if (currentByteValue > 0x7f) {
                    currentByteValue = buf[end++] & 0xff;
                    result |= (currentByteValue & 0x7f) << 21;
                    if (currentByteValue > 0x7f) {
                        currentByteValue = buf[end++];
                        result |= currentByteValue << 28;
                    }
                }
            }
        }

        thiz.setOffset(end - baseOffset);
        return result;
    }

    @Around("execution(* org.jf.dexlib2.dexbacked.BaseDexReader.skipUleb128())")
    public void skipUleb128(ProceedingJoinPoint joinPoint) {
        BaseDexReader thiz = (BaseDexReader) joinPoint.getThis();
        int baseOffset = getBaseOffset(thiz);

        int end = baseOffset + thiz.getOffset();
        byte currentByteValue;
        byte[] buf = getBuf(thiz);

        currentByteValue = buf[end++];
        if (currentByteValue < 0) { // if the MSB is set
            currentByteValue = buf[end++];
            if (currentByteValue < 0) { // if the MSB is set
                currentByteValue = buf[end++];
                if (currentByteValue < 0) { // if the MSB is set
                    currentByteValue = buf[end++];
                    if (currentByteValue < 0) { // if the MSB is set
                        currentByteValue = buf[end++];
//                        if (currentByteValue < 0) {
//                            throw new ExceptionWithContext(
//                                    "Invalid uleb128 integer encountered at offset 0x%x", offset);
//                        }
                    }
                }
            }
        }

        thiz.setOffset(end - baseOffset);
    }

//    @Around("execution(* org.jf.dexlib2.dexbacked.BaseDexReader.readSizedSmallUint(..))" +
//            "&& args(bytes)")
    public int readSizedSmallUint(ProceedingJoinPoint joinPoint, int bytes) {
        BaseDexReader thiz = (BaseDexReader) joinPoint.getThis();
        int baseOffset = getBaseOffset(thiz);
        int offset = thiz.getOffset();

        int o = baseOffset + offset;
        byte[] buf = getBuf(thiz);

        int result = 0;
        switch (bytes) {
            case 4:
                int b = buf[o+3];
                if (b < 0) {
                    LogHelper.warning(String.format("Encountered valid sized uint that is out of range at offset 0x%x : 0x%x", offset, b));
                }
                result = b << 24;
                // fall-through
            case 3:
                result |= (buf[o+2] & 0xff) << 16;
                // fall-through
            case 2:
                result |= (buf[o+1] & 0xff) << 8;
                // fall-through
            case 1:
                result |= (buf[o] & 0xff);
                break;
            default:
                throw new ExceptionWithContext("Invalid size %d for sized uint at offset 0x%x", bytes, offset);
        }

        thiz.setOffset( o + bytes - baseOffset);
        return result;
    }
}
