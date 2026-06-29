package com.google.api;

import com.google.api.JwtLocation;
import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;

/* loaded from: classes4.dex */
public interface JwtLocationOrBuilder extends MessageLiteOrBuilder {
    String getHeader();

    ByteString getHeaderBytes();

    JwtLocation.InCase getInCase();

    String getQuery();

    ByteString getQueryBytes();

    String getValuePrefix();

    ByteString getValuePrefixBytes();
}
