package org.bouncycastle.jce.provider;

import java.math.BigInteger;
import java.security.AlgorithmParameters;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.InvalidParameterException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.InvalidParameterSpecException;
import java.security.spec.MGF1ParameterSpec;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.interfaces.DHKey;
import javax.crypto.spec.OAEPParameterSpec;
import javax.crypto.spec.PSource;
import org.bouncycastle.crypto.AsymmetricBlockCipher;
import org.bouncycastle.crypto.BufferedAsymmetricBlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.encodings.OAEPEncoding;
import org.bouncycastle.crypto.encodings.PKCS1Encoding;
import org.bouncycastle.crypto.engines.ElGamalEngine;
import org.bouncycastle.crypto.params.ParametersWithRandom;
import org.bouncycastle.jce.interfaces.ElGamalKey;
import org.bouncycastle.jce.interfaces.ElGamalPrivateKey;
import org.bouncycastle.jce.interfaces.ElGamalPublicKey;
import org.bouncycastle.util.Strings;

/* loaded from: classes3.dex */
public class JCEElGamalCipher extends WrapCipherSpi {
    private BufferedAsymmetricBlockCipher cipher;
    private AlgorithmParameters engineParams;
    private AlgorithmParameterSpec paramSpec;

    public static class NoPadding extends JCEElGamalCipher {
        public NoPadding() {
            super(new ElGamalEngine());
        }
    }

    public static class PKCS1v1_5Padding extends JCEElGamalCipher {
        public PKCS1v1_5Padding() {
            super(new PKCS1Encoding(new ElGamalEngine()));
        }
    }

    public JCEElGamalCipher(AsymmetricBlockCipher asymmetricBlockCipher) {
        this.cipher = new BufferedAsymmetricBlockCipher(asymmetricBlockCipher);
    }

    private void initFromSpec(OAEPParameterSpec oAEPParameterSpec) throws NoSuchPaddingException {
        MGF1ParameterSpec mGF1ParameterSpec = (MGF1ParameterSpec) oAEPParameterSpec.getMGFParameters();
        Digest digest = JCEDigestUtil.getDigest(mGF1ParameterSpec.getDigestAlgorithm());
        if (digest == null) {
            throw new NoSuchPaddingException("no match on OAEP constructor for digest algorithm: " + mGF1ParameterSpec.getDigestAlgorithm());
        }
        this.cipher = new BufferedAsymmetricBlockCipher(new OAEPEncoding(new ElGamalEngine(), digest, ((PSource.PSpecified) oAEPParameterSpec.getPSource()).getValue()));
        this.paramSpec = oAEPParameterSpec;
    }

    @Override // org.bouncycastle.jce.provider.WrapCipherSpi, javax.crypto.CipherSpi
    protected int engineDoFinal(byte[] bArr, int i, int i2, byte[] bArr2, int i3) throws BadPaddingException, IllegalBlockSizeException {
        this.cipher.processBytes(bArr, i, i2);
        try {
            byte[] bArrDoFinal = this.cipher.doFinal();
            for (int i4 = 0; i4 != bArrDoFinal.length; i4++) {
                bArr2[i3 + i4] = bArrDoFinal[i4];
            }
            return bArrDoFinal.length;
        } catch (InvalidCipherTextException e) {
            throw new BadPaddingException(e.getMessage());
        }
    }

    @Override // org.bouncycastle.jce.provider.WrapCipherSpi, javax.crypto.CipherSpi
    protected byte[] engineDoFinal(byte[] bArr, int i, int i2) throws BadPaddingException, IllegalBlockSizeException {
        this.cipher.processBytes(bArr, i, i2);
        try {
            return this.cipher.doFinal();
        } catch (InvalidCipherTextException e) {
            throw new BadPaddingException(e.getMessage());
        }
    }

    @Override // org.bouncycastle.jce.provider.WrapCipherSpi, javax.crypto.CipherSpi
    protected int engineGetBlockSize() {
        return this.cipher.getInputBlockSize();
    }

