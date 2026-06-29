package org.apache.http.impl.conn;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogConfigurationException;
import org.apache.commons.logging.LogFactory;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.ClientConnectionOperator;
import org.apache.http.conn.ClientConnectionRequest;
import org.apache.http.conn.ConnectionPoolTimeoutException;
import org.apache.http.conn.DnsResolver;
import org.apache.http.conn.ManagedClientConnection;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.pool.ConnPoolControl;
import org.apache.http.pool.PoolStats;
import org.apache.http.util.Args;
import org.apache.http.util.Asserts;

@Deprecated
/* loaded from: classes3.dex */
public class PoolingClientConnectionManager implements ClientConnectionManager, ConnPoolControl<HttpRoute> {
    private final DnsResolver dnsResolver;
    private final Log log;
    private final ClientConnectionOperator operator;
    private final HttpConnPool pool;
    private final SchemeRegistry schemeRegistry;

    public PoolingClientConnectionManager(SchemeRegistry schemeRegistry) {
        this(schemeRegistry, -1L, TimeUnit.MILLISECONDS);
    }

    public PoolingClientConnectionManager(SchemeRegistry schemeRegistry, DnsResolver dnsResolver) {
        this(schemeRegistry, -1L, TimeUnit.MILLISECONDS, dnsResolver);
    }

    public PoolingClientConnectionManager() {
        this(SchemeRegistryFactory.createDefault());
    }

    public PoolingClientConnectionManager(SchemeRegistry schemeRegistry, long j, TimeUnit timeUnit) {
        this(schemeRegistry, j, timeUnit, new SystemDefaultDnsResolver());
    }

    public PoolingClientConnectionManager(SchemeRegistry schemeRegistry, long j, TimeUnit timeUnit, DnsResolver dnsResolver) throws LogConfigurationException {
        Log log = LogFactory.getLog(getClass());
        this.log = log;
        Args.notNull(schemeRegistry, "Scheme registry");
        Args.notNull(dnsResolver, "DNS resolver");
        this.schemeRegistry = schemeRegistry;
        this.dnsResolver = dnsResolver;
        ClientConnectionOperator clientConnectionOperatorCreateConnectionOperator = createConnectionOperator(schemeRegistry);
        this.operator = clientConnectionOperatorCreateConnectionOperator;
        this.pool = new HttpConnPool(log, clientConnectionOperatorCreateConnectionOperator, 2, 20, j, timeUnit);
    }

    protected void finalize() throws Throwable {
        try {
            shutdown();
        } finally {
            super.finalize();
        }
    }

    protected ClientConnectionOperator createConnectionOperator(SchemeRegistry schemeRegistry) {
        return new DefaultClientConnectionOperator(schemeRegistry, this.dnsResolver);
    }

    @Override // org.apache.http.conn.ClientConnectionManager
    public SchemeRegistry getSchemeRegistry() {
        return this.schemeRegistry;
    }

    private String format(HttpRoute httpRoute, Object obj) {
        StringBuilder sb = new StringBuilder("[route: ");
        sb.append(httpRoute).append("]");
        if (obj != null) {
            sb.append("[state: ").append(obj).append("]");
        }
        return sb.toString();
    }

    private String formatStats(HttpRoute httpRoute) {
        StringBuilder sb = new StringBuilder("[total kept alive: ");
        PoolStats totalStats = this.pool.getTotalStats();
        PoolStats stats = this.pool.getStats(httpRoute);
        sb.append(totalStats.getAvailable()).append("; route allocated: ");
        sb.append(stats.getLeased() + stats.getAvailable());
        sb.append(" of ").append(stats.getMax()).append("; total allocated: ");
        sb.append(totalStats.getLeased() + totalStats.getAvailable());
        sb.append(" of ").append(totalStats.getMax()).append("]");
        return sb.toString();
    }

    private String format(HttpPoolEntry httpPoolEntry) {
        StringBuilder sb = new StringBuilder("[id: ");
        sb.append(httpPoolEntry.getId()).append("][route: ");
        sb.append(httpPoolEntry.getRoute()).append("]");
        Object state = httpPoolEntry.getState();
        if (state != null) {
            sb.append("[state: ").append(state).append("]");
        }
        return sb.toString();
    }

