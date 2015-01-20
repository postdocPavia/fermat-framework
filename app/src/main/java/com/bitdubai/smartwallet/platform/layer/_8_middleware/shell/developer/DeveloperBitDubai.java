package com.bitdubai.smartwallet.platform.layer._8_middleware.shell.developer;

import com.bitdubai.smartwallet.platform.layer._8_middleware.MiddlewareEngine;
import com.bitdubai.smartwallet.platform.layer._8_middleware.MiddlewareEngineDeveloper;
import com.bitdubai.smartwallet.platform.layer._8_middleware.shell.developer.bitdubai.version_1.ShellMiddlewareEngine;

/**
 * Created by ciencias on 20.01.15.
 */
public class DeveloperBitDubai implements MiddlewareEngineDeveloper {

    MiddlewareEngine mMiddlewareEngine;

    @Override
    public MiddlewareEngine getMiddlewareEngine() {
        return mMiddlewareEngine;
    }

    public DeveloperBitDubai() {

        /**
         * I will choose from the different versions of my implementations which one to run. Now there is only one, so
         * it is easy to choose.
         */

        mMiddlewareEngine = new ShellMiddlewareEngine();

    }
}
