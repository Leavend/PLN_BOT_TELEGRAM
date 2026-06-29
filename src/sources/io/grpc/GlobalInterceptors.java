package io.grpc;

import io.grpc.ServerStreamTracer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: classes2.dex */
final class GlobalInterceptors {
    private static boolean isGlobalInterceptorsTracersGet;
    private static boolean isGlobalInterceptorsTracersSet;
    private static List<ClientInterceptor> clientInterceptors = Collections.emptyList();
    private static List<ServerInterceptor> serverInterceptors = Collections.emptyList();
    private static List<ServerStreamTracer.Factory> serverStreamTracerFactories = Collections.emptyList();

    private GlobalInterceptors() {
    }

    static synchronized void setInterceptorsTracers(List<ClientInterceptor> list, List<ServerInterceptor> list2, List<ServerStreamTracer.Factory> list3) {
        if (isGlobalInterceptorsTracersGet) {
            throw new IllegalStateException("Set cannot be called after any get call");
        }
        if (isGlobalInterceptorsTracersSet) {
            throw new IllegalStateException("Global interceptors and tracers are already set");
        }
        if (list != null) {
            clientInterceptors = Collections.unmodifiableList(new ArrayList(list));
        }
        if (list2 != null) {
            serverInterceptors = Collections.unmodifiableList(new ArrayList(list2));
        }
        if (list3 != null) {
            serverStreamTracerFactories = Collections.unmodifiableList(new ArrayList(list3));
        }
        isGlobalInterceptorsTracersSet = true;
    }

    static synchronized List<ClientInterceptor> getClientInterceptors() {
        isGlobalInterceptorsTracersGet = true;
        return clientInterceptors;
    }

    static synchronized List<ServerInterceptor> getServerInterceptors() {
        isGlobalInterceptorsTracersGet = true;
        return serverInterceptors;
    }

    static synchronized List<ServerStreamTracer.Factory> getServerStreamTracerFactories() {
        isGlobalInterceptorsTracersGet = true;
        return serverStreamTracerFactories;
    }
}
