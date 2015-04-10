/*
 * Catroid: An on-device visual programming system for Android devices
 * Copyright (C) 2010-2014 The Catrobat Team
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

package org.catrobat.catroid.devices.arduino.common.firmata.reader;

import org.catrobat.catroid.devices.arduino.common.firmata.IFirmata;
import org.catrobat.catroid.devices.arduino.common.firmata.message.I2cReplyMessage;

import static org.catrobat.catroid.devices.arduino.common.firmata.BytesHelper.decodeByte;

/**
 * MessageReader for I2cReplyMessage
 */
public class I2cReplyMessageReader extends BaseSysexMessageReader<I2cReplyMessage> {

    public I2cReplyMessageReader() {
        super((byte) I2cReplyMessage.COMMAND);
    }

    @Override
    protected I2cReplyMessage buildSysexMessage(byte[] buffer, int bufferLength) {
        I2cReplyMessage message = new I2cReplyMessage();
        // skip 2 first bytes - COMMAND_START and sysex command byte
        message.setSlaveAddress(decodeByte(buffer[2], buffer[3]));
        message.setRegister(decodeByte(buffer[4], buffer[5]));
        message.setBinaryData(extractIntArrayFromBuffer(buffer, 6, bufferLength - 2));
        // message.getData() is not used
        return message;
    }

    public void fireEvent(IFirmata.Listener listener) {
        listener.onI2cMessageReceived(getMessage());
    }
}