    @Override // org.apache.http.conn.ClientConnectionManager
    public ClientConnectionRequest requestConnection(HttpRoute httpRoute, Object obj) {
        Args.notNull(httpRoute, "HTTP route");
        if (this.log.isDebugEnabled()) {
            this.log.debug("Connection request: " + format(httpRoute, obj) + formatStats(httpRoute));
        }
        final Future<HttpPoolEntry> futureLease = this.pool.lease(httpRoute, obj);
        return new ClientConnectionRequest() { // from class: org.apache.http.impl.conn.PoolingClientConnectionManager.1
            @Override // org.apache.http.conn.ClientConnectionRequest
            public void abortRequest() {
                futureLease.cancel(true);
            }

            @Override // org.apache.http.conn.ClientConnectionRequest
            public ManagedClientConnection getConnection(long j, TimeUnit timeUnit) throws InterruptedException, ConnectionPoolTimeoutException {
                return PoolingClientConnectionManager.this.leaseConnection(futureLease, j, timeUnit);
            }
        };
    }

    ManagedClientConnection leaseConnection(Future<HttpPoolEntry> future, long j, TimeUnit timeUnit) throws ExecutionException, InterruptedException, ConnectionPoolTimeoutException, TimeoutException {
        try {
            HttpPoolEntry httpPoolEntry = future.get(j, timeUnit);
            if (httpPoolEntry == null || future.isCancelled()) {
                throw new InterruptedException();
            }
            Asserts.check(httpPoolEntry.getConnection() != null, "Pool entry with no connection");
            if (this.log.isDebugEnabled()) {
                this.log.debug("Connection leased: " + format(httpPoolEntry) + formatStats(httpPoolEntry.getRoute()));
            }
            return new ManagedClientConnectionImpl(this, this.operator, httpPoolEntry);
        } catch (ExecutionException e) {
            e = e;
            Throwable cause = e.getCause();
            if (cause != null) {
                e = cause;
            }
            this.log.error("Unexpected exception leasing connection from pool", e);
            throw new InterruptedException();
        } catch (TimeoutException unused) {
            throw new ConnectionPoolTimeoutException("Timeout waiting for connection from pool");
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x004c  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x00ae A[Catch: all -> 0x00df, TryCatch #2 {, blocks: (B:8:0x001e, B:10:0x0024, B:34:0x009d, B:36:0x00ae, B:37:0x00d2, B:40:0x00d5, B:41:0x00de, B:12:0x0026, B:14:0x002c, B:16:0x0032, B:19:0x0037, B:21:0x003f, B:22:0x0046, B:27:0x0052, B:31:0x0063, B:33:0x007d, B:26:0x0050), top: B:49:0x001e, inners: #0 }] */
    @Override // org.apache.http.conn.ClientConnectionManager
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void releaseConnection(org.apache.http.conn.ManagedClientConnection r8, long r9, java.util.concurrent.TimeUnit r11) {
        /*
            r7 = this;
            java.lang.String r0 = "Connection "
            java.lang.String r1 = "Connection released: "
            java.lang.String r2 = "for "
            boolean r3 = r8 instanceof org.apache.http.impl.conn.ManagedClientConnectionImpl
            java.lang.String r4 = "Connection class mismatch, connection not obtained from this manager"
            org.apache.http.util.Args.check(r3, r4)
            org.apache.http.impl.conn.ManagedClientConnectionImpl r8 = (org.apache.http.impl.conn.ManagedClientConnectionImpl) r8
            org.apache.http.conn.ClientConnectionManager r3 = r8.getManager()
            if (r3 != r7) goto L17
            r3 = 1
            goto L18
        L17:
            r3 = 0
        L18:
            java.lang.String r4 = "Connection not obtained from this manager"
            org.apache.http.util.Asserts.check(r3, r4)
            monitor-enter(r8)
            org.apache.http.impl.conn.HttpPoolEntry r3 = r8.detach()     // Catch: java.lang.Throwable -> Ldf
            if (r3 != 0) goto L26
            monitor-exit(r8)     // Catch: java.lang.Throwable -> Ldf
            return
        L26:
            boolean r4 = r8.isOpen()     // Catch: java.lang.Throwable -> Ld4
            if (r4 == 0) goto L46
            boolean r4 = r8.isMarkedReusable()     // Catch: java.lang.Throwable -> Ld4
            if (r4 != 0) goto L46
            r8.shutdown()     // Catch: java.io.IOException -> L36 java.lang.Throwable -> Ld4
            goto L46
        L36:
            r4 = move-exception
            org.apache.commons.logging.Log r5 = r7.log     // Catch: java.lang.Throwable -> Ld4
            boolean r5 = r5.isDebugEnabled()     // Catch: java.lang.Throwable -> Ld4
            if (r5 == 0) goto L46
            org.apache.commons.logging.Log r5 = r7.log     // Catch: java.lang.Throwable -> Ld4
            java.lang.String r6 = "I/O exception shutting down released connection"
            r5.debug(r6, r4)     // Catch: java.lang.Throwable -> Ld4
        L46:
            boolean r4 = r8.isMarkedReusable()     // Catch: java.lang.Throwable -> Ld4
            if (r4 == 0) goto L9d
            if (r11 == 0) goto L50
            r4 = r11
            goto L52
        L50:
            java.util.concurrent.TimeUnit r4 = java.util.concurrent.TimeUnit.MILLISECONDS     // Catch: java.lang.Throwable -> Ld4
        L52:
            r3.updateExpiry(r9, r4)     // Catch: java.lang.Throwable -> Ld4
            org.apache.commons.logging.Log r4 = r7.log     // Catch: java.lang.Throwable -> Ld4
            boolean r4 = r4.isDebugEnabled()     // Catch: java.lang.Throwable -> Ld4
            if (r4 == 0) goto L9d
            r4 = 0
            int r4 = (r9 > r4 ? 1 : (r9 == r4 ? 0 : -1))
            if (r4 <= 0) goto L7b
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> Ld4
            r4.<init>(r2)     // Catch: java.lang.Throwable -> Ld4
            java.lang.StringBuilder r9 = r4.append(r9)     // Catch: java.lang.Throwable -> Ld4
            java.lang.String r10 = " "
            java.lang.StringBuilder r9 = r9.append(r10)     // Catch: java.lang.Throwable -> Ld4
            java.lang.StringBuilder r9 = r9.append(r11)     // Catch: java.lang.Throwable -> Ld4
            java.lang.String r9 = r9.toString()     // Catch: java.lang.Throwable -> Ld4
            goto L7d
        L7b:
            java.lang.String r9 = "indefinitely"
        L7d:
            org.apache.commons.logging.Log r10 = r7.log     // Catch: java.lang.Throwable -> Ld4
            java.lang.StringBuilder r11 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> Ld4
            r11.<init>(r0)     // Catch: java.lang.Throwable -> Ld4
            java.lang.String r0 = r7.format(r3)     // Catch: java.lang.Throwable -> Ld4
            java.lang.StringBuilder r11 = r11.append(r0)     // Catch: java.lang.Throwable -> Ld4
            java.lang.String r0 = " can be kept alive "
            java.lang.StringBuilder r11 = r11.append(r0)     // Catch: java.lang.Throwable -> Ld4
            java.lang.StringBuilder r9 = r11.append(r9)     // Catch: java.lang.Throwable -> Ld4
            java.lang.String r9 = r9.toString()     // Catch: java.lang.Throwable -> Ld4
            r10.debug(r9)     // Catch: java.lang.Throwable -> Ld4
        L9d:
            org.apache.http.impl.conn.HttpConnPool r9 = r7.pool     // Catch: java.lang.Throwable -> Ldf
            boolean r10 = r8.isMarkedReusable()     // Catch: java.lang.Throwable -> Ldf
            r9.release(r3, r10)     // Catch: java.lang.Throwable -> Ldf
            org.apache.commons.logging.Log r9 = r7.log     // Catch: java.lang.Throwable -> Ldf
            boolean r9 = r9.isDebugEnabled()     // Catch: java.lang.Throwable -> Ldf
            if (r9 == 0) goto Ld2
            org.apache.commons.logging.Log r9 = r7.log     // Catch: java.lang.Throwable -> Ldf
            java.lang.StringBuilder r10 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> Ldf
            r10.<init>(r1)     // Catch: java.lang.Throwable -> Ldf
            java.lang.String r11 = r7.format(r3)     // Catch: java.lang.Throwable -> Ldf
            java.lang.StringBuilder r10 = r10.append(r11)     // Catch: java.lang.Throwable -> Ldf
            java.lang.Object r11 = r3.getRoute()     // Catch: java.lang.Throwable -> Ldf
            org.apache.http.conn.routing.HttpRoute r11 = (org.apache.http.conn.routing.HttpRoute) r11     // Catch: java.lang.Throwable -> Ldf
            java.lang.String r11 = r7.formatStats(r11)     // Catch: java.lang.Throwable -> Ldf
            java.lang.StringBuilder r10 = r10.append(r11)     // Catch: java.lang.Throwable -> Ldf
            java.lang.String r10 = r10.toString()     // Catch: java.lang.Throwable -> Ldf
            r9.debug(r10)     // Catch: java.lang.Throwable -> Ldf
        Ld2:
            monitor-exit(r8)     // Catch: java.lang.Throwable -> Ldf
            return
        Ld4:
            r9 = move-exception
            org.apache.http.impl.conn.HttpConnPool r10 = r7.pool     // Catch: java.lang.Throwable -> Ldf
            boolean r11 = r8.isMarkedReusable()     // Catch: java.lang.Throwable -> Ldf
            r10.release(r3, r11)     // Catch: java.lang.Throwable -> Ldf
            throw r9     // Catch: java.lang.Throwable -> Ldf
        Ldf:
            r9 = move-exception
            monitor-exit(r8)     // Catch: java.lang.Throwable -> Ldf
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.http.impl.conn.PoolingClientConnectionManager.releaseConnection(org.apache.http.conn.ManagedClientConnection, long, java.util.concurrent.TimeUnit):void");
    }

    @Override // org.apache.http.conn.ClientConnectionManager
    public void shutdown() {
        this.log.debug("Connection manager is shutting down");
        try {
            this.pool.shutdown();
        } catch (IOException e) {
            this.log.debug("I/O exception shutting down connection manager", e);
        }
        this.log.debug("Connection manager shut down");
    }

    @Override // org.apache.http.conn.ClientConnectionManager
    public void closeIdleConnections(long j, TimeUnit timeUnit) {
        if (this.log.isDebugEnabled()) {
            this.log.debug("Closing connections idle longer than " + j + " " + timeUnit);
        }
        this.pool.closeIdle(j, timeUnit);
    }

    @Override // org.apache.http.conn.ClientConnectionManager
    public void closeExpiredConnections() {
        this.log.debug("Closing expired connections");
        this.pool.closeExpired();
    }

    @Override // org.apache.http.pool.ConnPoolControl
    public int getMaxTotal() {
        return this.pool.getMaxTotal();
    }

    @Override // org.apache.http.pool.ConnPoolControl
    public void setMaxTotal(int i) {
        this.pool.setMaxTotal(i);
    }

    @Override // org.apache.http.pool.ConnPoolControl
    public int getDefaultMaxPerRoute() {
        return this.pool.getDefaultMaxPerRoute();
    }

    @Override // org.apache.http.pool.ConnPoolControl
    public void setDefaultMaxPerRoute(int i) {
        this.pool.setDefaultMaxPerRoute(i);
    }

    @Override // org.apache.http.pool.ConnPoolControl
    public int getMaxPerRoute(HttpRoute httpRoute) {
        return this.pool.getMaxPerRoute(httpRoute);
    }

    @Override // org.apache.http.pool.ConnPoolControl
    public void setMaxPerRoute(HttpRoute httpRoute, int i) {
        this.pool.setMaxPerRoute(httpRoute, i);
    }

    @Override // org.apache.http.pool.ConnPoolControl
    public PoolStats getTotalStats() {
        return this.pool.getTotalStats();
    }

    @Override // org.apache.http.pool.ConnPoolControl
    public PoolStats getStats(HttpRoute httpRoute) {
        return this.pool.getStats(httpRoute);
    }
}
