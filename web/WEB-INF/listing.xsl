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
        <xsl:apply-templates/> 
    </xsl:template>
    
    <xsl:template match="listing">
        <xsl:apply-templates/>
    </xsl:template>
    
    <xsl:template match="listing">
        <div class="listing single">
            <img class="houseplaceholder" src="house.png" />
            <div class="info">
                <p>ID: <xsl:value-of select="id"/></p>
                <p>Currently <xsl:value-of select="status"/></p>
                <p>Type of Accomodation: <xsl:value-of select="type"/></p>
                <p>Can accomodate up to <xsl:value-of select="guests"/> guests.</p>
                <p>Listed by: <xsl:value-of select="listerUsername"/></p>
                <p>Description: <xsl:value-of select="description"/></p>
                <xsl:apply-templates/>
            </div>
        </div>
        <ul class="tab">
            <li><a href="#" class="tablinks active" onclick="openTab(event, 'EnquiryForm')">Enquiry Form</a></li>
            <li><a href="#" class="tablinks" onclick="openTab(event, 'MapHolder');initMap();">Map</a></li>
        </ul>
        <script src="tabs.js" type="text/javascript"/>
    </xsl:template>
    
    <xsl:template match="location">
        <p>Location: <span id="location"><xsl:value-of select="number"/><xsl:text> </xsl:text> <xsl:value-of select="street"/>, <xsl:value-of select="suburb"/>, <xsl:value-of select="state"/><xsl:text> </xsl:text><xsl:value-of select="postcode"/></span></p>
    </xsl:template>
    
    <xsl:template match="id" />
    <xsl:template match="status" />
    <xsl:template match="type" />
    <xsl:template match="guests" />
    <xsl:template match="listerUsername" />
    <xsl:template match="description" />

</xsl:stylesheet>
