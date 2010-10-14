/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright (c) 2010 Oracle and/or its affiliates. All rights reserved.
 *
 * The contents of this file are subject to the terms of either the GNU
 * General Public License Version 2 only ("GPL") or the Common Development
 * and Distribution License("CDDL") (collectively, the "License").  You
 * may not use this file except in compliance with the License.  You can
 * obtain a copy of the License at
 * https://glassfish.dev.java.net/public/CDDL+GPL_1_1.html
 * or packager/legal/LICENSE.txt.  See the License for the specific
 * language governing permissions and limitations under the License.
 *
 * When distributing the software, include this License Header Notice in each
 * file and include the License file at packager/legal/LICENSE.txt.
 *
 * GPL Classpath Exception:
 * Oracle designates this particular file as subject to the "Classpath"
 * exception as provided by Oracle in the GPL Version 2 section of the License
 * file that accompanied this code.
 *
 * Modifications:
 * If applicable, add the following below the License Header, with the fields
 * enclosed by brackets [] replaced by your own identifying information:
 * "Portions Copyright [year] [name of copyright owner]"
 *
 * Contributor(s):
 * If you wish your version of this file to be governed by only the CDDL or
 * only the GPL Version 2, indicate your decision by adding "[Contributor]
 * elects to include this software in this distribution under the [CDDL or GPL
 * Version 2] license."  If you don't indicate a single choice of license, a
 * recipient has the option to distribute your version of this file under
 * either the CDDL, the GPL Version 2 or to extend the choice of license to
 * its licensees as provided above.  However, if you add GPL Version 2 code
 * and therefore, elected the GPL Version 2 license, then the option applies
 * only if the new code is made subject to such option by the copyright
 * holder.
 */

package org.glassfish.grizzly.http.util;

import java.io.CharConversionException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CoderResult;
import org.glassfish.grizzly.Buffer;
import org.glassfish.grizzly.memory.Buffers;

/**
 * {@link Buffer} chunk representation.
 * Helps HTTP module to avoid redundant String creation.
 *
 * @author Alexey Stashok
 */
public class BufferChunk implements Chunk {
    private static final Charset UTF8_CHARSET = Utils.lookupCharset("UTF-8");
    private static final UTF8Decoder UTF8_DECODER = new UTF8Decoder();

    private Buffer buffer;

    private int start;
    private int end;

    String cachedString;
    Charset cachedStringCharset;

    public void setBufferChunk(final Buffer buffer, final int start,
            final int end) {
        this.buffer = buffer;
        this.start = start;
        this.end = end;
        resetStringCache();
    }

    public Buffer getBuffer() {
        return buffer;
    }

    public void setBuffer(Buffer buffer) {
        this.buffer = buffer;
        resetStringCache();
    }

    @Override
    public int getStart() {
        return start;
    }

    @Override
    public void setStart(int start) {
        this.start = start;
        resetStringCache();
    }

    @Override
    public int getEnd() {
        return end;
    }

    @Override
    public void setEnd(int end) {
        this.end = end;
        resetStringCache();
    }

    public final int getLength() {
        return end - start;
    }

    public boolean isNull() {
        return buffer == null;
    }

    @Override
    public void delete(final int start, final int end) {
        final int diff = this.end - end;
        if (diff == 0) {
            this.end = start;
        } else {
            final int oldPos = buffer.position();
            final int oldLim = buffer.limit();

            try {
                Buffers.setPositionLimit(buffer, start, start + diff);
                buffer.put(buffer, end, diff);
                this.end = start + diff;
            } finally {
                Buffers.setPositionLimit(buffer, oldPos, oldLim);
            }
        }
    }
    
    @Override
    public final int indexOf(char c, int fromIndex) {
        return indexOf(buffer, start + fromIndex, end, c);
    }


    @Override
    public final int indexOf(String s, int fromIndex) {
        return indexOf(buffer, start + fromIndex, end, s);
    }

    boolean startsWith(String s, int pos) {
        final int len = s.length();
        if (len > getLength() - pos) {
            return false;
        }

        int off = start + pos;
        for (int i = 0; i < len; i++) {
            if (buffer.get(off++) != s.charAt(i)) {
                return false;
            }
        }

        return true;
    }

    public boolean startsWithIgnoreCase(String s, int pos) {
        final int len = s.length();
        if (len > getLength() - pos) {
            return false;
        }

        int off = start + pos;
        for (int i = 0; i < len; i++) {
            if (Ascii.toLower(buffer.get(off++)) != Ascii.toLower(s.charAt(i))) {
                return false;
            }
        }

        return true;
    }

    /**
     * Returns the starting index of the specified byte sequence within this
     * <code>Buffer</code>.
     *
     * @param b byte sequence to search for.
     *
     * @return the starting index of the specified byte sequence within this
     *  <code>Buffer</code>
     */
    public int findBytesAscii(byte[] b) {

        final byte first = b[0];
        final int from = getStart();
        final int to = getEnd();

        // Look for first char
        int srcEnd = b.length;

        for (int i = from; i <= to - srcEnd; i++) {
            if (Ascii.toLower(buffer.get(i)) != first) continue;
            // found first char, now look for a match
            int myPos = i + 1;
            for (int srcPos = 1; srcPos < srcEnd;) {
                if (Ascii.toLower(buffer.get(myPos++)) != b[srcPos++]) {
                    break;
                }
                if (srcPos == srcEnd) {
                    return i - from; // found it
                }
            }
        }
        
        return -1;
    }