    @Override // org.bouncycastle.jce.provider.WrapCipherSpi, javax.crypto.CipherSpi
    protected byte[] engineGetIV() {
        return null;
    }

    @Override // org.bouncycastle.jce.provider.WrapCipherSpi, javax.crypto.CipherSpi
    protected int engineGetKeySize(Key key) {
        BigInteger p;
        if (key instanceof ElGamalKey) {
            p = ((ElGamalKey) key).getParameters().getP();
        } else {
            if (!(key instanceof DHKey)) {
                throw new IllegalArgumentException("not an ElGamal key!");
            }
            p = ((DHKey) key).getParams().getP();
        }
        return p.bitLength();
    }

    @Override // org.bouncycastle.jce.provider.WrapCipherSpi, javax.crypto.CipherSpi
    protected int engineGetOutputSize(int i) {
        return this.cipher.getOutputBlockSize();
    }

    @Override // org.bouncycastle.jce.provider.WrapCipherSpi, javax.crypto.CipherSpi
    protected AlgorithmParameters engineGetParameters() throws NoSuchAlgorithmException, InvalidParameterSpecException, NoSuchProviderException {
        if (this.engineParams == null && this.paramSpec != null) {
            try {
                AlgorithmParameters algorithmParameters = AlgorithmParameters.getInstance("OAEP", "BC");
                this.engineParams = algorithmParameters;
                algorithmParameters.init(this.paramSpec);
            } catch (Exception e) {
                throw new RuntimeException(e.toString());
            }
        }
        return this.engineParams;
    }

    @Override // org.bouncycastle.jce.provider.WrapCipherSpi, javax.crypto.CipherSpi
    protected void engineInit(int i, Key key, AlgorithmParameters algorithmParameters, SecureRandom secureRandom) throws InvalidKeyException, InvalidAlgorithmParameterException {
        throw new InvalidAlgorithmParameterException("can't handle parameters in ElGamal");
    }

    @Override // org.bouncycastle.jce.provider.WrapCipherSpi, javax.crypto.CipherSpi
    protected void engineInit(int i, Key key, SecureRandom secureRandom) throws InvalidKeyException {
        engineInit(i, key, (AlgorithmParameterSpec) null, secureRandom);
    }

    @Override // org.bouncycastle.jce.provider.WrapCipherSpi, javax.crypto.CipherSpi
    protected void engineInit(int i, Key key, AlgorithmParameterSpec algorithmParameterSpec, SecureRandom secureRandom) throws InvalidKeyException {
        CipherParameters cipherParametersGeneratePrivateKeyParameter;
        BufferedAsymmetricBlockCipher bufferedAsymmetricBlockCipher;
        if (algorithmParameterSpec != null) {
            throw new IllegalArgumentException("unknown parameter type.");
        }
        if (key instanceof ElGamalPublicKey) {
            cipherParametersGeneratePrivateKeyParameter = ElGamalUtil.generatePublicKeyParameter((PublicKey) key);
        } else {
            if (!(key instanceof ElGamalPrivateKey)) {
                throw new InvalidKeyException("unknown key type passed to ElGamal");
            }
            cipherParametersGeneratePrivateKeyParameter = ElGamalUtil.generatePrivateKeyParameter((PrivateKey) key);
        }
        if (secureRandom != null) {
            cipherParametersGeneratePrivateKeyParameter = new ParametersWithRandom(cipherParametersGeneratePrivateKeyParameter, secureRandom);
        }
        boolean z = true;
        if (i == 1) {
            bufferedAsymmetricBlockCipher = this.cipher;
        } else {
            if (i != 2) {
                if (i != 3) {
                    if (i != 4) {
                        throw new InvalidParameterException("unknown opmode " + i + " passed to ElGamal");
                    }
                }
                bufferedAsymmetricBlockCipher = this.cipher;
            }
            bufferedAsymmetricBlockCipher = this.cipher;
            z = false;
        }
        bufferedAsymmetricBlockCipher.init(z, cipherParametersGeneratePrivateKeyParameter);
    }

