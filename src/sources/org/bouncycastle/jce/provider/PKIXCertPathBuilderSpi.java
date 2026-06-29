package org.bouncycastle.jce.provider;

import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.cert.CertPathBuilderException;
import java.security.cert.CertPathBuilderResult;
import java.security.cert.CertPathBuilderSpi;
import java.security.cert.CertPathParameters;
import java.security.cert.CertificateException;
import java.security.cert.PKIXBuilderParameters;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import org.bouncycastle.jce.exception.ExtCertPathBuilderException;
import org.bouncycastle.util.Selector;
import org.bouncycastle.x509.ExtendedPKIXBuilderParameters;
import org.bouncycastle.x509.X509CertStoreSelector;

/* loaded from: classes3.dex */
public class PKIXCertPathBuilderSpi extends CertPathBuilderSpi {
    private Exception certPathException;

    /* JADX WARN: Removed duplicated region for block: B:48:0x00bf  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected java.security.cert.CertPathBuilderResult build(java.security.cert.X509Certificate r6, org.bouncycastle.x509.ExtendedPKIXBuilderParameters r7, java.util.List r8) throws java.security.NoSuchAlgorithmException, org.bouncycastle.jce.provider.AnnotatedException, java.security.cert.CertificateException, java.security.NoSuchProviderException {
        /*
            r5 = this;
            java.lang.String r0 = "BC"
            boolean r1 = r8.contains(r6)
            r2 = 0
            if (r1 == 0) goto La
            return r2
        La:
            java.util.Set r1 = r7.getExcludedCerts()
            boolean r1 = r1.contains(r6)
            if (r1 == 0) goto L15
            return r2
        L15:
            int r1 = r7.getMaxPathLength()
            r3 = -1
            if (r1 == r3) goto L29
            int r1 = r8.size()
            int r1 = r1 + (-1)
            int r3 = r7.getMaxPathLength()
            if (r1 <= r3) goto L29
            return r2
        L29:
            r8.add(r6)
            java.lang.String r1 = "X.509"
            java.security.cert.CertificateFactory r1 = java.security.cert.CertificateFactory.getInstance(r1, r0)     // Catch: java.lang.Exception -> Lc3
            java.lang.String r3 = "PKIX"
            java.security.cert.CertPathValidator r0 = java.security.cert.CertPathValidator.getInstance(r3, r0)     // Catch: java.lang.Exception -> Lc3
            java.util.Set r3 = r7.getTrustAnchors()     // Catch: org.bouncycastle.jce.provider.AnnotatedException -> Lba
            java.lang.String r4 = r7.getSigProvider()     // Catch: org.bouncycastle.jce.provider.AnnotatedException -> Lba
            java.security.cert.TrustAnchor r3 = org.bouncycastle.jce.provider.CertPathValidatorUtilities.findTrustAnchor(r6, r3, r4)     // Catch: org.bouncycastle.jce.provider.AnnotatedException -> Lba
            if (r3 == 0) goto L74
            java.security.cert.CertPath r1 = r1.generateCertPath(r8)     // Catch: java.lang.Exception -> L6b org.bouncycastle.jce.provider.AnnotatedException -> Lba
            java.security.cert.CertPathValidatorResult r7 = r0.validate(r1, r7)     // Catch: java.lang.Exception -> L62 org.bouncycastle.jce.provider.AnnotatedException -> Lba
            java.security.cert.PKIXCertPathValidatorResult r7 = (java.security.cert.PKIXCertPathValidatorResult) r7     // Catch: java.lang.Exception -> L62 org.bouncycastle.jce.provider.AnnotatedException -> Lba
            java.security.cert.PKIXCertPathBuilderResult r0 = new java.security.cert.PKIXCertPathBuilderResult     // Catch: org.bouncycastle.jce.provider.AnnotatedException -> Lba
            java.security.cert.TrustAnchor r3 = r7.getTrustAnchor()     // Catch: org.bouncycastle.jce.provider.AnnotatedException -> Lba
            java.security.cert.PolicyNode r4 = r7.getPolicyTree()     // Catch: org.bouncycastle.jce.provider.AnnotatedException -> Lba
            java.security.PublicKey r7 = r7.getPublicKey()     // Catch: org.bouncycastle.jce.provider.AnnotatedException -> Lba
            r0.<init>(r1, r3, r4, r7)     // Catch: org.bouncycastle.jce.provider.AnnotatedException -> Lba
            return r0
        L62:
            r7 = move-exception
            org.bouncycastle.jce.provider.AnnotatedException r0 = new org.bouncycastle.jce.provider.AnnotatedException     // Catch: org.bouncycastle.jce.provider.AnnotatedException -> Lba
            java.lang.String r1 = "Certification path could not be validated."
            r0.<init>(r1, r7)     // Catch: org.bouncycastle.jce.provider.AnnotatedException -> Lba
            throw r0     // Catch: org.bouncycastle.jce.provider.AnnotatedException -> Lba
        L6b:
            r7 = move-exception
            org.bouncycastle.jce.provider.AnnotatedException r0 = new org.bouncycastle.jce.provider.AnnotatedException     // Catch: org.bouncycastle.jce.provider.AnnotatedException -> Lba
            java.lang.String r1 = "Certification path could not be constructed from certificate list."
            r0.<init>(r1, r7)     // Catch: org.bouncycastle.jce.provider.AnnotatedException -> Lba
            throw r0     // Catch: org.bouncycastle.jce.provider.AnnotatedException -> Lba
        L74:
            org.bouncycastle.jce.provider.CertPathValidatorUtilities.addAdditionalStoresFromAltNames(r6, r7)     // Catch: java.security.cert.CertificateParsingException -> Lb1 org.bouncycastle.jce.provider.AnnotatedException -> Lba
            java.util.HashSet r0 = new java.util.HashSet     // Catch: org.bouncycastle.jce.provider.AnnotatedException -> Lba
            r0.<init>()     // Catch: org.bouncycastle.jce.provider.AnnotatedException -> Lba
            java.util.Collection r1 = org.bouncycastle.jce.provider.CertPathValidatorUtilities.findIssuerCerts(r6, r7)     // Catch: org.bouncycastle.jce.provider.AnnotatedException -> La8
            r0.addAll(r1)     // Catch: org.bouncycastle.jce.provider.AnnotatedException -> La8
            boolean r1 = r0.isEmpty()     // Catch: org.bouncycastle.jce.provider.AnnotatedException -> Lba
            if (r1 != 0) goto La0
            java.util.Iterator r0 = r0.iterator()     // Catch: org.bouncycastle.jce.provider.AnnotatedException -> Lba
        L8d:
            boolean r1 = r0.hasNext()     // Catch: org.bouncycastle.jce.provider.AnnotatedException -> Lba
            if (r1 == 0) goto Lbd
            if (r2 != 0) goto Lbd
            java.lang.Object r1 = r0.next()     // Catch: org.bouncycastle.jce.provider.AnnotatedException -> Lba
            java.security.cert.X509Certificate r1 = (java.security.cert.X509Certificate) r1     // Catch: org.bouncycastle.jce.provider.AnnotatedException -> Lba
            java.security.cert.CertPathBuilderResult r2 = r5.build(r1, r7, r8)     // Catch: org.bouncycastle.jce.provider.AnnotatedException -> Lba
            goto L8d
        La0:
            org.bouncycastle.jce.provider.AnnotatedException r7 = new org.bouncycastle.jce.provider.AnnotatedException     // Catch: org.bouncycastle.jce.provider.AnnotatedException -> Lba
            java.lang.String r0 = "No issuer certificate for certificate in certification path found."
            r7.<init>(r0)     // Catch: org.bouncycastle.jce.provider.AnnotatedException -> Lba
            throw r7     // Catch: org.bouncycastle.jce.provider.AnnotatedException -> Lba
        La8:
            r7 = move-exception
            org.bouncycastle.jce.provider.AnnotatedException r0 = new org.bouncycastle.jce.provider.AnnotatedException     // Catch: org.bouncycastle.jce.provider.AnnotatedException -> Lba
            java.lang.String r1 = "Cannot find issuer certificate for certificate in certification path."
            r0.<init>(r1, r7)     // Catch: org.bouncycastle.jce.provider.AnnotatedException -> Lba
            throw r0     // Catch: org.bouncycastle.jce.provider.AnnotatedException -> Lba
        Lb1:
            r7 = move-exception
            org.bouncycastle.jce.provider.AnnotatedException r0 = new org.bouncycastle.jce.provider.AnnotatedException     // Catch: org.bouncycastle.jce.provider.AnnotatedException -> Lba
            java.lang.String r1 = "No additiontal X.509 stores can be added from certificate locations."
            r0.<init>(r1, r7)     // Catch: org.bouncycastle.jce.provider.AnnotatedException -> Lba
            throw r0     // Catch: org.bouncycastle.jce.provider.AnnotatedException -> Lba
        Lba:
            r7 = move-exception
            r5.certPathException = r7
        Lbd:
            if (r2 != 0) goto Lc2
            r8.remove(r6)
        Lc2:
            return r2
        Lc3:
            java.lang.RuntimeException r6 = new java.lang.RuntimeException
            java.lang.String r7 = "Exception creating support classes."
            r6.<init>(r7)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.jce.provider.PKIXCertPathBuilderSpi.build(java.security.cert.X509Certificate, org.bouncycastle.x509.ExtendedPKIXBuilderParameters, java.util.List):java.security.cert.CertPathBuilderResult");
    }

    @Override // java.security.cert.CertPathBuilderSpi
    public CertPathBuilderResult engineBuild(CertPathParameters certPathParameters) throws CertPathBuilderException, NoSuchAlgorithmException, AnnotatedException, CertificateException, NoSuchProviderException, InvalidAlgorithmParameterException {
        Exception exc;
        if (!(certPathParameters instanceof PKIXBuilderParameters) && !(certPathParameters instanceof ExtendedPKIXBuilderParameters)) {
            throw new InvalidAlgorithmParameterException("Parameters must be an instance of " + PKIXBuilderParameters.class.getName() + " or " + ExtendedPKIXBuilderParameters.class.getName() + ".");
        }
        if (!(certPathParameters instanceof ExtendedPKIXBuilderParameters)) {
            certPathParameters = ExtendedPKIXBuilderParameters.getInstance((PKIXBuilderParameters) certPathParameters);
        }
        ExtendedPKIXBuilderParameters extendedPKIXBuilderParameters = (ExtendedPKIXBuilderParameters) certPathParameters;
        ArrayList arrayList = new ArrayList();
        Selector targetConstraints = extendedPKIXBuilderParameters.getTargetConstraints();
        if (!(targetConstraints instanceof X509CertStoreSelector)) {
            throw new CertPathBuilderException("TargetConstraints must be an instance of " + X509CertStoreSelector.class.getName() + " for " + getClass().getName() + " class.");
        }
        try {
            Collection collectionFindCertificates = CertPathValidatorUtilities.findCertificates((X509CertStoreSelector) targetConstraints, extendedPKIXBuilderParameters.getStores());
            collectionFindCertificates.addAll(CertPathValidatorUtilities.findCertificates((X509CertStoreSelector) targetConstraints, extendedPKIXBuilderParameters.getCertStores()));
            if (collectionFindCertificates.isEmpty()) {
                throw new CertPathBuilderException("No certificate found matching targetContraints.");
            }
            Iterator it = collectionFindCertificates.iterator();
            CertPathBuilderResult certPathBuilderResultBuild = null;
            while (it.hasNext() && certPathBuilderResultBuild == null) {
                certPathBuilderResultBuild = build((X509Certificate) it.next(), extendedPKIXBuilderParameters, arrayList);
            }
            if (certPathBuilderResultBuild == null && (exc = this.certPathException) != null) {
                if (exc instanceof AnnotatedException) {
                    throw new CertPathBuilderException(this.certPathException.getMessage(), this.certPathException.getCause());
                }
                throw new CertPathBuilderException("Possible certificate chain could not be validated.", this.certPathException);
            }
            if (certPathBuilderResultBuild == null && this.certPathException == null) {
                throw new CertPathBuilderException("Unable to find certificate chain.");
            }
            return certPathBuilderResultBuild;
        } catch (AnnotatedException e) {
            throw new ExtCertPathBuilderException("Error finding target certificate.", e);
        }
    }
}
