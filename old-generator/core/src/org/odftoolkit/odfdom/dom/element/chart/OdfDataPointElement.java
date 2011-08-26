/************************************************************************
 *
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER
 *
 * Copyright 2008 Sun Microsystems, Inc. All rights reserved.
 *
 * Use is subject to license terms.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy
 * of the License at http://www.apache.org/licenses/LICENSE-2.0. You can also
 * obtain a copy of the License at http://odftoolkit.org/docs/license.txt
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 ************************************************************************/

/*
 * This file is automatically generated.
 * Don't edit manually.
 */    

package org.odftoolkit.odfdom.dom.element.chart;

import org.odftoolkit.odfdom.dom.OdfName;
import org.odftoolkit.odfdom.dom.OdfNamespace;
import org.odftoolkit.odfdom.doc.OdfFileDom;
import org.odftoolkit.odfdom.dom.style.OdfStyleFamily;
import org.odftoolkit.odfdom.dom.element.OdfStylableElement;
import org.odftoolkit.odfdom.dom.type.OdfNonNegativeInteger;
import org.odftoolkit.odfdom.dom.type.OdfStyleName;
import org.odftoolkit.odfdom.dom.type.OdfId;

;

/**
 * ODF DOM Element implementation for element "<chart:data-point>".
 */
public abstract class OdfDataPointElement extends OdfStylableElement
{        
    public static final OdfName ELEMENT_NAME = OdfName.get( OdfNamespace.CHART, "data-point" );

    public OdfDataPointElement( OdfFileDom _aOwnerDoc )
    {
        super( _aOwnerDoc, ELEMENT_NAME, OdfStyleFamily.Chart, OdfName.get( OdfNamespace.CHART, "style-name" ) );
    }

    public OdfName getOdfName()
    {
        return ELEMENT_NAME;
    }


    /**
     * Get value of attribute "chart:repeated".
     */
    public Integer getRepeated()
    {                    
        String aStringVal = getOdfAttribute( OdfName.get( OdfNamespace.CHART, "repeated" ) );
        return OdfNonNegativeInteger.valueOf( aStringVal);
    }

    /**
     * Set value of attribute "chart:repeated".
     */
    public void setRepeated( Integer _aRepeated )
    {                    
        String aStringVal = OdfNonNegativeInteger.toString( _aRepeated );
        setOdfAttribute( OdfName.get( OdfNamespace.CHART, "repeated" ), aStringVal );
    }

    /**
     * Get value of attribute "chart:style-name".
     */
    public String getStyleName()
    {                    
        String aStringVal = getOdfAttribute( OdfName.get( OdfNamespace.CHART, "style-name" ) );
        return OdfStyleName.valueOf( aStringVal);
    }

    /**
     * Set value of attribute "chart:style-name".
     */
    public void setStyleName( String _aStyleName )
    {                    
        String aStringVal = OdfStyleName.toString( _aStyleName );
        setOdfAttribute( OdfName.get( OdfNamespace.CHART, "style-name" ), aStringVal );
    }

    /**
     * Get value of attribute "xml:id".
     */
    public String getXmlid()
    {                    
        String aStringVal = getOdfAttribute( OdfName.get( OdfNamespace.XML, "id" ) );
        return OdfId.valueOf( aStringVal);
    }

    /**
     * Set value of attribute "xml:id".
     */
    public void setXmlid( String _aXmlid )
    {                    
        String aStringVal = OdfId.toString( _aXmlid );
        setOdfAttribute( OdfName.get( OdfNamespace.XML, "id" ), aStringVal );
    }

    /**
    * Create child element "chart:data-label".
    */
    public OdfDataLabelElement createDataLabelElement()
    {
        OdfDataLabelElement  _nDataLabel = ((OdfFileDom)this.ownerDocument).createOdfElement(OdfDataLabelElement.class);
        this.appendChild( _nDataLabel);
        return  _nDataLabel;
    }                   
               
}
