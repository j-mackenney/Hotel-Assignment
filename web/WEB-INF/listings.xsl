<?xml version="1.0" encoding="UTF-8"?>

<!--
    Document   : users.xsl
    Created on : 20 September 2016, 6:24 PM
    Author     : minniemanZ
    Description:
        Purpose of transformation follows.
-->

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0"
 xmlns:x="http://www.uts.edu.au/31284/wsd-diary/listings" exclude-result-prefixes="x">
    <xsl:output method="html"/>

    <!-- TODO customize transformation rules 
         syntax recommendation http://www.w3.org/TR/xslt 
    -->
    <xsl:template match="/">
        <h1> Found <xsl:value-of select="count(x:listings/listing)"/> listings</h1>
        <xsl:apply-templates/> 
    </xsl:template>
    
    <xsl:template match="x:listings">
        <xsl:apply-templates/>
    </xsl:template>
    
    <xsl:template match="listing">
        <div class="listing">
            <a href="listing.jsp?id={id}">
                <img class="houseplaceholder" src="house.png" />
            </a>
            <div class="info">
                <p>Type: <span class="type"><xsl:value-of select="type"/></span></p>
                <p>Guests: <xsl:value-of select="guests"/></p>
                <xsl:apply-templates/>
            </div>    
        </div>
    </xsl:template>
    
    <xsl:template match="location">
        <p>Location: <xsl:value-of select="suburb"/>, <xsl:value-of select="state"/></p>
    </xsl:template>
    
    <xsl:template match="id" />
    <xsl:template match="status" />
    <xsl:template match="type" />
    <xsl:template match="guests" />
    <xsl:template match="listerUsername" />
    <xsl:template match="description" />

</xsl:stylesheet>
