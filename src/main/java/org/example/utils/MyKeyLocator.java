package org.example.utils;

import io.jsonwebtoken.LocatorAdapter;
import io.jsonwebtoken.ProtectedHeader;

import java.security.Key;

public class MyKeyLocator extends LocatorAdapter<Key> {

    @Override
    protected Key locate(ProtectedHeader header) {
        return super.locate(header);
    }

}
