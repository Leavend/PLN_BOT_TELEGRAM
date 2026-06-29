package org.bouncycastle.jce.provider;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Principal;
import java.security.cert.CertPathBuilderException;
import java.security.cert.CertPathBuilderResult;
import java.security.cert.CertPathBuilderSpi;
import java.security.cert.CertPathParameters;
import java.security.cert.CertificateException;
import java.security.cert.PKIXBuilderParameters;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import javax.security.auth.x500.X500Principal;
import org.bouncycastle.jce.exception.ExtCertPathBuilderException;
import org.bouncycastle.util.Selector;
import org.bouncycastle.x509.ExtendedPKIXBuilderParameters;
import org.bouncycastle.x509.X509AttributeCertStoreSelector;
import org.bouncycastle.x509.X509AttributeCertificate;
import org.bouncycastle.x509.X509CertStoreSelector;

/* loaded from: classes3.dex */
public class PKIXAttrCertPathBuilderSpi extends CertPathBuilderSpi {
    private Exception certPathException;

    /* JADX WARN: Removed duplicated region for block: B:51:0x00d5  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private java.security.cert.CertPathBuilderResult build(org.bouncycastle.x509.X509AttributeCertificate r6, java.security.cert.X509Certificate r7, org.bouncycastle.x509.ExtendedPKIXBuilderParameters r8, java.util.List r9) throws java.security.NoSuchAlgorithmException, org.bouncycastle.jce.provider.AnnotatedException, java.security.cert.CertificateException, java.security.NoSuchProviderException {
        /*
            r5 = this;
            java.lang.String r0 = "BC"
            boolean r1 = r9.contains(r7)
            r2 = 0
            if (r1 == 0) goto La
            return r2
        La:
            java.util.Set r1 = r8.getExcludedCerts()
            boolean r1 = r1.contains(r7)
            if (r1 == 0) goto L15
            return r2
        L15:
            int r1 = r8.getMaxPathLength()
            r3 = -1
            if (r1 == r3) goto L29
            int r1 = r9.size()
            int r1 = r1 + (-1)
            int r3 = r8.getMaxPathLength()
            if (r1 <= r3) goto L29
            return r2
        L29:
            r9.add(r7)
            java.lang.String r1 = "X.509"
            java.security.cert.CertificateFactory r1 = java.security.cert.CertificateFactory.getInstance(r1, r0)     // Catch: java.lang.Exception -> Ld9
            java.lang.String r3 = "RFC3281"
            java.security.cert.CertPathValidator r0 = java.security.cert.CertPathValidator.getInstance(r3, r0)     // Catch: java.lang.Exception -> Ld9
            java.util.Set r3 = r8.getTrustAnchors()     // Catch: org.bouncycastle.jce.provider.AnnotatedException -> Lc9
            java.lang.String r4 = r8.getSigProvider()     // Catch: org.bouncycastle.jce.provider.AnnotatedException -> Lc9
            java.security.cert.TrustAnchor r3 = org.bouncycastle.jce.provider.CertPathValidatorUtilities.findTrustAnchor(r7, r3, r4)     // Catch: org.bouncycastle.jce.provider.AnnotatedException -> Lc9
            if (r3 == 0) goto L74
            java.security.cert.CertPath r6 = r1.generateCertPath(r9)     // Catch: java.lang.Exception -> L6b org.bouncycastle.jce.provider.AnnotatedException -> Lc9
            java.security.cert.CertPathValidatorResult r8 = r0.validate(r6, r8)     // Catch: java.lang.Exception -> L62 org.bouncycastle.jce.provider.AnnotatedException -> Lc9
            java.security.cert.PKIXCertPathValidatorResult r8 = (java.security.cert.PKIXCertPathValidatorResult) r8     // Catch: java.lang.Exception -> L62 org.bouncycastle.jce.provider.AnnotatedException -> Lc9
            java.security.cert.PKIXCertPathBuilderResult r0 = new java.security.cert.PKIXCertPathBuilderResult     // Catch: org.bouncycastle.jce.provider.AnnotatedException -> Lc9
            java.security.cert.TrustAnchor r1 = r8.getTrustAnchor()     // Catch: org.bouncycastle.jce.provider.AnnotatedException -> Lc9
            java.security.cert.PolicyNode r3 = r8.getPolicyTree()     // Catch: org.bouncycastle.jce.provider.AnnotatedException -> Lc9
            java.security.PublicKey r8 = r8.getPublicKey()     // Catch: org.bouncycastle.jce.provider.AnnotatedException -> Lc9
            r0.<init>(r6, r1, r3, r8)     // Catch: org.bouncycastle.jce.provider.AnnotatedException -> Lc9
            return r0
        L62:
            r6 = move-exception
            org.bouncycastle.jce.provider.AnnotatedException r8 = new org.bouncycastle.jce.provider.AnnotatedException     // Catch: org.bouncycastle.jce.provider.AnnotatedException -> Lc9
            java.lang.String r0 = "Certification path could not be validated."
            r8.<init>(r0, r6)     // Catch: org.bouncycastle.jce.provider.AnnotatedException -> Lc9
            throw r8     // Catch: org.bouncycastle.jce.provider.AnnotatedException -> Lc9
        L6b:
            r6 = move-exception
            org.bouncycastle.jce.provider.AnnotatedException r8 = new org.bouncycastle.jce.provider.AnnotatedException     // Catch: org.bouncycastle.jce.provider.AnnotatedException -> Lc9
            java.lang.String r0 = "Certification path could not be constructed from certificate list."
            r8.<init>(r0, r6)     // Catch: org.bouncycastle.jce.provider.AnnotatedException -> Lc9
            throw r8     // Catch: org.bouncycastle.jce.provider.AnnotatedException -> Lc9
        L74:
            org.bouncycastle.jce.provider.CertPathValidatorUtilities.addAdditionalStoresFromAltNames(r7, r8)     // Catch: java.security.cert.CertificateParsingException -> Lc0 org.bouncycastle.jce.provider.AnnotatedException -> Lc9
            java.util.HashSet r0 = new java.util.HashSet     // Catch: org.bouncycastle.jce.provider.AnnotatedException -> Lc9
            r0.<init>()     // Catch: org.bouncycastle.jce.provider.AnnotatedException -> Lc9
            java.util.Collection r1 = org.bouncycastle.jce.provider.CertPathValidatorUtilities.findIssuerCerts(r7, r8)     // Catch: org.bouncycastle.jce.provider.AnnotatedException -> Lb7
            r0.addAll(r1)     // Catch: org.bouncycastle.jce.provider.AnnotatedException -> Lb7
            boolean r1 = r0.isEmpty()     // Catch: org.bouncycastle.jce.provider.AnnotatedException -> Lc9
            if (r1 != 0) goto Laf
            java.util.Iterator r0 = r0.iterator()     // Catch: org.bouncycastle.jce.provider.AnnotatedException -> Lc9
        L8d:
            boolean r1 = r0.hasNext()     // Catch: org.bouncycastle.jce.provider.AnnotatedException -> Lc9
            if (r1 == 0) goto Ld3
            if (r2 != 0) goto Ld3
            java.lang.Object r1 = r0.next()     // Catch: org.bouncycastle.jce.provider.AnnotatedException -> Lc9
            java.security.cert.X509Certificate r1 = (java.security.cert.X509Certificate) r1     // Catch: org.bouncycastle.jce.provider.AnnotatedException -> Lc9
            javax.security.auth.x500.X500Principal r3 = r1.getIssuerX500Principal()     // Catch: org.bouncycastle.jce.provider.AnnotatedException -> Lc9
            javax.security.auth.x500.X500Principal r4 = r1.getSubjectX500Principal()     // Catch: org.bouncycastle.jce.provider.AnnotatedException -> Lc9
            boolean r3 = r3.equals(r4)     // Catch: org.bouncycastle.jce.provider.AnnotatedException -> Lc9
            if (r3 == 0) goto Laa
            goto L8d
        Laa:
            java.security.cert.CertPathBuilderResult r2 = r5.build(r6, r1, r8, r9)     // Catch: org.bouncycastle.jce.provider.AnnotatedException -> Lc9
            goto L8d
        Laf:
            org.bouncycastle.jce.provider.AnnotatedException r6 = new org.bouncycastle.jce.provider.AnnotatedException     // Catch: org.bouncycastle.jce.provider.AnnotatedException -> Lc9
            java.lang.String r8 = "No issuer certificate for certificate in certification path found."
            r6.<init>(r8)     // Catch: org.bouncycastle.jce.provider.AnnotatedException -> Lc9
            throw r6     // Catch: org.bouncycastle.jce.provider.AnnotatedException -> Lc9
        Lb7:
            r6 = move-exception
            org.bouncycastle.jce.provider.AnnotatedException r8 = new org.bouncycastle.jce.provider.AnnotatedException     // Catch: org.bouncycastle.jce.provider.AnnotatedException -> Lc9
            java.lang.String r0 = "Cannot find issuer certificate for certificate in certification path."
            r8.<init>(r0, r6)     // Catch: org.bouncycastle.jce.provider.AnnotatedException -> Lc9
            throw r8     // Catch: org.bouncycastle.jce.provider.AnnotatedException -> Lc9
        Lc0:
            r6 = move-exception
            org.bouncycastle.jce.provider.AnnotatedException r8 = new org.bouncycastle.jce.provider.AnnotatedException     // Catch: org.bouncycastle.jce.provider.AnnotatedException -> Lc9
            java.lang.String r0 = "No additional X.509 stores can be added from certificate locations."
            r8.<init>(r0, r6)     // Catch: org.bouncycastle.jce.provider.AnnotatedException -> Lc9
            throw r8     // Catch: org.bouncycastle.jce.provider.AnnotatedException -> Lc9
        Lc9:
            r6 = move-exception
            org.bouncycastle.jce.provider.AnnotatedException r8 = new org.bouncycastle.jce.provider.AnnotatedException
            java.lang.String r0 = "No valid certification path could be build."
            r8.<init>(r0, r6)
            r5.certPathException = r8
        Ld3:
            if (r2 != 0) goto Ld8
            r9.remove(r7)
        Ld8:
            return r2
        Ld9:
            java.lang.RuntimeException r6 = new java.lang.RuntimeException
            java.lang.String r7 = "Exception creating support classes."
            r6.<init>(r7)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.jce.provider.PKIXAttrCertPathBuilderSpi.build(org.bouncycastle.x509.X509AttributeCertificate, java.security.cert.X509Certificate, org.bouncycastle.x509.ExtendedPKIXBuilderParameters, java.util.List):java.security.cert.CertPathBuilderResult");
    }

    @Override // java.security.cert.CertPathBuilderSpi
    public CertPathBuilderResult engineBuild(CertPathParameters certPathParameters) throws CertPathBuilderException, NoSuchAlgorithmException, AnnotatedException, CertificateException, NoSuchProviderException, InvalidAlgorithmParameterException {
        if (!(certPathParameters instanceof PKIXBuilderParameters) && !(certPathParameters instanceof ExtendedPKIXBuilderParameters)) {
            throw new InvalidAlgorithmParameterException("Parameters must be an instance of " + PKIXBuilderParameters.class.getName() + " or " + ExtendedPKIXBuilderParameters.class.getName() + ".");
        }
        if (!(certPathParameters instanceof ExtendedPKIXBuilderParameters)) {
            certPathParameters = ExtendedPKIXBuilderParameters.getInstance((PKIXBuilderParameters) certPathParameters);
        }
        ExtendedPKIXBuilderParameters extendedPKIXBuilderParameters = (ExtendedPKIXBuilderParameters) certPathParameters;
        ArrayList arrayList = new ArrayList();
        Selector targetConstraints = extendedPKIXBuilderParameters.getTargetConstraints();
        if (!(targetConstraints instanceof X509AttributeCertStoreSelector)) {
            throw new CertPathBuilderException("TargetConstraints must be an instance of " + X509AttributeCertStoreSelector.class.getName() + " for " + getClass().getName() + " class.");
        }
        try {
            Collection collectionFindCertificates = CertPathValidatorUtilities.findCertificates((X509AttributeCertStoreSelector) targetConstraints, extendedPKIXBuilderParameters.getStores());
            if (collectionFindCertificates.isEmpty()) {
                throw new CertPathBuilderException("No attribute certificate found matching targetContraints.");
            }
            Iterator it = collectionFindCertificates.iterator();
            CertPathBuilderResult certPathBuilderResultBuild = null;
            while (it.hasNext() && certPathBuilderResultBuild == null) {
                X509AttributeCertificate x509AttributeCertificate = (X509AttributeCertificate) it.next();
                X509CertStoreSelector x509CertStoreSelector = new X509CertStoreSelector();
                Principal[] principals = x509AttributeCertificate.getIssuer().getPrincipals();
                HashSet hashSet = new HashSet();
                for (Principal principal : principals) {
                    try {
                        if (principal instanceof X500Principal) {
                            x509CertStoreSelector.setSubject(((X500Principal) principal).getEncoded());
                        }
                        hashSet.addAll(CertPathValidatorUtilities.findCertificates(x509CertStoreSelector, extendedPKIXBuilderParameters.getStores()));
                        hashSet.addAll(CertPathValidatorUtilities.findCertificates(x509CertStoreSelector, extendedPKIXBuilderParameters.getCertStores()));
                    } catch (IOException e) {
                        throw new ExtCertPathBuilderException("cannot encode X500Principal.", e);
                    } catch (AnnotatedException e2) {
                        throw new ExtCertPathBuilderException("Public key certificate for attribute certificate cannot be searched.", e2);
                    }
                }
                if (hashSet.isEmpty()) {
                    throw new CertPathBuilderException("Public key certificate for attribute certificate cannot be found.");
                }
                Iterator it2 = hashSet.iterator();
                while (it2.hasNext() && certPathBuilderResultBuild == null) {
                    certPathBuilderResultBuild = build(x509AttributeCertificate, (X509Certificate) it2.next(), extendedPKIXBuilderParameters, arrayList);
                }
            }
            if (certPathBuilderResultBuild == null && this.certPathException != null) {
                throw new ExtCertPathBuilderException("Possible certificate chain could not be validated.", this.certPathException);
            }
            if (certPathBuilderResultBuild == null && this.certPathException == null) {
                throw new CertPathBuilderException("Unable to find certificate chain.");
            }
            return certPathBuilderResultBuild;
        } catch (AnnotatedException e3) {
            throw new ExtCertPathBuilderException("Error finding target attribute certificate.", e3);
        }
    }
}