    public boolean equals(CharSequence s) {
        if (getLength() != s.length()) {
            return false;
        }

        for (int i = start; i < end; i++) {
            if (buffer.get(i) != s.charAt(i - start)) {
                return false;
            }
        }

        return true;
    }

    public boolean equalsIgnoreCase(CharSequence s) {
        if (getLength() != s.length()) {
            return false;
        }

        for (int i = start; i < end; i++) {
            if (Ascii.toLower(buffer.get(i)) != Ascii.toLower(s.charAt(i - start))) {
                return false;
            }
        }

        return true;
    }

    @Override
    public String toString() {
        return toString(null);
    }

    public String toString(Charset charset) {
        if (charset == null) charset = UTF8_CHARSET;

        if (cachedString != null && charset.equals(cachedStringCharset)) {
            return cachedString;
        }

        cachedString = buffer.toStringContent(charset, start, end);

        cachedStringCharset = charset;

        return cachedString;
    }

    @Override
    public String toString(int start, int end) {
        return buffer.toStringContent(UTF8_CHARSET, start, end);
    }

    protected final void resetStringCache() {
        cachedString = null;
        cachedStringCharset = null;
    }
    
    protected final void reset() {
        buffer = null;
        start = -1;
        end = -1;
        resetStringCache();
    }
    
    public final void recycle() {
        reset();
    }


    private static int indexOf(Buffer buffer, int off, int end, char qq) {
        // Works only for UTF
        while (off < end) {
            byte b = buffer.get(off);
            if (b == qq) {
                return off;
            }
            off++;
        }
        
        return -1;
    }

    private static int indexOf(Buffer buffer, int off, final int end, final CharSequence s) {
        // Works only for UTF
        final int strLen = s.length();
        if (strLen == 0) {
            return off;
        }

        if (strLen > (end - off)) return -1;

        int strOffs = 0;
        final int lastOffs = end - strLen;

        while (off <= lastOffs + strOffs) {
            final byte b = buffer.get(off);
            if (b == s.charAt(strOffs)) {
                strOffs++;
                if (strOffs == strLen) {
                    return off - strLen + 1;
                }
            } else {
                strOffs = 0;
            }

            off++;
        }
        return -1;
    }

    /**
     * @return -1, 0 or +1 if inferior, equal, or superior to the String.
     */
    public int compareIgnoreCase(int start, int end, String compareTo) {
        int result = 0;

        int len = compareTo.length();
        if ((end - start) < len) {
            len = end - start;
        }
        for (int i = 0; (i < len) && (result == 0); i++) {
            if (Ascii.toLower(buffer.get(i + start)) > Ascii.toLower(compareTo.charAt(i))) {
                result = 1;
            } else if (Ascii.toLower(buffer.get(i + start)) < Ascii.toLower(compareTo.charAt(i))) {
                result = -1;
            }
        }
        if (result == 0) {
            if (compareTo.length() > (end - start)) {
                result = -1;
            } else if (compareTo.length() < (end - start)) {
                result = 1;
            }
        }
        return result;
    }


    /**
     * @return -1, 0 or +1 if inferior, equal, or superior to the String.
     */
    public int compare(int start, int end, String compareTo) {
        int result = 0;
        int len = compareTo.length();
        if ((end - start) < len) {
            len = end - start;
        }
        for (int i = 0; (i < len) && (result == 0); i++) {
            if (buffer.get(i + start) > compareTo.charAt(i)) {
                result = 1;
            } else if (buffer.get(i + start) < compareTo.charAt(i)) {
                result = -1;
            }
        }
        if (result == 0) {
            if (compareTo.length() > (end - start)) {
                result = -1;
            } else if (compareTo.length() < (end - start)) {
                result = 1;
            }
        }
        return result;
    }

    /**
     * Convert a {@link BufferChunk} using the specified encoding.
     * @param charChank char array to put result to.
     * @param encoding the encoding value
     * @throws java.lang.Exception
     */
    public CharChunk toChars(final CharChunk cc, final Charset encoding)
            throws CharConversionException {

        cc.allocate(getLength(), -1);

        if (UTF8_CHARSET.equals(encoding)) {
            try {
                UTF8_DECODER.convert(this, cc);
            } catch (IOException e) {
                if (!(e instanceof CharConversionException)) {
                    throw new CharConversionException();
                }

                throw (CharConversionException) e;
            }
//            uri.setChars(cc.getChars(), cc.getStart(), cc.getEnd());
            return cc;
        } else if (!Constants.DEFAULT_CHARACTER_ENCODING.equalsIgnoreCase(encoding.name())) {
            final ByteBuffer bb = buffer.toByteBuffer(start, end);
            final char[] ccBuf = cc.getChars();
            final int ccStart = cc.getStart();
            final CharBuffer cb = CharBuffer.wrap(ccBuf, ccStart, ccBuf.length - ccStart);

            final CharsetDecoder decoder = encoding.newDecoder();
            final CoderResult cr = decoder.decode(bb, cb, true);

            if (cr != CoderResult.UNDERFLOW) {
                throw new CharConversionException("Decoding error");
            }

            cc.setEnd(cb.position());
//            uri.setChars(cc.getChars(), cc.getStart(), cc.getEnd());

            return cc;
        }

        // Default encoding: fast conversion
        final char[] cbuf = cc.getChars();
        for (int i = 0; i < getLength(); i++) {
            cbuf[i] = (char) (buffer.get(i + start) & 0xff);
        }

        return cc;
//        uri.setChars(cbuf, 0, bc.getLength());
    }
}
