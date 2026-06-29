package com.google.firebase.database;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.Transaction;
import com.google.firebase.database.core.CompoundWrite;
import com.google.firebase.database.core.DatabaseConfig;
import com.google.firebase.database.core.Path;
import com.google.firebase.database.core.Repo;
import com.google.firebase.database.core.RepoManager;
import com.google.firebase.database.core.ValidationPath;
import com.google.firebase.database.core.utilities.Pair;
import com.google.firebase.database.core.utilities.ParsedUrl;
import com.google.firebase.database.core.utilities.PushIdGenerator;
import com.google.firebase.database.core.utilities.Utilities;
import com.google.firebase.database.core.utilities.Validation;
import com.google.firebase.database.core.utilities.encoding.CustomClassMapper;
import com.google.firebase.database.snapshot.ChildKey;
import com.google.firebase.database.snapshot.Node;
import com.google.firebase.database.snapshot.NodeUtilities;
import com.google.firebase.database.snapshot.PriorityUtilities;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;
import net.lingala.zip4j.util.InternalZipConstants;

/* loaded from: classes4.dex */
public class DatabaseReference extends Query {
    private static DatabaseConfig defaultConfig;

    public interface CompletionListener {
        void onComplete(DatabaseError databaseError, DatabaseReference databaseReference);
    }

    DatabaseReference(Repo repo, Path path) {
        super(repo, path);
    }

    DatabaseReference(String str, DatabaseConfig databaseConfig) {
        this(Utilities.parseUrl(str), databaseConfig);
    }

    private DatabaseReference(ParsedUrl parsedUrl, DatabaseConfig databaseConfig) {
        this(RepoManager.getRepo(databaseConfig, parsedUrl.repoInfo), parsedUrl.path);
    }

    public DatabaseReference child(String str) throws DatabaseException {
        if (str == null) {
            throw new NullPointerException("Can't pass null for argument 'pathString' in child()");
        }
        if (getPath().isEmpty()) {
            Validation.validateRootPathString(str);
        } else {
            Validation.validatePathString(str);
        }
        return new DatabaseReference(this.repo, getPath().child(new Path(str)));
    }

    public DatabaseReference push() {
        return new DatabaseReference(this.repo, getPath().child(ChildKey.fromString(PushIdGenerator.generatePushChildName(this.repo.getServerTime()))));
    }

    public Task<Void> setValue(Object obj) {
        return setValueInternal(obj, PriorityUtilities.parsePriority(this.path, null), null);
    }

    public Task<Void> setValue(Object obj, Object obj2) {
        return setValueInternal(obj, PriorityUtilities.parsePriority(this.path, obj2), null);
    }

    public void setValue(Object obj, CompletionListener completionListener) throws DatabaseException {
        setValueInternal(obj, PriorityUtilities.parsePriority(this.path, null), completionListener);
    }

    public void setValue(Object obj, Object obj2, CompletionListener completionListener) throws DatabaseException {
        setValueInternal(obj, PriorityUtilities.parsePriority(this.path, obj2), completionListener);
    }

    private Task<Void> setValueInternal(Object obj, Node node, CompletionListener completionListener) throws DatabaseException {
        Validation.validateWritablePath(getPath());
        ValidationPath.validateWithObject(getPath(), obj);
        Object objConvertToPlainJavaTypes = CustomClassMapper.convertToPlainJavaTypes(obj);
        Validation.validateWritableObject(objConvertToPlainJavaTypes);
        final Node nodeNodeFromJSON = NodeUtilities.NodeFromJSON(objConvertToPlainJavaTypes, node);
        final Pair<Task<Void>, CompletionListener> pairWrapOnComplete = Utilities.wrapOnComplete(completionListener);
        this.repo.scheduleNow(new Runnable() { // from class: com.google.firebase.database.DatabaseReference.1
            @Override // java.lang.Runnable
            public void run() {
                DatabaseReference.this.repo.setValue(DatabaseReference.this.getPath(), nodeNodeFromJSON, (CompletionListener) pairWrapOnComplete.getSecond());
            }
        });
        return pairWrapOnComplete.getFirst();
    }

    public Task<Void> setPriority(Object obj) {
        return setPriorityInternal(PriorityUtilities.parsePriority(this.path, obj), null);
    }

    public void setPriority(Object obj, CompletionListener completionListener) throws DatabaseException {
        setPriorityInternal(PriorityUtilities.parsePriority(this.path, obj), completionListener);
    }

