/*
 * Catroid: An on-device visual programming system for Android devices
 * Copyright (C) 2010-2015 The Catrobat Team
 * (<http://developer.catrobat.org/credits>)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * An additional term exception under section 7 of the GNU Affero
 * General Public License, version 3, is available at
 * http://developer.catrobat.org/license_additional_term
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.catrobat.catroid.devices.arduino.common.firmata.message;

import java.text.MessageFormat;

/**
 * Response to FirmwareVersionMessage
 */
public class FirmwareVersionMessage extends SysexMessage {

    public FirmwareVersionMessage() {
        super();
    }

    public FirmwareVersionMessage(int major, int minor, String name) {
        this();
        setName(name);
        setMajor(major);
        setMinor(minor);
    }

    private int major;

    public int getMajor() {
        return major;
    }

    public void setMajor(int major) {
        this.major = major;
    }

    private int minor;

    public int getMinor() {
        return minor;
    }

    public void setMinor(int minor) {
        this.minor = minor;
    }

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

	@Override
    public boolean equals(Object obj) {
        if (!super.equals(obj)) {
			return false;
		}

        FirmwareVersionMessage message = (FirmwareVersionMessage)obj;
        return message != null &&
               message.getMajor() == getMajor() &&
               message.getMinor() == getMinor() &&
               (
                   (message.getName() == null && getName() == null)
                    ||
                   (message.getName() != null && message.getName().equals(getName()))
               );
    }

    @Override
    public String toString() {
        return MessageFormat.format("FirmwareVersionMessage[major={0}, minor={1}, name=''{2}'']", major, minor, name);
    }
}
