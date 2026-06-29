package com.google.firebase.database.core.utilities.tuple;

import com.google.firebase.database.core.Path;

/* loaded from: classes4.dex */
public class PathAndId {

    /* renamed from: id, reason: collision with root package name */
    private long f90id;
    private Path path;

    public PathAndId(Path path, long j) {
        this.path = path;
        this.f90id = j;
    }

    public Path getPath() {
        return this.path;
    }

    public long getId() {
        return this.f90id;
    }
}
