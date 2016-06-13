/*
 * Copyright 2012, Google Inc.
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are
 * met:
 *
 *     * Redistributions of source code must retain the above copyright
 * notice, this list of conditions and the following disclaimer.
 *     * Redistributions in binary form must reproduce the above
 * copyright notice, this list of conditions and the following disclaimer
 * in the documentation and/or other materials provided with the
 * distribution.
 *     * Neither the name of Google Inc. nor the names of its
 * contributors may be used to endorse or promote products derived from
 * this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT
 * OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.jf.dexlib2;

public class AccessorTypes {
    protected boolean boolean_val;
    protected byte byte_val;
    protected char char_val;
    protected short short_val;
    protected int int_val;
    protected long long_val;
    protected float float_val;
    protected double double_val;

    protected class Accessors {
        public void boolean_and(boolean val) {
            boolean_val &= val;
        }

        public void boolean_or(boolean val) {
            boolean_val |= val;
        }

        public void boolean_xor(boolean val) {
            boolean_val ^= val;
        }

        public void byte_preinc() {
            ++byte_val;
        }

        public void byte_postinc() {
            byte_val++;
        }

        public void byte_predec() {
            --byte_val;
        }

        public void byte_postdec() {
            byte_val--;
        }

        public void byte_add(int val) {
            byte_val += val;
        }

        public void byte_add(float val) {
            byte_val += val;
        }

        public void byte_add(long val) {
            byte_val += val;
        }

        public void byte_add(double val) {
            byte_val += val;
        }

        public void byte_sub(int val) {
            byte_val -= val;
        }

        public void byte_sub(float val) {
            byte_val -= val;
        }

        public void byte_sub(long val) {
            byte_val -= val;
        }

        public void byte_sub(double val) {
            byte_val -= val;
        }

        public void byte_mul(int val) {
            byte_val *= val;
        }

        public void byte_mul(float val) {
            byte_val *= val;
        }

        public void byte_mul(long val) {
            byte_val *= val;
        }

        public void byte_mul(double val) {
            byte_val *= val;
        }

        public void byte_div(int val) {
            byte_val /= val;
        }

        public void byte_div(float val) {
            byte_val /= val;
        }

        public void byte_div(long val) {
            byte_val /= val;
        }

        public void byte_div(double val) {
            byte_val /= val;
        }

        public void byte_rem(int val) {
            byte_val %= val;
        }

        public void byte_rem(float val) {
            byte_val %= val;
        }

        public void byte_rem(long val) {
            byte_val %= val;
        }

        public void byte_rem(double val) {
            byte_val %= val;
        }

        public void byte_and(int val) {
            byte_val &= val;
        }

        public void byte_and(long val) {
            byte_val &= val;
        }

        public void byte_or(int val) {
            byte_val |= val;
        }

        public void byte_or(long val) {
            byte_val |= val;
        }

        public void byte_xor(int val) {
            byte_val ^= val;
        }

        public void byte_xor(long val) {
            byte_val ^= val;
        }

        public void byte_shl(int val) {
            byte_val <<= val;
        }

        public void byte_shl(long val) {
            byte_val <<= val;
        }

        public void byte_shr(int val) {
            byte_val >>= val;
        }

        public void byte_shr(long val) {
            byte_val >>= val;
        }

        public void byte_ushr(int val) {
            byte_val >>>= val;
        }

        public void byte_ushr(long val) {
            byte_val >>>= val;
        }

        public void char_preinc() {
            ++char_val;
        }

        public void char_postinc() {
            char_val++;
        }

        public void char_predec() {
            --char_val;
        }

        public void char_postdec() {
            char_val--;
        }

        public void char_add(int val) {
            char_val += val;
        }

        public void char_add(float val) {
            char_val += val;
        }

        public void char_add(long val) {
            char_val += val;
        }

        public void char_add(double val) {
            char_val += val;
        }

        public void char_sub(int val) {
            char_val -= val;
        }

        public void char_sub(float val) {
            char_val -= val;
        }

        public void char_sub(long val) {
            char_val -= val;
        }

        public void char_sub(double val) {
            char_val -= val;
        }

        public void char_mul(int val) {
            char_val *= val;
        }

        public void char_mul(float val) {
            char_val *= val;
        }

        public void char_mul(long val) {
            char_val *= val;
        }

        public void char_mul(double val) {
            char_val *= val;
        }

        public void char_div(int val) {
            char_val /= val;
        }

        public void char_div(float val) {
            char_val /= val;
        }

        public void char_div(long val) {
            char_val /= val;
        }

        public void char_div(double val) {
            char_val /= val;
        }

        public void char_rem(int val) {
            char_val %= val;
        }

        public void char_rem(float val) {
            char_val %= val;
        }

        public void char_rem(long val) {
            char_val %= val;
        }

        public void char_rem(double val) {
            char_val %= val;
        }

        public void char_and(int val) {
            char_val &= val;
        }

        public void char_and(long val) {
            char_val &= val;
        }

        public void char_or(int val) {
            char_val |= val;
        }

        public void char_or(long val) {
            char_val |= val;
        }

        public void char_xor(int val) {
            char_val ^= val;
        }

        public void char_xor(long val) {
            char_val ^= val;
        }

        public void char_shl(int val) {
            char_val <<= val;
        }

        public void char_shl(long val) {
            char_val <<= val;
        }

        public void char_shr(int val) {
            char_val >>= val;
        }

        public void char_shr(long val) {
            char_val >>= val;
        }

        public void char_ushr(int val) {
            char_val >>>= val;
        }

        public void char_ushr(long val) {
            char_val >>>= val;
        }

        public void short_preinc() {
            ++short_val;
        }

        public void short_postinc() {
            short_val++;
        }

        public void short_predec() {
            --short_val;
        }

        public void short_postdec() {
            short_val--;
        }

        public void short_add(int val) {
            short_val += val;
        }

        public void short_add(float val) {
            short_val += val;
        }

        public void short_add(long val) {
            short_val += val;
        }

        public void short_add(double val) {
            short_val += val;
        }

        public void short_sub(int val) {
            short_val -= val;
        }

        public void short_sub(float val) {
            short_val -= val;
        }

        public void short_sub(long val) {
            short_val -= val;
        }

        public void short_sub(double val) {
            short_val -= val;
        }

        public void short_mul(int val) {
            short_val *= val;
        }

        public void short_mul(float val) {
            short_val *= val;
        }

        public void short_mul(long val) {
            short_val *= val;
        }

        public void short_mul(double val) {
            short_val *= val;
        }

        public void short_div(int val) {
            short_val /= val;
        }

        public void short_div(float val) {
            short_val /= val;
        }

        public void short_div(long val) {
            short_val /= val;
        }

        public void short_div(double val) {
            short_val /= val;
        }

        public void short_rem(int val) {
            short_val %= val;
        }

        public void short_rem(float val) {
            short_val %= val;
        }

        public void short_rem(long val) {
            short_val %= val;
        }

        public void short_rem(double val) {
            short_val %= val;
        }

        public void short_and(int val) {
            short_val &= val;
        }

        public void short_and(long val) {
            short_val &= val;
        }

        public void short_or(int val) {
            short_val |= val;
        }

        public void short_or(long val) {
            short_val |= val;
        }

        public void short_xor(int val) {
            short_val ^= val;
        }

        public void short_xor(long val) {
            short_val ^= val;
        }

        public void short_shl(int val) {
            short_val <<= val;
        }

        public void short_shl(long val) {
            short_val <<= val;
        }

        public void short_shr(int val) {
            short_val >>= val;
        }

        public void short_shr(long val) {
            short_val >>= val;
        }

        public void short_ushr(int val) {
            short_val >>>= val;
        }

        public void short_ushr(long val) {
            short_val >>>= val;
        }

        public void int_preinc() {
            ++int_val;
        }

        public void int_postinc() {
            int_val++;
        }

        public void int_predec() {
            --int_val;
        }

        public void int_postdec() {
            int_val--;
        }

        public void int_add(int val) {
            int_val += val;
        }

        public void int_add(float val) {
            int_val += val;
        }

        public void int_add(long val) {
            int_val += val;
        }

        public void int_add(double val) {
            int_val += val;
        }

        public void int_sub(int val) {
            int_val -= val;
        }

        public void int_sub(float val) {
            int_val -= val;
        }

        public void int_sub(long val) {
            int_val -= val;
        }

        public void int_sub(double val) {
            int_val -= val;
        }

        public void int_mul(int val) {
            int_val *= val;
        }

        public void int_mul(float val) {
            int_val *= val;
        }

        public void int_mul(long val) {
            int_val *= val;
        }

        public void int_mul(double val) {
            int_val *= val;
        }

        public void int_div(int val) {
            int_val /= val;
        }

        public void int_div(float val) {
            int_val /= val;
        }

        public void int_div(long val) {
            int_val /= val;
        }

        public void int_div(double val) {
            int_val /= val;
        }

        public void int_rem(int val) {
            int_val %= val;
        }

        public void int_rem(float val) {
            int_val %= val;
        }

        public void int_rem(long val) {
            int_val %= val;
        }

        public void int_rem(double val) {
            int_val %= val;
        }

        public void int_and(int val) {
            int_val &= val;
        }

        public void int_and(long val) {
            int_val &= val;
        }

        public void int_or(int val) {
            int_val |= val;
        }

        public void int_or(long val) {
            int_val |= val;
        }

        public void int_xor(int val) {
            int_val ^= val;
        }

        public void int_xor(long val) {
            int_val ^= val;
        }

        public void int_shl(int val) {
            int_val <<= val;
        }

        public void int_shl(long val) {
            int_val <<= val;
        }

        public void int_shr(int val) {
            int_val >>= val;
        }

        public void int_shr(long val) {
            int_val >>= val;
        }

        public void int_ushr(int val) {
            int_val >>>= val;
        }

        public void int_ushr(long val) {
            int_val >>>= val;
        }

        public void long_preinc() {
            ++long_val;
        }

        public void long_postinc() {
            long_val++;
        }

        public void long_predec() {
            --long_val;
        }

        public void long_postdec() {
            long_val--;
        }

        public void long_add(int val) {
            long_val += val;
        }

        public void long_add(float val) {
            long_val += val;
        }

        public void long_add(long val) {
            long_val += val;
        }

        public void long_add(double val) {
            long_val += val;
        }

        public void long_sub(int val) {
            long_val -= val;
        }

        public void long_sub(float val) {
            long_val -= val;
        }

        public void long_sub(long val) {
            long_val -= val;
        }

        public void long_sub(double val) {
            long_val -= val;
        }

        public void long_mul(int val) {
            long_val *= val;
        }

        public void long_mul(float val) {
            long_val *= val;
        }

        public void long_mul(long val) {
            long_val *= val;
        }

        public void long_mul(double val) {
            long_val *= val;
        }

        public void long_div(int val) {
            long_val /= val;
        }

        public void long_div(float val) {
            long_val /= val;
        }

        public void long_div(long val) {
            long_val /= val;
        }

        public void long_div(double val) {
            long_val /= val;
        }

        public void long_rem(int val) {
            long_val %= val;
        }

        public void long_rem(float val) {
            long_val %= val;
        }

        public void long_rem(long val) {
            long_val %= val;
        }

        public void long_rem(double val) {
            long_val %= val;
        }

        public void long_and(int val) {
            long_val &= val;
        }

        public void long_and(long val) {
            long_val &= val;
        }

        public void long_or(int val) {
            long_val |= val;
        }

        public void long_or(long val) {
            long_val |= val;
        }

        public void long_xor(int val) {
            long_val ^= val;
        }

        public void long_xor(long val) {
            long_val ^= val;
        }

        public void long_shl(int val) {
            long_val <<= val;
        }

        public void long_shl(long val) {
            long_val <<= val;
        }

        public void long_shr(int val) {
            long_val >>= val;
        }

        public void long_shr(long val) {
            long_val >>= val;
        }

        public void long_ushr(int val) {
            long_val >>>= val;
        }

        public void long_ushr(long val) {
            long_val >>>= val;
        }

        public void float_preinc() {
            ++float_val;
        }

        public void float_postinc() {
            float_val++;
        }

        public void float_predec() {
            --float_val;
        }

        public void float_postdec() {
            float_val--;
        }

        public void float_add(int val) {
            float_val += val;
        }

        public void float_add(float val) {
            float_val += val;
        }

        public void float_add(long val) {
            float_val += val;
        }

        public void float_add(double val) {
            float_val += val;
        }

        public void float_sub(int val) {
            float_val -= val;
        }

        public void float_sub(float val) {
            float_val -= val;
        }

        public void float_sub(long val) {
            float_val -= val;
        }

        public void float_sub(double val) {
            float_val -= val;
        }

        public void float_mul(int val) {
            float_val *= val;
        }

        public void float_mul(float val) {
            float_val *= val;
        }

        public void float_mul(long val) {
            float_val *= val;
        }

        public void float_mul(double val) {
            float_val *= val;
        }

        public void float_div(int val) {
            float_val /= val;
        }

        public void float_div(float val) {
            float_val /= val;
        }

        public void float_div(long val) {
            float_val /= val;
        }

        public void float_div(double val) {
            float_val /= val;
        }

        public void float_rem(int val) {
            float_val %= val;
        }

        public void float_rem(float val) {
            float_val %= val;
        }

        public void float_rem(long val) {
            float_val %= val;
        }

        public void float_rem(double val) {
            float_val %= val;
        }

        public void double_preinc() {
            ++double_val;
        }

        public void double_postinc() {
            double_val++;
        }

        public void double_predec() {
            --double_val;
        }

        public void double_postdec() {
            double_val--;
        }

        public void double_add(int val) {
            double_val += val;
        }

        public void double_add(float val) {
            double_val += val;
        }

        public void double_add(long val) {
            double_val += val;
        }

        public void double_add(double val) {
            double_val += val;
        }

        public void double_sub(int val) {
            double_val -= val;
        }

        public void double_sub(float val) {
            double_val -= val;
        }

        public void double_sub(long val) {
            double_val -= val;
        }

        public void double_sub(double val) {
            double_val -= val;
        }

        public void double_mul(int val) {
            double_val *= val;
        }

        public void double_mul(float val) {
            double_val *= val;
        }

        public void double_mul(long val) {
            double_val *= val;
        }

        public void double_mul(double val) {
            double_val *= val;
        }

        public void double_div(int val) {
            double_val /= val;
        }

        public void double_div(float val) {
            double_val /= val;
        }

        public void double_div(long val) {
            double_val /= val;
        }

        public void double_div(double val) {
            double_val /= val;
        }

        public void double_rem(int val) {
            double_val %= val;
        }

        public void double_rem(float val) {
            double_val %= val;
        }

        public void double_rem(long val) {
            double_val %= val;
        }

        public void double_rem(double val) {
            double_val %= val;
        }
    }
}