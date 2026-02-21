package com.unity3d.services.core.device.reader;

import com.unity3d.services.core.configuration.ConfigurationReader;
import com.unity3d.services.core.configuration.Experiments;
import com.unity3d.services.core.configuration.IExperiments;
import com.unity3d.services.core.configuration.InitRequestType;
import com.unity3d.services.core.configuration.PrivacyConfigStorage;
import com.unity3d.services.core.device.Storage;
import com.unity3d.services.core.device.StorageManager;
import com.unity3d.services.core.device.reader.pii.PiiDataProvider;
import com.unity3d.services.core.device.reader.pii.PiiDataSelector;
import com.unity3d.services.core.device.reader.pii.PiiTrackingStatusReader;
import com.unity3d.services.core.lifecycle.CachedLifecycle;
import com.unity3d.services.core.misc.JsonFlattenerRules;
import com.unity3d.services.core.misc.JsonStorageAggregator;
import java.util.Arrays;
import java.util.Collections;

/* JADX INFO: loaded from: classes.dex */
public class DeviceInfoReaderBuilder {
    private final ConfigurationReader _configurationReader;
    private final IGameSessionIdReader _gameSessionIdReader;
    private final PrivacyConfigStorage _privacyConfigStorage;

    public DeviceInfoReaderBuilder(ConfigurationReader configurationReader, PrivacyConfigStorage privacyConfigStorage, IGameSessionIdReader iGameSessionIdReader) {
        this._configurationReader = configurationReader;
        this._privacyConfigStorage = privacyConfigStorage;
        this._gameSessionIdReader = iGameSessionIdReader;
    }

    public IDeviceInfoReader build() {
        IDeviceInfoReader deviceInfoReaderWithPII;
        Storage storage = StorageManager.getStorage(StorageManager.StorageType.PRIVATE);
        Storage storage2 = StorageManager.getStorage(StorageManager.StorageType.PUBLIC);
        JsonStorageAggregator jsonStorageAggregator = new JsonStorageAggregator(Arrays.asList(storage2, storage));
        DeviceInfoReaderFilterProvider deviceInfoReaderFilterProvider = new DeviceInfoReaderFilterProvider(storage);
        DeviceInfoReaderWithStorageInfo deviceInfoReaderWithStorageInfo = new DeviceInfoReaderWithStorageInfo(new DeviceInfoReaderWithLifecycle(new DeviceInfoReaderExtended(buildWithRequestType(InitRequestType.TOKEN)), CachedLifecycle.getLifecycleListener()), getTsiRequestStorageRules(), storage, storage2);
        PiiTrackingStatusReader piiTrackingStatusReader = new PiiTrackingStatusReader(jsonStorageAggregator);
        if (this._privacyConfigStorage != null && getCurrentExperiments().isPrivacyRequestEnabled()) {
            deviceInfoReaderWithPII = new DeviceInfoReaderWithPrivacy(deviceInfoReaderWithStorageInfo, this._privacyConfigStorage, new PiiDataProvider(), piiTrackingStatusReader);
        } else {
            deviceInfoReaderWithPII = new DeviceInfoReaderWithPII(deviceInfoReaderWithStorageInfo, new PiiDataSelector(piiTrackingStatusReader, storage, getCurrentExperiments()), new PiiDataProvider());
        }
        return new DeviceInfoReaderWithMetrics(new DeviceInfoReaderWithFilter(deviceInfoReaderWithPII, deviceInfoReaderFilterProvider.getFilterList()));
    }

    protected IDeviceInfoReader buildWithRequestType(InitRequestType initRequestType) {
        return new DeviceInfoReaderWithRequestType(new MinimalDeviceInfoReader(this._gameSessionIdReader), initRequestType);
    }

    private IExperiments getCurrentExperiments() {
        ConfigurationReader configurationReader = this._configurationReader;
        if (configurationReader == null || configurationReader.getCurrentConfiguration() == null) {
            return new Experiments();
        }
        return this._configurationReader.getCurrentConfiguration().getExperiments();
    }

    private JsonFlattenerRules getTsiRequestStorageRules() {
        return new JsonFlattenerRules(Arrays.asList("privacy", "gdpr", "framework", "adapter", "mediation", "unity", "pipl", "configuration", "user", JsonStorageKeyNames.UNIFIED_CONFIG_KEY), Collections.singletonList("value"), Arrays.asList("ts", "exclude", "pii", "nonBehavioral", "nonbehavioral"));
    }
}
