package com.google.android.material.color.utilities;

import com.google.firebase.remoteconfig.FirebaseRemoteConfig;

/* loaded from: classes4.dex */
public class SchemeMonochrome extends DynamicScheme {
    public SchemeMonochrome(Hct hct, boolean z, double d) {
        super(hct, Variant.MONOCHROME, z, d, TonalPalette.fromHueAndChroma(hct.getHue(), FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE), TonalPalette.fromHueAndChroma(hct.getHue(), FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE), TonalPalette.fromHueAndChroma(hct.getHue(), FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE), TonalPalette.fromHueAndChroma(hct.getHue(), FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE), TonalPalette.fromHueAndChroma(hct.getHue(), FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE));
    }
}
