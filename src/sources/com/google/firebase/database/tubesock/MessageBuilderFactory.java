package com.google.firebase.database.tubesock;

import java.nio.ByteBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.CodingErrorAction;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes4.dex */
class MessageBuilderFactory {

    interface Builder {
        boolean appendBytes(byte[] bArr);

        WebSocketMessage toMessage();
    }

    MessageBuilderFactory() {
    }

    static class BinaryBuilder implements Builder {
        private int pendingByteCount = 0;
        private List<byte[]> pendingBytes = new ArrayList();

        BinaryBuilder() {
        }

        @Override // com.google.firebase.database.tubesock.MessageBuilderFactory.Builder
        public boolean appendBytes(byte[] bArr) {
            this.pendingBytes.add(bArr);
            this.pendingByteCount += bArr.length;
            return true;
        }

        @Override // com.google.firebase.database.tubesock.MessageBuilderFactory.Builder
        public WebSocketMessage toMessage() {
            byte[] bArr = new byte[this.pendingByteCount];
            int length = 0;
            for (int i = 0; i < this.pendingBytes.size(); i++) {
                byte[] bArr2 = this.pendingBytes.get(i);
                System.arraycopy(bArr2, 0, bArr, length, bArr2.length);
                length += bArr2.length;
            }
            return new WebSocketMessage(bArr);
        }
    }

    static class TextBuilder implements Builder {
        private static ThreadLocal<CharsetDecoder> localDecoder = new ThreadLocal<CharsetDecoder>() { // from class: com.google.firebase.database.tubesock.MessageBuilderFactory.TextBuilder.1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // java.lang.ThreadLocal
            public CharsetDecoder initialValue() {
                CharsetDecoder charsetDecoderNewDecoder = Charset.forName("UTF8").newDecoder();
                charsetDecoderNewDecoder.onMalformedInput(CodingErrorAction.REPORT);
                charsetDecoderNewDecoder.onUnmappableCharacter(CodingErrorAction.REPORT);
                return charsetDecoderNewDecoder;
            }
        };
        private static ThreadLocal<CharsetEncoder> localEncoder = new ThreadLocal<CharsetEncoder>() { // from class: com.google.firebase.database.tubesock.MessageBuilderFactory.TextBuilder.2
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // java.lang.ThreadLocal
            public CharsetEncoder initialValue() {
                CharsetEncoder charsetEncoderNewEncoder = Charset.forName("UTF8").newEncoder();
                charsetEncoderNewEncoder.onMalformedInput(CodingErrorAction.REPORT);
                charsetEncoderNewEncoder.onUnmappableCharacter(CodingErrorAction.REPORT);
                return charsetEncoderNewEncoder;
            }
        };
        private StringBuilder builder = new StringBuilder();
        private ByteBuffer carryOver;

        TextBuilder() {
        }

        @Override // com.google.firebase.database.tubesock.MessageBuilderFactory.Builder
        public boolean appendBytes(byte[] bArr) {
            String strDecodeString = decodeString(bArr);
            if (strDecodeString == null) {
                return false;
            }
            this.builder.append(strDecodeString);
            return true;
        }

        @Override // com.google.firebase.database.tubesock.MessageBuilderFactory.Builder
        public WebSocketMessage toMessage() {
            if (this.carryOver != null) {
                return null;
            }
            return new WebSocketMessage(this.builder.toString());
        }

        private String decodeString(byte[] bArr) {
            try {
                return localDecoder.get().decode(ByteBuffer.wrap(bArr)).toString();
            } catch (CharacterCodingException unused) {
                return null;
            }
        }

