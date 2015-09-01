/*
 * Copyright (c) 2015, Parallel Universe Software Co. and Contributors. All rights reserved.
 * 
 * This program and the accompanying materials are licensed under the terms 
 * of the Eclipse Public License v1.0, available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

import capsule.posix.PosixSuperCapsule;
import capsule.SuperCapsuleImpl;
import java.nio.file.Path;

/**
 *
 * @author pron
 */
public class SuperCapsule extends Capsule implements capsule.CapsuleAPI {
    private final SuperCapsuleImpl impl;

    public SuperCapsule(Path jarFile) {
        super(jarFile);
        this.impl = createImpl();
    }

    public SuperCapsule(Capsule pred) {
        super(pred);
        this.impl = createImpl();
    }

    private SuperCapsuleImpl createImpl() {
        if (isUnix() || isMac())
            return new PosixSuperCapsule(this);
        throw new UnsupportedOperationException("This caplet only runs on POSIX systems. This platform has been identified as " + getPlatform());
    }

    @Override
    protected int launch(ProcessBuilder pb) {
        return impl.launch(pb);
    }

    @Override
    public void log1(int level, String str) {
        Capsule.log(level, str);
    }
}