    @Override // org.bouncycastle.jce.provider.WrapCipherSpi, javax.crypto.CipherSpi
    protected void engineSetMode(String str) throws NoSuchAlgorithmException {
        String upperCase = Strings.toUpperCase(str);
        if (!upperCase.equals("NONE") && !upperCase.equals("ECB")) {
            throw new NoSuchAlgorithmException("can't support mode " + str);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0052  */
    @Override // org.bouncycastle.jce.provider.WrapCipherSpi, javax.crypto.CipherSpi
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected void engineSetPadding(java.lang.String r5) throws javax.crypto.NoSuchPaddingException {
        /*
            r4 = this;
            java.lang.String r0 = org.bouncycastle.util.Strings.toUpperCase(r5)
            java.lang.String r1 = "NOPADDING"
            boolean r1 = r0.equals(r1)
            if (r1 == 0) goto L1a
            org.bouncycastle.crypto.BufferedAsymmetricBlockCipher r5 = new org.bouncycastle.crypto.BufferedAsymmetricBlockCipher
            org.bouncycastle.crypto.engines.ElGamalEngine r0 = new org.bouncycastle.crypto.engines.ElGamalEngine
            r0.<init>()
            r5.<init>(r0)
        L16:
            r4.cipher = r5
            goto Lce
        L1a:
            java.lang.String r1 = "PKCS1PADDING"
            boolean r1 = r0.equals(r1)
            if (r1 == 0) goto L32
            org.bouncycastle.crypto.BufferedAsymmetricBlockCipher r5 = new org.bouncycastle.crypto.BufferedAsymmetricBlockCipher
            org.bouncycastle.crypto.encodings.PKCS1Encoding r0 = new org.bouncycastle.crypto.encodings.PKCS1Encoding
            org.bouncycastle.crypto.engines.ElGamalEngine r1 = new org.bouncycastle.crypto.engines.ElGamalEngine
            r1.<init>()
            r0.<init>(r1)
            r5.<init>(r0)
            goto L16
        L32:
            java.lang.String r1 = "ISO9796-1PADDING"
            boolean r1 = r0.equals(r1)
            if (r1 == 0) goto L4a
            org.bouncycastle.crypto.BufferedAsymmetricBlockCipher r5 = new org.bouncycastle.crypto.BufferedAsymmetricBlockCipher
            org.bouncycastle.crypto.encodings.ISO9796d1Encoding r0 = new org.bouncycastle.crypto.encodings.ISO9796d1Encoding
            org.bouncycastle.crypto.engines.ElGamalEngine r1 = new org.bouncycastle.crypto.engines.ElGamalEngine
            r1.<init>()
            r0.<init>(r1)
            r5.<init>(r0)
            goto L16
        L4a:
            java.lang.String r1 = "OAEPPADDING"
            boolean r1 = r0.equals(r1)
            if (r1 == 0) goto L59
        L52:
            javax.crypto.spec.OAEPParameterSpec r5 = javax.crypto.spec.OAEPParameterSpec.DEFAULT
        L54:
            r4.initFromSpec(r5)
            goto Lce
        L59:
            java.lang.String r1 = "OAEPWITHMD5ANDMGF1PADDING"
            boolean r1 = r0.equals(r1)
            java.lang.String r2 = "MGF1"
            if (r1 == 0) goto L72
            javax.crypto.spec.OAEPParameterSpec r5 = new javax.crypto.spec.OAEPParameterSpec
            java.security.spec.MGF1ParameterSpec r0 = new java.security.spec.MGF1ParameterSpec
            java.lang.String r1 = "MD5"
            r0.<init>(r1)
            javax.crypto.spec.PSource$PSpecified r3 = javax.crypto.spec.PSource.PSpecified.DEFAULT
            r5.<init>(r1, r2, r0, r3)
            goto L54
        L72:
            java.lang.String r1 = "OAEPWITHSHA1ANDMGF1PADDING"
            boolean r1 = r0.equals(r1)
            if (r1 == 0) goto L7b
            goto L52
        L7b:
            java.lang.String r1 = "OAEPWITHSHA224ANDMGF1PADDING"
            boolean r1 = r0.equals(r1)
            if (r1 == 0) goto L92
            javax.crypto.spec.OAEPParameterSpec r5 = new javax.crypto.spec.OAEPParameterSpec
            java.security.spec.MGF1ParameterSpec r0 = new java.security.spec.MGF1ParameterSpec
            java.lang.String r1 = "SHA-224"
            r0.<init>(r1)
            javax.crypto.spec.PSource$PSpecified r3 = javax.crypto.spec.PSource.PSpecified.DEFAULT
            r5.<init>(r1, r2, r0, r3)
            goto L54
        L92:
            java.lang.String r1 = "OAEPWITHSHA256ANDMGF1PADDING"
            boolean r1 = r0.equals(r1)
            if (r1 == 0) goto La6
            javax.crypto.spec.OAEPParameterSpec r5 = new javax.crypto.spec.OAEPParameterSpec
            java.security.spec.MGF1ParameterSpec r0 = java.security.spec.MGF1ParameterSpec.SHA256
            javax.crypto.spec.PSource$PSpecified r1 = javax.crypto.spec.PSource.PSpecified.DEFAULT
            java.lang.String r3 = "SHA-256"
            r5.<init>(r3, r2, r0, r1)
            goto L54
        La6:
            java.lang.String r1 = "OAEPWITHSHA384ANDMGF1PADDING"
            boolean r1 = r0.equals(r1)
            if (r1 == 0) goto Lba
            javax.crypto.spec.OAEPParameterSpec r5 = new javax.crypto.spec.OAEPParameterSpec
            java.security.spec.MGF1ParameterSpec r0 = java.security.spec.MGF1ParameterSpec.SHA384
            javax.crypto.spec.PSource$PSpecified r1 = javax.crypto.spec.PSource.PSpecified.DEFAULT
            java.lang.String r3 = "SHA-384"
            r5.<init>(r3, r2, r0, r1)
            goto L54
        Lba:
            java.lang.String r1 = "OAEPWITHSHA512ANDMGF1PADDING"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto Lcf
            javax.crypto.spec.OAEPParameterSpec r5 = new javax.crypto.spec.OAEPParameterSpec
            java.security.spec.MGF1ParameterSpec r0 = java.security.spec.MGF1ParameterSpec.SHA512
            javax.crypto.spec.PSource$PSpecified r1 = javax.crypto.spec.PSource.PSpecified.DEFAULT
            java.lang.String r3 = "SHA-512"
            r5.<init>(r3, r2, r0, r1)
            goto L54
        Lce:
            return
        Lcf:
            javax.crypto.NoSuchPaddingException r0 = new javax.crypto.NoSuchPaddingException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.StringBuilder r5 = r1.append(r5)
            java.lang.String r1 = " unavailable with ElGamal."
            java.lang.StringBuilder r5 = r5.append(r1)
            java.lang.String r5 = r5.toString()
            r0.<init>(r5)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.jce.provider.JCEElGamalCipher.engineSetPadding(java.lang.String):void");
    }

    @Override // org.bouncycastle.jce.provider.WrapCipherSpi, javax.crypto.CipherSpi
    protected int engineUpdate(byte[] bArr, int i, int i2, byte[] bArr2, int i3) {
        this.cipher.processBytes(bArr, i, i2);
        return 0;
    }

    @Override // org.bouncycastle.jce.provider.WrapCipherSpi, javax.crypto.CipherSpi
    protected byte[] engineUpdate(byte[] bArr, int i, int i2) {
        this.cipher.processBytes(bArr, i, i2);
        return null;
    }
}