    private Task<Void> setPriorityInternal(final Node node, CompletionListener completionListener) throws DatabaseException {
        Validation.validateWritablePath(getPath());
        final Pair<Task<Void>, CompletionListener> pairWrapOnComplete = Utilities.wrapOnComplete(completionListener);
        this.repo.scheduleNow(new Runnable() { // from class: com.google.firebase.database.DatabaseReference.2
            @Override // java.lang.Runnable
            public void run() {
                DatabaseReference.this.repo.setValue(DatabaseReference.this.getPath().child(ChildKey.getPriorityKey()), node, (CompletionListener) pairWrapOnComplete.getSecond());
            }
        });
        return pairWrapOnComplete.getFirst();
    }

    public Task<Void> updateChildren(Map<String, Object> map) {
        return updateChildrenInternal(map, null);
    }

    public void updateChildren(Map<String, Object> map, CompletionListener completionListener) {
        updateChildrenInternal(map, completionListener);
    }

    private Task<Void> updateChildrenInternal(Map<String, Object> map, CompletionListener completionListener) {
        if (map == null) {
            throw new NullPointerException("Can't pass null for argument 'update' in updateChildren()");
        }
        final Map<String, Object> mapConvertToPlainJavaTypes = CustomClassMapper.convertToPlainJavaTypes(map);
        final CompoundWrite compoundWriteFromPathMerge = CompoundWrite.fromPathMerge(Validation.parseAndValidateUpdate(getPath(), mapConvertToPlainJavaTypes));
        final Pair<Task<Void>, CompletionListener> pairWrapOnComplete = Utilities.wrapOnComplete(completionListener);
        this.repo.scheduleNow(new Runnable() { // from class: com.google.firebase.database.DatabaseReference.3
            @Override // java.lang.Runnable
            public void run() {
                DatabaseReference.this.repo.updateChildren(DatabaseReference.this.getPath(), compoundWriteFromPathMerge, (CompletionListener) pairWrapOnComplete.getSecond(), mapConvertToPlainJavaTypes);
            }
        });
        return pairWrapOnComplete.getFirst();
    }

    public Task<Void> removeValue() {
        return setValue(null);
    }

    public void removeValue(CompletionListener completionListener) throws DatabaseException {
        setValue((Object) null, completionListener);
    }

    public OnDisconnect onDisconnect() throws DatabaseException {
        Validation.validateWritablePath(getPath());
        return new OnDisconnect(this.repo, getPath());
    }

    public void runTransaction(Transaction.Handler handler) throws DatabaseException {
        runTransaction(handler, true);
    }

    public void runTransaction(final Transaction.Handler handler, final boolean z) throws DatabaseException {
        if (handler == null) {
            throw new NullPointerException("Can't pass null for argument 'handler' in runTransaction()");
        }
        Validation.validateWritablePath(getPath());
        this.repo.scheduleNow(new Runnable() { // from class: com.google.firebase.database.DatabaseReference.4
            @Override // java.lang.Runnable
            public void run() {
                DatabaseReference.this.repo.startTransaction(DatabaseReference.this.getPath(), handler, z);
            }
        });
    }

    public static void goOffline() {
        goOffline(getDefaultConfig());
    }

    static void goOffline(DatabaseConfig databaseConfig) {
        RepoManager.interrupt(databaseConfig);
    }

    public static void goOnline() {
        goOnline(getDefaultConfig());
    }

    static void goOnline(DatabaseConfig databaseConfig) {
        RepoManager.resume(databaseConfig);
    }

    public FirebaseDatabase getDatabase() {
        return this.repo.getDatabase();
    }

    public String toString() {
        DatabaseReference parent = getParent();
        if (parent == null) {
            return this.repo.toString();
        }
        try {
            return parent.toString() + InternalZipConstants.ZIP_FILE_SEPARATOR + URLEncoder.encode(getKey(), "UTF-8").replace("+", "%20");
        } catch (UnsupportedEncodingException e) {
            throw new DatabaseException("Failed to URLEncode key: " + getKey(), e);
        }
    }

    public DatabaseReference getParent() {
        Path parent = getPath().getParent();
        if (parent != null) {
            return new DatabaseReference(this.repo, parent);
        }
        return null;
    }

    public DatabaseReference getRoot() {
        return new DatabaseReference(this.repo, new Path(""));
    }

    public String getKey() {
        if (getPath().isEmpty()) {
            return null;
        }
        return getPath().getBack().asString();
    }

    public boolean equals(Object obj) {
        return (obj instanceof DatabaseReference) && toString().equals(obj.toString());
    }

    public int hashCode() {
        return toString().hashCode();
    }

    void setHijackHash(final boolean z) {
        this.repo.scheduleNow(new Runnable() { // from class: com.google.firebase.database.DatabaseReference.5
            @Override // java.lang.Runnable
            public void run() {
                DatabaseReference.this.repo.setHijackHash(z);
            }
        });
    }

    private static synchronized DatabaseConfig getDefaultConfig() {
        if (defaultConfig == null) {
            defaultConfig = new DatabaseConfig();
        }
        return defaultConfig;
    }
}
