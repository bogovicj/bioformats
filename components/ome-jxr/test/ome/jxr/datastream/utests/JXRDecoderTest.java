/*
 * #%L
 * OME library for reading the JPEG XR file format.
 * %%
 * Copyright (C) 2013 Open Microscopy Environment:
 *   - Board of Regents of the University of Wisconsin-Madison
 *   - Glencoe Software, Inc.
 *   - University of Dundee
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 2 of the 
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public 
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/gpl-2.0.html>.
 * #L%
 */

package ome.jxr.datastream.utests;

import static org.testng.AssertJUnit.assertNull;

import java.io.IOException;

import ome.jxr.JXRException;
import ome.jxr.StaticDataProvider;
import ome.jxr.datastream.JXRDecoder;
import ome.jxr.metadata.JXRMetadata;

import org.testng.annotations.Test;

public class JXRDecoderTest {

  @Test(dataProvider = "testDecoder",
      dataProviderClass = StaticDataProvider.class)
  public void testDecodeShouldReturnNullWithoutMetadata(JXRDecoder decoder)
      throws Exception {
    assertNull(decoder.decode(null));
  }

  @Test(dataProvider = "testDecoder",
      dataProviderClass = StaticDataProvider.class,
      expectedExceptions = JXRException.class)
  public void testDecodeWithEmptyMetadataShouldThrowJXRE(JXRDecoder decoder)
      throws IOException, JXRException {
    decoder.decode(new JXRMetadata(0));
  }
  
}
