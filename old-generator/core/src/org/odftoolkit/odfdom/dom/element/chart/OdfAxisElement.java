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
import org.odftoolkit.odfdom.dom.type.chart.OdfDimensionType;
import org.odftoolkit.odfdom.dom.type.OdfStyleName;

;

/**
 * ODF DOM Element implementation for element "<chart:axis>".
 */
public abstract class OdfAxisElement extends OdfStylableElement
{        
    public static final OdfName ELEMENT_NAME = OdfName.get( OdfNamespace.CHART, "axis" );

    public OdfAxisElement( OdfFileDom _aOwnerDoc )
    {
        super( _aOwnerDoc, ELEMENT_NAME, OdfStyleFamily.Chart, OdfName.get( OdfNamespace.CHART, "style-name" ) );
    }

    public OdfName getOdfName()
    {
        return ELEMENT_NAME;
    }

    /**
     * Initialize mandatory attributes.
     */
    public void init(OdfDimensionType _aDimension)
    {
        setDimension( _aDimension );
    }

    /**
     * Get value of attribute "chart:dimension".
     */
    public OdfDimensionType getDimension()
    {                    
        String aStringVal = getOdfAttribute( OdfName.get( OdfNamespace.CHART, "dimension" ) );
        return OdfDimensionType.enumValueOf( aStringVal);
    }

    /**
     * Set value of attribute "chart:dimension".
     */
    public void setDimension( OdfDimensionType _aDimension )
    {                    
        String aStringVal = OdfDimensionType.toString( _aDimension );
        setOdfAttribute( OdfName.get( OdfNamespace.CHART, "dimension" ), aStringVal );
    }

    /**
     * Get value of attribute "chart:name".
     */
    public String getName()
    {
        return getOdfAttribute( OdfName.get( OdfNamespace.CHART, "name" ) );
    }

    /**
     * Set value of attribute "chart:name".
     */
    public void setName( String _aName )
    {
        setOdfAttribute( OdfName.get( OdfNamespace.CHART, "name" ), _aName );
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
    * Create child element "chart:title".
    */
    public OdfTitleElement createTitleElement()
    {
        OdfTitleElement  _nTitle = ((OdfFileDom)this.ownerDocument).createOdfElement(OdfTitleElement.class);
        this.appendChild( _nTitle);
        return  _nTitle;
    }                   
               
    /**
    * Create child element "chart:categories".
    */
    public OdfCategoriesElement createCategoriesElement()
    {
        OdfCategoriesElement  _nCategories = ((OdfFileDom)this.ownerDocument).createOdfElement(OdfCategoriesElement.class);
        this.appendChild( _nCategories);
        return  _nCategories;
    }                   
               
    /**
    * Create child element "chart:grid".
    */
    public OdfGridElement createGridElement()
    {
        OdfGridElement  _nGrid = ((OdfFileDom)this.ownerDocument).createOdfElement(OdfGridElement.class);
        this.appendChild( _nGrid);
        return  _nGrid;
    }                   
               
}
