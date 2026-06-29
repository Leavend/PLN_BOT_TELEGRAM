package org.mockito.internal.configuration.plugins;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Iterator;
import org.mockito.exceptions.base.MockitoException;
import org.mockito.internal.util.io.IOUtil;
import org.mockito.plugins.PluginSwitch;

/* loaded from: classes3.dex */
class PluginFinder {
    private final PluginSwitch pluginSwitch;

    public PluginFinder(PluginSwitch pluginSwitch) {
        this.pluginSwitch = pluginSwitch;
    }

    String findPluginClass(Iterable<URL> iterable) throws IOException {
        Iterator<URL> it = iterable.iterator();
        while (true) {
            InputStream inputStreamOpenStream = null;
            if (!it.hasNext()) {
                return null;
            }
            URL next = it.next();
            try {
                try {
                    inputStreamOpenStream = next.openStream();
                    String pluginClass = new PluginFileReader().readPluginClass(inputStreamOpenStream);
                    if (pluginClass != null && this.pluginSwitch.isEnabled(pluginClass)) {
                        return pluginClass;
                    }
                } catch (Exception e) {
                    throw new MockitoException("Problems reading plugin implementation from: " + next, e);
                }
            } finally {
                IOUtil.closeQuietly(inputStreamOpenStream);
            }
        }
    }
}
