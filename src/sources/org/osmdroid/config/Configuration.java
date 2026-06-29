package org.osmdroid.config;

/* loaded from: classes3.dex */
public class Configuration {
    private static IConfigurationProvider ref;

    public static synchronized IConfigurationProvider getInstance() {
        if (ref == null) {
            ref = new DefaultConfigurationProvider();
        }
        return ref;
    }

    public static void setConfigurationProvider(IConfigurationProvider iConfigurationProvider) {
        ref = iConfigurationProvider;
    }
}