        /* JADX WARN: Code restructure failed: missing block: B:10:0x003a, code lost:
        
            if (r6.remaining() <= 0) goto L12;
         */
        /* JADX WARN: Code restructure failed: missing block: B:11:0x003c, code lost:
        
            r5.carryOver = r6;
         */
        /* JADX WARN: Code restructure failed: missing block: B:12:0x003e, code lost:
        
            com.google.firebase.database.tubesock.MessageBuilderFactory.TextBuilder.localEncoder.get().encode(java.nio.CharBuffer.wrap(r2));
            r2.flip();
         */
        /* JADX WARN: Code restructure failed: missing block: B:13:0x0054, code lost:
        
            return r2.toString();
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        private java.lang.String decodeStringStreaming(byte[] r6) throws java.nio.charset.CharacterCodingException {
            /*
                r5 = this;
                r0 = 0
                java.nio.ByteBuffer r6 = r5.getBuffer(r6)     // Catch: java.nio.charset.CharacterCodingException -> L6b
                int r1 = r6.remaining()     // Catch: java.nio.charset.CharacterCodingException -> L6b
                float r1 = (float) r1     // Catch: java.nio.charset.CharacterCodingException -> L6b
                java.lang.ThreadLocal<java.nio.charset.CharsetDecoder> r2 = com.google.firebase.database.tubesock.MessageBuilderFactory.TextBuilder.localDecoder     // Catch: java.nio.charset.CharacterCodingException -> L6b
                java.lang.Object r2 = r2.get()     // Catch: java.nio.charset.CharacterCodingException -> L6b
                java.nio.charset.CharsetDecoder r2 = (java.nio.charset.CharsetDecoder) r2     // Catch: java.nio.charset.CharacterCodingException -> L6b
                float r2 = r2.averageCharsPerByte()     // Catch: java.nio.charset.CharacterCodingException -> L6b
                float r1 = r1 * r2
                int r1 = (int) r1     // Catch: java.nio.charset.CharacterCodingException -> L6b
                java.nio.CharBuffer r2 = java.nio.CharBuffer.allocate(r1)     // Catch: java.nio.charset.CharacterCodingException -> L6b
            L1c:
                java.lang.ThreadLocal<java.nio.charset.CharsetDecoder> r3 = com.google.firebase.database.tubesock.MessageBuilderFactory.TextBuilder.localDecoder     // Catch: java.nio.charset.CharacterCodingException -> L6b
                java.lang.Object r3 = r3.get()     // Catch: java.nio.charset.CharacterCodingException -> L6b
                java.nio.charset.CharsetDecoder r3 = (java.nio.charset.CharsetDecoder) r3     // Catch: java.nio.charset.CharacterCodingException -> L6b
                r4 = 0
                java.nio.charset.CoderResult r3 = r3.decode(r6, r2, r4)     // Catch: java.nio.charset.CharacterCodingException -> L6b
                boolean r4 = r3.isError()     // Catch: java.nio.charset.CharacterCodingException -> L6b
                if (r4 == 0) goto L30
                return r0
            L30:
                boolean r4 = r3.isUnderflow()     // Catch: java.nio.charset.CharacterCodingException -> L6b
                if (r4 == 0) goto L55
                int r1 = r6.remaining()     // Catch: java.nio.charset.CharacterCodingException -> L6b
                if (r1 <= 0) goto L3e
                r5.carryOver = r6     // Catch: java.nio.charset.CharacterCodingException -> L6b
            L3e:
                java.nio.CharBuffer r6 = java.nio.CharBuffer.wrap(r2)     // Catch: java.nio.charset.CharacterCodingException -> L6b
                java.lang.ThreadLocal<java.nio.charset.CharsetEncoder> r1 = com.google.firebase.database.tubesock.MessageBuilderFactory.TextBuilder.localEncoder     // Catch: java.nio.charset.CharacterCodingException -> L6b
                java.lang.Object r1 = r1.get()     // Catch: java.nio.charset.CharacterCodingException -> L6b
                java.nio.charset.CharsetEncoder r1 = (java.nio.charset.CharsetEncoder) r1     // Catch: java.nio.charset.CharacterCodingException -> L6b
                r1.encode(r6)     // Catch: java.nio.charset.CharacterCodingException -> L6b
                r2.flip()     // Catch: java.nio.charset.CharacterCodingException -> L6b
                java.lang.String r6 = r2.toString()     // Catch: java.nio.charset.CharacterCodingException -> L6b
                return r6
            L55:
                boolean r3 = r3.isOverflow()     // Catch: java.nio.charset.CharacterCodingException -> L6b
                if (r3 == 0) goto L1c
                int r1 = r1 * 2
                int r1 = r1 + 1
                java.nio.CharBuffer r3 = java.nio.CharBuffer.allocate(r1)     // Catch: java.nio.charset.CharacterCodingException -> L6b
                r2.flip()     // Catch: java.nio.charset.CharacterCodingException -> L6b
                r3.put(r2)     // Catch: java.nio.charset.CharacterCodingException -> L6b
                r2 = r3
                goto L1c
            L6b:
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.database.tubesock.MessageBuilderFactory.TextBuilder.decodeStringStreaming(byte[]):java.lang.String");
        }

        private ByteBuffer getBuffer(byte[] bArr) {
            ByteBuffer byteBuffer = this.carryOver;
            if (byteBuffer != null) {
                ByteBuffer byteBufferAllocate = ByteBuffer.allocate(bArr.length + byteBuffer.remaining());
                byteBufferAllocate.put(this.carryOver);
                this.carryOver = null;
                byteBufferAllocate.put(bArr);
                byteBufferAllocate.flip();
                return byteBufferAllocate;
            }
            return ByteBuffer.wrap(bArr);
        }
    }

    static Builder builder(byte b) {
        if (b == 2) {
            return new BinaryBuilder();
        }
        return new TextBuilder();
    }
}
